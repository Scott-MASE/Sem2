package com.ericsson.oss.service.topology;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ericsson.oss.service.data.ObjectCreationListener;
import com.ericsson.oss.service.data.ManagedObject;
import com.ericsson.oss.service.data.ObjectManager;

/**
 * Implementation of the Site Topology Service.
 * <p>
 * Allows users to expand topology nodes to get their children in the topology.
 * <p>
 * The site topology structure can be summarised as follows:
 * <ul>
 * <li>the root nodes of the topology are of type SubNetwork
 * <li>A SubNetwork parent can be expanded into nodes of type Site
 * <li>A Site parent can be expanded into nodes of type NetworkElement
 */
public class SiteTopology implements ObjectCreationListener {

	/**
	 * The cache used to store topology information.
	 */
	public Cache cache;
	
	/**
	 * Get the root nodes for this topology.
	 * @return the root nodes, which will be of type SubNetwork.
	 */
	public Collection<ITopologyNode> getRootNodes() {
		if (cache.getRoots() == null) {
			List<ITopologyNode> rootNodes = new ArrayList<ITopologyNode>();
			Collection<ManagedObject> objects = objectManager.getObjectsByType("SubNetwork");
			if (objects != null) {
				for (ManagedObject object : objects) {
					if (object.getType() == "SubNetwork") {
						// the sub-type is null and the parent is null
						TopologyNode tn = new TopologyNode(object.getName(), object.getDistinguishedName(), object.getType(), null, null);
						rootNodes.add(tn);
					}
				}
			}
			return rootNodes;
		} else {
			return cache.getRoots();
		}
	}
	
	/**
	 * Expand a topology node into its directly connected nodes.
	 * @param parentNode the topology node to expand.
	 * @return the child nodes underneath the parent. 
	 */
	public Collection<ITopologyNode> expandNode(ITopologyNode parentNode) {
		// if the parent node is in the cache, return the information as stored in the cache
		if (cache.get(parentNode) != null) {
			return cache.get(parentNode);
		}
		// we need to determine the parent node's type to see how to expand it
		List<ITopologyNode> nodes = new ArrayList<ITopologyNode>();
		if (parentNode.readType().equals("SubNetwork")) {
			ManagedObject subNetwork = objectManager.getObjectByDistinguishedName(parentNode.getId());
			Collection<? extends ManagedObject> siteManagedObjects = subNetwork.getAssociatedObjects("SubNetwork_To_Site");
			if (siteManagedObjects != null) {
			for (ManagedObject managedObject : siteManagedObjects) {
				TopologyNode node = new TopologyNode(managedObject.getName(), managedObject.getDistinguishedName(), "Site", "Not Used", parentNode);
				nodes.add(node);
			}
			}
			cache.add((TopologyNode) parentNode, nodes);
			return nodes;
		}
		if (parentNode.readType().equals("Site")) {
			ManagedObject site = objectManager.getObjectByDistinguishedName(parentNode.getId());
			// ManegedElement objects are connected to Site objects by an association
			Collection<? extends ManagedObject> siteManagedObjects = site.getAssociatedObjects("Site_To_NetworkElement");
			List<ITopologyNode> elementNodes = new ArrayList<ITopologyNode>();
			if (siteManagedObjects != null) {
			for (ManagedObject managedObject : siteManagedObjects) {
			TopologyNode node = new TopologyNode(managedObject.getName(), managedObject.getDistinguishedName(), "NotUsed", "NetworkElement", parentNode);
			nodes.add(node);
			}
			}
			cache.add((TopologyNode) parentNode, elementNodes);
			return elementNodes;
		}
		// for all other node types, we can return nothing
		return null;
	}
	
	/**
	 * The Object Manager, used to get information about Managed Objects in the system.
	 */
	private ObjectManager objectManager;
	
	public SiteTopology(ObjectManager manager) {
		this.objectManager = manager;
		this.cache = new Cache();
		// add subscription for data change events
		this.objectManager.addDataChangeListener(this);
	}
	
	@Override
	public void objectCreated(ManagedObject mo) {
		if (mo.getType().equals("Subnetwork")) {
			TopologyNode node = new TopologyNode(mo.getName(), mo.getDistinguishedName(), "Site", null, null);
			cache.getRoots().add(node);
		}
		if (mo.getType() == "Site") {
			Collection<? extends ManagedObject> subNetworks = mo.getAssociatedObjects("SubNetwork_To_Site");
			// we know there will only be one subnetwork associated with a site
			ManagedObject subnetwork = subNetworks.iterator().next();
			// calculate the id of the subnetwork node 
			String subNetworkID = subnetwork.getDistinguishedName();
			// find the node in the cache
			ITopologyNode subNetworkNode = cache.lookUpNode(subNetworkID);
			// get the sites for the subnetwork from the cache
			Collection<ITopologyNode> sites = cache.get(subNetworkNode);
			// create a node to represent the new Site
			TopologyNode siteNode = new TopologyNode(mo.getName(), mo.getDistinguishedName(), "Site", null, subNetworkNode);
			// add to the cache
			sites.add(siteNode);
		}
		if (mo.getType().equals("ManagedElement")) {
			Collection<? extends ManagedObject> sites = mo.getAssociatedObjects("Site_To_NetworkElement");
			// we know there will only be one site associated with a managed element
			ManagedObject site = sites.iterator().next();
			// calculate the id of the site node 
			String siteID = site.getDistinguishedName();
			// find the node in the cache
			ITopologyNode siteNode = cache.lookUpNode(siteID);
			// get the managed elements for the site from the cache
			Collection<ITopologyNode> elements = cache.get(siteNode);
			// create a node to represent the new element
			TopologyNode elementNode = new TopologyNode(mo.getName(), mo.getDistinguishedName(), "NetworkElement", null, siteNode);
			// add to the cache
			elements.add(elementNode);
		}
		// can ignore all other types.
		
	}

}

package com.ericsson.oss.service.topology;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Caches topology nodes. Should be used to cache topology information and thereby avoid using the ObjectManager resources.
 */
public class Cache {
		
	// store the info in a map
	Map<ITopologyNode, Collection<ITopologyNode>> map;

	List<ITopologyNode> rootNodes = new ArrayList<ITopologyNode>();
	
	public Cache() {
		map = new HashMap<ITopologyNode, Collection<ITopologyNode>>();
	}
	
	/**
	 * Add the root nodes to the cache.
	 * @param rootNodes the root nodes of the topology.
	 */
	public void add(List<ITopologyNode> rootNodes) {
		this.rootNodes = rootNodes;
		
	}

	/**
	 * Get the root nodes from the cache.
	 * @return the root nodes of the topology.
	 */
	public List<ITopologyNode> getRoots() {
		return rootNodes;
	}

	/**
	 * Add topology information to the cache.
	 * @param node the parent node which is being expanded.
	 * @param nodes the child nodes which are expanded to from the parent node.
	 */
	public void add(TopologyNode node, List<ITopologyNode> nodes) {
		map.put(node, nodes);
	}

	/**
	 * Get the cached child nodes for a parent.
	 * @param node the parent node.
	 * @return the child nodes which have been cached for that parent.
	 */
	public Collection<ITopologyNode> get(ITopologyNode node) {
		return map.get(node.getId());
	}


	ITopologyNode lookUpNode(String nodeID) {
		// look in the root nodes first
		for (Iterator iterator = rootNodes.iterator(); iterator.hasNext();) {
			ITopologyNode node = (ITopologyNode) iterator.next();
			if (node.equals(nodeID)) {
				return node;
			}
		}
		// no match in the root nodes, look up the other cached nodes
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			ITopologyNode node = (ITopologyNode) iterator.next();
			if (node.equals(nodeID)) {
				return node;
			}
			Collection<ITopologyNode> childNodes = get(node);
			for (Iterator childIterator = childNodes.iterator(); childIterator.hasNext();) {
				ITopologyNode childNode = (ITopologyNode) iterator.next();
				if (childNode.equals(nodeID)) {
					return childNode;
				}
			}
		}
		// if we get here, we could not find a match so return null
		return null;
	}
	
}

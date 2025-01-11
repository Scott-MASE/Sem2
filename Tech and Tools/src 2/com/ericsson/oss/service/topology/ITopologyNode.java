package com.ericsson.oss.service.topology;

/**
 * A single node in a network topology.
 */
public interface ITopologyNode {
	
	/**
	 * Get the user friendly name of the node. To be used to present the node
	 * in GUIs, etc. Not guaranteed to be unique.
	 * @return the node's name.
	 */
	String getName();
	
	/**
	 * Get the unique identifier of the node.
	 * @return the node's name.
	 */
	String getId();
	
	/**
	 * Get the identifier of the node's parent in the topology.
	 * <p>
	 * If the node has no parent, null will be returned.
	 * @return the parent node's ID.
	 */
	String getParentId();
	
	/**
	 * Get the type of the node.
	 * @return the node's type.
	 */
	String readType();
	
}

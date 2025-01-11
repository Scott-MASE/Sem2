package com.ericsson.oss.service.topology;

/**
 * Implements ITopologyNode.
 */
public class TopologyNode implements ITopologyNode {

	private String name;
	private String id;
	private String pid;
	private String type;

	// create a topology node using the supplied data
	protected TopologyNode(String s1, String s2, String s3, String s4, ITopologyNode n1) {
		this.name = s1;
		this.id = s2;
		this.type = s3;
		this.pid = n1.getId();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getParentId() {
		return pid;
	}

	@Override
	public String readType() {
		return type;
	}

	/**
	 * Equality is based on the node's ID, so just check that.
	 */
	@Override
	public boolean equals(Object obj) {
		String otherId;
		if (obj instanceof String) {
			otherId = (String) obj;
		} else if (obj instanceof TopologyNode) {
			otherId = ((TopologyNode) obj).getId();
		} else {
			return false;
		}
		return this.id.equals(otherId);
	}
	
	

}

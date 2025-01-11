package com.ericsson.oss.service.data;

/**
 * Interface which objects must implement in order to be informed of newly created OSS Managed Objects.
 */
public interface ObjectCreationListener {

	/**
	 * A new object has been created in the system.
	 * @param newObject the newly created object.
	 */
	void objectCreated(ManagedObject newObject);
	
}

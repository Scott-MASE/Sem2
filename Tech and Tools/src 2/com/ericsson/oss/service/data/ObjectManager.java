package com.ericsson.oss.service.data;

import java.util.Collection;

/**
 * Provides access functions for locating ManagedObjects in the 
 * OSS database.
 */
public interface ObjectManager {
	
	/**
	 * Get all ManagedObjects of the specified type.
	 * @param ojbectType the type of object to locate. Must not be <code>null</code>.
	 * @return a collection of all ManagedObjects of the specified type.
	 * This is guaranteed to be a non-null return value. 
	 * If no objects of the specified type exist then an empty collection will be returned.
	 */
	Collection<ManagedObject> getObjectsByType(String ojbectType);

	/**
	 * Get the ManagedObject in the database with the supplied distinguished name.
	 * <p>
	 * If no object exists which has the supplied name, then <code>null</code> will be returned.
	 * @param name the distinguished name of the required object. 
	 * @return the matching object if it exists, null otherwise.
	 */
	ManagedObject getObjectByDistinguishedName(String name);
	
	/**
	 * Register an object to be notified about data change events.
	 * <p>
	 * Once registered the data change listener will receive information 
	 * about objects being added to and removed from the system.
	 * @param listener the data change listener which is being registered.
	 */
	void addDataChangeListener(ObjectCreationListener listener);
}

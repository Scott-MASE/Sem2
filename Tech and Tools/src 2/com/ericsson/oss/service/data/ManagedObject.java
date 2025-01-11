package com.ericsson.oss.service.data;

import java.util.Collection;
/**
 * Represents a single object in the OSS database.
 */
public interface ManagedObject {

	/**
	 * Get the (non-unique) name of this object.
	 * <p>
	 * This name is a more user-friendly name than the distinguished
	 * name but cannot be used as a unique identifier for this object.
	 * @return the object's name.
	 */
	String getName();
	
	/**
	 * Get the distinguished name of this object.
	 * <p>
	 * The distinguished name is an identifier which is guaranteed
	 * to be unique within the system.
	 * @return the object's name.
	 */
	String getDistinguishedName();

	/**
	 * Get the data type of this object.
	 * 
	 * @return the object's type.
	 */
	String getType();

	/**
	 * Get objects associated with this object according to the supplied
	 * association name. 
	 * @param associationName the name of the link between this object and its
	 * associated objects.
	 * @return a collection of associated objects. Guaranteed to be non-null. If no
	 * associated objects are found an empty collection will be returned.
	 */
	Collection<ManagedObject> getAssociatedObjects(String associationName);

	/**
	 * Get the names of all associations supported by this object.
	 * @return a collection of association names which can be used in the method {@link #getAssociatedObjects(String)}.
	 */
	Collection<String> getAssociationNames();
	
	/**
	 * Get all objects associated with this object. 
	 * <p>
	 * This method will return all objects associated via any association type, i.e.
	 * it is the amalgamation of calling {@link #getAssociatedObjects(String)}
	 * several times for all valid association names.
	 * @return a collection of associated objects. Guaranteed to be non-null. If no
	 * associated objects are found an empty collection will be returned.
	 */
	Collection<ManagedObject> getAllAssociatedObjects();

	/**
	 * Get the value of the supplied attribute on this object.
	 * @param attributeName the name of the required attribute.
	 * @return the value of the required attribute. 
	 * @throws IllegalArgumentException if the supplied attribute name does not 
	 * exist on this object.
	 */
	Object getAttribute(String attributeName) throws IllegalArgumentException;
}

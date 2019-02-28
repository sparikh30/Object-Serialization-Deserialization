package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.SerializableObject;

public interface SerStrategy {
	   void processInput(SerializableObject sObject) throws IllegalArgumentException, IllegalAccessException;
}
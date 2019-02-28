package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;


public class StoreRestoreHandler implements InvocationHandler {

	private FileProcessor fp;
	private String fileName;
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		Object myObj = null;
		if(method.getName().equals("writeObj")){
			if(args[2].equals("XML")){
				serializeData((SerializableObject)args[0], new XMLSerialization(fp));
			}			
		} 		
		else if(method.getName().equals("readObj")) {
			myObj=DeserializeData(new XMLDeserialization(fp));
			//myObj = deser.makeObject();
		}
		return myObj;
	}
	
	private Object DeserializeData(DeserStrategy myStrategy) {
		// TODO Auto-generated method stub
		return myStrategy.deserializeData();
	}

	public void serializeData(SerializableObject serObject, SerStrategy myStrategy) {
		 try{
	          myStrategy.processInput(serObject);
		 } catch (Exception e){
			 System.out.println("Exception Occoured during XML serialization ");
		 }
	 }
	
	public void setFile(String filenameIn) {	
		fileName=filenameIn;
		fp=new FileProcessor(fileName);
	}
	
	public void closeFile() {
		fp.closeReader();
		fp.closeWriter();
	}
	
}

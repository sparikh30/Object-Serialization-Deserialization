package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.Field;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

public class XMLSerialization implements SerStrategy {
  
	private FileProcessor fp ;
	private SerializeTypes st;
	
	public XMLSerialization(FileProcessor fpIn) {
		// TODO Auto-generated constructor stub
		fp=fpIn;
		st=new SerializeTypes();
	}
	public void processInput(SerializableObject sObject) throws IllegalArgumentException, IllegalAccessException {
     // all the code to create the output file with XML snippets for
     // an object
		Class className = sObject.getClass();
		fp.writeToFile("<DPSerialization>");
		fp.writeToFile(st.serializeComplexLine(className.getSimpleName()));
		Field[] classfields = className.getDeclaredFields();
		for(int i = 0; i < classfields.length; i++){
			String type =classfields[i].getName();		// getting field name
			classfields[i].setAccessible(true);			// Throws Exception otherwise. 
			Object value = classfields[i].get(sObject);	// getting actual field value.  Alternative : we can also use getters for this.
			
			if(type.equals("myLong") || type.equals("myOtherLong")) {
				if( 10 > (long)value) {
					continue;
				}
			}
			if(type.equals("myInt") || type.equals("myOtherint")) {
				if( 10 > (int)value) {
					continue;
				}
			}
			if(type.equals("myDoubleT") || type.equals("myOtherDoubleTv")) {
				if( 10 > (double)value) {
					continue;
				}
			}
			
			fp.writeToFile(st.convertToString(value,type));
		}				
		fp.writeToFile("</complexType>");
		fp.writeToFile("</DPSerialization>");
	}
}
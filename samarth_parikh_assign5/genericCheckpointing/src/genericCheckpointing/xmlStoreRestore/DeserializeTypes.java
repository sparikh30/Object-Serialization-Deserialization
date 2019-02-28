package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.Method;
import java.util.HashMap;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

public class DeserializeTypes {

	private FileProcessor fp;
	
	public DeserializeTypes(FileProcessor fpIn) {
		fp = fpIn;
	}
	
	public String getValue(String lineIn) {
		
		String res ="";
		String []arr;
		if(!lineIn.equals("")) {
			arr = lineIn.split("\\s*<\\s*|\\s*>\\s*");
			res=arr[2];
		}

		return res.trim();
	}
	
	public String getCls(String q) {
		
		String res="";
		
		if(!q.equals("")) {
			
		String [] x = q.split("xsd:");
        String []w = new String[3];
        if(x.length==2)
        w= x[1].split("\"");
        res=w[0];
		}
		return res.trim();
		
	}
	
	public String getMethod(String lineIn) {
		
			String res="";
			
			if(!lineIn.equals("")) {
				String [] x = lineIn.split("xsi");
				String []q= x[0].split("<");
				String aa = q[1];
		        aa= aa.substring(0, 1).toUpperCase() + aa.substring(1);
				res="set"+aa;
			}	        
	        return res.trim();
	}

	public Object makeObject() {
		
		SerializableObject obj = null;
		String line="";
		Class<?> classRef=null;
		boolean flag=false;
		Method myMeth=null;
		
		
		HashMap<String,Class<?>> getDataTypeClass = new HashMap<>();
		getDataTypeClass.put("int",int.class );
		getDataTypeClass.put("long",long.class );
		getDataTypeClass.put("string",String.class );
		getDataTypeClass.put("boolean",boolean.class );
		getDataTypeClass.put("double",double.class );
		getDataTypeClass.put("float",float.class );
		getDataTypeClass.put("short",short.class );
		getDataTypeClass.put("char",char.class );
		
		
		while((line=fp.readLine()) != null) {
			line = line.trim();
			if(line.equals(""))continue;
//			System.out.println("----------"+line);
			if(line.contains("<DPSerialization>") && flag==false) {
				flag=true;
				continue;
			}
			if(line.contains("</DPSerialization>") && flag==true) {
				flag=false;
				break;
			}
			
			if(flag) {
				
			  try {
				  if(line.contains("MyAllTypes") && line.contains("<complexType")) {
					 String[] arg= line.split("\"");
					 String classToUse = arg[1];
					 classRef = Class.forName(classToUse);
					 obj= (SerializableObject) classRef.newInstance();
				  }
				  else if(line.contains("</complexType>")) {
					  continue;
				  }
				  else{
					  String Datatype = getCls(line);
//					  System.out.println(Datatype);
					  String allMyTypeMethod = getMethod(line);
					  myMeth= classRef.getDeclaredMethod(allMyTypeMethod, getDataTypeClass.get(Datatype));
					  
					  if(Datatype.equals("int"))
						  myMeth.invoke(obj, Integer.parseInt(getValue(line)));
					  if(Datatype.equals("long"))
						  myMeth.invoke(obj, Long.parseLong(getValue(line)));
					  if(Datatype.equals("float"))
						  myMeth.invoke(obj, Float.parseFloat(getValue(line)));
					  if(Datatype.equals("string"))
						  myMeth.invoke(obj, getValue(line));
					  if(Datatype.equals("boolean"))
						  myMeth.invoke(obj, Boolean.parseBoolean(getValue(line)));
					  if(Datatype.equals("double"))
						  myMeth.invoke(obj, Double.parseDouble(getValue(line)));
					  if(Datatype.equals("short"))
						  myMeth.invoke(obj, Short.parseShort(getValue(line)));
					  if(Datatype.equals("char"))
						  myMeth.invoke(obj, (getValue(line)).charAt(0));
				  }
			  }
			  catch(Exception e) {
				  e.printStackTrace();
			  }
			}
		}		
		return obj;
	}
}

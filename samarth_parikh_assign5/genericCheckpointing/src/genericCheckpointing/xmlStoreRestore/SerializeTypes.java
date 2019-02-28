package genericCheckpointing.xmlStoreRestore;

public class SerializeTypes {

		String serializeComplexLine(String x) {
			return "<complexType xsi:type=\"genericCheckpointing.util."+x+"\">";
		}
		
		public String convertToString(Object val,String type) {
			String res="";
			
			if(type.equals("myInt") || type.equals("myOtherInt")){
				res = serializeInt((int)val,type);
			} 
			else if(type.equals("myLong") || type.equals("myOtherLong"))
			{
				res = serializeLong((long)val,type);
			} 
			else if(type.equals("myBool"))
			{
				res = serializeBool((Boolean)val,type);
			} 
			else if(type.equals("myString"))
			{
				res = serializeString((String)val,type);
			} 
			else if(type.equals("myFloatT"))
			{
				res = serializeFloat((Float)val,type);
			} 
			else if(type.equals("myShortT"))
			{
				res = serializeShort((Short)val,type);
			} 
			else if(type.equals("myCharT"))
			{
				res = serializeChar((char)val,type);
			}
			else if(type.equals("myOtherDoubleT") || type.equals("myDoubleT"))
			{
				res = serializeDouble((double)val,type);
			}
			else {
				System.err.println("Unknown Type");
			}	
			
			return res;
		}

		private String serializeDouble(double val, String type) {
			// TODO Auto-generated method stub
			return "\t<"+type+" xsi:type=\"xsd:double\">"+val+"</"+type+">";
		}

		private String serializeChar(char val, String type) {
			// TODO Auto-generated method stub
			return "\t<"+type+" xsi:type=\"xsd:char\">"+val+"</"+type+">";
		}

		private String serializeShort(Short val, String type) {
			// TODO Auto-generated method stub
			return "\t<"+type+" xsi:type=\"xsd:short\">"+val+"</"+type+">";
		}

		private String serializeFloat(Float val, String type) {
			// TODO Auto-generated method stub
			return "\t<"+type+" xsi:type=\"xsd:float\">"+val+"</"+type+">";
		}

		private String serializeString(String val, String type) {
			// TODO Auto-generated method stub
			return "\t<"+type+" xsi:type=\"xsd:string\">"+val+"</"+type+">";
		}

		private String serializeBool(Boolean val, String type) {
			// TODO Auto-generated method stub
			return "\t<"+type+" xsi:type=\"xsd:boolean\">"+val+"</"+type+">";
		}

		private String serializeLong(long val, String type) {
			// TODO Auto-generated method stub
			return "\t<"+type+" xsi:type=\"xsd:long\">"+val+"</"+type+">";
		}

		private String serializeInt(int val, String type) {
			// TODO Auto-generated method stub
			return "\t<"+type+" xsi:type=\"xsd:int\">"+val+"</"+type+">";
		}
		
		
}

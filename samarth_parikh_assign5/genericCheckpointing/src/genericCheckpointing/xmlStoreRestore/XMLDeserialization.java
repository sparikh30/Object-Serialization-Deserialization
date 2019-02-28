package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.FileProcessor;

public class XMLDeserialization implements DeserStrategy {

	
	private FileProcessor fp;
	private DeserializeTypes dt;
	
	public XMLDeserialization(FileProcessor fpIn) {
		// TODO Auto-generated constructor stub
		fp=fpIn;
		dt=new DeserializeTypes(fp);
	}

	@Override
	public Object deserializeData() {
		// TODO Auto-generated method stub
		return dt.makeObject();
	}
	

}


package genericCheckpointing.driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.visitor.PalindromVisitorImpl;
import genericCheckpointing.visitor.PrimeVisitorImpl;
import genericCheckpointing.visitor.VisitorI;
import genericCheckpointing.visitor.pallindromVisitor;
import genericCheckpointing.visitor.primeVisitor;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

/**
 * 
 * @author Samarth
 *	Driver class that created proxy and invoke the method on proxy. 
 */
public class Driver {
    
    public static void main(String[] args) {
	
    if (args.length != 3 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")) 
    {
			System.err.println("Error: Incorrect number of arguments. Program accepts 3 argumnets.");
			System.exit(0);
	}
    
    //saving the arguments into variable
    String mode = args[0];
    String diff_Objects = args[1];
    String fileName = "src/"+args[2];
    int NUM_OF_OBJECTS =-1;
    
    //Validating the file and integer inputs
    File inputCheck = new File(fileName);
    
    if(mode.equals("deser")) {
    	if(! (inputCheck.exists() && inputCheck.canRead())) {
    		System.err.println(fileName + " Does not exist");
    		System.err.println("Exiting");
    		System.exit(0);
    	}
    }
    else if(mode.equals("serdeser")) {
    	if(inputCheck.exists()) {
    		BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(fileName));
				if (br.readLine() != null) {
					System.err.println("An Non-empty input file already exist");
					System.err.println("Exiting");
					System.exit(1);
				}
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}     
		    catch (IOException e) {
				e.printStackTrace();
			}
    	} else
			try {
				inputCheck.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	
    }
    else
    {
    	System.err.println("Invalid Mode entered. Exiting");
		System.exit(0);
    }
    
    //validating the number of object integer value
    
    try {
    	NUM_OF_OBJECTS=Integer.parseInt(diff_Objects);
	}
	catch(NumberFormatException e) {
		System.err.println("Invalid input for Debug Value. Not a Number :" + diff_Objects);
		System.err.println("Exiting");
		System.exit(0);
	}
    
	ProxyCreator pc = new ProxyCreator();
	
	StoreRestoreHandler storeRestoreHandler = new StoreRestoreHandler();
	
	StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy( new Class[] { StoreI.class, RestoreI.class}, storeRestoreHandler  );
		
	storeRestoreHandler.setFile(fileName);

	MyAllTypesFirst myFirst;
	MyAllTypesSecond  mySecond;

	ArrayList<SerializableObject> myListOld = new ArrayList<SerializableObject>();
	
	if(mode.equals("serdeser")) {
	for (int i=0; i<NUM_OF_OBJECTS; i++) {

	    myFirst = new MyAllTypesFirst(i);
	    mySecond = new MyAllTypesSecond(i);

	    ((StoreI) cpointRef).writeObj(myFirst, 13,  "XML");
	    ((StoreI) cpointRef).writeObj(mySecond, 17, "XML");
	    
	    myListOld.add(myFirst);
	    myListOld.add(mySecond);

	}
	storeRestoreHandler.closeFile();
	}
	storeRestoreHandler.setFile(fileName);
	ArrayList<SerializableObject> myListNew = new ArrayList<SerializableObject>();
	SerializableObject myRecordRet;

	// create a data structure to store the returned ojects
	for (int j=0; j<2*NUM_OF_OBJECTS; j++) {
		
	    myRecordRet = ((RestoreI) cpointRef).readObj("XML");
	    if(myRecordRet == null)
	    	break;
	    
	    myListNew.add(myRecordRet);
	}
	
	storeRestoreHandler.closeFile();

	if(mode.equals("serdeser")) {
		findMisMatch(myListNew,myListOld);
	}
	if(mode.equals("deser")) {
		for(SerializableObject a : myListNew) {
		System.out.println(a.toString());
		}
	}


        // FIXME
        // Create an instance of the PrimeVisitorImpl and use it to determine the number of unique integers in all the instances of MyAllTypesFirst and MyAllTypesSecond
		if(mode.equals("serdeser")) {
			

			PrimeVisitorImpl prime = new PrimeVisitorImpl();
			VisitorI v = new primeVisitor(myListOld);
			try {
				prime.accept(v);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			VisitorI pali = new pallindromVisitor(myListOld);
			PalindromVisitorImpl palin = new PalindromVisitorImpl();
			
			
			try {
				palin.accept(pali);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
    }

	private static void findMisMatch(ArrayList<SerializableObject> myListNew, ArrayList<SerializableObject> myListOld) {
		int misMatch=0;
		int ListLength=0;
		
		if(myListNew.size()==myListOld.size())
			ListLength=myListNew.size();
		else
		{
			if(myListNew.size()>myListOld.size()) {
				ListLength=myListOld.size();
				misMatch=myListNew.size()-myListOld.size();
			}
			else {
				ListLength=myListNew.size();
				misMatch=myListOld.size()-myListNew.size();
			}
		}
		
		for(int i=0 ; i < ListLength; i++){
			if(!myListOld.get(i).equals(myListNew.get(i))){
				misMatch++;
			}
		}
		System.out.println("MisMatch :"+misMatch);
	}
}
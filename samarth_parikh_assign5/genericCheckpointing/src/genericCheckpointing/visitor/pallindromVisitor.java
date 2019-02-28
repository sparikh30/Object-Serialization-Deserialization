package genericCheckpointing.visitor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import genericCheckpointing.util.SerializableObject;

public class pallindromVisitor implements VisitorI {
	
	private ArrayList<SerializableObject> myListOld;
	
	public pallindromVisitor( ArrayList<SerializableObject> myListOldIn) {
		myListOld=myListOldIn;
	}
	@Override
	public void visit(PalindromVisitorImpl palindrom) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		System.out.println("List of Pallindrome strings that were serialized: ");
		
		for(SerializableObject a : myListOld) {
			try {
				if(a.getClass().getSimpleName().equals("MyAllTypesFirst")) {
					Method m=a.getClass().getMethod("getMyString");
					String aa = (String) m.invoke(a);
					if(palindrom.checkPallindrome(aa)) {
						System.out.println(aa);
					}
				}
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void visit(PrimeVisitorImpl prime) {
		// TODO Auto-generated method stub
		
	}

}

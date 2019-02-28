package genericCheckpointing.visitor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import genericCheckpointing.util.SerializableObject;

public class primeVisitor implements VisitorI{

	private ArrayList<SerializableObject> myListOld;
	
	public primeVisitor( ArrayList<SerializableObject> myListOldIn) {
		myListOld=myListOldIn;
	}
	
	@Override
	public void visit(PrimeVisitorImpl prime) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		
		System.out.println("List of Prime numbers (odd numbers here) that were serialized: ");
		
		for(SerializableObject a : myListOld) {
			try {
				if(a.getClass().getSimpleName().equals("MyAllTypesFirst")) {
					Method m=a.getClass().getMethod("getMyInt");
					int aa = (int) m.invoke(a);
					if(prime.isPrime(aa)) {
						System.out.println(aa);
					}
					m=a.getClass().getMethod("getMyOtherInt");
					aa = (int) m.invoke(a);
					if(prime.isPrime(aa)) {
						System.out.println(aa);
					}
				}
				if(a.getClass().getSimpleName().equals("MyAllTypesSecond")) {
					Method m=a.getClass().getMethod("getMyShortT");
					Short aa = (Short) m.invoke(a);
					if(prime.isPrime(aa)) {
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
	public void visit(PalindromVisitorImpl palindrom) {
		// TODO Auto-generated method stub
	}
}

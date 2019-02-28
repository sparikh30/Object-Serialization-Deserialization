package genericCheckpointing.visitor;

import java.lang.reflect.InvocationTargetException;

public class PalindromVisitorImpl {
	
	public void accept(VisitorI visitor) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	      visitor.visit(this);
	}
	
	public boolean checkPallindrome(String aIn) {			// Source for pallindrome String: w3Schools.com
		String rev="";
		int len = aIn.length();
		boolean flag=false;
		for ( int i = len - 1; i >= 0; i-- )
	         rev = rev + aIn.charAt(i);
		
		if (aIn.equals(rev)) flag=true;
		else flag=false;
		
		return flag;
	}

}

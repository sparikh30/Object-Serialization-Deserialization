package genericCheckpointing.visitor;

import java.lang.reflect.InvocationTargetException;

public class PrimeVisitorImpl {
	
	public void accept(VisitorI visitor) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	      visitor.visit(this);
	}
	
	public boolean isPrime(int z) {
		if(z%2==0)return false;
		else return true;
	}

}

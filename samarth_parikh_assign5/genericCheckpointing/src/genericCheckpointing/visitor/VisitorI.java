package genericCheckpointing.visitor;

import java.lang.reflect.InvocationTargetException;

public interface VisitorI {
	public void visit(PalindromVisitorImpl palindrom ) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	public void visit(PrimeVisitorImpl prime) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}

package genericCheckpointing.util;

import java.util.Random;

public class MyAllTypesSecond extends SerializableObject {
	
	private double myDoubleT;
	private double myOtherDoubleT;
	private float myFloatT;
	private short myShortT;
	private char myCharT;
	
	public MyAllTypesSecond() {
		// TODO Auto-generated constructor stub
	}
	
	public MyAllTypesSecond(int random) {
		random=random+1;
		Random rand = new Random();
		final String alphabet="abcdefghijklmnopqrstuvwxyz";
		float n = (float) (1.0 + rand.nextFloat()*(30.0 - 1.0));
		
		myDoubleT=n;
		myOtherDoubleT=(double) (rand.nextFloat()*100);
		myFloatT = (float) (Math.random()*random)*100;
		myShortT=(short) rand.nextInt(Short.MAX_VALUE + 1);
		myCharT=alphabet.charAt(rand.nextInt(alphabet.length()));
	}
	
	public double getMyDoubleT() {
		return myDoubleT;
	}
	public void setMyDoubleT(double myDoubleT) {
		this.myDoubleT = myDoubleT;
	}
	public double getMyOtherDoubleT() {
		return myOtherDoubleT;
	}
	public void setMyOtherDoubleT(double myOtherDoubleT) {
		this.myOtherDoubleT = myOtherDoubleT;
	}
	public float getMyFloatT() {
		return myFloatT;
	}
	public void setMyFloatT(float myFloatT) {
		this.myFloatT = myFloatT;
	}
	public short getMyShortT() {
		return myShortT;
	}
	public void setMyShortT(short myShortT) {
		this.myShortT = myShortT;
	}
	public char getMyCharT() {
		return myCharT;
	}
	public void setMyCharT(char myCharT) {
		this.myCharT = myCharT;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "myDoubleT : " + myDoubleT + " ,myOtherDoubleT: "+ myOtherDoubleT + " ,myFloatT: "+ myFloatT+" ,myShortT: " + myShortT+ " ,myCharT: " +myCharT;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj==null)
			return false;
		if(this == obj)
			return true;
		if(!(obj instanceof MyAllTypesSecond))
			return false;
		
		MyAllTypesSecond second = (MyAllTypesSecond)obj;
		
		//comparing data memebers
		
		if( Double.compare(myDoubleT,second.myDoubleT) != 0)
			return false;
		if(Double.compare(myOtherDoubleT,second.myOtherDoubleT) != 0)
			return false;
		if(Float.compare(myFloatT, second.myFloatT) != 0 )
			return false;
		if(myShortT!=second.myShortT)
			return false;
		if(myCharT!=second.myCharT)
			return false;
		
		return true;
	}
	
	// hashcode method reference : https://medium.com/codelog/overriding-hashcode-method-effective-java-notes-723c1fedf51c
	
	@Override
	public int hashCode() {
		int result = 19;
		long doub1 = Double.doubleToLongBits(myDoubleT);
		long doub2 = Double.doubleToLongBits(myOtherDoubleT);
	
		result = 31 * result + (int) (doub1^ (doub1 >>> 32));
		result = 31 * result + (int) (doub2^ (doub2 >>> 32));
		result = 31 * result + Float.floatToIntBits(myFloatT);
		result = 31 * result + myShortT;
		result = 31 * result + myCharT;
		
		return result;
	}
}

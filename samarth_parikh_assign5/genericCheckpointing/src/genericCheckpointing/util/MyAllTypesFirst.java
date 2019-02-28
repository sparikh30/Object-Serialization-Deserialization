package genericCheckpointing.util;

import java.util.Random;

public class MyAllTypesFirst extends SerializableObject {
	
	private int myInt;
	private int myOtherInt;
	private long myLong;
	private long myOtherLong;
	private String myString;
	private boolean myBool;
	
	public MyAllTypesFirst() {
		// TODO Auto-generated constructor stub
	}
	
	public MyAllTypesFirst(int random) {
		Random rand = new Random();
		random+=1;
		myInt=rand.nextInt(50) + 1;
		myOtherInt=rand.nextInt(50) + 1;
		myLong=(long)rand.nextLong()*random;
		myOtherLong=(long)rand.nextLong()*random;
		myString=""+Math.random()*100;
		if(Math.random()%2==0)
		myBool=false;
		else myBool=true;
	}

	public int getMyInt() {
		return myInt;
	}
	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}
	public int getMyOtherInt() {
		return myOtherInt;
	}
	public void setMyOtherInt(int myOtherInt) {
		this.myOtherInt = myOtherInt;
	}
	public long getMyLong() {
		return myLong;
	}
	public void setMyLong(long myLong) {
		this.myLong = myLong;
	}
	public long getMyOtherLong() {
		return myOtherLong;
	}
	public void setMyOtherLong(long myOtherLong) {
		this.myOtherLong = myOtherLong;
	}
	public String getMyString() {
		return myString;
	}
	public void setMyString(String myString) {
		this.myString = myString;
	}
	public boolean isMyBool() {
		return myBool;
	}
	public void setMyBool(boolean myBool) {
		this.myBool = myBool;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "myInt :" + myInt + " ,myOtherInt :"+ myOtherInt + " ,myLong: "+ myLong+" ,myOtherLong: " + myOtherLong+ " ,myString: " +myString +" ,myBool: " + myBool;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj==null)
			return false;
		if(this == obj)
			return true;
		if(!(obj instanceof MyAllTypesFirst))
			return false;
		
		MyAllTypesFirst first = (MyAllTypesFirst)obj;
		
		//comparing data memebers
		
		if(myBool!=first.myBool)
			return false;
		if(myInt!=first.myInt)
			return false;
		if(myLong!=first.myLong)
			return false;
		if(myOtherInt!=first.myOtherInt)
			return false;
		if(myOtherLong!=first.myOtherLong)
			return false;
		if(myString == null && first.myString!=null)
			return false;
		if(myString!=null && !myString.equals(first.myString)) {
			return false;
		}
		return true;
	}
	
	// hashcode method reference : https://medium.com/codelog/overriding-hashcode-method-effective-java-notes-723c1fedf51c
	
	@Override
	public int hashCode() {
		int result = 19;
		result = 31 * result + myString.hashCode();
		result = 31 * result + myInt;
		result = 31 * result + myOtherInt;
		result = 31 * result + (int) (myLong^ (myLong >>> 32));
		result = 31 * result + (int) (myOtherLong^ (myOtherLong >>> 32));
		result = 31 * result + (myBool ? 1 : 0);
		
		return result;
	}
}

package genericCheckpointing.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Input File Processor program is responsible for parsing input line by line 
 * @author samarth
 *
 */

public class FileProcessor {
	private BufferedReader BufReader;
	private BufferedWriter BufWriter;
	
	public FileProcessor(String filePath) {
		
		try {
			BufReader = new BufferedReader(new FileReader(filePath));
			BufWriter = new BufferedWriter(new FileWriter(filePath,true));
		}
		catch(FileNotFoundException e) {
			System.err.println("File Not Found Exception");
			System.exit(0);
		}
		catch(Exception e) {
			System.err.println("File Processor Exception");
		}
		finally{
			
		}
	}
	
	/**
	 * This is readLine method reads input file line by line
	 * @return String returns one String per line each time
	 */ 
	public String readLine()
	{
		String line=null;
		try {
			if((line=BufReader.readLine())!=null) {
				return line;
			}
		}
		catch(IOException e) {		// If buffer reader goes wrong
			System.err.println("Error while reading the file");
			System.err.println("Exiting...");
			System.exit(0);
		}
		catch(Exception e) {	// Handling any kind of exception and print stack trace
			e.printStackTrace();
		}
		finally{
			
		}
		return null;
	}
	
	/**
	 * Write to file
	 * @param line
	 */
	public void writeToFile(String line){
		try{
			BufWriter.write(line);
			BufWriter.newLine();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Disconnect the file reader 
	 */
	public void closeReader(){
		try {
			BufReader.close();
		} catch (IOException e) {
			System.err.println("Could not close input/output stream " + e.getMessage());
		}
	}
	
	/**
	 * Disconnect the writer from the file
	 */
	public void closeWriter(){
		try {
			BufWriter.close();
		} catch (IOException e) {
			System.err.println("Could not close input/output stream " + e.getMessage());
		}
	}
}
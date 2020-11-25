import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class LoadRoster {
	private static final String delimeter = ",";
	private static final int columnNum = 6;
	private static String line = "";
	private static String[][] data;

	public static void readCSV(File csv) {
		int column, row = 0;
		int rowNum=0;
		
		try {
		FileReader read = new FileReader(csv);
        BufferedReader buffer = new BufferedReader(read);
        
        while(buffer.readLine() != null) { rowNum++;}	//reading number of lines in file
        data = new String[rowNum][columnNum];	//initializing the 2d array
        
        buffer.close();		//clearing the buffer to read the file again
        FileReader file = new FileReader(csv);
        buffer = new BufferedReader(file);	
        
        while((line = buffer.readLine()) != null) {	//reads until end of file
        	
        	for (column =0;column < columnNum;column++) {
        		data[row] = line.split(delimeter);	//filling array with file info
        	}
        	
        	row++;
        }
        
        buffer.close();
        
		}
		catch(IOException io) {
			io.printStackTrace();
		}
		
	}
	
	public String[][] getData(){
		return data;
	}

}

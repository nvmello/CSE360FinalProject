import java.io.*;

import javax.swing.JOptionPane;

public class AddAttendance {
	private String [][] attendance;
	private String[] time;
	private int extraNum,currentSize,rosterNum;

	/**
	 *
	 * @param csv
	 * @param roster
	 */
	public void setAttendance(File csv,String [][] roster) {
		String line,delimeter=",";
		int row =0,rowNum=0;
		
		try {
			FileReader read = new FileReader(csv);
	        BufferedReader buffer = new BufferedReader(read);
	        
	        while(buffer.readLine() != null) { rowNum++;}	//reading number of lines in file
	        attendance = new String [rowNum][2];	//initializing the array
	        
	        buffer.close();		//clearing the buffer to read the file again
	        FileReader file = new FileReader(csv);
	        buffer = new BufferedReader(file);	
	        
	        while((line = buffer.readLine()) != null) { //filling in the attendance array
	        	attendance[row]=line.split(delimeter);
	        	row++;
	        	
	        }

	        timeArray(roster);
		}
	
		catch(IOException io) {
			io.printStackTrace();
		}
	}

	/**
	 * returns the index of the found asu ID
	 * @param roster
	 * @param currentSize
	 * @return
	 */
	public int search(String [][] roster,int currentSize) {
			for(int j=0;j<roster.length;j++) {
				if(attendance[currentSize][0].equals(roster[j][5])) {
					return j;
				}
			}
		
		return (-1);
	}

	/**
	 *
	 * @param roster
	 */
	public void timeArray(String [][] roster) {	
		time = new String[roster.length];
		int totalTime,search;
		currentSize=0;
		extraNum=0;
		rosterNum =0;
		
		for(int i =0;i<roster.length;i++) {	//initializes all time to 0
			time[i] = "0";
		}
		
		while(currentSize < attendance.length) {	
			search =search(roster,currentSize);
			
			if(search != -1) {	//if ID exists then update the time
				if((Integer.parseInt(time[search])) == 0) {rosterNum++;}	//updating the amount of users info was updated
				totalTime = Integer.parseInt(time[search]) + Integer.parseInt(attendance[currentSize][1]); //adds the time there to the new time
				time[search] = String.valueOf(totalTime);
			}
			else { //telling user how many extra attendees there were
				extraNum++;
			}
			
			currentSize++;
		}
		
	}

	/**
	 *
	 */
	public void extraUser() {
		JOptionPane.showMessageDialog(null,"Data loaded for " + rosterNum + " users in the roster.\n"
				+ extraNum + " additional attendee(s) found");
	}

	/**
	 *
	 * @return
	 */
	public String [] getAttendance() {
		return time;
	}
}

import java.io.*;

public class LoadRoster {
	private static final String delimeter = ",";
	private static String line = "";

	public static void readCSV(String csv) {
		
		try {
		File file = new File(csv);
		FileReader read = new FileReader(file);
        BufferedReader buffer = new BufferedReader(read);
        String[] temp;
        
        while((line = buffer.readLine()) != null) {
        	temp = line.split(delimeter);
        	
        	//right now just printing out the file word for word
        	//change later 
        	for(int i = 0;i < temp.length;++i) {
        		System.out.println(temp[i]);
        	}
        }
        
		}
		catch(IOException io) {
			io.printStackTrace();
		}
		
	}
	
}

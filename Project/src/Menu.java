import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;  // Import the Scanner class
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


public class Menu extends JFrame implements ActionListener,MenuListener {
    JFrame jf;
    JTable t; 
    JScrollPane js;
    JFileChooser fc;
    DefaultTableModel model;
    LoadRoster newRost;
    
    public Menu() {
        jf = new JFrame();
        jf.setTitle("CSE360 Final Project");
        jf.setSize(500,500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);

        fc = new JFileChooser();

        JMenuBar jmb = new JMenuBar();
        jf.setJMenuBar(jmb);

        JMenu file = new JMenu("File");
        jmb.add(file);

        JMenu about = new JMenu("About");
        jmb.add(about);
        about.addMenuListener(this);

        JMenuItem load = new JMenuItem("Load Roster", KeyEvent.VK_O);
        file.add(load);
        load.setActionCommand("Load Roster");
        load.addActionListener(this);
        file.addSeparator();

        JMenuItem addAtt = new JMenuItem("Add Attendance");
        file.add(addAtt);
        addAtt.setActionCommand("Add Attendance");
        addAtt.addActionListener(this);
        file.addSeparator();


        JMenuItem save = new JMenuItem("Save");
        file.add(save);
        save.setActionCommand("Save");
        save.addActionListener(this);
        file.addSeparator();


        JMenuItem plot = new JMenuItem("Plot Data");
        file.add(plot);
        plot.setActionCommand("Plot Data");
        plot.addActionListener(this);



    }


    public static void main(String[] args){
        Menu menu = new Menu();
    }

    /**
     * Currently reads in a file path from the user when "Load Roster" button is clicked
     * File path on Nicks PC for testing: " C:\Users\nvmor\Desktop\input1.csv "
     */
    public void actionPerformed(ActionEvent e) {
        if("Load Roster".equals(e.getActionCommand())){
            Scanner input = new Scanner(System.in);  // Create a Scanner object
            newRost = new LoadRoster();  //Creates a new load roster obj


            JFileChooser fileChooser = new JFileChooser(); //JFileChooser points to user's default directory
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Only .csv files","csv");
            fileChooser.setFileFilter(filter);	//only allowing csv file extensions

            int result = fileChooser.showOpenDialog(null);
            if(result == JFileChooser.APPROVE_OPTION) {

                newRost.readCSV(fileChooser.getSelectedFile());	//passing in the selected file to be read
            }

            String[] headers = {"ID","First Name","Last Name","Program","Academic Level","ASURITE"};

            model = new DefaultTableModel(newRost.getData(),headers); //filling in the JTable with file info
            t = new JTable(model);
            
           js = new JScrollPane(t);
           t.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
           jf.add(js);
        }

        if("Add Attendance".equals(e.getActionCommand())){
        	AddAttendance newAtt = new AddAttendance();
        	
        	JFileChooser fileChooser = new JFileChooser(); //JFileChooser points to user's default directory
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Only .csv files","csv");
            fileChooser.setFileFilter(filter);	//only allowing csv file extensions

            int result = fileChooser.showOpenDialog(null);
            if(result == JFileChooser.APPROVE_OPTION) {          	
                newAtt.setAttendance(fileChooser.getSelectedFile(),newRost.getData());
            }
            
            //Replace this with a date picker
            String date = JOptionPane.showInputDialog("DATE");
            
            String []newColumn = newAtt.getAttendance();
            model.addColumn(date,newColumn);	//add a new column to the JTable with the specified date
            newAtt.extraUser();		//showing the user attendee information was loaded
            
        }

        if("Save".equals(e.getActionCommand())) {
            File saveFile = fc.getSelectedFile();
            //writing to a file
            int userSelection = fc.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fc.getSelectedFile();
                try {
                    FileWriter fw = new FileWriter(saveFile);
                    BufferedWriter bw = new BufferedWriter(fw);
                    for (int i = 0; i < t.getRowCount(); i++) {
                        for (int j = 0; i < t.getColumnCount(); j++) {
                            //writing
                            bw.write(t.getValueAt(i, j).toString() + ",");
                        }
                        bw.newLine();//RECORD PER LINE
                    }
                    bw.close();
                    fw.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "ERRPR", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
            if("Plot Data".equals(e.getActionCommand())){
            System.out.println("It works!4");
        }

    }


    //dialog box for the about section
	@Override
	public void menuSelected(MenuEvent e) {
		JOptionPane.showMessageDialog(jf, "Team Information:\n"
    			+ "");
	}


	@Override
	public void menuDeselected(MenuEvent e) {
		
	}


	@Override
	public void menuCanceled(MenuEvent e) {
		
		
	}
}

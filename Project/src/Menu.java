import javafx.scene.control.DatePicker;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Menu extends JFrame implements ActionListener,MenuListener {
    JFrame frame;
    JTable table;
    JScrollPane scrollPane;
    JFileChooser chooser;
    DefaultTableModel model;
    LoadRoster newRost;
    private static final long serialVersionUID = 6294689542092367723L;
    /**
     *
     */
    public Menu() {
        frame = new JFrame();
        frame.setTitle("CSE360 Final Project");
        frame.pack();
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        chooser = new JFileChooser();

        JMenuBar jmb = new JMenuBar();
        frame.setJMenuBar(jmb);

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
        frame.setVisible(true);

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        Menu menu = new Menu();
    }

    /**
     * Currently reads in a file path from the user when "Load Roster" button is clicked
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

                LoadRoster.readCSV(fileChooser.getSelectedFile());	//passing in the selected file to be read
            }

            String[] headers = {"ID","First Name","Last Name","Program","Academic Level","ASURITE"};

            model = new DefaultTableModel(newRost.getData(),headers); //filling in the JTable with file info
            table = new JTable(model);
            
           scrollPane = new JScrollPane(table);
           table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
           frame.add(scrollPane);
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
            
//            Replace this with a date picker
            String date = JOptionPane.showInputDialog("DATE");



                    String[]newColumn = newAtt.getAttendance();
            model.addColumn(date,newColumn);	//add a new column to the JTable with the specified date
            newAtt.extraUser();		//showing the user attendee information was loaded
            
        }

        if("Save".equals(e.getActionCommand())) {
            Scanner input = new Scanner(System.in);  // Create a Scanner object
            Save mainSave = new Save();
            newRost = new LoadRoster();  //Creates a new load roster obj


            JFileChooser fileChooser = new JFileChooser(); //JFileChooser points to user's default directory
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Only .csv files","csv");
            fileChooser.setFileFilter(filter);	//only allowing csv file extensions

            int result = fileChooser.showOpenDialog(null);
            if(result == JFileChooser.APPROVE_OPTION) {

                mainSave.saveCSV(table, fileChooser.getSelectedFile().getAbsolutePath());
            }

        }
            if("Plot Data".equals(e.getActionCommand())){
                graph newGraph = new graph(table);
                newGraph.setSize(600, 400);
                newGraph.setLocationRelativeTo(null);
                newGraph.setVisible(true);
        }
        frame.setVisible(true);
    }


    //dialog box for the about section
    //add names and ASU user ID
	@Override
	public void menuSelected(MenuEvent e) {
		JOptionPane.showMessageDialog(frame, "Team Information:" +
                "\nReanna Schnabel:  rmschnab"
    			+ "\nJulie Houtman:  jhoutman"
                + "\nNick Morello: nvmorell"
                + "\nLamanh Ngo: llngo"
                + "\nAndrew Spicher: wspicher");
	}


	@Override
	public void menuDeselected(MenuEvent e) {
		
	}


	@Override
	public void menuCanceled(MenuEvent e) {
		
		
	}



}



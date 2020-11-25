import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Scanner;  // Import the Scanner class
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
public class Menu extends JFrame implements ActionListener {
    JFrame jf;
    JFileChooser fc;

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
            LoadRoster newRost = new LoadRoster();  //Creates a new load roster obj


            JFileChooser fileChooser = new JFileChooser(); //JFileChooser points to user's default directory
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Only .csv files","csv");
            fileChooser.setFileFilter(filter);	//only allowing csv file extensions

            int result = fileChooser.showOpenDialog(null);
            if(result == JFileChooser.APPROVE_OPTION) {

                newRost.readCSV(fileChooser.getSelectedFile());	//passing in the selected file to be read
            }

            String[] headers = {"ID","First Name","Last Name","Program and Plan","Academic Level","ASURITE"};

            JTable t = new JTable(newRost.getData(),headers); //filling in the JTable with file info

            JScrollPane js = new JScrollPane(t);
            jf.add(js);
        }

        if("Add Attendance".equals(e.getActionCommand())){
            System.out.println("It works2!");
        }

        if("Save".equals(e.getActionCommand())){
            System.out.println("It works!3");
        }
        if("Plot Data".equals(e.getActionCommand())){
            System.out.println("It works!4");
        }

    }
}

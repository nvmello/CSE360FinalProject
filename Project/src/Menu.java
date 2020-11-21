import javax.swing.*;
import java.awt.*;
import java.util.Scanner;  // Import the Scanner class
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {

    public Menu() {
        JFrame jf = new JFrame();
        jf.setTitle("CSE360 Final Project");
        jf.setSize(500,500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);

        JMenuBar jmb = new JMenuBar();
        jf.setJMenuBar(jmb);

        JMenu file = new JMenu("File");
        jmb.add(file);
        JMenuItem load = new JMenuItem("Load Roster");
        file.add(load);
        file.addSeparator();
        JMenuItem addAtt = new JMenuItem("Add Attendance");
        file.add(addAtt);
        load.addActionListener(this);
        file.addSeparator();
        JMenuItem save = new JMenuItem("Save");
        file.add(save);
        file.addSeparator();
        JMenuItem plot = new JMenuItem("Plot Data");
        file.add(plot);

        JMenu about = new JMenu("About");
        jmb.add(about);
    }


    public static void main(String[] args){
        Menu menu = new Menu();
    }

    /**
     * Currently reads in a file path from the user when "Load Roster" button is clicked
     * File path on Nicks PC for testing: " C:\Users\nvmor\Desktop\input1.csv "
     */
    public void actionPerformed(ActionEvent e) {
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        LoadRoster newRost = new LoadRoster();  //Creates a new load roster obj

        System.out.println("Please enter a file path to a CSV: ");  //prompt
        String filePath = input.nextLine();     //gets file path from a user in the form of a string
        newRost.readCSV(filePath);
    }
}

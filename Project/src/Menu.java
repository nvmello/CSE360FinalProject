import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu {
    public static void main(String[] args){
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
        file.addSeparator();
        JMenuItem save = new JMenuItem("Save");
        file.add(save);
        file.addSeparator();
        JMenuItem plot = new JMenuItem("Plot Data");
        file.add(plot);

        JMenu about = new JMenu("About");
        jmb.add(about);

    }
}

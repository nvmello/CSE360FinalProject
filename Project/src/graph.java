import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class graph extends JFrame{
    public graph(JTable table){
        XYDataset dataset = createDataset(table);

        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot("Attendance Record",
                        "Percentage of Attendance",
                "Total Number of Students", dataset);


        //Changes background color
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(new Color(255,228,196));


        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);

    }


    public XYDataset createDataset(JTable table){
        XYSeriesCollection dataset = new XYSeriesCollection();//the dataset

        // Y-Axis is how many students from (0 to total row in the table
        //atm Y-Axis is just 1-total students
        // still need to add stuff
        int[] count = new int[table.getRowCount()];

        for(int i = 6; i < table.getColumnCount(); i++){
            XYSeries series = new XYSeries(table.getColumnName(i));
            for(int j = 0; j < table.getRowCount();j++) {
                    count[j] = Integer.parseInt(table.getValueAt(j,i).toString());
                    series.add(count[j],j);
                }
            dataset.addSeries(series);
        }
        return dataset;
    }
}

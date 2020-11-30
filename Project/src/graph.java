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

    /**
     * Reads in the table and creates a dataset for the graph
     */
    public XYDataset createDataset(JTable table){
        XYSeriesCollection dataset = new XYSeriesCollection();//the dataset


        int[] count = new int[table.getRowCount()];

        for(int i = 6; i < table.getColumnCount(); i++){    //starting at column 6 read in the attendance
            XYSeries series = new XYSeries(table.getColumnName(i));
            for(int j = 0; j < table.getRowCount();j++) {   //read in the row of attendance
                    count[j] = Integer.parseInt(table.getValueAt(j,i).toString());
                    series.add(count[j],j); //add to the series
                }
            dataset.addSeries(series);  //add the series into the dataset
        }
        return dataset;
    }
}

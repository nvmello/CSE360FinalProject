import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class Save {
    public Save(){}

    /**
     * Saves all data from the jTable into a CSV file
     * @param tblToSave: the jTable
     * @param filePath: the file path where the CSV is to be saved
     */
    public void saveCSV(JTable tblToSave, String filePath) {
        try {
            TableModel saveModel = tblToSave.getModel();
            FileWriter fWriter = new FileWriter(new File(filePath));

            //This loop saves the headers from out jTable
            for (int i = 0; i < saveModel.getColumnCount(); i++) {
                fWriter.write(saveModel.getColumnName(i) + ",");
            }
            fWriter.write("\n");
            //This Loop writes the rest of the data to the CSV
            for (int i = 0; i < saveModel.getRowCount(); i++) {
                for (int j = 0; j < saveModel.getColumnCount(); j++) {
                    fWriter.write(saveModel.getValueAt(i, j).toString() + ",");
                }
                fWriter.write("\n");
            }

            fWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

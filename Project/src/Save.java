import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
    public Save(){}
    public void saveCSV(JTable tblToSave, String filePath) {
        try {
            TableModel saveModel = tblToSave.getModel();
            FileWriter fWriter = new FileWriter(new File(filePath));

            for (int i = 0; i < saveModel.getColumnCount(); i++) {
                fWriter.write(saveModel.getColumnName(i) + ",");
            }
            fWriter.write("\n");
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

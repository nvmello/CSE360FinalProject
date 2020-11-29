import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class save {
    public save(){}
    public void saveFile(JTable table, File file) throws IOException {
        TableModel model = table.getModel();
        FileWriter out = new FileWriter(file);

        for(int i = 0; i< model.getColumnCount();i++){
            out.write(model.getColumnName(i) + ",");
        }

        out.write("\n");

        for(int i = 0; i < model.getRowCount();i++){
            for(int j = 0; j < model.getColumnCount();j++){
                out.write(model.getValueAt(i,j).toString()+",");
            }
            out.write("\n");
        }
        out.close();
    }
}

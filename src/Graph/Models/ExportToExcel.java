package Graph.Models;

import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class ExportToExcel {
	JTable table;
	
	public ExportToExcel(Component c, JTable table){
		this.table = table;
		fileChooser(c);
	}
	
	public void fileChooser(Component c) {
		JFileChooser fc = new JFileChooser();
		int option = fc.showSaveDialog(c);
		if (option == JFileChooser.APPROVE_OPTION) {
			String filename = fc.getSelectedFile().getName();
			String path = fc.getSelectedFile().getParentFile().getPath();

			int len = filename.length();
			String ext = "";
			String file = "";

			if (len > 4) {
				ext = filename.substring(len - 4, len);
			}

			if (ext.equals(".xls")) {
				file = path + "\\" + filename;
			} else {
				file = path + "\\" + filename + ".xls";
			}
			toExcel(new File(file));
		}
	}

	public void toExcel(File file) {
		try {
			TableModel model = table.getModel();
			FileWriter excel = new FileWriter(file);

			for (int i = 0; i < model.getColumnCount(); i++) {
				excel.write(model.getColumnName(i) + "\t");
			}

			excel.write("\n");

			for (int i = 0; i < model.getRowCount(); i++) {
				for (int j = 0; j < model.getColumnCount(); j++) {
					if (model.getValueAt(i, j) == null)
						excel.write("\t");
					else
						excel.write(model.getValueAt(i, j).toString() + "\t");
				}
				excel.write("\n");
			}
			excel.close();

		} catch (IOException e) {
			System.out.println(e);
		}
	}

}

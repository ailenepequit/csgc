package Graph.Views;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Formatter {
	
	final String imagesPath = "C:\\Users\\USER\\OneDrive\\workspace\\CSGC\\src\\resource\\images\\";
	final String newIcon = imagesPath + "new.png";
	final String addIcon = imagesPath + "plus.png";
	final String updateIcon = imagesPath + "saveupdate.png";
	final String deleteIcon = imagesPath + "delete.png";
	final String viewTimetableIcon = imagesPath + "calendar.png";
	final String printIcon = imagesPath + "printeicon.png";
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	TableRowSorter trs;
	
	public void comboBoxFormat(JComboBox cb) {
		cb.setForeground(new Color(47, 79, 79));
		cb.setFont(new Font("Arial", Font.PLAIN, 13));
	}

	public void buttonFormat(JButton button, String icon) {
		button.setIcon(new ImageIcon(icon));
		button.setFont(new Font("SansSerif", Font.PLAIN, 12));
		button.setForeground(Color.BLACK);
		button.setFocusPainted(false);
		button.setOpaque(true);
		button.setFocusPainted(false);
		button.setBorderPainted(true);
		button.setBackground(new Color(240, 240, 240));
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
	}
	
	public void labelFormat(JLabel label) {
		label.setBackground(new Color(240, 240, 240));
		label.setForeground(Color.BLACK);
		label.setOpaque(true);
		label.setFont(new Font("SansSerif", Font.PLAIN, 11));
	}
	
	public void tableFormat(JTable t) {
		t.setRowSelectionAllowed(true);
		t.setGridColor(Color.lightGray);
		t.setRowHeight(21);
		t.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		t.setFont(new Font("SansSerif", Font.PLAIN, 12));
		t.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		t.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
	}
	
	public void tableSorter(DefaultTableModel m, JTable t) {
		trs = new TableRowSorter(m);

		class IntComparator implements Comparator {
			public int compare(Object o1, Object o2) {
				Integer int1 = (Integer) o1;
				Integer int2 = (Integer) o2;
				return int1.compareTo(int2);
			}

			public boolean equals(Object o2) {
				return this.equals(o2);
			}
		}

		trs.setComparator(0, new IntComparator());
		t.setRowSorter(trs);
	}

	public String to24hr(String time) {
		DateFormat df = new SimpleDateFormat("hh:mm aa");
		Date date = null;
		try {
			date = df.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		DateFormat _df = new SimpleDateFormat("HH:mm:ss");
		return _df.format(date);
	}
}

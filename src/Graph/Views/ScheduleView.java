package Graph.Views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Graph.Models.Offering;

import javax.swing.JScrollPane;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class ScheduleView extends JFrame {

	private JPanel contentPane;
	Offering c = new Offering();
	ArrayList<Offering> offeringlist;
	MultiSpanCellTable mtable;
	private JTable scheduleTable;
	JLabel header;
	int id;
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	ArrayList<Offering> sched;
	DefaultTableModel schedmodel;
	Object[][] data_sched;

	Formatter format = new Formatter();

	public ScheduleView(String x, ArrayList<Offering> sched) {
		this.sched = new ArrayList<Offering>(sched);
		setBounds(100, 100, 1090, 746);
		setLocationRelativeTo(null);
		setTitle("Schedule");

		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		components(x);
		scheduleTableComponents();
	}

	public void components(String x) {

		header = new JLabel("      " + x);
		header.setBounds(0, 0, 1074, 32);
		header.setForeground(Color.white);
		header.setBackground(new Color(46, 139, 87));
		header.setOpaque(true);
		contentPane.add(header);

	}

	public void scheduleTableComponents() {

		data_sched = new Object[][] { { null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null }, };

		String[] cols = new String[] { "Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

		schedmodel = new DefaultTableModel(data_sched, cols);
		setTableCellValue();
		scheduleTable = new JTable(schedmodel) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component comp = super.prepareRenderer(renderer, row, col);
				Object value = getModel().getValueAt(row, col);
				
				if (col != 0 && value != null) {
					comp.setBackground(Color.pink);
				} else {
					comp.setBackground(Color.white);
				}
				return comp;
			}

			public boolean getScrollableTracksViewportWidth() {
				return getPreferredSize().width < getParent().getWidth();
			}
		};
		//scheduleTable.setGridColor(Color.lightGray);
		scheduleTable.setRowSelectionAllowed(false);
		format.tableFormat(scheduleTable);
		scheduleTable.setVisible(true);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 1054, 668);
		contentPane.add(scrollPane);

		String[] timeslot = { "07:00-07:30 AM", "07:30-08:00 AM", "08:00-08:30 AM", "08:30-09:00 AM", "09:00-09:30 AM",
				"09:30-10:00 AM", "10:00-10:30 AM", "10:30-11:00 AM", "11:00-11:30 AM", "11:30-12:00 PM",
				"12:00-12:30 PM", "12:30-01:00 PM", "01:00-01:30 PM", "01:30-02:00 PM", "02:00-02:30 PM",
				"02:30-03:00 PM", "03:00-03:30 PM", "03:30-04:00 PM", "04:00-04:30 PM", "04:30-05:00 PM",
				"05:00-05:30 PM", "05:30-06:00 PM", "06:00-06:30 PM", "06:30-07:00 PM", "07:00-07:30 PM",
				"07:30-08:00 PM", "08:00-08:30 PM", "08:30-09:00 PM" };

		for (int i = 0; i < scheduleTable.getRowCount(); i++) {
			scheduleTable.setValueAt(timeslot[i], i, 0);
		}

		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int col = 0; col < scheduleTable.getColumnCount(); col++) {
			scheduleTable.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
		}

		scrollPane.setViewportView(scheduleTable);
	}

	public void setTableCellValue() {
		for (Offering o : sched) {
			String day = o.getTimeslot();
			int row = 0;
			String room = "";
			System.out.println(o.getRoom());
			try {
				if (!o.getRoom().equals(null))
					room = o.getRoom();
				else
					room = "TBA";
			} catch (Exception e) {
				System.err.println(e);
			}
			if (day.contains("MTh")) {
				row = setTableRow(day);
				schedmodel.setValueAt("", row - 1, 1);
				schedmodel.setValueAt(o.getDescription(), row, 1);
				schedmodel.setValueAt(room, row + 1, 1);
				schedmodel.setValueAt("", row-1, 4);
				schedmodel.setValueAt(o.getDescription(), row, 4);
				schedmodel.setValueAt(room, row + 1, 4);
			} else if (day.contains("TuF")) {
				row = setTableRow(day);
				schedmodel.setValueAt("", row - 1, 2);
				schedmodel.setValueAt(o.getDescription(), row, 2);
				schedmodel.setValueAt(room, row + 1, 2);
				schedmodel.setValueAt("", row - 1, 5);
				schedmodel.setValueAt(o.getDescription(), row, 5);
				schedmodel.setValueAt(room, row + 1, 5);
			}
			if (day.contains("W")) {
				row = setWSTableRow(day);
				schedmodel.setValueAt("", row - 1, 3);
				schedmodel.setValueAt(o.getDescription(), row, 3);
				schedmodel.setValueAt(room, row + 1, 3);
				schedmodel.setValueAt("", row + 2, 3);
			}
			if (day.contains("S")) {
				row = setWSTableRow(day);
				schedmodel.setValueAt("", row - 1, 6);
				schedmodel.setValueAt(o.getDescription(), row, 6);
				schedmodel.setValueAt(room, row + 1, 6);
				schedmodel.setValueAt("", row + 2, 6);
			}
		}
	}

	public int setTableRow(String timeslot) {
		int row = 0;
		if (timeslot.contains("7:30-9:00 AM")) {
			row = 2;
		} else if (timeslot.contains("9:00-10:30 AM")) {
			row = 5;
		} else if (timeslot.contains("10:30-12:00 PM")) {
			row = 8;
		} else if (timeslot.contains("12:00-1:30 PM")) {
			row = 11;
		} else if (timeslot.contains("1:30-3:00 PM")) {
			row = 14;
		} else if (timeslot.contains("3:00-4:30 PM")) {
			row = 17;
		} else if (timeslot.contains("4:30-6:00 PM")) {
			row = 20;
		} else if (timeslot.contains("6:00-7:30 PM")) {
			row = 23;
		} else if (timeslot.contains("7:30-9:00 PM")) {
			row = 26;
		}
		return row;
	}

	public int setWSTableRow(String timeslot) {
		int row = 0;
		if (timeslot.contains("8:00-10:00 AM")) {
			row = 3;
		} else if (timeslot.contains("10:00-12:00 PM")) {
			row = 7;
		} else if (timeslot.contains("1:00-3:00 PM")) {
			row = 13;
		} else if (timeslot.contains("3:00-5:00 PM")) {
			row = 17;
		} else if (timeslot.contains("5:00-7:00 PM")) {
			row = 21;
		} else if (timeslot.contains("7:00-9:00 PM")) {
			row = 25;
		}
		return row;
	}
}

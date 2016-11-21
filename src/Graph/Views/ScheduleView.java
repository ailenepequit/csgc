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

import DAO.TimeslotDAO;
import Graph.Models.Offering;

import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ScheduleView extends JFrame {

	private JPanel contentPane;
	Offering c = new Offering();
	ArrayList<Offering> offeringlist;
	MultiSpanCellTable mtable;
	private JTable scheduleTable;
	JLabel header;
	String title;
	int id;
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	ArrayList<Offering> sched;
	DefaultTableModel schedmodel;
	Object[][] data_sched;
	TimeslotDAO t = new TimeslotDAO();

	HashMap<Color, String> colors = new HashMap<Color, String>();
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

		this.title = x;
		components();
		scheduleTableComponents();
	}

	public void components() {
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(975, 7, 89, 23);
		format.buttonFormat(btnPrint, format.printIcon);
		btnPrint.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				TablePrinter t = new TablePrinter(title, scheduleTable);
				t.setVisible(true);
			}
		});
		contentPane.add(btnPrint);

		header = new JLabel("   " + title);
		header.setFont(new Font("Tahoma", Font.PLAIN, 15));
		header.setHorizontalAlignment(SwingConstants.CENTER);
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

				if (value != null && col != 0) {
					String day = "";
					if (col == 1 || col == 4)
						day = "MTh";
					if (col == 2 || col == 5)
						day = "TuF";
					if (col == 3)
						day = "W";
					if (col == 6)
						day = "S";

					if (col == 1 || col == 4 || col == 2 || col == 5) {
						if (row == 1 || row == 2 || row == 3)
							comp.setBackground(getKey(colors, t.getColor("7:30-9:00 AM " + day)));
						if (row == 4 || row == 5 || row == 6)
							comp.setBackground(getKey(colors, t.getColor("9:00-10:30 AM " + day)));
						if (row == 7 || row == 8 || row == 9)
							comp.setBackground(getKey(colors, t.getColor("10:30-12:00 PM " + day)));
						if (row == 10 || row == 11 || row == 12)
							comp.setBackground(getKey(colors, t.getColor("12:00-1:30 PM " + day)));
						if (row == 13 || row == 14 || row == 15)
							comp.setBackground(getKey(colors, t.getColor("1:30-3:00 PM " + day)));
						if (row == 16 || row == 17 || row == 18)
							comp.setBackground(getKey(colors, t.getColor("3:00-4:30 PM " + day)));
						if (row == 19 || row == 20 || row == 21)
							comp.setBackground(getKey(colors, t.getColor("4:30-6:00 PM " + day)));
						if (row == 22 || row == 23 || row == 24)
							comp.setBackground(getKey(colors, t.getColor("6:00-7:30 PM " + day)));
						if (row == 25 || row == 26 || row == 27)
							comp.setBackground(getKey(colors, t.getColor("7:30-9:00 PM " + day)));
					}
					if (col == 3 || col == 6) {
						if (row == 2 || row == 3 || row == 4 || row == 5)
							comp.setBackground(getKey(colors, t.getColor("8:00-10:00 AM " + day)));
						if (row == 6 || row == 7 || row == 8 || row == 9)
							comp.setBackground(getKey(colors, t.getColor("10:00-12:00 PM " + day)));
						if (row == 12 || row == 13 || row == 14 || row == 15)
							comp.setBackground(getKey(colors, t.getColor("1:00-3:00 PM " + day)));
						if (row == 16 || row == 17 || row == 18 || row == 19)
							comp.setBackground(getKey(colors, t.getColor("3:00-5:00 PM " + day)));
						if (row == 20 || row == 21 || row == 22 || row == 23)
							comp.setBackground(getKey(colors, t.getColor("5:00-7:00 PM " + day)));
						if (row == 24 || row == 25 || row == 26 || row == 27)
							comp.setBackground(getKey(colors, t.getColor("7:00-9:00 PM " + day)));
					}
				} else {
					comp.setBackground(Color.white);
				}
				return comp;
			}

			public boolean getScrollableTracksViewportWidth() {
				return getPreferredSize().width < getParent().getWidth();
			}
		};
		scheduleTable.setShowGrid(true);
		((DefaultTableCellRenderer) scheduleTable.getDefaultRenderer(Object.class)).setOpaque(false);
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
		mapColors();
		for (Offering o : sched) {
			String day = o.getTimeslot();
			int row = 0;
			String room = "";
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

				schedmodel.setValueAt("", row - 1, 4);
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

	public Color getKey(HashMap<Color, String> map, String value) {
		Color key = null;
		for (Map.Entry<Color, String> entry : map.entrySet()) {
			if ((value == null && entry.getValue() == null) || (value != null && value.equals(entry.getValue()))) {
				key = entry.getKey();
				break;
			}
		}
		return key;
	}

	public void mapColors() {
		colors.put(new Color(241, 169, 160), "WAX FLOWER");
		colors.put(new Color(224, 130, 131), "NEW YORK PINK");
		colors.put(new Color(174, 168, 221), "WISTFUL");
		colors.put(new Color(190, 144, 212), "LIGHT WISTERIA");
		colors.put(new Color(228, 241, 254), "ALICE BLUE");
		colors.put(new Color(129, 207, 224), "SPRAY");
		colors.put(new Color(197, 239, 247), "HUMMING BIRD");
		colors.put(new Color(149, 204, 172), "MALIBU");
		colors.put(new Color(137, 196, 244), "JORDY BLUE");
		colors.put(new Color(200, 247, 190), "MADANG");
		colors.put(new Color(134, 226, 213), "RIPTIDE");
		colors.put(new Color(102, 204, 153), "MEDIUM AQUAMARINE");
		colors.put(new Color(253, 227, 167), "CAPE HONEY");
		colors.put(new Color(210, 215, 211), "PUMICE");
		colors.put(new Color(144, 198, 149), "DARK SEA GREEN");
		colors.put(new Color(209, 178, 159), "#D1B299");
		colors.put(new Color(255, 158, 198), "#EF9EC6");
		colors.put(new Color(212, 222, 102), "#D4DE66");
		colors.put(new Color(158, 143, 255), "#9E8FFF");
		colors.put(new Color(230, 255, 69), "#E6FF45");
		colors.put(new Color(255, 181, 208), "#FFB5D0");
		colors.put(new Color(255, 184, 69), "#FFB845");
		colors.put(new Color(255, 250, 184), "#FFFAB8");
		colors.put(new Color(255, 204, 204), "#FFCCCC");
		colors.put(new Color(230, 255, 204), "#E6FFCC");
		colors.put(new Color(212, 171, 255), "#D4ABFF");
		colors.put(new Color(236, 222, 250), "#ECDEFA");
		colors.put(new Color(222, 250, 250), "#DEFAFA");
		colors.put(new Color(252, 225, 225), "#FCE1E1");
		colors.put(new Color(250, 239, 222), "#FAEFDE");
		colors.put(new Color(255, 247, 148), "#FFF794");
	}
}

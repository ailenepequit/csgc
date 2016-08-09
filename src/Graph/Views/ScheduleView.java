package Graph.Views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Graph.Models.Offering;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
public class ScheduleView extends JFrame {

	private JPanel contentPane;
	JScrollPane scrollPane;
	private JTable timeTable;
	Offering c = new Offering();
	ArrayList<Offering> offeringlist;
	MultiSpanCellTable mtable;
	private JTable scheduleTable;
	JLabel header, label;
	String table;
	int id;

	Formatter format = new Formatter();

	public ScheduleView(int id, String table) {
		this.id = id;
		this.table = table;
		setBounds(100, 100, 1090, 740);
		setLocationRelativeTo(null);
		setTitle(table.toUpperCase() + " Schedule");

		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		components();
		timeTableComponents();
		scheduleTableComponents();
		setMtable();
	}

	public void components() {
		String[] view = { "Tabular", "Calendar" };
		JComboBox comboBox = new JComboBox(view);
		comboBox.setBounds(861, 23, 102, 20);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().equals("Tabular")) {
					timeTable.setVisible(true);
					scheduleTable.setVisible(false);
				}
				if (comboBox.getSelectedItem().equals("Calendar")) {
					scrollPane.setViewportView(mtable);
					scheduleTable.setVisible(false);
					timeTable.setVisible(false);
					mtable.setVisible(true);
					setMtable();
				}
			}

		});

		label = new JLabel("");
		label.setBackground(new Color(46, 139, 87));
		label.setOpaque(true);
		label.setForeground(new Color(255, 255, 255));
		label.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label.setBounds(10, 26, 779, 18);

		header = new JLabel("");
		header.setBounds(0, 0, 1074, 52);
		header.setBackground(new Color(46, 139, 87));
		header.setOpaque(true);

		contentPane.add(comboBox);
		
		contentPane.add(label);
		contentPane.add(header);
	}

	public void timeTableComponents() {
		Object[][] data = new Object[][] { { null, null, null, null, null, null, null, null, null, null } };
		String[] columns = new String[] { "Id", "SY", "Semester", "Start Time", "End Time", "Day", "Description",
				"Faculty", "Class Size", "Room" };

		if (table.equals("room")) {
			offeringlist = c.courseListByRoom(id);
			data = new Object[offeringlist.size()][];
			if (offeringlist.size() != 0) {
				for (int i = 0; i < offeringlist.size(); i++) {
					data[i] = offeringlist.get(i).toTimetableArray();
				}
			} else
				data = new Object[][] { { null, null, null, null, null, null, null, null, null, null } };

			// label.setText(offeringlist.get(id).getRoom() + " Schedule");
		}

		if (table.equals("faculty")) {
			offeringlist = c.courseListByFaculty(id);
			data = new Object[offeringlist.size()][];
			if (offeringlist.size() != 0) {
				for (int i = 0; i < offeringlist.size(); i++) {
					data[i] = offeringlist.get(i).toTimetableArray();
				}
			} else
				data = new Object[][] { { null, null, null, null, null, null, null, null, null, null } };

			label.setText(offeringlist.get(id).getFaculty() + " Schedule");
		}

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableModel model = new DefaultTableModel(data, columns);
		timeTable = new JTable(model);
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) timeTable.getTableHeader().getDefaultRenderer();

		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		timeTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		timeTable.setRowSelectionAllowed(false);
		timeTable.setGridColor(Color.lightGray);
		timeTable.setRowHeight(21);
		timeTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		timeTable.setFont(new Font("SansSerif", Font.PLAIN, 12));
		timeTable.setBounds(10, 61, 1054, 489);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 54, 1074, 647);
		//scrollPane.setViewportView(timeTable);
		contentPane.add(scrollPane);
	}

	public void scheduleTableComponents() {

		Object[][] data_sched = new Object[][] { { null, null, null, null, null, null, null },
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

		DefaultTableModel schedmodel = new DefaultTableModel(data_sched, cols);

		scheduleTable = new JTable(schedmodel);

		String[] timeslot = { "07:00AM-07:30AM", "07:30AM-08:00AM", "08:00AM-08:30AM", "08:30AM-09:00AM",
				"09:00AM-09:30AM", "09:30AM-10:00AM", "10:00AM-10:30AM", "10:30AM-11:00AM", "11:00AM-11:30AM",
				"11:30AM-12:00PM", "12:00PM-12:30PM", "12:30PM-01:00PM", "01:00PM-01:30PM", "01:30PM-02:00PM",
				"02:00PM-02:30PM", "02:30PM-03:00PM", "03:00PM-03:30PM", "03:30PM-04:00PM", "04:00PM-04:30PM",
				"04:30PM-05:00PM", "05:00PM-05:30PM", "05:30PM-06:00PM", "06:00PM-06:30PM", "06:30PM-07:00PM",
				"07:00PM-07:30PM", "07:30PM-08:00PM", "08:00PM-08:30PM", "08:30PM-09:00PM" };
		for (int i = 0; i < scheduleTable.getRowCount(); i++) {
			scheduleTable.setValueAt(timeslot[i], i, 0);
		}
		scheduleTable.setVisible(false);
		scheduleTable.setBounds(10, 74, 1054, 616);
		contentPane.add(scheduleTable);
	}

	public void setMtable() {
		Object[][] data = new Object[][] { { null, null, null, null, null, null, null },
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
		AttributiveCellTableModel ml = new AttributiveCellTableModel(data, cols);
		final CellSpan cellAtt = (CellSpan) ml.getCellAttribute();
		mtable = new MultiSpanCellTable(ml);
		String[] timeslot = { "07:00AM-07:30AM", "07:30AM-08:00AM", "08:00AM-08:30AM", "08:30AM-09:00AM",
				"09:00AM-09:30AM", "09:30AM-10:00AM", "10:00AM-10:30AM", "10:30AM-11:00AM", "11:00AM-11:30AM",
				"11:30AM-12:00PM", "12:00PM-12:30PM", "12:30PM-01:00PM", "01:00PM-01:30PM", "01:30PM-02:00PM",
				"02:00PM-02:30PM", "02:30PM-03:00PM", "03:00PM-03:30PM", "03:30PM-04:00PM", "04:00PM-04:30PM",
				"04:30PM-05:00PM", "05:00PM-05:30PM", "05:30PM-06:00PM", "06:00PM-06:30PM", "06:30PM-07:00PM",
				"07:00PM-07:30PM", "07:30PM-08:00PM", "08:00PM-08:30PM", "08:30PM-09:00PM" };
		for (int i = 0; i < mtable.getRowCount(); i++) {
			mtable.setValueAt(timeslot[i], i, 0);
		}
		scrollPane.setViewportView(mtable);
	
		// int[] col = { 1, 1 };
		// int[] rows = { 1, 2 };
		// cellAtt.combine(rows, col);
		// MultiSpanCellTableUI m = new MultiSpanCellTableUI();
		// m.paint(getGraphics(), mtable);
		// ColoredCell cc = (ColoredCell) ml.getCellAttribute();
		// cc.setBackground(Color.CYAN, 3, 1);
		// /table.clearSelection();
		// mtable.revalidate();
		// mtable.repaint();
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) mtable.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		// mtable.setEnabled(false);
		mtable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		mtable.setRowSelectionAllowed(false);
		mtable.setGridColor(Color.lightGray);
		mtable.setRowHeight(21);
		mtable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		mtable.setFont(new Font("SansSerif", Font.PLAIN, 12));
		mtable.setBounds(10, 61, 1054, 489);

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] rows = mtable.getSelectedRows();
				int[] cols = mtable.getSelectedColumns();
				for(int i = 0; i < rows.length; i++)
					System.out.print("row " + rows[i]);
				for(int j = 0; j < cols.length; j++)
					System.out.println("col " + cols[j]);
				
				cellAtt.combine(rows, cols);
				mtable.clearSelection();
				mtable.revalidate();
				mtable.repaint();
			}
		});
		format.buttonFormat(btnPrint, format.printIcon);
		btnPrint.setBounds(993, 22, 71, 23);
		contentPane.add(btnPrint);
	}
}

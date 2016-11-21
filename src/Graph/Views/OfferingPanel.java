package Graph.Views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import DAO.BlockSubjectsDAO;
import Graph.Models.ExportToExcel;
import Graph.Models.Faculty;
import Graph.Models.Offering;
import Graph.Models.Room;
import Graph.Models.Scheduler;
import Graph.Models.Subject;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class OfferingPanel extends JPanel {

	private int offeringID, slots;
	private JButton editBtn, btnDeleteOffering;

	String sem, sy;
	DefaultComboBoxModel<String> cb = new DefaultComboBoxModel<String>();
	DefaultTableModel offeringModel;

	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	DefaultTableCellRenderer marginRenderer = new DefaultTableCellRenderer();
	JScrollPane scrollPane;
	JList<String> listBy;
	final JComboBox<String> comboBox;
	JButton btnViewTimetable;

	DefaultListModel<String> listModel = new DefaultListModel<String>();
	BlockSubjectsDAO b = new BlockSubjectsDAO();

	Offering offering = new Offering();
	Subject subject = new Subject();
	Room room = new Room();
	Faculty faculty = new Faculty();

	ArrayList<Offering> courselist;
	ArrayList<Subject> subjectlist;
	ArrayList<Offering> block_subjects, rooms, fac;

	Formatter format = new Formatter();

	String[] offerings_columns;
	Object[][] offerings_data;
	String facultyname, bname, sort;
	String[] name;

	String desc, timeslot, selected_fac, selected_room;
	double units;
	private JTable table;

	static Scheduler s;
	Statistics sv;
	
	public OfferingPanel() {
		setBounds(252, 73, 1002, 586);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(259, 68, 733, 460);
		scrollPane.getViewport().setBackground(Color.WHITE);

		JButton printBtn = new JButton("Print");
		printBtn.setBounds(903, 12, 89, 30);
		format.buttonFormat(printBtn, format.printIcon);
		printBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = "Schedule for SY " + sy + " " + sem + " Term";
				if (!comboBox.getSelectedItem().toString().equals("All"))
					title = "Schedule for " + comboBox.getSelectedItem().toString() + " " + listBy.getSelectedValue()
							+ " SY " + sy + " " + sem + " Term";
				TablePrinter t = new TablePrinter(title, table);
				t.setVisible(true);
			}
		});

		btnDeleteOffering = new JButton("Delete");
		btnDeleteOffering.setEnabled(false);
		btnDeleteOffering.setBounds(894, 539, 95, 30);
		format.buttonFormat(btnDeleteOffering, format.deleteIcon);
		btnDeleteOffering.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				offeringID = Integer.parseInt(table.getValueAt(i, 0).toString());
				// offeringModel.removeRow(i);
				// getTimeslot, getRoom
				// insert time/day and room to available timeslot
			}
		});

		editBtn = new JButton("Edit");
		editBtn.setBounds(783, 539, 95, 30);
		editBtn.setEnabled(false);
		format.buttonFormat(editBtn, format.updateIcon);
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				int i = table.getSelectedRow();
				offeringID = Integer.parseInt(table.getValueAt(i, 0).toString());
				HomeView.formPanel.setVisible(true);
				HomeView.formPanel.setFormData(i, offeringID, desc, units, timeslot, selected_room, selected_fac,
						slots);
				HomeView.formPanel.btnEditOffering.setVisible(true);
				HomeView.formPanel.displayAvailableTimeslots();
			}
		});

		JButton btnGenerateTimetable = new JButton("Generate Timetable");
		btnGenerateTimetable.setBounds(10, 12, 163, 30);
		format.buttonFormat(btnGenerateTimetable, format.imagesPath + "automate.png");
		btnGenerateTimetable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.removeAllElements();
				comboBox.setSelectedItem("All");

				showOption();
				sortList("All");
				setTableModel("All");
			}
		});

		JButton btnExport = new JButton("Export");
		btnExport.setBounds(783, 12, 104, 30);
		format.buttonFormat(btnExport, format.imagesPath + "excelicon.png");
		btnExport.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {
				ExportToExcel e = new ExportToExcel(OfferingPanel.this, table);
			}
		});

		listBy = new JList<String>();
		listBy.setBorder(new TitledBorder(null, "Block List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listBy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		listBy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listBy.setBounds(10, 104, 223, 424);

		add(listBy);
		listBy.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String sort = comboBox.getSelectedItem().toString();
				setTableModel(sort);
			}
		});

		setLayout(null);
		String[] sortBy = { "All", "Block", "Room", "Faculty" };
		for (String s : sortBy)
			cb.addElement(s);
		comboBox = new JComboBox<String>(cb);
		format.comboBoxFormat(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == comboBox) {
					listModel.removeAllElements();
					sort = comboBox.getSelectedItem().toString();
					sortList(sort);
				}
			}
		});
		comboBox.setBounds(10, 68, 125, 23);

		btnViewTimetable = new JButton("View Timetable");
		format.buttonFormat(btnViewTimetable, "");
		btnViewTimetable.setBounds(664, 12, 104, 30);
		btnViewTimetable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sched = comboBox.getSelectedItem().toString();
				ScheduleView s = null;
				if (sched.equals("Block")) {
					s = new ScheduleView("Block: " + listBy.getSelectedValue().toString() + " School Year: " + sy
							+ " Semester: " + sem, block_subjects);
				}
				if (sched.equals("Room")) {
					s = new ScheduleView("Room: " + listBy.getSelectedValue().toString() + "School Year: " + sy
							+ " Semester: " + sem, rooms);
				}
				if (sched.equals("Faculty")) {
					s = new ScheduleView("Name: " + listBy.getSelectedValue().toString() + " School Year: " + sy
							+ " Semester: " + sem, fac);
				}
				s.setVisible(true);
			}
		});

		JButton btnGraphColoring = new JButton("Graph Coloring");
		btnGraphColoring.setBounds(545, 12, 104, 30);
		format.buttonFormat(btnGraphColoring, "");
		btnGraphColoring.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GraphColoringView g = new GraphColoringView();
				g.setVisible(true);
				g.showOption();
			}
		});

		add(printBtn);
		add(scrollPane);
		add(btnDeleteOffering);
		add(editBtn);
		add(btnGenerateTimetable);
		add(btnExport);
		add(comboBox);
		add(btnViewTimetable);
		add(btnGraphColoring);
	}

	public void setTableModel(String sort) {
		btnViewTimetable.setEnabled(true);
		switch (sort) {
		case "Block":
			block_subjects = offering.offeringByBlock(listBy.getSelectedValue().toString());
			offerings_data = new Object[block_subjects.size()][];
			for (int i = 0; i < block_subjects.size(); i++) {
				offerings_data[i] = block_subjects.get(i).toTimetableArray();
			}
			break;
		case "Room":
			rooms = offering.getOfferings("room_desc = '" + listBy.getSelectedValue().toString() + "'");
			offerings_data = new Object[rooms.size()][];
			for (int i = 0; i < rooms.size(); i++) {
				offerings_data[i] = rooms.get(i).toTimetableArray();
			}
			break;
		case "Faculty":
			fac = offering
					.getOfferings("offerings.facID = '" + faculty.getFacID(listBy.getSelectedValue().toString()) + "'");
			offerings_data = new Object[fac.size()][];
			for (int i = 0; i < fac.size(); i++) {
				offerings_data[i] = fac.get(i).toTimetableArray();
			}
			break;
		case "All":
			courselist = new ArrayList<Offering>(offering.offeringsList());
			offerings_data = new Object[courselist.size()][];
			for (int i = 0; i < courselist.size(); i++) {
				offerings_data[i] = courselist.get(i).toTimetableArray();
			}
			btnViewTimetable.setEnabled(false);
			break;
		}
		offerings_columns = new String[] { "Id", "Description", "Units", "Time/Day", "Room", "Faculty", "Class Size" };
		tableComponents(offerings_data, offerings_columns);
		scrollPane.setViewportView(table);
	}

	public void addOfferingToTable(int offeringID, String sy, String sem, String start_time, String end_time,
			String day, String subject, String faculty, String slots, Object rm) {
		Object[] newOffering = new Object[offeringModel.getRowCount()];
		newOffering[0] = offeringID;
		newOffering[1] = sy;
		newOffering[2] = sem;
		newOffering[3] = start_time;
		newOffering[4] = end_time;
		newOffering[5] = day;
		newOffering[6] = subject;
		newOffering[7] = faculty;
		newOffering[8] = slots;
		newOffering[9] = rm;
		offeringModel.addRow(newOffering);
	}

	public void updateOfferingFromTable(int row, String timeslot, String faculty, String room) {
		offeringModel.setValueAt(timeslot, row, 3);
		offeringModel.setValueAt(room, row, 4);
		offeringModel.setValueAt(faculty, row, 5);
	}

	public void showOption() {
		JComboBox<String> syCB = new JComboBox<String>();
		syCB.setModel(new DefaultComboBoxModel<String>(new String[] { "2016-2017", "2017-2018", "2018-2019",
				"2019-2020", "2021-2022", "2022-2023", "2023-2024", "2024-2025", "2025-2026" }));
		JComboBox<String> semCB = new JComboBox<String>();
		semCB.setModel(new DefaultComboBoxModel<String>(new String[] { "1st", "2nd", "S" }));

		Object[] message = { "School Year: ", syCB, "Semester: ", semCB };

		int option = JOptionPane.showConfirmDialog(this, message, "Generate Offeing Schedule for: ",
				JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			HomeView.lblGeneratedTimetableFor.setVisible(true);
			HomeView.lblGeneratedTimetableFor.setText("Generated Schedule for SY " + syCB.getSelectedItem().toString()
					+ " " + semCB.getSelectedItem().toString() + " Semester");

			sem = semCB.getSelectedItem().toString();
			sy = syCB.getSelectedItem().toString();
			s = new Scheduler(syCB.getSelectedItem().toString(), semCB.getSelectedItem().toString());
			s.start();
			sv = new Statistics();
			sv.ids_T(s.ids_T());
			sv.ids_R(s.ids_R());
			sv.ids_F(s.ids_F());
			sv.time_exec_T(s.time_exec_T());
			sv.time_exec_R(s.time_exec_R());
			sv.time_exec_F(s.time_exec_F());
			sv.create();
			
			courselist = offering.offeringsList();
			JOptionPane.showMessageDialog(null,
					"Successfully Generated Offering Schedule. " 
							+ "\n\nTotal subjects scheduled: " + courselist.size() + "\n\nTimeslot Assignment: \t"
							+ s.timeslotAssignmentExec() + " s" + "\nRoom Assignment: \t" + s.roomAssignmentExec()
							+ " s" + "\nFaculty Assignment: \t" + s.facultyAssignmentExec() + " s"
							+ "\n\nTotal Time Execution: \t" + s.timeExecution() + " seconds",
					"Completed", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void sortList(String sort) {
		String sortBy = "";
		switch (sort) {
		case "Block":
			ArrayList<String> block_no = b.blockList();
			for (String b : block_no) {
				listModel.addElement(b);
			}
			listBy.setModel(listModel);
			sortBy = "Block List";
			break;
		case "Room":
			ArrayList<Room> rooms = room.roomList("All");
			for (Room r : rooms) {
				listModel.addElement(r.getName());
			}
			listBy.setModel(listModel);
			sortBy = "Room List";
			break;
		case "Faculty":
			ArrayList<Faculty> facs = faculty.facultyList();
			for (Faculty f : facs) {
				listModel.addElement(f.getName());
			}
			listBy.setModel(listModel);
			sortBy = "Faculty List";
			break;
		case "All":
			setTableModel("All");
			sortBy = "";
			break;
		}

		listBy.setBorder(new TitledBorder(null, sortBy, TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}

	public void tableComponents(Object[][] data, String[] columns) {
		offeringModel = new DefaultTableModel(data, offerings_columns) {
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		table = new JTable(offeringModel) {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width + 10;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				tableColumn.setPreferredWidth(
						Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
				return component;
			}
		};

		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		marginRenderer.setHorizontalAlignment(SwingConstants.LEADING);
		marginRenderer.setBorder(new EmptyBorder(new Insets(10, 10, 0, 0)));
		format.tableFormat(table);
		format.tableSorter(offeringModel, table);
		for (int col = 0; col < table.getColumnCount(); col++) {
			table.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
		}
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					editBtn.setEnabled(true);
					btnDeleteOffering.setEnabled(true);
					int i = table.getSelectedRow();
					offeringID = Integer.parseInt(table.getValueAt(i, 0).toString());

					if (offeringModel.getValueAt(i, 5) == null)
						selected_fac = "";
					else
						selected_fac = offeringModel.getValueAt(i, 5).toString();
					if (offeringModel.getValueAt(i, 4) == null)
						selected_room = "";
					else
						selected_room = offeringModel.getValueAt(i, 4).toString();

					desc = offeringModel.getValueAt(i, 1).toString();
					units = Double.parseDouble(offeringModel.getValueAt(i, 2).toString());
					timeslot = offeringModel.getValueAt(i, 3).toString();
					slots = Integer.parseInt(offeringModel.getValueAt(i, 6).toString());

				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});
	}
}

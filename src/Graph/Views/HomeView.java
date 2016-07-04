package Graph.Views;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import Graph.Controllers.WindowController;
import Graph.Models.Building;
import Graph.Models.ExportToExcel;
import Graph.Models.Faculty;
import Graph.Models.Offering;
import Graph.Models.Room;
import Graph.Models.Subject;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import java.awt.Insets;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import java.awt.Label;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
public class HomeView extends JFrame {

	private int offeringID, roomID, bID, subjectID, facultyID;
	private JPanel contentPane, formPanel, timeTablePanel, sidebarPanel, roomsPanel, subjectsPanel, facultyPanel,
			visualizationPanel;
	private JTable table, roomTable;
	private JTextField slotsField, roomField, capacityField;;
	private JComboBox syComboBox, semComboBox, subjectComboBox, facultyComboBox, roomComboBox, dayComboBox,
			buildingComboBox, typeComboBox, yrlvlComboBox, semCombobox, genderComboBox;
	private JButton addBtn, editBtn, btnAddOffering, btnEditOffering, btnDeleteOffering, btnAddRoom, btnUpdateRoom,
			btnDeleteRoom, btnViewRoomTimetable, btnAddSubject, btnUpdateSubject, btnDeleteSubject,
			btnViewSubjectTimetable, btnAddFaculty, btnUpdateFaculty, btnDeleteFaculty, btnViewFacultyTimetable;
	private JComboBox startPeriod, endPeriod;
	private JSpinner startHrSpinner, endHrSpinner, startMinSpinner, endMinSpinner;

	DefaultComboBoxModel<String> fac = new DefaultComboBoxModel<String>();
	DefaultComboBoxModel<String> sub = new DefaultComboBoxModel<String>();
	DefaultComboBoxModel<String> rm = new DefaultComboBoxModel<String>();
	DefaultTableModel offeringModel, roomModel, subjectModel, facultyModel;

	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	DefaultTableCellRenderer marginRenderer = new DefaultTableCellRenderer();
	TableRowSorter trs;

	Offering offering = new Offering();
	Subject subject = new Subject();
	Room room = new Room();
	Faculty faculty = new Faculty();

	ArrayList<Offering> courselist = offering.offeringsList();
	ArrayList<Subject> subjectlist = subject.subjectList();
	ArrayList<Room> roomlist = room.roomList();
	ArrayList<Faculty> facultylist = faculty.facultyList();

	WindowController w;

	String[] offerings_columns;
	Object[][] offerings_data;
	String facultyname, bname;
	String[] name;
	private JTable subjectTable;
	private JTextField descriptionField;
	private JTextField unitsField;

	final String imagesPath = "C:\\Users\\USER\\OneDrive\\workspace\\CSGC\\src\\resource\\images\\";
	final String newIcon = imagesPath + "new.png";
	final String addIcon = imagesPath + "plus.png";
	final String updateIcon = imagesPath + "saveupdate.png";
	final String deleteIcon = imagesPath + "delete.png";
	final String viewTimetableIcon = imagesPath + "calendar.png";
	final String printIcon = imagesPath + "printeicon.png";
	private JTable facultyTable;
	private JTextField fnameField;
	private JTextField mnameField;
	private JTextField lnameField;
	private JTextField bdayField;
	private JTextField phoneField;
	private JTextField addressField;
	private JTextField numSubjectsField;
	private JTextField numRoomsField;
	private JTextField numFacultyField;

	public HomeView() {
		contentPane = new JPanel();
		contentPane.setForeground(new Color(47, 79, 79));
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		components();

		setDefaultCloseOperation(0);
		setSize(1280, 740);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Home");
	}

	public void components() {
		headerComponents();
		footerComponents();
		sidebarComponents();
		timetableComponents();
		timetableFormComponents();
		roomsPanelComponents();
		subjectPanelComponents();
		facultyPanelComponents();
		visualizationPanelComponents();
	}

	public void headerComponents() {
		JLabel lblAbsked = new JLabel("ABSkeD");
		lblAbsked.setBackground(new Color(0, 0, 0));
		lblAbsked.setForeground(new Color(255, 255, 255));
		lblAbsked.setOpaque(true);
		lblAbsked.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbsked.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblAbsked.setBounds(0, 0, 242, 58);

		JLabel lblGeneratedTimetableFor = new JLabel("List of Offerings");
		lblGeneratedTimetableFor.setVisible(false);
		lblGeneratedTimetableFor.setBackground(new Color(46, 139, 87));
		lblGeneratedTimetableFor.setForeground(new Color(0, 0, 0));
		lblGeneratedTimetableFor.setOpaque(true);
		lblGeneratedTimetableFor.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblGeneratedTimetableFor.setBounds(316, 18, 378, 30);

		JButton logoutBtn = new JButton("");
		logoutBtn.setIcon(new ImageIcon(imagesPath + "logout.png"));
		logoutBtn.setBackground(new Color(46, 139, 87));
		logoutBtn.setOpaque(true);
		logoutBtn.setForeground(Color.WHITE);
		logoutBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login lg = new Login();
				lg.show();
			}
		});
		logoutBtn.setFont(new Font("Tahoma", Font.BOLD, 10));
		logoutBtn.setBorderPainted(false);
		logoutBtn.setFocusPainted(false);
		logoutBtn.setBounds(1197, 8, 40, 40);
		logoutBtn.setOpaque(true);

		Label headerlbl = new Label("");
		headerlbl.setBackground(new Color(46, 139, 87));
		headerlbl.setBounds(242, 0, 1020, 58);

		contentPane.add(lblAbsked);
		contentPane.add(lblGeneratedTimetableFor);
		contentPane.add(logoutBtn);
		contentPane.add(headerlbl);
	}

	public void footerComponents() {
		JLabel footerlbl = new JLabel("Copyright @ 2014  |  ABSkeD          ");
		footerlbl.setHorizontalAlignment(SwingConstants.TRAILING);
		footerlbl.setFont(new Font("SansSerif", Font.PLAIN, 10));
		footerlbl.setForeground(Color.WHITE);
		footerlbl.setBackground(Color.BLACK);
		footerlbl.setOpaque(true);
		footerlbl.setBounds(0, 670, 1263, 31);
		contentPane.add(footerlbl);
	}

	public void sidebarComponents() {
		sidebarPanel = new JPanel();
		sidebarPanel.setBackground(new Color(51, 51, 51));
		sidebarPanel.setBounds(0, 57, 242, 615);
		sidebarPanel.setBorder(new LineBorder(Color.black, 1));
		sidebarPanel.setLayout(null);

		JButton homeBtn = new JButton("   Home");
		sidebarButtonFormat(homeBtn, new ImageIcon(imagesPath + "homeicon.png"));
		homeBtn.setBounds(0, 0, 242, 40);
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeTablePanel.setVisible(true);
				formPanel.setVisible(false);
				roomsPanel.setVisible(false);
				subjectsPanel.setVisible(false);
				facultyPanel.setVisible(false);
				visualizationPanel.setVisible(false);
			}
		});

		JButton coursesBtn = new JButton("   Courses");
		sidebarButtonFormat(coursesBtn, new ImageIcon(imagesPath + "scholarly.png"));
		coursesBtn.setBounds(0, 38, 242, 40);
		coursesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeTablePanel.setVisible(false);
				formPanel.setVisible(false);
				roomsPanel.setVisible(false);
				subjectsPanel.setVisible(true);
				facultyPanel.setVisible(false);
				visualizationPanel.setVisible(false);
			}
		});

		JButton facultyBtn = new JButton("   Faculty");
		facultyBtn.setBounds(0, 76, 242, 40);
		sidebarButtonFormat(facultyBtn, new ImageIcon(imagesPath + "faculty.png"));
		facultyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeTablePanel.setVisible(false);
				formPanel.setVisible(false);
				roomsPanel.setVisible(false);
				subjectsPanel.setVisible(false);
				facultyPanel.setVisible(true);
				visualizationPanel.setVisible(false);
			}
		});

		JButton roomsBtn = new JButton("   Rooms");
		roomsBtn.setBounds(0, 114, 242, 40);
		sidebarButtonFormat(roomsBtn, new ImageIcon(imagesPath + "chair.png"));
		roomsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeTablePanel.setVisible(false);
				formPanel.setVisible(false);
				roomsPanel.setVisible(true);
				subjectsPanel.setVisible(false);
				facultyPanel.setVisible(false);
				visualizationPanel.setVisible(false);
			}
		});

		JButton visualizationBtn = new JButton("   Visualization");
		visualizationBtn.setBounds(0, 152, 242, 40);
		sidebarButtonFormat(visualizationBtn, new ImageIcon(imagesPath + "graph.png"));
		visualizationBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeTablePanel.setVisible(false);
				formPanel.setVisible(false);
				roomsPanel.setVisible(false);
				subjectsPanel.setVisible(false);
				facultyPanel.setVisible(false);
				visualizationPanel.setVisible(true);
				numSubjectsField.setText("" + subject.count());
				numRoomsField.setText("" + room.count());
				numFacultyField.setText("" + faculty.count());
			}
		});
		contentPane.add(sidebarPanel);
		sidebarPanel.add(homeBtn);
		sidebarPanel.add(coursesBtn);
		sidebarPanel.add(facultyBtn);
		sidebarPanel.add(roomsBtn);
		sidebarPanel.add(visualizationBtn);
	}

	public void sidebarButtonFormat(JButton button, ImageIcon icon) {
		button.setIcon(icon);
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("SansSerif", Font.PLAIN, 12));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(51, 51, 51));
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1),
				BorderFactory.createEmptyBorder(10, 20, 10, 10)));
	}

	public void timetableComponents() {
		timeTablePanel = new JPanel();
		timeTablePanel.setBounds(252, 73, 1002, 586);

		// headers for the table
		offerings_columns = new String[] { "Id", "SY", "Semester", "Start Time", "End Time", "Day", "Description",
				"Faculty", "Class Size", "Room" };

		// actual data for the table in a 2d array
		offerings_data = new Object[courselist.size()][];
		for (int i = 0; i < courselist.size(); i++) {
			offerings_data[i] = courselist.get(i).toTimetableArray();
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 982, 481);
		scrollPane.getViewport().setBackground(Color.WHITE);

		offeringModel = new DefaultTableModel(offerings_data, offerings_columns) {
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		marginRenderer.setHorizontalAlignment(SwingConstants.LEADING);
		marginRenderer.setBorder(new EmptyBorder(new Insets(10, 10, 0, 0)));

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
		tableFormat(table);
		tableSorter(offeringModel, table);
		for (int col = 0; col < table.getColumnCount(); col++) {
			if (col == 3 || col == 4 || col == 6 || col == 7)
				table.getColumnModel().getColumn(col).setCellRenderer(marginRenderer);

			else
				table.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
		}

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					int i = table.getSelectedRow();

					offeringID = Integer.parseInt(table.getValueAt(i, 0).toString());

					syComboBox.setSelectedItem(offeringModel.getValueAt(i, 1).toString());
					semComboBox.setSelectedItem(offeringModel.getValueAt(i, 2).toString());

					if (offeringModel.getValueAt(i, 3) == null) {
						startHrSpinner.setValue("");
						startMinSpinner.setValue("");
						startPeriod.setSelectedItem("");
					} else {
						String start_time = offeringModel.getValueAt(i, 3).toString();
						startHrSpinner.setValue(start_time.substring(0, 2));
						startMinSpinner.setValue(start_time.substring(3, 5));
						startPeriod.setSelectedItem(start_time.substring(6, 8));
					}
					if (offeringModel.getValueAt(i, 4) == null) {
						endHrSpinner.setValue("");
						endMinSpinner.setValue("");
						endPeriod.setSelectedItem("");
					} else {
						String end_time = offeringModel.getValueAt(i, 4).toString();
						endHrSpinner.setValue(end_time.substring(0, 2));
						endMinSpinner.setValue(end_time.substring(3, 5));
						endPeriod.setSelectedItem(end_time.substring(6, 8));
					}
					if (offeringModel.getValueAt(i, 5) == null)
						dayComboBox.setSelectedItem("");
					else {
						dayComboBox.setSelectedItem(offeringModel.getValueAt(i, 5).toString());
						subjectComboBox.setSelectedItem(offeringModel.getValueAt(i, 6).toString());
						facultyComboBox.setSelectedItem(offeringModel.getValueAt(i, 7).toString());
						slotsField.setText(offeringModel.getValueAt(i, 8).toString());
					}
					if (offeringModel.getValueAt(i, 9) == null)
						rm.setSelectedItem("");
					else
						rm.setSelectedItem(offeringModel.getValueAt(i, 9).toString());

				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});
		scrollPane.setViewportView(table);

		JButton printBtn = new JButton("Print");
		printBtn.setBounds(919, 11, 70, 25);
		buttonFormat(printBtn, new ImageIcon(printIcon));
		printBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {
				ExportToExcel e = new ExportToExcel(HomeView.this, table);
			}
		});

		btnDeleteOffering = new JButton("Delete");
		btnDeleteOffering.setBounds(894, 539, 95, 25);
		buttonFormat(btnDeleteOffering, new ImageIcon(deleteIcon));
		btnDeleteOffering.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				offeringID = Integer.parseInt(table.getValueAt(i, 0).toString());
				if (i >= 0) {
					offering.deleteOffering(offeringID);
					offering.deleteDaysched(offeringID);
					offeringModel.removeRow(i);
				} else {
					System.out.println("Delete Error");
				}
			}
		});

		addBtn = new JButton("Add Offering");
		addBtn.setBounds(20, 12, 107, 25);
		buttonFormat(addBtn, new ImageIcon(addIcon));
		addBtn.setBackground(new Color(240, 240, 240));
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeTablePanel.setVisible(false);
				formPanel.setVisible(true);
				btnAddOffering.setVisible(true);
				btnEditOffering.setVisible(false);

			}
		});

		editBtn = new JButton("Edit");
		editBtn.setBounds(814, 539, 70, 25);
		buttonFormat(editBtn, new ImageIcon(updateIcon));
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeTablePanel.setVisible(false);
				formPanel.setVisible(true);
				btnAddOffering.setVisible(false);
				btnEditOffering.setVisible(true);
			}
		});

		JButton btnGenerateTimetable = new JButton("Generate Timetable");
		btnGenerateTimetable.setBounds(145, 12, 149, 25);
		buttonFormat(btnGenerateTimetable, new ImageIcon(imagesPath + "automate.png"));

		JButton btnExport = new JButton("Export");
		btnExport.setBounds(814, 12, 95, 25);
		buttonFormat(btnExport, new ImageIcon(imagesPath + "excelicon.png"));
		btnExport.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {
				ExportToExcel e = new ExportToExcel(HomeView.this, table);
			}
		});

		contentPane.add(timeTablePanel);
		timeTablePanel.setLayout(null);
		timeTablePanel.add(printBtn);
		timeTablePanel.add(scrollPane);
		timeTablePanel.add(btnDeleteOffering);
		timeTablePanel.add(addBtn);
		timeTablePanel.add(editBtn);
		timeTablePanel.add(btnGenerateTimetable);
		timeTablePanel.add(btnExport);
	}

	public void timetableFormComponents() {
		formPanel = new JPanel();
		formPanel.setVisible(false);
		formPanel.setBackground(new Color(240, 240, 240));
		formPanel.setBounds(252, 72, 1002, 587);
		formPanel.setLayout(null);

		formPanelButtons();
		formPanelLabels();
		formPanelComboBoxes();

		String[] hr = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "1" };
		String[] min = { "00", "30" };

		startHrSpinner = new JSpinner();
		startHrSpinner.setBounds(409, 288, 40, 23);
		startHrSpinner.setModel(new SpinnerListModel(hr));

		startMinSpinner = new JSpinner();
		startMinSpinner.setBounds(458, 288, 40, 23);
		startMinSpinner.setModel(new SpinnerListModel(min));

		endMinSpinner = new JSpinner();
		endMinSpinner.setBounds(458, 328, 40, 23);
		endMinSpinner.setModel(new SpinnerListModel(min));

		endHrSpinner = new JSpinner();
		endHrSpinner.setBounds(409, 328, 40, 23);
		endHrSpinner.setModel(new SpinnerListModel(hr));

		slotsField = new JTextField();
		slotsField.setBounds(633, 247, 53, 23);
		slotsField.setFont(new Font("Arial", Font.PLAIN, 13));
		slotsField.setColumns(10);

		contentPane.add(formPanel);

		formPanel.add(startHrSpinner);
		formPanel.add(startMinSpinner);
		formPanel.add(endMinSpinner);
		formPanel.add(endHrSpinner);
		formPanel.add(slotsField);

	}

	public void formPanelButtons() {
		btnAddOffering = new JButton("Add");
		btnAddOffering.setBounds(409, 425, 80, 25);
		buttonFormat(btnAddOffering);
		btnAddOffering.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facultyname = facultyComboBox.getSelectedItem().toString();
				name = facultyname.split("\\s+");
				Object rm = null;
				if (roomComboBox.getSelectedItem().toString().equals(null)) {
					rm = null;
				} else
					rm = room.getID(roomComboBox.getSelectedItem().toString());

				String start_time = startHrSpinner.getValue() + ":" + startMinSpinner.getValue() + " "
						+ startPeriod.getSelectedItem().toString();
				String end_time = endHrSpinner.getValue() + ":" + endMinSpinner.getValue() + " "
						+ endPeriod.getSelectedItem().toString();
				String sy = syComboBox.getSelectedItem().toString();
				String sem = semComboBox.getSelectedItem().toString();
				String subj = subjectComboBox.getSelectedItem().toString();
				String slots = slotsField.getText();
				String day = dayComboBox.getSelectedItem().toString();
				offering.addOffering(faculty.getFacID(facultyname), rm, subject.getID(subj), sy, sem,
						Integer.parseInt(slots));
				offering.addDaySched(offering.getLastOfferno(), offering.getDaycode(day), to24hr(start_time),
						to24hr(end_time));
				addOfferingToTable(offering.getLastOfferno(), sy, sem, start_time, end_time, day, subj, facultyname,
						slots, rm);
			}
		});

		btnEditOffering = new JButton("Edit");
		btnEditOffering.setBounds(409, 425, 80, 25);
		buttonFormat(btnEditOffering);
		btnEditOffering.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facultyname = facultyComboBox.getSelectedItem().toString();
				String roomname = roomComboBox.getSelectedItem().toString();
				String start_time = startHrSpinner.getValue().toString() + ":" + startMinSpinner.getValue() + " "
						+ startPeriod.getSelectedItem();
				String end_time = endHrSpinner.getValue().toString() + ":" + endMinSpinner.getValue() + " "
						+ endPeriod.getSelectedItem();
				;

				int i = table.getSelectedRow();
				offeringID = Integer.parseInt(table.getValueAt(i, 0).toString());
				offeringModel.setValueAt(offeringID, i, 0);
				offeringModel.setValueAt(syComboBox.getSelectedItem(), i, 1);
				offeringModel.setValueAt(semComboBox.getSelectedItem(), i, 2);
				offeringModel.setValueAt(start_time, i, 3);
				offeringModel.setValueAt(end_time, i, 4);
				offeringModel.setValueAt(dayComboBox.getSelectedItem(), i, 5);
				offeringModel.setValueAt(subjectComboBox.getSelectedItem(), i, 6);
				offeringModel.setValueAt(facultyComboBox.getSelectedItem(), i, 7);
				offeringModel.setValueAt(slotsField.getText(), i, 8);
				offeringModel.setValueAt(roomComboBox.getSelectedItem(), i, 9);

				if (roomname.equals(null) || roomname.equals("null"))
					offering.editOffering(offeringID, faculty.getFacID(facultyname), "NULL",
							subject.getID(subjectComboBox.getSelectedItem().toString()),
							syComboBox.getSelectedItem().toString(), semComboBox.getSelectedItem().toString(),
							Integer.parseInt(slotsField.getText()));
				else
					offering.editOffering(offeringID, faculty.getFacID(facultyname), room.getID(roomname),
							subject.getID(subjectComboBox.getSelectedItem().toString()),
							syComboBox.getSelectedItem().toString(), semComboBox.getSelectedItem().toString(),
							Integer.parseInt(slotsField.getText()));
				String st = to24hr(start_time);
				String et = to24hr(end_time);
				offering.editDaySched(offeringID, offering.getDaycode(dayComboBox.getSelectedItem().toString()), st, et);
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(548, 426, 80, 25);
		buttonFormat(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formPanel.setVisible(false);
				timeTablePanel.setVisible(true);
			}
		});

		formPanel.add(btnAddOffering);
		formPanel.add(btnEditOffering);
		formPanel.add(btnCancel);
	}

	public void addOfferingToTable(int offeringID, String sy, String sem, String start_time, String end_time, String day,
			String subject, String faculty, String slots, Object rm) {
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

	public void formPanelLabels() {
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setBounds(335, 128, 60, 14);
		labelFormat(lblSubject);

		JLabel lblFaculty = new JLabel("Faculty");
		lblFaculty.setBounds(335, 169, 60, 14);
		labelFormat(lblFaculty);

		JLabel lblRoom = new JLabel("Room");
		lblRoom.setBounds(335, 209, 60, 14);
		labelFormat(lblRoom);

		JLabel lblDays = new JLabel("Day(s)");
		lblDays.setBounds(335, 251, 46, 14);
		labelFormat(lblDays);

		JLabel lblStartTime = new JLabel("Start Time");
		lblStartTime.setBounds(335, 292, 74, 14);
		labelFormat(lblStartTime);

		JLabel lblEndTime = new JLabel("End Time");
		lblEndTime.setBounds(335, 332, 74, 14);
		labelFormat(lblEndTime);

		JLabel lblSlots = new JLabel("Slots");
		lblSlots.setBounds(577, 251, 46, 14);
		labelFormat(lblSlots);

		JLabel lblSchoolYear = new JLabel("School Year");
		lblSchoolYear.setBounds(335, 95, 77, 14);
		labelFormat(lblSchoolYear);

		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setBounds(546, 95, 77, 14);
		labelFormat(lblSemester);

		formPanel.add(lblSchoolYear);
		formPanel.add(lblSemester);
		formPanel.add(lblSlots);
		formPanel.add(lblEndTime);
		formPanel.add(lblStartTime);
		formPanel.add(lblSubject);
		formPanel.add(lblRoom);
		formPanel.add(lblDays);
		formPanel.add(lblFaculty);
	}

	public void formPanelComboBoxes() {
		for (int i = 0; i < facultylist.size(); i++) {
			fac.addElement(facultylist.get(i).getName());
		}

		for (int i = 0; i < roomlist.size(); i++) {
			rm.addElement(roomlist.get(i).getName());
		}

		for (int i = 0; i < subjectlist.size(); i++) {
			sub.addElement(subjectlist.get(i).getSubject());
		}

		String[] sy = { "2015-2016", "2016-2017", "2017-2018" };
		String[] sem = { "1st", "2nd", "S" };
		String[] days = { "M", "Tu", "W", "Th", "F", "Sa", "MTh", "TuF", "WSa" };
		String[] period = { "AM", "PM" };

		semComboBox = new JComboBox(sem);
		semComboBox.setBounds(633, 91, 60, 23);
		comboBoxFormat(semComboBox);

		syComboBox = new JComboBox(sy);
		syComboBox.setBounds(421, 91, 111, 23);
		comboBoxFormat(syComboBox);

		subjectComboBox = new JComboBox<String>(sub);
		subjectComboBox.setBounds(409, 124, 284, 23);
		comboBoxFormat(subjectComboBox);

		facultyComboBox = new JComboBox<String>(fac);
		facultyComboBox.setBounds(409, 165, 284, 23);
		comboBoxFormat(facultyComboBox);

		roomComboBox = new JComboBox(rm);
		roomComboBox.setBounds(409, 205, 284, 23);
		comboBoxFormat(roomComboBox);

		dayComboBox = new JComboBox(days);
		dayComboBox.setBounds(409, 247, 89, 23);
		comboBoxFormat(dayComboBox);

		startPeriod = new JComboBox(period);
		startPeriod.setBounds(508, 288, 46, 23);
		startPeriod.setModel(new DefaultComboBoxModel(period));

		endPeriod = new JComboBox();
		endPeriod.setBounds(508, 328, 46, 23);
		endPeriod.setModel(new DefaultComboBoxModel(period));

		formPanel.add(semComboBox);
		formPanel.add(syComboBox);
		formPanel.add(facultyComboBox);
		formPanel.add(roomComboBox);
		formPanel.add(dayComboBox);
		formPanel.add(subjectComboBox);
		formPanel.add(startPeriod);
		formPanel.add(endPeriod);
	}

	public void labelFormat(JLabel label) {
		label.setBackground(new Color(240, 240, 240));
		label.setForeground(Color.BLACK);
		label.setOpaque(true);
		label.setFont(new Font("SansSerif", Font.PLAIN, 11));
	}

	public void roomsPanelComponents() {
		Building b = new Building();
		DefaultComboBoxModel<String> cm = new DefaultComboBoxModel<String>();
		ArrayList<Building> blist = b.buildingList();

		String[] room_columns = new String[] { "Id", "Building", "Name", "Type", "Capacity" };

		Object[][] room_data = new Object[roomlist.size()][];
		for (int i = 0; i < roomlist.size(); i++) {
			room_data[i] = roomlist.get(i).toObjectArray();
		}
		for (int i = 0; i < blist.size(); i++) {
			cm.addElement(blist.get(i).getBname());
		}
		roomModel = new DefaultTableModel(room_data, room_columns) {
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		String[] type = { "Laboratory", "Lecture" };

		roomsPanel = new JPanel();
		roomsPanel.setBounds(253, 73, 1001, 586);
		roomsPanel.setLayout(null);
		roomsPanel.setVisible(false);

		btnAddRoom = new JButton("Add");
		btnAddRoom.setEnabled(false);
		buttonFormat(btnAddRoom, new ImageIcon(addIcon));
		btnAddRoom.setBounds(590, 493, 89, 25);
		btnAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String building = buildingComboBox.getSelectedItem().toString();
				bID = b.getID(building);
				int roomID = room.getID(roomField.getText());
				String roomname = roomField.getText();
				String type = typeComboBox.getSelectedItem().toString();
				String capacity = capacityField.getText();
				room.addRoom(roomname, bID, type, Integer.parseInt(capacity));
				addRoomToTable(roomID, roomname, building, type, capacity);
			}
		});

		btnDeleteRoom = new JButton("Delete");
		btnDeleteRoom.setEnabled(false);
		buttonFormat(btnDeleteRoom, new ImageIcon(deleteIcon));
		btnDeleteRoom.setBounds(883, 493, 89, 25);
		btnDeleteRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = roomTable.getSelectedRow();
				roomID = Integer.parseInt(roomTable.getValueAt(i, 0).toString());
				if (i >= 0) {
					room.deleteRoom(roomID);
					roomModel.removeRow(i);
					clearRoomFormFields();
				} else {
					System.out.println("Delete Error");
				}
			}
		});

		btnViewRoomTimetable = new JButton("View Room Timetable");
		btnViewRoomTimetable.setEnabled(false);
		buttonFormat(btnViewRoomTimetable, new ImageIcon(viewTimetableIcon));
		btnViewRoomTimetable.setBounds(810, 18, 155, 25);
		btnViewRoomTimetable.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				System.out.println(roomID);
				ScheduleView sv = new ScheduleView(roomID, "room");
				sv.show();
			}
		});

		btnUpdateRoom = new JButton("Update ");
		btnUpdateRoom.setEnabled(false);
		btnUpdateRoom.setBounds(737, 493, 96, 25);
		buttonFormat(btnUpdateRoom, new ImageIcon(updateIcon));
		btnUpdateRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = roomTable.getSelectedRow();
				roomID = Integer.parseInt(roomTable.getValueAt(i, 0).toString());
				roomModel.setValueAt(roomID, i, 0);
				roomModel.setValueAt(cm.getSelectedItem(), i, 1);
				roomModel.setValueAt(roomField.getText(), i, 2);
				roomModel.setValueAt(capacityField.getText(), i, 4);
				roomModel.setValueAt(typeComboBox.getSelectedItem(), i, 3);
				bID = b.getID(cm.getSelectedItem().toString());
				room.editRoom(roomID, roomField.getText(), bID, typeComboBox.getSelectedItem().toString(),
						Integer.parseInt(capacityField.getText()));
			}
		});

		JButton btnNewRoom = new JButton("New");
		buttonFormat(btnNewRoom, new ImageIcon(newIcon));
		btnNewRoom.setBounds(30, 18, 89, 25);
		btnNewRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearRoomFormFields();
			}
		});

		roomTable = new JTable(roomModel) {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width + 10;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				tableColumn.setPreferredWidth(
						Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
				return component;
			}
		};
		;
		tableFormat(roomTable);
		roomTable.setBounds(26, 92, 446, 462);

		JScrollPane roomListScrollPane = new JScrollPane();
		roomListScrollPane.setBounds(20, 59, 516, 457);
		roomListScrollPane.setViewportView(roomTable);
		roomListScrollPane.getViewport().setBackground(Color.WHITE);

		roomTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int i = roomTable.getSelectedRow();
				btnAddRoom.setEnabled(false);
				btnUpdateRoom.setEnabled(true);
				btnDeleteRoom.setEnabled(true);
				btnViewRoomTimetable.setEnabled(true);
				roomID = Integer.parseInt(roomModel.getValueAt(i, 0).toString());
				bname = roomModel.getValueAt(i, 1).toString();
				cm.setSelectedItem(bname);
				roomField.setText(roomModel.getValueAt(i, 2).toString());
				typeComboBox.setSelectedItem(roomModel.getValueAt(i, 3).toString());
				capacityField.setText(roomModel.getValueAt(i, 4).toString());
			}
		});

		JLabel lblRoomList = new JLabel("Room List");
		lblRoomList.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoomList.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblRoomList.setBounds(224, 21, 111, 14);

		JPanel roomFormPanel = new JPanel();
		roomFormPanel.setBounds(590, 59, 382, 423);
		roomFormPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		roomFormPanel.setLayout(null);

		JLabel lblBuilding = new JLabel("Building");
		lblBuilding.setBounds(25, 68, 55, 14);
		labelFormat(lblBuilding);

		JLabel lblRoomName = new JLabel("Room Name");
		lblRoomName.setBounds(25, 113, 70, 14);
		labelFormat(lblRoomName);

		JLabel lblType = new JLabel("Type");
		lblType.setBounds(25, 154, 46, 14);
		labelFormat(lblType);

		JLabel lblCapacity = new JLabel("Capacity");
		lblCapacity.setBounds(25, 193, 46, 14);
		labelFormat(lblCapacity);

		JLabel lblRoomInformationDetails = new JLabel("Room Information Details");
		lblRoomInformationDetails.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblRoomInformationDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoomInformationDetails.setBounds(89, 26, 216, 14);

		buildingComboBox = new JComboBox(cm);
		buildingComboBox.setBounds(115, 65, 244, 23);
		comboBoxFormat(buildingComboBox);

		roomField = new JTextField();
		roomField.setBounds(115, 109, 244, 23);
		roomField.setColumns(10);

		typeComboBox = new JComboBox<Object>(type);
		typeComboBox.setSelectedIndex(-1);
		comboBoxFormat(typeComboBox);
		typeComboBox.setBounds(115, 150, 182, 23);

		capacityField = new JTextField();
		capacityField.setHorizontalAlignment(SwingConstants.TRAILING);
		capacityField.setBounds(115, 189, 86, 23);
		capacityField.setColumns(10);

		contentPane.add(roomsPanel);
		roomsPanel.add(lblRoomList);
		roomsPanel.add(btnAddRoom);
		roomsPanel.add(btnNewRoom);
		roomsPanel.add(btnDeleteRoom);
		roomsPanel.add(roomListScrollPane);
		roomsPanel.add(btnViewRoomTimetable);
		roomsPanel.add(btnUpdateRoom);
		roomsPanel.add(roomFormPanel);
		roomFormPanel.add(lblBuilding);
		roomFormPanel.add(lblRoomName);
		roomFormPanel.add(lblType);
		roomFormPanel.add(lblCapacity);
		roomFormPanel.add(lblRoomInformationDetails);
		roomFormPanel.add(buildingComboBox);
		roomFormPanel.add(roomField);
		roomFormPanel.add(typeComboBox);
		roomFormPanel.add(capacityField);
	}

	public void addRoomToTable(int roomID, String room, String building, String type, String capacity) {
		Object[] newRoom = new Object[roomTable.getRowCount()];
		newRoom[0] = roomID;
		newRoom[1] = room;
		newRoom[2] = building;
		newRoom[3] = type;
		newRoom[4] = capacity;
		roomModel.addRow(newRoom);
	}

	public void clearRoomFormFields() {
		buildingComboBox.setSelectedIndex(-1);
		roomField.setText("");
		typeComboBox.setSelectedIndex(-1);
		capacityField.setText("");
		btnAddRoom.setEnabled(true);
		btnUpdateRoom.setEnabled(false);
		btnDeleteRoom.setEnabled(false);
		btnViewRoomTimetable.setEnabled(false);
	}

	public void subjectPanelComponents() {
		Object[][] subject_data = new Object[subjectlist.size()][];
		for (int i = 0; i < subjectlist.size(); i++) {
			subject_data[i] = subjectlist.get(i).toObjectArray();
		}
		String[] subject_columns = new String[] { "Id", "Decsription", "Units", "Yr Level", "Semester" };
		subjectModel = new DefaultTableModel(subject_data, subject_columns) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		String[] sem = { "1st", "2nd", "S" };

		String[] yr = { "1", "2", "3", "4" };

		subjectsPanel = new JPanel();
		subjectsPanel.setBounds(253, 73, 1001, 586);
		subjectsPanel.setLayout(null);
		subjectsPanel.setVisible(false);

		btnAddSubject = new JButton("Add");
		btnAddSubject.setEnabled(false);
		buttonFormat(btnAddSubject, new ImageIcon(addIcon));
		btnAddSubject.setBounds(614, 493, 89, 25);
		btnAddSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				subject.addSubject(descriptionField.getText(), Double.parseDouble(unitsField.getText()),
						Integer.parseInt(yrlvlComboBox.getSelectedItem().toString()), semCombobox.getSelectedItem());
			}
		});

		btnUpdateSubject = new JButton("Update");
		btnUpdateSubject.setEnabled(false);
		buttonFormat(btnUpdateSubject, new ImageIcon(updateIcon));
		btnUpdateSubject.setBounds(747, 493, 93, 25);
		btnUpdateSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = subjectTable.getSelectedRow();
				subjectID = Integer.parseInt(subjectTable.getValueAt(i, 0).toString());
				subjectModel.setValueAt(subjectID, i, 0);
				subjectModel.setValueAt(descriptionField.getText(), i, 1);
				subjectModel.setValueAt(unitsField.getText(), i, 2);
				subjectModel.setValueAt(yrlvlComboBox.getSelectedItem(), i, 3);
				subjectModel.setValueAt(semCombobox.getSelectedItem(), i, 4);
				subject.editSubject(subjectID, descriptionField.getText(), Double.parseDouble(unitsField.getText()),
						Integer.parseInt(yrlvlComboBox.getSelectedItem().toString()), semCombobox.getSelectedItem());
			}
		});

		btnDeleteSubject = new JButton("Delete");
		buttonFormat(btnDeleteSubject, new ImageIcon(deleteIcon));
		btnDeleteSubject.setBounds(881, 493, 93, 25);
		btnDeleteSubject.setEnabled(false);
		btnDeleteSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = subjectTable.getSelectedRow();
				if (i >= 0) {
					subject.deleteSubject(subjectID);
					subjectModel.removeRow(i);
					clearSubjectFormFields();
				} else {
					System.out.println("Delete Error");
				}
			}
		});

		btnViewSubjectTimetable = new JButton("View Subject Timetable");
		buttonFormat(btnViewSubjectTimetable, new ImageIcon(viewTimetableIcon));
		btnViewSubjectTimetable.setEnabled(false);
		btnViewSubjectTimetable.setBounds(810, 18, 165, 25);

		JButton btnNewSubject = new JButton("New");
		btnNewSubject.setBounds(30, 18, 89, 25);
		buttonFormat(btnNewSubject, new ImageIcon(newIcon));
		btnNewSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearSubjectFormFields();
			}
		});

		JScrollPane subjectsScrollPane = new JScrollPane();
		subjectsScrollPane.setBounds(20, 59, 534, 457);
		subjectsScrollPane.getViewport().setBackground(Color.WHITE);

		subjectTable = new JTable(subjectModel) {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width + 10;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				tableColumn.setPreferredWidth(
						Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
				return component;
			}
		};
		subjectsScrollPane.setViewportView(subjectTable);

		tableFormat(subjectTable);
		subjectTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int i = subjectTable.getSelectedRow();
				btnUpdateSubject.setEnabled(true);
				btnAddSubject.setEnabled(false);
				btnDeleteSubject.setEnabled(true);
				btnViewSubjectTimetable.setEnabled(true);
				subjectID = Integer.parseInt(subjectTable.getValueAt(i, 0).toString());
				descriptionField.setText(subjectModel.getValueAt(i, 1).toString());
				unitsField.setText(subjectModel.getValueAt(i, 2).toString());
				yrlvlComboBox.setSelectedItem(subjectModel.getValueAt(i, 3).toString());
				semCombobox.setSelectedItem(subjectModel.getValueAt(i, 4).toString());
			}
		});

		JLabel lblSubjectList = new JLabel("Subject List");
		lblSubjectList.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubjectList.setBounds(251, 18, 81, 14);

		JPanel subjectFormPanel = new JPanel();
		subjectFormPanel.setBounds(604, 59, 370, 423);
		subjectFormPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		subjectFormPanel.setLayout(null);

		JLabel lblSubjectInformationDetails = new JLabel("Subject Information Details");
		lblSubjectInformationDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubjectInformationDetails.setBounds(101, 26, 175, 14);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(31, 68, 71, 14);
		labelFormat(lblDescription);

		JLabel lblUnits = new JLabel("Units");
		lblUnits.setBounds(31, 113, 46, 14);
		labelFormat(lblUnits);

		JLabel lblYearLevel = new JLabel("Year Level");
		lblYearLevel.setBounds(31, 158, 71, 14);
		labelFormat(lblYearLevel);

		JLabel lblSubjectSemester = new JLabel("Semester");
		lblSubjectSemester.setBounds(31, 204, 71, 14);
		labelFormat(lblSubjectSemester);

		descriptionField = new JTextField();
		descriptionField.setBounds(107, 65, 230, 20);
		descriptionField.setColumns(10);

		unitsField = new JTextField();
		unitsField.setBounds(107, 111, 86, 20);
		unitsField.setColumns(10);

		semCombobox = new JComboBox(sem);
		semCombobox.setBounds(107, 201, 86, 20);
		comboBoxFormat(semCombobox);

		yrlvlComboBox = new JComboBox(yr);
		comboBoxFormat(yrlvlComboBox);
		yrlvlComboBox.setBounds(107, 155, 86, 20);

		contentPane.add(subjectsPanel);
		subjectsPanel.add(subjectsScrollPane);
		subjectsPanel.add(btnAddSubject);
		subjectsPanel.add(btnNewSubject);
		subjectsPanel.add(btnUpdateSubject);
		subjectsPanel.add(btnDeleteSubject);
		subjectsPanel.add(btnViewSubjectTimetable);
		subjectsPanel.add(lblSubjectList);
		subjectsPanel.add(subjectFormPanel);
		subjectFormPanel.add(lblSubjectInformationDetails);
		subjectFormPanel.add(lblDescription);
		subjectFormPanel.add(lblUnits);
		subjectFormPanel.add(lblYearLevel);
		subjectFormPanel.add(lblSubjectSemester);
		subjectFormPanel.add(descriptionField);
		subjectFormPanel.add(unitsField);
		subjectFormPanel.add(semCombobox);
		subjectFormPanel.add(yrlvlComboBox);
	}

	public void clearSubjectFormFields() {
		btnAddSubject.setEnabled(true);
		btnDeleteSubject.setEnabled(false);
		btnUpdateSubject.setEnabled(false);
		btnViewSubjectTimetable.setEnabled(false);
		descriptionField.setText("");
		unitsField.setText("");
		yrlvlComboBox.setSelectedIndex(-1);
		semCombobox.setSelectedIndex(-1);
	}

	public void facultyPanelComponents() {
		String[] faculty_columns = new String[] { "Id", "Name", "Gender", "Bday", "Phone", "Address" };

		Object[][] faculty_data = new Object[facultylist.size()][];
		for (int i = 0; i < facultylist.size(); i++) {
			faculty_data[i] = facultylist.get(i).toObjectArray();
		}

		facultyModel = new DefaultTableModel(faculty_data, faculty_columns) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		String[] gender = { "Male", "Female" };

		facultyPanel = new JPanel();
		facultyPanel.setBounds(253, 73, 1001, 586);
		facultyPanel.setVisible(false);
		facultyPanel.setLayout(null);

		JPanel facultyFormPanel = new JPanel();
		facultyFormPanel.setBounds(593, 59, 384, 423);
		facultyFormPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		facultyFormPanel.setLayout(null);

		JLabel lblFacultyInformationDetails = new JLabel("Faculty Information Details");
		lblFacultyInformationDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblFacultyInformationDetails.setBounds(96, 26, 201, 14);

		JLabel lblFname = new JLabel("Firstname");
		lblFname.setBounds(37, 68, 75, 14);
		labelFormat(lblFname);

		JLabel lblMiddlename = new JLabel("Middlename");
		lblMiddlename.setBounds(37, 113, 61, 14);
		labelFormat(lblMiddlename);

		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setBounds(37, 158, 46, 14);
		labelFormat(lblLastname);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(37, 203, 46, 14);
		labelFormat(lblGender);

		JLabel lblBirthday = new JLabel("Birthday");
		lblBirthday.setBounds(37, 248, 46, 14);
		labelFormat(lblBirthday);

		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(37, 293, 46, 14);
		labelFormat(lblPhone);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(37, 338, 46, 14);
		labelFormat(lblAddress);

		fnameField = new JTextField();
		fnameField.setBounds(108, 65, 235, 20);
		fnameField.setColumns(10);

		mnameField = new JTextField();
		mnameField.setBounds(108, 110, 235, 20);
		facultyPanel.add(facultyFormPanel);
		mnameField.setColumns(10);

		lnameField = new JTextField();
		lnameField.setBounds(108, 155, 235, 20);
		lnameField.setColumns(10);

		genderComboBox = new JComboBox(gender);
		comboBoxFormat(genderComboBox);
		genderComboBox.setBounds(108, 200, 110, 20);

		bdayField = new JTextField();
		bdayField.setBounds(108, 245, 110, 20);
		bdayField.setColumns(10);

		phoneField = new JTextField();
		phoneField.setBounds(108, 290, 235, 20);
		phoneField.setColumns(10);

		addressField = new JTextField();
		addressField.setBounds(108, 335, 235, 20);
		addressField.setColumns(10);

		btnAddFaculty = new JButton("Add");
		btnAddFaculty.setEnabled(false);
		buttonFormat(btnAddFaculty, new ImageIcon(addIcon));
		btnAddFaculty.setBounds(602, 491, 89, 25);
		btnAddFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				faculty.addFaculty(fnameField.getText(), mnameField.getText(), lnameField.getText(),
						genderComboBox.getSelectedItem().toString(), bdayField.getText(), phoneField.getText(),
						addressField.getText());
			}
		});

		btnUpdateFaculty = new JButton("Update");
		btnUpdateFaculty.setEnabled(false);
		buttonFormat(btnUpdateFaculty, new ImageIcon(updateIcon));
		btnUpdateFaculty.setBounds(745, 491, 89, 25);
		btnUpdateFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = facultyTable.getSelectedRow();
				facultyID = Integer.parseInt(facultyTable.getValueAt(i, 0).toString());
				facultyModel.setValueAt(facultyID, i, 0);
				facultyModel.setValueAt(fnameField.getText() + " " + mnameField.getText() + " " + lnameField.getText(),
						i, 1);
				facultyModel.setValueAt(genderComboBox.getSelectedItem().toString(), i, 2);
				facultyModel.setValueAt(bdayField.getText(), i, 3);
				facultyModel.setValueAt(phoneField.getText(), i, 4);
				facultyModel.setValueAt(addressField.getText(), i, 5);
				faculty.editFaculty(facultyID, fnameField.getText(), mnameField.getText(), lnameField.getText(),
						genderComboBox.getSelectedItem().toString(), bdayField.getText(), phoneField.getText(),
						addressField.getText());
			}
		});

		btnDeleteFaculty = new JButton("Delete");
		btnDeleteFaculty.setEnabled(false);
		buttonFormat(btnDeleteFaculty, new ImageIcon(deleteIcon));
		btnDeleteFaculty.setBounds(888, 491, 89, 25);
		btnDeleteFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = facultyTable.getSelectedRow();
				if (i >= 0) {
					faculty.deleteFaculty(facultyID);
					facultyModel.removeRow(i);
					clearFacultyFormFields();
				} else {
					System.out.println("Delete Error");
				}
			}
		});

		btnViewFacultyTimetable = new JButton("View Faculty Timetable");
		btnViewFacultyTimetable.setEnabled(false);
		buttonFormat(btnViewFacultyTimetable, new ImageIcon(viewTimetableIcon));
		btnViewFacultyTimetable.setBounds(810, 19, 163, 25);

		JButton btnNewFaculty = new JButton("New");
		buttonFormat(btnNewFaculty, new ImageIcon(newIcon));
		btnNewFaculty.setBounds(30, 18, 89, 25);
		btnNewFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFacultyFormFields();
			}
		});

		JScrollPane facultyScrollPane = new JScrollPane();
		facultyScrollPane.setBounds(20, 59, 552, 457);
		facultyScrollPane.getViewport().setBackground(Color.WHITE);

		facultyTable = new JTable(facultyModel) {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width + 10;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				tableColumn.setPreferredWidth(
						Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
				return component;
			}
		};
		facultyScrollPane.setViewportView(facultyTable);

		tableFormat(facultyTable);
		facultyTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int i = facultyTable.getSelectedRow();
				btnAddFaculty.setEnabled(false);
				btnUpdateFaculty.setEnabled(true);
				btnDeleteFaculty.setEnabled(true);
				btnViewFacultyTimetable.setEnabled(true);
				facultyID = Integer.parseInt(facultyModel.getValueAt(i, 0).toString());
				fnameField.setText(facultylist.get(i).getFname());
				mnameField.setText(facultylist.get(i).getMname());
				lnameField.setText(facultylist.get(i).getLname());
				genderComboBox.setSelectedItem(facultyModel.getValueAt(i, 2).toString());
				if (facultyModel.getValueAt(i, 3) == null)
					bdayField.setText("");
				else
					bdayField.setText(facultyModel.getValueAt(i, 3).toString());
				if (facultyModel.getValueAt(i, 4) == null)
					phoneField.setText("");
				else
					phoneField.setText(facultyModel.getValueAt(i, 4).toString());
				if (facultyModel.getValueAt(i, 5) == null)
					addressField.setText("");
				else
					addressField.setText(facultyModel.getValueAt(i, 5).toString());
			}
		});

		JLabel lblFacultyList = new JLabel("Faculty List");
		lblFacultyList.setHorizontalAlignment(SwingConstants.CENTER);
		lblFacultyList.setBounds(249, 23, 89, 14);

		contentPane.add(facultyPanel);
		facultyFormPanel.add(lblFacultyInformationDetails);
		facultyFormPanel.add(lblFname);
		facultyFormPanel.add(lblMiddlename);
		facultyFormPanel.add(lblLastname);
		facultyFormPanel.add(lblGender);
		facultyFormPanel.add(lblBirthday);
		facultyFormPanel.add(lblPhone);
		facultyFormPanel.add(lblAddress);
		facultyFormPanel.add(fnameField);
		facultyFormPanel.add(mnameField);
		facultyFormPanel.add(lnameField);
		facultyFormPanel.add(genderComboBox);
		facultyFormPanel.add(bdayField);
		facultyFormPanel.add(phoneField);
		facultyFormPanel.add(addressField);
		facultyPanel.add(btnAddFaculty);
		facultyPanel.add(btnUpdateFaculty);
		facultyPanel.add(btnDeleteFaculty);
		facultyPanel.add(btnViewFacultyTimetable);
		facultyPanel.add(btnNewFaculty);
		facultyPanel.add(facultyScrollPane);
		facultyPanel.add(lblFacultyList);
	}

	public void visualizationPanelComponents() {
		visualizationPanel = new JPanel();
		visualizationPanel.setVisible(false);
		visualizationPanel.setBackground(Color.WHITE);
		visualizationPanel.setBounds(252, 72, 1002, 587);
		visualizationPanel.setLayout(null);

		JPanel graphPanel = new JPanel();
		graphPanel.setBounds(10, 11, 982, 394);

		JButton btnVisualize = new JButton("Visualize");
		btnVisualize.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnVisualize.setBounds(819, 416, 173, 30);
		btnVisualize.setIcon(new ImageIcon(imagesPath + "visualize.png"));
		
		JTextArea logArea = new JTextArea();
		logArea.setLineWrap(true);
		logArea.setBackground(new Color(204, 255, 204));
		logArea.setColumns(2);
		logArea.setEditable(false);
		logArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		logArea.setBounds(10, 416, 799, 160);
		
		numSubjectsField = new JTextField();
		numSubjectsField.setHorizontalAlignment(SwingConstants.RIGHT);
		numSubjectsField.setBackground(Color.WHITE);
		numSubjectsField.setEditable(false);
		numSubjectsField.setBounds(934, 467, 58, 25);
		numSubjectsField.setColumns(10);

		numRoomsField = new JTextField();
		numRoomsField.setHorizontalAlignment(SwingConstants.RIGHT);
		numRoomsField.setBackground(Color.WHITE);
		numRoomsField.setEditable(false);
		numRoomsField.setBounds(934, 503, 58, 25);
		numRoomsField.setColumns(10);

		numFacultyField = new JTextField();
		numFacultyField.setHorizontalAlignment(SwingConstants.RIGHT);
		numFacultyField.setBackground(Color.WHITE);
		numFacultyField.setEditable(false);
		numFacultyField.setBounds(934, 539, 58, 25);
		numFacultyField.setColumns(10);

		JLabel lblOfSubjects = new JLabel("# of Subjects:");
		labelFormat(lblOfSubjects);
		lblOfSubjects.setBounds(819, 472, 105, 14);
		
		JLabel lblOfRooms = new JLabel("# of Rooms:");
		labelFormat(lblOfRooms);
		lblOfRooms.setBounds(819, 508, 105, 14);
		
		JLabel lblOfFaculty = new JLabel("# of Faculty:");
		labelFormat(lblOfFaculty);
		lblOfFaculty.setBounds(819, 544, 105, 14);
		
		contentPane.add(visualizationPanel);
		visualizationPanel.add(graphPanel);
		visualizationPanel.add(btnVisualize);
		visualizationPanel.add(logArea);
		visualizationPanel.add(numSubjectsField);
		visualizationPanel.add(numRoomsField);
		visualizationPanel.add(numFacultyField);
		visualizationPanel.add(lblOfSubjects);
		visualizationPanel.add(lblOfRooms);
		visualizationPanel.add(lblOfFaculty);
	}

	public void clearFacultyFormFields() {
		fnameField.setText("");
		mnameField.setText("");
		lnameField.setText("");
		genderComboBox.setSelectedIndex(-1);
		bdayField.setText("");
		phoneField.setText("");
		addressField.setText("");
		btnAddFaculty.setEnabled(true);
		btnUpdateFaculty.setEnabled(false);
		btnDeleteFaculty.setEnabled(false);
		btnViewFacultyTimetable.setEnabled(false);
	}

	public void comboBoxFormat(JComboBox cb) {
		cb.setForeground(new Color(47, 79, 79));
		cb.setFont(new Font("Arial", Font.PLAIN, 13));
	}

	public void buttonFormat(JButton button) {
		button.setFont(new Font("SansSerif", Font.PLAIN, 12));
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBackground(Color.BLACK);
	}

	public void buttonFormat(JButton button, ImageIcon icon) {
		button.setIcon(icon);
		button.setFont(new Font("SansSerif", Font.PLAIN, 12));
		button.setForeground(Color.BLACK);
		button.setFocusPainted(false);
		button.setOpaque(true);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setBackground(new Color(240, 240, 240));
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
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

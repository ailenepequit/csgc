package Graph.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.table.DefaultTableCellRenderer;
import Graph.Models.Faculty;
import Graph.Models.Offering;
import Graph.Models.Room;
import Graph.Models.Subject;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class OfferingFormPanel extends JPanel {

	private int row, offeringID;
	private JPanel panel;
	JTextField slotsField;
	JComboBox syComboBox, semComboBox, subjectComboBox, facultyComboBox, roomComboBox, dayComboBox, startPeriod,
			endPeriod;
	JButton btnAddOffering, btnEditOffering;
	JSpinner startHrSpinner, endHrSpinner, startMinSpinner, endMinSpinner;
	JLabel lblOffering;

	DefaultComboBoxModel<String> fac = new DefaultComboBoxModel<String>();
	DefaultComboBoxModel<String> sub = new DefaultComboBoxModel<String>();
	DefaultComboBoxModel<String> rm = new DefaultComboBoxModel<String>();

	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	DefaultTableCellRenderer marginRenderer = new DefaultTableCellRenderer();

	Offering offering = new Offering();
	Subject subject = new Subject();
	Room room = new Room();
	Faculty faculty = new Faculty();

	ArrayList<Offering> courselist = offering.offeringsList();
	ArrayList<Subject> subjectlist = subject.subjectList("All");
	ArrayList<Room> roomlist = room.roomList("All");
	ArrayList<Faculty> facultylist = faculty.facultyList();

	Formatter format = new Formatter();

	String[] offerings_columns;
	Object[][] offerings_data;
	String facultyname, bname;
	String[] name;

	public OfferingFormPanel() {
		setVisible(false);
		setBackground(Color.WHITE);
		setBounds(252, 72, 1002, 587);
		setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(Color.DARK_GRAY));
		panel.setBounds(285, 103, 449, 408);
		panel.setLayout(null);
		
		formPanelButtons();
		formPanelLabels();
		formPanelComboBoxes();

		String[] hr = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "1" };
		String[] min = { "00", "30" };
		
		startHrSpinner = new JSpinner();
		startHrSpinner.setBounds(121, 220, 41, 23);
		startHrSpinner.setModel(new SpinnerListModel(hr));

		startMinSpinner = new JSpinner();
		startMinSpinner.setBounds(172, 220, 40, 23);
		startMinSpinner.setModel(new SpinnerListModel(min));

		endMinSpinner = new JSpinner();
		endMinSpinner.setBounds(172, 257, 40, 23);
		endMinSpinner.setModel(new SpinnerListModel(min));

		endHrSpinner = new JSpinner();
		endHrSpinner.setBounds(121, 257, 41, 23);
		endHrSpinner.setModel(new SpinnerListModel(hr));

		slotsField = new JTextField();
		slotsField.setBounds(342, 183, 60, 23);
		slotsField.setFont(new Font("Arial", Font.PLAIN, 13));
		slotsField.setColumns(10);

		add(panel);
		panel.add(startHrSpinner);
		panel.add(startMinSpinner);
		panel.add(endMinSpinner);
		panel.add(endHrSpinner);
		panel.add(slotsField);
		
	}

	public void setFormData(int row, int offeringID, String sy, String sem, Object startHr, Object startMin,
			Object startP, Object endHr, Object endMin, Object endP, String day, String subj, String fac, String slots,
			String room) {
		this.row = row;
		this.offeringID = offeringID;
		syComboBox.setSelectedItem(sy);
		semComboBox.setSelectedItem(sem);
		System.out.println(startHr);
//		startHrSpinner.setValue(startHr);
//		startMinSpinner.setValue(startMin);
		startPeriod.setSelectedItem(startP);
//		endHrSpinner.setValue(endHr);
//		endMinSpinner.setValue(endMin);
//		endPeriod.setSelectedItem(endP);
		dayComboBox.setSelectedItem(day);
		subjectComboBox.setSelectedItem(subj);
		facultyComboBox.setSelectedItem(fac);
		slotsField.setText(slots);
		roomComboBox.setSelectedItem(room);
	}


	public void clearFields() {
		syComboBox.setSelectedIndex(-1);
		semComboBox.setSelectedIndex(-1);
//		startHrSpinner.getModel().setValue("00");
//		startMinSpinner.setValue(Integer.toString(00));
		startPeriod.setSelectedIndex(-1);
//		endHrSpinner.setValue(Integer.toString(00));
//		endMinSpinner.setValue(Integer.toString(00));
		endPeriod.setSelectedIndex(-1);
		dayComboBox.setSelectedIndex(-1);
		subjectComboBox.setSelectedIndex(-1);
		facultyComboBox.setSelectedIndex(-1);
		slotsField.setText("");
		roomComboBox.setSelectedIndex(-1);
	}

	public void formPanelButtons() {
		btnAddOffering = new JButton("Add");
		btnAddOffering.setBounds(121, 354, 91, 30);
		format.buttonFormat(btnAddOffering, format.addIcon);

		btnEditOffering = new JButton("Update");
		btnEditOffering.setBounds(121, 354, 91, 30);
		format.buttonFormat(btnEditOffering, format.updateIcon);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(247, 354, 91, 30);
		format.buttonFormat(btnCancel, format.imagesPath + "cancel.png");

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				HomeView.offeringPanel.setVisible(true);
			}
		});
		btnEditOffering.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facultyname = facultyComboBox.getSelectedItem().toString();
				String roomname = roomComboBox.getSelectedItem().toString();
				String start_time = startHrSpinner.getValue().toString() + ":" + startMinSpinner.getValue() + " "
						+ startPeriod.getSelectedItem();
				String end_time = endHrSpinner.getValue().toString() + ":" + endMinSpinner.getValue() + " "
						+ endPeriod.getSelectedItem();

				HomeView.offeringPanel.updateOfferingFromTable(row, offeringID, syComboBox.getSelectedItem(),
						semComboBox.getSelectedItem(), start_time, end_time, dayComboBox.getSelectedItem(),
						subjectComboBox.getSelectedItem(), facultyComboBox.getSelectedItem(), slotsField.getText(),
						roomComboBox.getSelectedItem());

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
				String st = format.to24hr(start_time);
				String et = format.to24hr(end_time);
				offering.editDaySched(offeringID, offering.getDaycode(dayComboBox.getSelectedItem().toString()), st,
						et);
			}
		});
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
				offering.addDaySched(offering.getLastOfferno(), offering.getDaycode(day), format.to24hr(start_time),
						format.to24hr(end_time));
				HomeView.offeringPanel.addOfferingToTable(offering.getLastOfferno(), sy, sem, start_time, end_time, day,
						subj, facultyname, slots, rm);
			}
		});
		
		panel.add(btnAddOffering);
		panel.add(btnEditOffering);
		panel.add(btnCancel);
	}

	public void formPanelLabels() {

		JLabel lblSchoolYear = new JLabel("School Year");
		lblSchoolYear.setBounds(47, 42, 74, 14);
		format.labelFormat(lblSchoolYear);

		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setBounds(287, 42, 51, 14);
		format.labelFormat(lblSemester);

		JLabel lblSlots = new JLabel("Slots");
		lblSlots.setBounds(287, 187, 51, 14);
		format.labelFormat(lblSlots);

		JLabel lblEndTime = new JLabel("End Time");
		lblEndTime.setBounds(47, 261, 74, 14);
		format.labelFormat(lblEndTime);

		JLabel lblStartTime = new JLabel("Start Time");
		lblStartTime.setBounds(47, 224, 74, 14);
		format.labelFormat(lblStartTime);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setBounds(47, 76, 74, 14);
		format.labelFormat(lblSubject);

		JLabel lblRoom = new JLabel("Room");
		lblRoom.setBounds(47, 150, 74, 14);
		format.labelFormat(lblRoom);

		JLabel lblDays = new JLabel("Day(s)");
		lblDays.setBounds(47, 187, 64, 14);
		format.labelFormat(lblDays);

		JLabel lblFaculty = new JLabel("Faculty");
		lblFaculty.setBounds(47, 113, 74, 14);
		format.labelFormat(lblFaculty);

		lblOffering = new JLabel("Offering");
		lblOffering.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblOffering.setHorizontalAlignment(SwingConstants.CENTER);
		lblOffering.setBounds(285, 61, 449, 19);
		
		add(lblOffering);
		panel.add(lblSchoolYear);
		panel.add(lblSemester);
		panel.add(lblSlots);
		panel.add(lblEndTime);
		panel.add(lblStartTime);
		panel.add(lblSubject);
		panel.add(lblRoom);
		panel.add(lblDays);
		panel.add(lblFaculty);
		
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
		semComboBox.setBounds(342, 38, 60, 23);
		panel.add(semComboBox);
		format.comboBoxFormat(semComboBox);

		syComboBox = new JComboBox(sy);
		syComboBox.setBounds(121, 38, 112, 23);
		panel.add(syComboBox);
		format.comboBoxFormat(syComboBox);

		facultyComboBox = new JComboBox<String>(fac);
		facultyComboBox.setBounds(121, 109, 281, 23);
		panel.add(facultyComboBox);
		format.comboBoxFormat(facultyComboBox);

		roomComboBox = new JComboBox(rm);
		roomComboBox.setBounds(121, 146, 281, 23);
		panel.add(roomComboBox);
		format.comboBoxFormat(roomComboBox);

		dayComboBox = new JComboBox(days);
		dayComboBox.setBounds(121, 183, 91, 23);
		panel.add(dayComboBox);
		format.comboBoxFormat(dayComboBox);

		subjectComboBox = new JComboBox<String>(sub);
		subjectComboBox.setBounds(121, 72, 281, 23);
		panel.add(subjectComboBox);
		format.comboBoxFormat(subjectComboBox);

		startPeriod = new JComboBox(period);
		startPeriod.setBounds(222, 220, 51, 23);
		panel.add(startPeriod);
		startPeriod.setModel(new DefaultComboBoxModel(period));

		endPeriod = new JComboBox();
		endPeriod.setBounds(222, 257, 51, 23);
		panel.add(endPeriod);
		endPeriod.setModel(new DefaultComboBoxModel(period));
	}
}

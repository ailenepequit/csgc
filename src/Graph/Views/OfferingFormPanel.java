package Graph.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import DAO.FacultyDAO;
import DAO.OfferingDAO;
import DAO.RoomDAO;
import DAO.TimeslotDAO;
import Graph.Models.Faculty;
import Graph.Models.Offering;
import Graph.Models.Room;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class OfferingFormPanel extends JPanel {

	private int row, offerID, slots;
	String desc, p_timeslot, p_faculty, p_room;
	double units;

	private JPanel panel;
	JComboBox facultyComboBox, roomComboBox, timeDayComboBox;
	JButton btnEditOffering;

	DefaultComboBoxModel<String> td = new DefaultComboBoxModel<String>();
	DefaultComboBoxModel<String> fac = new DefaultComboBoxModel<String>();
	DefaultComboBoxModel<String> rm = new DefaultComboBoxModel<String>();
	DefaultListModel<String> listModel = new DefaultListModel<String>();
	
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	DefaultTableCellRenderer marginRenderer = new DefaultTableCellRenderer();

	Room room = new Room();
	RoomDAO r = new RoomDAO();
	Faculty faculty = new Faculty();
	Offering offering = new Offering();
	TimeslotDAO t = new TimeslotDAO();
	FacultyDAO f = new FacultyDAO();
	OfferingDAO o = new OfferingDAO();

	ArrayList<Room> roomlist;
	ArrayList<Faculty> facultylist;
	ArrayList<String> timeslotList;

	Formatter format = new Formatter();

	String[] offerings_columns;
	Object[][] offerings_data;
	String[] name;
	private JTextField subjectField;

	public OfferingFormPanel() {
		setVisible(false);
		setBackground(Color.WHITE);
		setBounds(252, 72, 1002, 587);
		setLayout(null);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Update Offering", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(102, 51, 449, 467);
		panel.setLayout(null);

		formPanelButtons();
		formPanelLabels();
	
		add(panel);
	
		subjectField = new JTextField();
		subjectField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		subjectField.setEditable(false);
		subjectField.setBounds(121, 63, 281, 25);
		panel.add(subjectField);
		subjectField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Available Time/Day/Room", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		scrollPane.setBounds(648, 51, 276, 467);
		add(scrollPane);

		JList list = new JList();
		list.setFont(new Font("Tahoma", Font.PLAIN, 13));
		list.setBorder(
				null);
		scrollPane.setViewportView(list);
		
		ArrayList<String> avail_timeslots = t.getAllAvailableTimeslots();
		for (String a : avail_timeslots) {
			listModel.addElement(a);
		}
		list.setModel(listModel);
	}

	public void setFormData(int row, int offerID, String desc, double units, String timeslot, String room, String fac, int slots) {
		this.row = row;
		this.offerID = offerID;
		this.desc = desc;
		this.units = units;
		this.slots = slots;
		this.p_faculty = fac;
		this.p_room = room;
		this.p_timeslot = timeslot;
		subjectField.setText(desc);
		System.out.println(row + " " + offerID + " " + desc + " " + units + " " + timeslot + " " + room + " " + fac + " " + slots);
		timeDayComboBox();
	}

	public void formPanelButtons() {

		btnEditOffering = new JButton("Update");
		btnEditOffering.setBounds(121, 354, 91, 30);
		format.buttonFormat(btnEditOffering, format.updateIcon);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(247, 354, 91, 30);
		format.buttonFormat(btnCancel, format.imagesPath + "cancel.png");

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rm.removeAllElements();
				fac.removeAllElements();
				setVisible(false);
				HomeView.offeringPanel.setVisible(true);
			}
		});
		btnEditOffering.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String facultyname = facultyComboBox.getSelectedItem().toString();
				String roomname = roomComboBox.getSelectedItem().toString();
				String selected_time = timeDayComboBox.getSelectedItem().toString();
				HomeView.offeringPanel.updateOfferingFromTable(row, selected_time, facultyname, roomname);
				o.updateOfferingTimeslot(offerID, selected_time, r.getID(roomname), f.getID(facultyname));
				t.deleteAvailableTimeslot(r.getID(roomname), selected_time);
				t.insertAvailableTimeslot(r.getID(p_room), p_timeslot);
				HomeView.offeringPanel.setVisible(true);
				setVisible(false);
			}
		});
		panel.add(btnEditOffering);
		panel.add(btnCancel);
	}

	public void formPanelLabels() {

		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setBounds(44, 63, 74, 25);
		format.labelFormat(lblSubject);

		JLabel lblRoom = new JLabel("Room");
		lblRoom.setBounds(41, 220, 77, 25);
		format.labelFormat(lblRoom);

		JLabel lblFaculty = new JLabel("Faculty");
		lblFaculty.setBounds(44, 263, 74, 25);
		format.labelFormat(lblFaculty);
		
		JLabel lblTimeday = new JLabel("Time/Day");
		lblTimeday.setBounds(41, 175, 77, 25);
		format.labelFormat(lblFaculty);
		
		JLabel lblSuggestedSchedule = new JLabel("Suggested Schedules");
		lblSuggestedSchedule.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblSuggestedSchedule.setBounds(41, 139, 171, 14);
		panel.add(lblSubject);
		panel.add(lblRoom);
		panel.add(lblFaculty);
		panel.add(lblTimeday);
		panel.add(lblSuggestedSchedule);
	}

	public void timeDayComboBox() {
		if (p_faculty.equals("") || units > 2.0)
			timeslotList = t.getAvailableTimeslots("MTh", "TuF");
		else
			timeslotList = t.getAvailableTimeslots("W", "S");
		
		for (int i = 0; i < timeslotList.size(); i++) {
			td.addElement(timeslotList.get(i).toString());
		}

		timeDayComboBox = new JComboBox(td);
		timeDayComboBox.setBounds(121, 183, 281, 20);
		timeDayComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String t = timeDayComboBox.getSelectedItem().toString();
				System.out.println("."+p_faculty+".");
				if(!p_faculty.equals("  ")){
					roomComboBox(t);
					facultyComboBox(t);	
				}
			}
		});
		panel.add(timeDayComboBox);
		format.comboBoxFormat(timeDayComboBox);
	}

	public void facultyComboBox(String timeslot) {
		fac.removeAllElements();
		facultylist = f.availableFaculty_Timeslot(timeslot);
		for (int i = 0; i < facultylist.size(); i++) {
			fac.addElement(facultylist.get(i).getName());
		}

		facultyComboBox = new JComboBox<String>(fac);
		facultyComboBox.setBounds(121, 263, 281, 23);
		panel.add(facultyComboBox);
		format.comboBoxFormat(facultyComboBox);
		facultyComboBox.setSelectedIndex(-1);
	}

	public void roomComboBox(String timeslot) {
		rm.removeAllElements();
		roomlist = r.availableRooms_Timeslot(timeslot, slots);
		for (int i = 0; i < roomlist.size(); i++) {
			rm.addElement(roomlist.get(i).getName());
		}
		roomComboBox = new JComboBox(rm);
		roomComboBox.setBounds(121, 222, 281, 23);
		panel.add(roomComboBox);

		roomComboBox.setSelectedIndex(-1);
		format.comboBoxFormat(roomComboBox);
	}
}

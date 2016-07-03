//package Graph.Views;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.table.DefaultTableModel;
//
//import Graph.Models.Faculty;
//
//import javax.swing.JScrollPane;
//import javax.swing.JTextField;
//
//import java.util.ArrayList;
//import java.util.Date;
//
//import javax.swing.JComboBox;
//import javax.swing.JTable;
//import javax.swing.JLabel;
//import java.awt.Font;
//import javax.swing.JButton;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.awt.event.ActionEvent;
//import java.awt.Color;
//import javax.swing.ListSelectionModel;
//import javax.swing.JSeparator;
//import javax.swing.SwingConstants;
//
//@SuppressWarnings("serial")
//public class FacultyView extends JFrame {
//
//	private JPanel contentPane;
//	private JComboBox<?> genderComboBox;
//	private JTextField fnameField;
//	private JTextField mnameField;
//	private JTextField lnameField;
//	private JTextField addressField;
//	private JTable facultyTable;
//	private JTextField phoneField;
//	private String[] faculty_columns;
//	private Object[][] faculty_data;
//	private JTextField bdayField;
//	private int facultyID;
//	private JTextField availStartTimeField;
//	private JTextField availEndTimeField;
//	Faculty faculty = new Faculty();
//	ArrayList<Faculty> facultylist;
//	DefaultTableModel facultyModel;
//	
//	public FacultyView() {
//		setBounds(100, 100, 1140, 514);
//		contentPane = new JPanel();
//		contentPane.setBackground(new Color(153, 153, 153));
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		setLocationRelativeTo(null);
//		setTitle("Faculty Page");
//		contentPane.setLayout(null);
//		
//		
//		facultylist = faculty.facultyList();
//		faculty_columns = new String[] { "Id", "Name", "Gender", "Bday", "Phone", "Address" , "Preferred Time", " ", ""};
//
//		faculty_data = new Object[facultylist.size()][];
//		for (int i = 0; i < facultylist.size(); i++) {
//			faculty_data[i] = facultylist.get(i).toObjectArray();
//		}
//		
//		components();
//		JLabel label = new JLabel("");
//		label.setBackground(new Color(51, 51, 51));
//		label.setOpaque(true);
//		label.setBounds(795, 0, 329, 475);
//		contentPane.add(label);
//	}
//	
//	private void components() {
//		
//		
//		facultyModel = new DefaultTableModel(faculty_data, faculty_columns){
//		    public boolean isCellEditable(int row, int column) {
//			       return false;
//			    }
//			};
//			
//		facultyTable = new JTable(facultyModel);
//		facultyTable.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		facultyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		
//		facultyTable.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				int i = facultyTable.getSelectedRow();
//				facultyID = Integer.parseInt(facultyModel.getValueAt(i, 0).toString());
//				fnameField.setText(facultylist.get(i).getFname());
//				mnameField.setText(facultylist.get(i).getMname());
//				lnameField.setText(facultylist.get(i).getLname());
//				genderComboBox.setSelectedItem(facultyModel.getValueAt(i, 2).toString());
//				if(facultyModel.getValueAt(i, 3) == null)
//					bdayField.setText("");
//				else
//					bdayField.setText(facultyModel.getValueAt(i, 3).toString());
//				if(facultyModel.getValueAt(i, 4) == null)
//					phoneField.setText("");
//				else
//					phoneField.setText(facultyModel.getValueAt(i, 4).toString());
//				if(facultyModel.getValueAt(i, 5) == null)
//					addressField.setText("");
//				else
//					addressField.setText(facultyModel.getValueAt(i, 5).toString());
//				availStartTimeField.setText(facultyModel.getValueAt(i, 6).toString());
//				availEndTimeField.setText(facultyModel.getValueAt(i, 7).toString());
//			}
//		});	
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(10, 11, 770, 453);
//		scrollPane.setViewportView(facultyTable);
//		contentPane.add(scrollPane);
//		
//		fnameField = new JTextField();
//		fnameField.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		fnameField.setBounds(898, 73, 203, 23);
//		contentPane.add(fnameField);
//		fnameField.setColumns(10);
//		
//		mnameField = new JTextField();
//		mnameField.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		mnameField.setBounds(898, 102, 203, 23);
//		contentPane.add(mnameField);
//		mnameField.setColumns(10);
//		
//		lnameField = new JTextField();
//		lnameField.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		lnameField.setBounds(898, 137, 203, 23);
//		contentPane.add(lnameField);
//		lnameField.setColumns(10);
//		
//		addressField = new JTextField();
//		addressField.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		addressField.setBounds(898, 275, 203, 23);
//		contentPane.add(addressField);
//		addressField.setColumns(10);
//		
//		String[] gender = {"Male", "Female"};
//		genderComboBox = new JComboBox<Object>(gender);
//		genderComboBox.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		genderComboBox.setBounds(978, 171, 123, 23);
//		contentPane.add(genderComboBox);
//		
//		JLabel lblFirstname = new JLabel("Firstname");
//		lblFirstname.setBackground(new Color(51, 51, 51));
//		lblFirstname.setForeground(new Color(255, 255, 255));
//		lblFirstname.setOpaque(true);
//		lblFirstname.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		lblFirstname.setBounds(817, 77, 84, 14);
//		contentPane.add(lblFirstname);
//		
//		JLabel lblGender = new JLabel("Gender");
//		lblGender.setBackground(new Color(51, 51, 51));
//		lblGender.setOpaque(true);
//		lblGender.setForeground(new Color(255, 255, 255));
//		lblGender.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		lblGender.setBounds(898, 174, 70, 14);
//		contentPane.add(lblGender);
//		
//		JLabel lblMiddlename = new JLabel("Middlename");
//		lblMiddlename.setBackground(new Color(51, 51, 51));
//		lblMiddlename.setOpaque(true);
//		lblMiddlename.setForeground(new Color(255, 255, 255));
//		lblMiddlename.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		lblMiddlename.setBounds(817, 108, 84, 14);
//		contentPane.add(lblMiddlename);
//		
//		JLabel lblLastname = new JLabel("Lastname");
//		lblLastname.setBackground(new Color(51, 51, 51));
//		lblLastname.setOpaque(true);
//		lblLastname.setForeground(new Color(255, 255, 255));
//		lblLastname.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		lblLastname.setBounds(817, 139, 84, 14);
//		contentPane.add(lblLastname);
//		
//		JLabel lblAddress = new JLabel("Address");
//		lblAddress.setBackground(new Color(51, 51, 51));
//		lblAddress.setOpaque(true);
//		lblAddress.setForeground(new Color(255, 255, 255));
//		lblAddress.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		lblAddress.setBounds(817, 279, 70, 14);
//		contentPane.add(lblAddress);
//		
//		JLabel lblPhone = new JLabel("Phone");
//		lblPhone.setBackground(new Color(51, 51, 51));
//		lblPhone.setOpaque(true);
//		lblPhone.setForeground(new Color(255, 255, 255));
//		lblPhone.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		lblPhone.setBounds(817, 245, 46, 14);
//		contentPane.add(lblPhone);
//		
//		phoneField = new JTextField();
//		phoneField.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		phoneField.setBounds(898, 241, 203, 23);
//		contentPane.add(phoneField);
//		phoneField.setColumns(10);
//		
//		JLabel lblBirthday = new JLabel("Birthday");
//		lblBirthday.setBackground(new Color(51, 51, 51));
//		lblBirthday.setOpaque(true);
//		lblBirthday.setForeground(new Color(255, 255, 255));
//		lblBirthday.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		lblBirthday.setBounds(898, 211, 70, 14);
//		contentPane.add(lblBirthday);
//		
//		bdayField = new JTextField();
//		bdayField.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		bdayField.setBounds(978, 207, 123, 23);
//		contentPane.add(bdayField);
//		bdayField.setColumns(10);
//		
//		JButton btnAdd = new JButton("Add");
//		btnAdd.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				faculty.addFaculty(fnameField.getText(),mnameField.getText(), lnameField.getText(), genderComboBox.getSelectedItem().toString(), bdayField.getText(), phoneField.getText(), addressField.getText(), to24hr(availStartTimeField.getText()), to24hr(availStartTimeField.getText()));
//			}
//		});
//		btnAdd.setBackground(Color.BLACK);
//		btnAdd.setForeground(Color.WHITE);
//		btnAdd.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		btnAdd.setBounds(817, 369, 84, 23);
//		contentPane.add(btnAdd);
//		
//		JButton btnEdit = new JButton("Edit");
//		btnEdit.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int i = facultyTable.getSelectedRow();
//				facultyID = Integer.parseInt(facultyTable.getValueAt(i, 0).toString());
//				facultyModel.setValueAt(facultyID, i, 0);
//				facultyModel.setValueAt(fnameField.getText() + " " + mnameField.getText() + " " + lnameField.getText(), i, 1);
//				facultyModel.setValueAt(genderComboBox.getSelectedItem().toString(), i, 2);
//				facultyModel.setValueAt(bdayField.getText(), i, 3);
//				facultyModel.setValueAt(phoneField.getText(), i, 4);
//				facultyModel.setValueAt(addressField.getText(), i, 5);
//				facultyModel.setValueAt(to24hr(availStartTimeField.getText()), i, 6);
//				facultyModel.setValueAt(to24hr(availEndTimeField.getText()), i, 7);
//				faculty.editFaculty(facultyID, fnameField.getText(),mnameField.getText(), lnameField.getText(), genderComboBox.getSelectedItem().toString(), bdayField.getText(), phoneField.getText(), addressField.getText(), to24hr(availStartTimeField.getText()), to24hr(availEndTimeField.getText()));
//			}
//		});
//		btnEdit.setBackground(Color.BLACK);
//		btnEdit.setForeground(Color.WHITE);
//		btnEdit.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		btnEdit.setBounds(918, 369, 84, 23);
//		contentPane.add(btnEdit);
//		
//		JButton btnDelete = new JButton("Delete");
//		btnDelete.setBackground(Color.BLACK);
//		btnDelete.setForeground(Color.WHITE);
//		btnDelete.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		btnDelete.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int i = facultyTable.getSelectedRow();
//                if(i >= 0){
//                	faculty.deleteFaculty(facultyID);
//                    facultyModel.removeRow(i);
//                }
//                else{
//                    System.out.println("Delete Error");
//                }
//			}
//		});
//		btnDelete.setBounds(1017, 369, 84, 23);
//		contentPane.add(btnDelete);
//		
//		JLabel lblTimePreference = new JLabel("Time Preference");
//		lblTimePreference.setBackground(new Color(51, 51, 51));
//		lblTimePreference.setOpaque(true);
//		lblTimePreference.setForeground(new Color(255, 255, 255));
//		lblTimePreference.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		lblTimePreference.setBounds(817, 312, 100, 14);
//		contentPane.add(lblTimePreference);
//		
//		availStartTimeField = new JTextField();
//		availStartTimeField.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		availStartTimeField.setBounds(920, 309, 77, 23);
//		contentPane.add(availStartTimeField);
//		availStartTimeField.setColumns(10);
//		
//		availEndTimeField = new JTextField();
//		availEndTimeField.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		availEndTimeField.setBounds(1024, 309, 77, 23);
//		contentPane.add(availEndTimeField);
//		availEndTimeField.setColumns(10);
//		
//		JButton btnPrintSchedule = new JButton("Print Schedule");
//		btnPrintSchedule.addActionListener(new ActionListener() {
//			@SuppressWarnings("deprecation")
//			public void actionPerformed(ActionEvent arg0) {
//				ScheduleView sv = new ScheduleView(facultyID, "faculty");
//				sv.show();
//			}
//		});
//		btnPrintSchedule.setBackground(new Color(0, 0, 0));
//		btnPrintSchedule.setForeground(new Color(255, 255, 255));
//		btnPrintSchedule.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		btnPrintSchedule.setBounds(898, 422, 116, 23);
//		contentPane.add(btnPrintSchedule);
//		
//		JLabel lblTo = new JLabel("-");
//		lblTo.setHorizontalAlignment(SwingConstants.CENTER);
//		lblTo.setFont(new Font("SansSerif", Font.PLAIN, 12));
//		lblTo.setBackground(new Color(51, 51, 51));
//		lblTo.setOpaque(true);
//		lblTo.setForeground(new Color(255, 255, 255));
//		lblTo.setBounds(1007, 312, 7, 14);
//		contentPane.add(lblTo);
//		
//		JLabel lblFacultyInformation = new JLabel("Faculty Information");
//		lblFacultyInformation.setHorizontalAlignment(SwingConstants.CENTER);
//		lblFacultyInformation.setBackground(new Color(51, 51, 51));
//		lblFacultyInformation.setOpaque(true);
//		lblFacultyInformation.setForeground(new Color(255, 255, 255));
//		lblFacultyInformation.setFont(new Font("SansSerif", Font.PLAIN, 13));
//		lblFacultyInformation.setBounds(908, 31, 115, 14);
//		contentPane.add(lblFacultyInformation);
//		
//		JSeparator separator = new JSeparator();
//		separator.setBounds(817, 40, 284, 2);
//		contentPane.add(separator);
//	}
//
//	public String to24hr(String time)
//	{
//		DateFormat df = new SimpleDateFormat("HH:mm aa");
//		Date date = null;
//		try {
//			date = df.parse(time);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		DateFormat _df = new SimpleDateFormat("hh:mm:ss");
//		return _df.format(date);
//	}
//}

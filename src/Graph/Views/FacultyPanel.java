package Graph.Views;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Graph.Models.Faculty;

@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class FacultyPanel extends JPanel {

	private int facultyID;
	private JComboBox genderComboBox;
	private JButton btnAddFaculty, btnUpdateFaculty, btnDeleteFaculty, btnViewFacultyTimetable;
	Faculty faculty = new Faculty();
	ArrayList<Faculty> facultylist = faculty.facultyList();
	private JTable facultyTable;
	private JTextField fnameField, mnameField, lnameField, bdayField, phoneField, addressField;
	Formatter format = new Formatter();
	DefaultTableModel facultyModel;

	public FacultyPanel() {
		setBackground(Color.WHITE);
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

		setBounds(253, 73, 1001, 586);
		setVisible(false);
		setLayout(null);

		JPanel facultyFormPanel = new JPanel();
		facultyFormPanel.setBounds(593, 68, 376, 400);
		facultyFormPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		facultyFormPanel.setLayout(null);

		JLabel lblFacultyInformationDetails = new JLabel("Faculty Information Details");
		lblFacultyInformationDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblFacultyInformationDetails.setBounds(96, 26, 201, 14);

		JLabel lblFname = new JLabel("Firstname");
		lblFname.setBounds(37, 68, 61, 14);
		format.labelFormat(lblFname);

		JLabel lblMiddlename = new JLabel("Middlename");
		lblMiddlename.setBounds(37, 113, 61, 14);
		format.labelFormat(lblMiddlename);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(37, 203, 61, 14);
		format.labelFormat(lblGender);

		JLabel lblBirthday = new JLabel("Birthday");
		lblBirthday.setBounds(37, 248, 61, 14);
		format.labelFormat(lblBirthday);

		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(37, 293, 61, 14);
		format.labelFormat(lblPhone);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(37, 338, 61, 14);
		format.labelFormat(lblAddress);

		fnameField = new JTextField();
		fnameField.setBounds(108, 65, 235, 20);
		fnameField.setColumns(10);

		mnameField = new JTextField();
		mnameField.setBounds(108, 110, 235, 20);
		add(facultyFormPanel);
		mnameField.setColumns(10);

		lnameField = new JTextField();
		lnameField.setBounds(108, 155, 235, 20);
		lnameField.setColumns(10);

		genderComboBox = new JComboBox(gender);
		format.comboBoxFormat(genderComboBox);
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
		format.buttonFormat(btnAddFaculty, format.addIcon);
		btnAddFaculty.setBounds(593, 486, 91, 30);
		btnAddFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				faculty.addFaculty(fnameField.getText(), mnameField.getText(), lnameField.getText(),
						genderComboBox.getSelectedItem().toString(), bdayField.getText(), phoneField.getText(),
						addressField.getText());
			}
		});

		btnUpdateFaculty = new JButton("Update");
		btnUpdateFaculty.setEnabled(false);
		format.buttonFormat(btnUpdateFaculty, format.updateIcon);
		btnUpdateFaculty.setBounds(736, 486, 91, 30);
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
		format.buttonFormat(btnDeleteFaculty, format.deleteIcon);
		btnDeleteFaculty.setBounds(878, 486, 91, 30);
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
		format.buttonFormat(btnViewFacultyTimetable, format.viewTimetableIcon);
		btnViewFacultyTimetable.setBounds(802, 19, 170, 30);

		JButton btnNewFaculty = new JButton("New");
		format.buttonFormat(btnNewFaculty, format.newIcon);
		btnNewFaculty.setBounds(20, 19, 89, 30);
		btnNewFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFacultyFormFields();
			}
		});

		JScrollPane facultyScrollPane = new JScrollPane();
		facultyScrollPane.setBounds(20, 68, 552, 448);
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

		format.tableFormat(facultyTable);
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
		lblFacultyList.setBounds(250, 27, 89, 14);

		facultyFormPanel.add(lblFacultyInformationDetails);
		facultyFormPanel.add(lblFname);
		facultyFormPanel.add(lblMiddlename);

		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setBounds(37, 158, 61, 14);
		format.labelFormat(lblLastname);
		
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
		add(btnAddFaculty);
		add(btnUpdateFaculty);
		add(btnDeleteFaculty);
		add(btnViewFacultyTimetable);
		add(btnNewFaculty);
		add(facultyScrollPane);
		add(lblFacultyList);
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

}

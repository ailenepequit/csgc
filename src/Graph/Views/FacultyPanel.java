package Graph.Views;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Graph.Models.Faculty;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.GridLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import DAO.FacultyDAO;

@SuppressWarnings({ "rawtypes", "serial"})
public class FacultyPanel extends JPanel {

	private int facultyID;
	private JComboBox genderComboBox;
	private JButton btnAddFaculty, btnUpdateFaculty, btnDeleteFaculty;
	Faculty faculty = new Faculty();
	FacultyDAO fDAO = new FacultyDAO();
	ArrayList<Faculty> facultylist = faculty.facultyList();
	private JTable facultyTable;
	private JTextField fnameField, mnameField, lnameField, bdayField, phoneField, addressField;
	Formatter format = new Formatter();
	DefaultTableModel facultyModel;
	private JTextField specField;

	public FacultyPanel() {
		setBackground(Color.WHITE);
		String[] faculty_columns = new String[] { "Id", "Name", "Specialization", "Load" };

		Object[][] faculty_data = new Object[facultylist.size()][];
		for (int i = 0; i < facultylist.size(); i++) {
			faculty_data[i] = facultylist.get(i).toObjectArray();
		}

		facultyModel = new DefaultTableModel(faculty_data, faculty_columns) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		//String[] gender = { "Male", "Female" };

		setBounds(253, 73, 1001, 586);
		setVisible(false);
		setLayout(null);

		JPanel facultyFormPanel = new JPanel();
		facultyFormPanel.setBounds(600, 60, 370, 430);
		facultyFormPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Faculty Information",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JLabel lblFname = new JLabel("Firstname");
		format.labelFormat(lblFname);

		fnameField = new JTextField();
		fnameField.setColumns(10);

		mnameField = new JTextField();
		add(facultyFormPanel);
		mnameField.setColumns(10);

		lnameField = new JTextField();
		lnameField.setColumns(10);

		// genderComboBox = new JComboBox(gender);
		// format.comboBoxFormat(genderComboBox);
		//
		// bdayField = new JTextField();
		// bdayField.setColumns(10);
		//
		// phoneField = new JTextField();
		// phoneField.setColumns(10);
		//
		// addressField = new JTextField();
		// addressField.setColumns(10);

		btnAddFaculty = new JButton("Add");
		btnAddFaculty.setEnabled(false);
		format.buttonFormat(btnAddFaculty, format.addIcon);
		btnAddFaculty.setBounds(600, 500, 91, 30);
		btnAddFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				faculty.addFaculty(fnameField.getText(), mnameField.getText(), lnameField.getText(),
//						genderComboBox.getSelectedItem().toString(), bdayField.getText(), phoneField.getText(),
//						addressField.getText());
				fDAO.addFaculty(fnameField.getText(), mnameField.getText(), lnameField.getText(),specField.getText().toString());
				String faculty = fnameField.getText() + " " + mnameField.getText() + " " + lnameField.getText() ;
				int facID = fDAO.getID(faculty);
				addFacultyToTable(facID, faculty, specField.getText());
			}
		});

		btnUpdateFaculty = new JButton("Update");
		btnUpdateFaculty.setEnabled(false);
		format.buttonFormat(btnUpdateFaculty, format.updateIcon);
		btnUpdateFaculty.setBounds(741, 500, 91, 30);
		btnUpdateFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = facultyTable.getSelectedRow();
				facultyID = Integer.parseInt(facultyTable.getValueAt(i, 0).toString());
				facultyModel.setValueAt(facultyID, i, 0);
				facultyModel.setValueAt(fnameField.getText() + " " + mnameField.getText() + " " + lnameField.getText(),
						i, 1);
				facultyModel.setValueAt(specField.getText(), i, 2);
//				facultyModel.setValueAt(genderComboBox.getSelectedItem().toString(), i, 2);
//				facultyModel.setValueAt(bdayField.getText(), i, 3);
//				facultyModel.setValueAt(phoneField.getText(), i, 4);
//				facultyModel.setValueAt(addressField.getText(), i, 5);
//				faculty.editFaculty(facultyID, fnameField.getText(), mnameField.getText(), lnameField.getText(),
//						genderComboBox.getSelectedItem().toString(), bdayField.getText(), phoneField.getText(),
//						addressField.getText());
				fDAO.editFaculty(facultyID, fnameField.getText(), mnameField.getText(), lnameField.getText(), specField.getText());
			
			}
		});

		btnDeleteFaculty = new JButton("Delete");
		btnDeleteFaculty.setEnabled(false);
		format.buttonFormat(btnDeleteFaculty, format.deleteIcon);
		btnDeleteFaculty.setBounds(879, 500, 91, 30);
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

		JButton btnNewFaculty = new JButton("New");
		format.buttonFormat(btnNewFaculty, format.newIcon);
		btnNewFaculty.setBounds(880, 20, 90, 30);
		btnNewFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFacultyFormFields();
			}
		});
		facultyFormPanel.setLayout(new GridLayout(10, 2, -100, 10));
		facultyFormPanel.add(lblFname);
		facultyFormPanel.add(fnameField);

		JLabel lblMiddlename = new JLabel("Middlename");
		format.labelFormat(lblMiddlename);
		facultyFormPanel.add(lblMiddlename);
		facultyFormPanel.add(mnameField);

		JLabel lblLastname = new JLabel("Lastname");
		format.labelFormat(lblLastname);

		facultyFormPanel.add(lblLastname);
		facultyFormPanel.add(lnameField);
		
		JLabel lblSpecialization = new JLabel("Specialization");
		format.labelFormat(lblSpecialization);
		facultyFormPanel.add(lblSpecialization);
		
		specField = new JTextField();
		facultyFormPanel.add(specField);
		specField.setColumns(10);
//
//		JLabel lblGender = new JLabel("Gender");
//		format.labelFormat(lblGender);
//		facultyFormPanel.add(lblGender);
//		facultyFormPanel.add(genderComboBox);
//
//		JLabel lblBirthday = new JLabel("Birthday");
//		format.labelFormat(lblBirthday);
//		facultyFormPanel.add(lblBirthday);
//		facultyFormPanel.add(bdayField);
//
//		JLabel lblPhone = new JLabel("Phone");
//		format.labelFormat(lblPhone);
//		facultyFormPanel.add(lblPhone);
//		facultyFormPanel.add(phoneField);
//
//		JLabel lblAddress = new JLabel("Address");
//		format.labelFormat(lblAddress);
//		facultyFormPanel.add(lblAddress);
////		facultyFormPanel.add(addressField);
//		facultyFormPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(
//				new Component[] { lblFname, fnameField, lblMiddlename, mnameField, lblLastname, lnameField, lblGender,
//						genderComboBox, lblBirthday, bdayField, lblPhone, phoneField, lblAddress, addressField }));
		
		facultyFormPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { lblFname, fnameField, lblMiddlename, mnameField, lblLastname, lnameField}));
		add(btnAddFaculty);
		add(btnUpdateFaculty);
		add(btnDeleteFaculty);
		add(btnNewFaculty);

		JPanel tablePanel = new JPanel();
		tablePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Faculty List",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tablePanel.setBounds(20, 60, 550, 470);
		add(tablePanel);
		tablePanel.setLayout(null);

		JScrollPane facultyScrollPane = new JScrollPane();
		facultyScrollPane.setBounds(6, 16, 538, 443);
		tablePanel.add(facultyScrollPane);
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
		facultyTable.setBorder(null);
		facultyScrollPane.setViewportView(facultyTable);

		format.tableFormat(facultyTable);
		facultyTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int i = facultyTable.getSelectedRow();
				btnAddFaculty.setEnabled(false);
				btnUpdateFaculty.setEnabled(true);
				btnDeleteFaculty.setEnabled(true);
				facultyID = Integer.parseInt(facultyModel.getValueAt(i, 0).toString());
				fnameField.setText(facultylist.get(i).getFname());
				mnameField.setText(facultylist.get(i).getMname());
				lnameField.setText(facultylist.get(i).getLname());
				specField.setText(facultyModel.getValueAt(i, 2).toString());
				
				//genderComboBox.setSelectedItem(facultyModel.getValueAt(i, 2).toString());
				// if (facultyModel.getValueAt(i, 3) == null)
				// bdayField.setText("");
				// else
				// bdayField.setText(facultyModel.getValueAt(i, 3).toString());
				// if (facultyModel.getValueAt(i, 4) == null)
				// phoneField.setText("");
				// else
				// phoneField.setText(facultyModel.getValueAt(i, 4).toString());
				// if (facultyModel.getValueAt(i, 5) == null)
				// addressField.setText("");
				// else
				// addressField.setText(facultyModel.getValueAt(i,
				// 5).toString());
			}
		});
	}
	public void addFacultyToTable(int facID, String faculty, String spec) {
		Object[] newFaculty = new Object[facultyTable.getRowCount()];
		newFaculty[0] = facID;
		newFaculty[1] = faculty;
		newFaculty[2] = spec;
		newFaculty[3] = 0;
		facultyModel.addRow(newFaculty);
	}

	public void clearFacultyFormFields() {
		fnameField.setText("");
		mnameField.setText("");
		lnameField.setText("");
		genderComboBox.setSelectedIndex(-1);
		bdayField.setText("");
		phoneField.setText("");
		addressField.setText("");
		specField.setText("");
		btnAddFaculty.setEnabled(true);
		btnUpdateFaculty.setEnabled(false);
		btnDeleteFaculty.setEnabled(false);
	}
}

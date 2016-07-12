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

import Graph.Models.Subject;

@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class SubjectPanel extends JPanel {

	Subject subject = new Subject();
	ArrayList<Subject> subjectlist = subject.subjectList();
	private JTable subjectTable;
	private JTextField descriptionField;
	private JTextField unitsField;
	private JButton btnAddSubject, btnUpdateSubject, btnDeleteSubject, btnViewSubjectTimetable;
	private JComboBox yrlvlComboBox, semCombobox;
	private int subjectID;
	Formatter format = new Formatter();
	DefaultTableModel subjectModel;
	
	public SubjectPanel() {
		setBackground(Color.WHITE);
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

		setBounds(253, 73, 1001, 586);
		setLayout(null);
		setVisible(false);

		btnAddSubject = new JButton("Add");
		btnAddSubject.setEnabled(false);
		format.buttonFormat(btnAddSubject, format.addIcon);
		btnAddSubject.setBounds(604, 493, 91, 30);
		btnAddSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				subject.addSubject(descriptionField.getText(), Double.parseDouble(unitsField.getText()),
						Integer.parseInt(yrlvlComboBox.getSelectedItem().toString()), semCombobox.getSelectedItem());
			}
		});

		btnUpdateSubject = new JButton("Update");
		btnUpdateSubject.setEnabled(false);
		format.buttonFormat(btnUpdateSubject, format.updateIcon);
		btnUpdateSubject.setBounds(745, 493, 91, 30);
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
		format.buttonFormat(btnDeleteSubject, format.deleteIcon);
		btnDeleteSubject.setBounds(879, 493, 91, 30);
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
		format.buttonFormat(btnViewSubjectTimetable, format.viewTimetableIcon);
		btnViewSubjectTimetable.setEnabled(false);
		btnViewSubjectTimetable.setBounds(802, 19, 170, 30);

		JButton btnNewSubject = new JButton("New");
		btnNewSubject.setBounds(20, 19, 89, 30);
		format.buttonFormat(btnNewSubject, format.newIcon);
		btnNewSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearSubjectFormFields();
			}
		});

		JScrollPane subjectsScrollPane = new JScrollPane();
		subjectsScrollPane.setBounds(20, 68, 534, 455);
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

		format.tableFormat(subjectTable);
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
		lblSubjectList.setBounds(251, 27, 81, 14);

		JPanel subjectFormPanel = new JPanel();
		subjectFormPanel.setBounds(602, 68, 370, 414);
		subjectFormPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		subjectFormPanel.setLayout(null);

		JLabel lblSubjectInformationDetails = new JLabel("Subject Information Details");
		lblSubjectInformationDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubjectInformationDetails.setBounds(101, 26, 175, 14);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(31, 68, 71, 14);
		format.labelFormat(lblDescription);

		JLabel lblUnits = new JLabel("Units");
		lblUnits.setBounds(31, 113, 46, 14);
		format.labelFormat(lblUnits);

		JLabel lblYearLevel = new JLabel("Year Level");
		lblYearLevel.setBounds(31, 158, 71, 14);
		format.labelFormat(lblYearLevel);

		JLabel lblSubjectSemester = new JLabel("Semester");
		lblSubjectSemester.setBounds(31, 204, 71, 14);
		format.labelFormat(lblSubjectSemester);

		descriptionField = new JTextField();
		descriptionField.setBounds(107, 65, 230, 20);
		descriptionField.setColumns(10);

		unitsField = new JTextField();
		unitsField.setBounds(107, 111, 86, 20);
		unitsField.setColumns(10);

		semCombobox = new JComboBox(sem);
		semCombobox.setBounds(107, 201, 86, 20);
		format.comboBoxFormat(semCombobox);

		yrlvlComboBox = new JComboBox(yr);
		format.comboBoxFormat(yrlvlComboBox);
		yrlvlComboBox.setBounds(107, 155, 86, 20);

		add(subjectsScrollPane);
		add(btnAddSubject);
		add(btnNewSubject);
		add(btnUpdateSubject);
		add(btnDeleteSubject);
		add(btnViewSubjectTimetable);
		add(lblSubjectList);
		add(subjectFormPanel);
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
}

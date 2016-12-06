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

import DAO.SubjectDAO;
import Graph.Models.Subject;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class SubjectPanel extends JPanel {

	SubjectDAO sDAO = new SubjectDAO();
	Subject subject = new Subject();
	ArrayList<Subject> subjectlist;
	private JTable subjectTable;
	private JTextField descriptionField;
	private JTextField lecUnitsField;
	private JButton btnAddSubject, btnUpdateSubject, btnDeleteSubject;
	private JComboBox yrlvlComboBox, semCombobox;
	private int subjectID;
	Formatter format = new Formatter();
	DefaultTableModel subjectModel;
	Object[][] subject_data;
	String[] subject_columns;
	private JTextField labUnitsField;
	private JTextField tagField;

	public SubjectPanel() {
		setBackground(Color.WHITE);
		subjectlist = subject.subjectList("All");
		subject_data = new Object[subjectlist.size()][];
		for (int i = 0; i < subjectlist.size(); i++) {
			subject_data[i] = subjectlist.get(i).toObjectArray();
		}
		subject_columns = new String[] { "Id", "Decsription", "Lec Units", "Lab Units", "Yr Level", "Semester", "Tag" };

		subjectModel = new DefaultTableModel(subject_data, subject_columns);

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
				String desc = descriptionField.getText();
				String lec = lecUnitsField.getText();
				String lab = labUnitsField.getText();
				String yr = yrlvlComboBox.getSelectedItem().toString();
				String sem = semCombobox.getSelectedItem().toString();
				String tag = tagField.getText();
				subject.addSubject(desc, Double.parseDouble(lec),Integer.parseInt(yr), sem);
				int id = sDAO.getID(desc);
				addSubjectToTable(id, desc, lec, lab, yr, sem, tag);
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
				subjectModel.setValueAt(lecUnitsField.getText(), i, 2);
				subjectModel.setValueAt(labUnitsField.getText(), i, 3);
				subjectModel.setValueAt(yrlvlComboBox.getSelectedItem(), i, 4);
				subjectModel.setValueAt(semCombobox.getSelectedItem(), i, 5);
				subjectModel.setValueAt(tagField.getText(), i,6);
				sDAO.editSubject(subjectID, descriptionField.getText(), Double.parseDouble(lecUnitsField.getText()),Double.parseDouble(labUnitsField.getText()),
						Integer.parseInt(yrlvlComboBox.getSelectedItem().toString()), semCombobox.getSelectedItem(), tagField.getText().toString());
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

		JButton btnNewSubject = new JButton("New");
		btnNewSubject.setBounds(881, 19, 89, 30);
		format.buttonFormat(btnNewSubject, format.newIcon);
		btnNewSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearSubjectFormFields();
			}
		});

		JPanel subjectFormPanel = new JPanel();
		subjectFormPanel.setBounds(602, 60, 370, 422);
		subjectFormPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Course Information Details", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		subjectFormPanel.setLayout(null);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(20, 41, 71, 14);
		format.labelFormat(lblDescription);

		JLabel lblLecUnits = new JLabel("Lec Units");
		lblLecUnits.setBounds(20, 131, 46, 14);
		format.labelFormat(lblLecUnits);

		JLabel lblYearLevel = new JLabel("Year Level");
		lblYearLevel.setBounds(20, 187, 60, 14);
		format.labelFormat(lblYearLevel);

		JLabel lblSubjectSemester = new JLabel("Semester");
		lblSubjectSemester.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSubjectSemester.setBounds(182, 187, 71, 14);
		format.labelFormat(lblSubjectSemester);

		String[] s = { "All", "1st", "2nd", "S" };
		final JComboBox comboBox = new JComboBox(s);
		comboBox.setBounds(21, 19, 73, 30);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sem = comboBox.getSelectedItem().toString();
				if (sem.equals("All"))
					subjectlist = subject.subjectList("All");
				else
					subjectlist = sDAO.subjectsBySem(sem);
				subject_data = new Object[subjectlist.size()][];
				for (int i = 0; i < subjectlist.size(); i++) {
					subject_data[i] = subjectlist.get(i).toObjectArray();
				}
				subjectModel = new DefaultTableModel(subject_data, subject_columns);
				subjectTable.setModel(subjectModel);
				// subjectTable.getUpdateSelectionOnSort();
				// subjectTable.repaint();
			}
		});

		descriptionField = new JTextField();
		descriptionField.setBounds(20, 65, 329, 30);
		descriptionField.setColumns(10);

		lecUnitsField = new JTextField();
		lecUnitsField.setBounds(86, 123, 86, 30);
		lecUnitsField.setColumns(10);

		semCombobox = new JComboBox(sem);
		semCombobox.setBounds(263, 179, 86, 30);
		format.comboBoxFormat(semCombobox);

		yrlvlComboBox = new JComboBox(yr);
		format.comboBoxFormat(yrlvlComboBox);
		yrlvlComboBox.setBounds(86, 179, 86, 30);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Course List", TitledBorder.CENTER,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(20, 60, 550, 470);
		add(panel);
		panel.setLayout(null);

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

		format.tableFormat(subjectTable);
		subjectTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int i = subjectTable.getSelectedRow();
				btnUpdateSubject.setEnabled(true);
				btnAddSubject.setEnabled(false);
				btnDeleteSubject.setEnabled(true);
				subjectID = Integer.parseInt(subjectTable.getValueAt(i, 0).toString());
				descriptionField.setText(subjectModel.getValueAt(i, 1).toString());
				lecUnitsField.setText(subjectModel.getValueAt(i, 2).toString());
				labUnitsField.setText(subjectModel.getValueAt(i, 3).toString());
				yrlvlComboBox.setSelectedItem(subjectModel.getValueAt(i, 4).toString());
				semCombobox.setSelectedItem(subjectModel.getValueAt(i, 5).toString());
				tagField.setText(subjectModel.getValueAt(i,6).toString());
			}
		});

		JScrollPane subjectsScrollPane = new JScrollPane();
		subjectsScrollPane.setBounds(6, 16, 538, 443);

		panel.add(subjectsScrollPane);
		subjectsScrollPane.setViewportView(subjectTable);
		subjectsScrollPane.getViewport().setBackground(Color.WHITE);

		add(btnAddSubject);
		add(btnNewSubject);
		add(btnUpdateSubject);
		add(btnDeleteSubject);
		add(comboBox);
		add(subjectFormPanel);
		subjectFormPanel.add(lblDescription);
		subjectFormPanel.add(lblLecUnits);
		subjectFormPanel.add(lblYearLevel);
		subjectFormPanel.add(lblSubjectSemester);
		subjectFormPanel.add(descriptionField);
		subjectFormPanel.add(lecUnitsField);
		subjectFormPanel.add(semCombobox);
		subjectFormPanel.add(yrlvlComboBox);

		JLabel lblLabUnits = new JLabel("Lab Units");
		lblLabUnits.setBounds(207, 131, 46, 14);
		format.labelFormat(lblLabUnits);
		subjectFormPanel.add(lblLabUnits);

		labUnitsField = new JTextField();
		labUnitsField.setBounds(263, 123, 86, 30);
		subjectFormPanel.add(labUnitsField);
		labUnitsField.setColumns(10);

		JLabel lblTag = new JLabel("Tag");
		lblTag.setBounds(20, 239, 46, 14);
		format.labelFormat(lblTag);
		subjectFormPanel.add(lblTag);

		tagField = new JTextField();
		tagField.setBounds(20, 264, 329, 30);
		subjectFormPanel.add(tagField);
		tagField.setColumns(10);
	}

	public void addSubjectToTable(int subjID, String desc, String lecUnit, String labUnit, String year, String sem, String tag) {
		Object[] newSubject = new Object[subjectTable.getRowCount()];
		newSubject[0] = subjID;
		newSubject[1] = desc;
		newSubject[2] = lecUnit;
		newSubject[3] = labUnit;
		newSubject[4] = year;
		newSubject[5] = sem;
		newSubject[6] = tag;
		subjectModel.addRow(newSubject);
	}

	public void clearSubjectFormFields() {
		btnAddSubject.setEnabled(true);
		btnDeleteSubject.setEnabled(false);
		btnUpdateSubject.setEnabled(false);
		descriptionField.setText("");
		lecUnitsField.setText("");
		labUnitsField.setText("");
		tagField.setText("");
		yrlvlComboBox.setSelectedIndex(-1);
		semCombobox.setSelectedIndex(-1);
	}
}

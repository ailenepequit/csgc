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

import Graph.Models.ExportToExcel;
import Graph.Models.Faculty;
import Graph.Models.Offering;
import Graph.Models.Room;
import Graph.Models.Subject;

@SuppressWarnings("serial")
public class OfferingPanel extends JPanel {

	private int offeringID;
	private JTable table;
	private JButton addBtn, editBtn, btnDeleteOffering;
	
	DefaultComboBoxModel<String> fac = new DefaultComboBoxModel<String>();
	DefaultComboBoxModel<String> sub = new DefaultComboBoxModel<String>();
	DefaultComboBoxModel<String> rm = new DefaultComboBoxModel<String>();
	DefaultTableModel offeringModel;
	
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	DefaultTableCellRenderer marginRenderer = new DefaultTableCellRenderer();
	
	Offering offering = new Offering();
	Subject subject = new Subject();
	Room room = new Room();
	Faculty faculty = new Faculty();

	ArrayList<Offering> courselist = offering.offeringsList();

	Formatter format = new Formatter();

	String[] offerings_columns;
	Object[][] offerings_data;
	String facultyname, bname;
	String[] name;
	
	String sy = ""; //sy
	String sem = ""; //sem
	Object startHr = "", startMin= "", startP= "";
	Object endHr= "", endMin="", endP="";
	String day, selected_subj, selected_fac, slots, selected_room;
	
	public OfferingPanel() {
		setBounds(252, 73, 1002, 586);

		// headers for the table
		offerings_columns = new String[] { "Id", "SY", "Semester", "Start Time", "End Time", "Day", "Description",
				"Faculty", "Class Size", "Room" };

		// actual data for the table in a 2d array
		offerings_data = new Object[courselist.size()][];
		for (int i = 0; i < courselist.size(); i++) {
			offerings_data[i] = courselist.get(i).toTimetableArray();
		}

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 982, 466);
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
		format.tableFormat(table);
		format.tableSorter(offeringModel, table);
		for (int col = 0; col < table.getColumnCount(); col++) {
			if (col == 3 || col == 4 || col == 6 || col == 7)
				table.getColumnModel().getColumn(col).setCellRenderer(marginRenderer);

			else
				table.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
		}

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					editBtn.setEnabled(true);
					btnDeleteOffering.setEnabled(true);
					int i = table.getSelectedRow();
					offeringID = Integer.parseInt(table.getValueAt(i, 0).toString());
					sy = offeringModel.getValueAt(i, 1).toString(); //sy
					sem = offeringModel.getValueAt(i, 2).toString(); //sem

					if (offeringModel.getValueAt(i, 3) == null) {
						startHr = -1;
						startMin = -1;
						startP = -1;
					} else {
						String start_time = offeringModel.getValueAt(i, 3).toString();
						startHr = start_time.substring(0, 2);
						startMin = start_time.substring(3, 5);
						startP = start_time.substring(6, 8);
					}
					if (offeringModel.getValueAt(i, 4) == null) {
						endHr = -1;
						endMin = -1;
						endP = -1;
					} else {
						String end_time = offeringModel.getValueAt(i, 4).toString();
						endHr = end_time.substring(0, 2);
						endMin = end_time.substring(3, 5);
						endP = end_time.substring(6, 8);
					}
					if (offeringModel.getValueAt(i, 5) == null)
						day = "";
					else {
						day = offeringModel.getValueAt(i, 5).toString();
					}
					if (offeringModel.getValueAt(i, 6) == null)
						selected_subj = "";				
					else
						selected_subj = offeringModel.getValueAt(i, 6).toString();
					if (offeringModel.getValueAt(i, 7) == null)
						selected_fac = "";
					else
						selected_fac = offeringModel.getValueAt(i, 7).toString();
					if(offeringModel.getValueAt(i, 8) == null)
						slots = "";
					else
						slots = offeringModel.getValueAt(i, 8).toString();
						
					if (offeringModel.getValueAt(i, 9) == null)
						selected_room = "";
					else
						selected_room = offeringModel.getValueAt(i, 9).toString();
					
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});
		

		JButton printBtn = new JButton("Print");
		printBtn.setBounds(903, 12, 89, 30);
		format.buttonFormat(printBtn, format.printIcon);
		printBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {
				ExportToExcel e = new ExportToExcel(OfferingPanel.this, table);
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
//				if (i >= 0) {
//					offering.deleteOffering(offeringID);
//					offering.deleteDaysched(offeringID);
//					offeringModel.removeRow(i);
//				} else {
//					System.out.println("Delete Error");
//				}
			}
		});

		addBtn = new JButton("Add Offering");
		addBtn.setBounds(10, 12, 125, 30);
		format.buttonFormat(addBtn, format.addIcon);
		addBtn.setBackground(new Color(240, 240, 240));
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				HomeView.formPanel.setVisible(true);
				HomeView.formPanel.clearFields();
				HomeView.formPanel.lblOffering.setText("Create New Offering");
				HomeView.formPanel.btnAddOffering.setVisible(true);
				HomeView.formPanel.btnEditOffering.setVisible(false);
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
				HomeView.formPanel.setFormData(i, offeringID, sy, sem, startHr, startMin, startP, endHr, endMin, endP, day, selected_subj, selected_fac, slots, selected_room);
				HomeView.formPanel.lblOffering.setText("Update Offering");
				HomeView.formPanel.btnAddOffering.setVisible(false);
				HomeView.formPanel.btnEditOffering.setVisible(true);
			}
		});

		JButton btnGenerateTimetable = new JButton("Generate Timetable");
		btnGenerateTimetable.setBounds(159, 12, 163, 30);
		format.buttonFormat(btnGenerateTimetable, format.imagesPath + "automate.png");
		btnGenerateTimetable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				setVisible(false);
//				HomeView.sysem.setVisible(true);
				showOption();
				scrollPane.setViewportView(table);
				HomeView.lblGeneratedTimetableFor.setVisible(true);
				HomeView.lblGeneratedTimetableFor.setText("Generated Schedule for SY " + offering.getSY() + " " + offering.getSemester() + " Semester");
				//HomeView.graphPanel.subjectList = subject.subjectList(offering.getSemester());
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

		setLayout(null);
		add(printBtn);
		add(scrollPane);
		add(btnDeleteOffering);
		add(addBtn);
		add(editBtn);
		add(btnGenerateTimetable);
		add(btnExport);
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

	public void updateOfferingFromTable(int row, int offeringID, Object sy, Object sem, String start_time, String end_time, Object day, Object subject, Object faculty, Object slots, Object room) {
		offeringModel.setValueAt(offeringID, row, 0);
		offeringModel.setValueAt(sy, row, 1);
		offeringModel.setValueAt(sem, row, 2);
		offeringModel.setValueAt(start_time, row, 3);
		offeringModel.setValueAt(end_time, row, 4);
		offeringModel.setValueAt(day, row, 5);
		offeringModel.setValueAt(subject, row, 6);
		offeringModel.setValueAt(faculty, row, 7);
		offeringModel.setValueAt(slots, row, 8);
		offeringModel.setValueAt(room, row, 9);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void showOption(){
		JComboBox syCB = new JComboBox();
		syCB.setModel(new DefaultComboBoxModel(new String[] {"2016-2017", "2017-2018", "2018-2019", "2019-2020", "2021-2022", "2022-2023", "2023-2024", "2024-2025", "2025-2026"}));
		JComboBox semCB = new JComboBox();
		semCB.setModel(new DefaultComboBoxModel(new String[] {"1st", "2nd", "S"}));
		
		Object[] message = {
				"School Year: ", syCB,
				"Semester: ", semCB
		};
		
		int option = JOptionPane.showConfirmDialog(this, message, "Generate Offeing Schedule for: ", JOptionPane.OK_CANCEL_OPTION);
		
		if(option == JOptionPane.OK_OPTION){
			offering.setSY(syCB.getSelectedItem().toString());
			offering.setSemester(semCB.getSelectedItem().toString());
		}
	}
}

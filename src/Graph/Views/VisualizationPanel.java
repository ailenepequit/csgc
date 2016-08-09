package Graph.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Graph.Models.Subject;
import javax.swing.JScrollPane;

@SuppressWarnings({ "serial", "unused" })
public class VisualizationPanel extends JPanel {

	JTextField numFacultyField, numSubjectsField, numRoomsField;
	JTextArea logArea;
	Formatter format = new Formatter();
	private int subject, faculty, room;
	
	public VisualizationPanel() {
		setVisible(false);
		setBackground(Color.WHITE);
		setBounds(253, 476, 1001, 182);
		setLayout(null);

		JButton btnVisualize = new JButton("Visualize");
		format.buttonFormat(btnVisualize, format.imagesPath + "visualize.png");
		btnVisualize.setBounds(819, 11, 173, 30);
		btnVisualize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeView.graphPanel.setVisible(true);;
				HomeView.graphPanel.update();
				HomeView.graphPanel.repaint();
			}
		});

		numSubjectsField = new JTextField();
		numSubjectsField.setHorizontalAlignment(SwingConstants.RIGHT);
		numSubjectsField.setBackground(Color.WHITE);
		numSubjectsField.setEditable(false);
		numSubjectsField.setBounds(934, 62, 58, 25);
		numSubjectsField.setColumns(10);

		numRoomsField = new JTextField();
		numRoomsField.setHorizontalAlignment(SwingConstants.RIGHT);
		numRoomsField.setBackground(Color.WHITE);
		numRoomsField.setEditable(false);
		numRoomsField.setBounds(934, 98, 58, 25);
		numRoomsField.setColumns(10);

		numFacultyField = new JTextField();
		numFacultyField.setHorizontalAlignment(SwingConstants.RIGHT);
		numFacultyField.setBackground(Color.WHITE);
		numFacultyField.setEditable(false);
		numFacultyField.setBounds(934, 134, 58, 25);
		numFacultyField.setColumns(10);

		JLabel lblOfSubjects = new JLabel("# of Subjects:");
		format.labelFormat(lblOfSubjects);
		lblOfSubjects.setBounds(819, 67, 105, 14);

		JLabel lblOfRooms = new JLabel("# of Rooms:");
		format.labelFormat(lblOfRooms);
		lblOfRooms.setBounds(819, 103, 105, 14);

		JLabel lblOfFaculty = new JLabel("# of Faculty:");
		format.labelFormat(lblOfFaculty);
		lblOfFaculty.setBounds(819, 139, 105, 14);

		add(btnVisualize);
		add(numSubjectsField);
		add(numRoomsField);
		add(numFacultyField);
		add(lblOfSubjects);
		add(lblOfRooms);
		add(lblOfFaculty);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 799, 160);
		add(scrollPane);

		logArea = new JTextArea();
		logArea.setRows(8);
		scrollPane.setViewportView(logArea);
		logArea.setText("Processing. . .\n");
		logArea.setLineWrap(true);
		logArea.setBackground(new Color(204, 255, 204));
		logArea.setColumns(2);
		logArea.setMargin(new Insets(10,10,10,10));
		logArea.setEditable(false);
		logArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
	}

	public void setGraphData(int subject, int room, int faculty) {
		this.subject = subject;
		this.room = room;
		this.faculty = faculty;
		numSubjectsField.setText("" + subject);
		numRoomsField.setText("" + room);
		numFacultyField.setText("" + faculty);
	}
}

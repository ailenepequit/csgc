package Graph.Views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Graph.Controllers.WindowController;
import Graph.Models.Faculty;
import Graph.Models.Offering;
import Graph.Models.Room;
import Graph.Models.Subject;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import java.awt.Label;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class HomeView extends JFrame {
	
	private JPanel contentPane, sidebarPanel;
	private JButton homeBtn, subjectsBtn, facultyBtn, roomsBtn, visualizationBtn;

	GraphPanel graphPanel;
	RoomPanel roomsPanel;
	SubjectPanel  subjectsPanel;
	FacultyPanel facultyPanel;
	static OfferingPanel offeringPanel;
	static OfferingFormPanel formPanel;
	
	Offering offering = new Offering();
	Subject subject = new Subject();
	Room room = new Room();
	Faculty faculty = new Faculty();
	
	ArrayList<Subject> subjectList = subject.subjectList();
	int N = subjectList.size();
	
	WindowController w;
	Formatter format = new Formatter();

	public HomeView() {
		contentPane = new JPanel();
		contentPane.setForeground(new Color(47, 79, 79));
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		components();

		setDefaultCloseOperation(0);
		setSize(1269, 728);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Home");
	}

	public void components() {
		headerComponents();
		footerComponents();
		sidebarComponents();
		offeringPanel = new OfferingPanel();
		formPanel = new OfferingFormPanel();
		roomsPanel = new RoomPanel();
		subjectsPanel = new SubjectPanel();
		facultyPanel = new FacultyPanel();
		graphPanel = new GraphPanel(this);
		graphPanel.setLayout(new BorderLayout());
		contentPane.add(offeringPanel);
		contentPane.add(formPanel);
		contentPane.add(roomsPanel);
		contentPane.add(subjectsPanel);
		contentPane.add(facultyPanel);
		contentPane.add(graphPanel);
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
		logoutBtn.setIcon(new ImageIcon(format.imagesPath + "logout.png"));
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

		homeBtn = new JButton("   Home");
		sidebarButtonFormat(homeBtn, format.imagesPath + "homeicon.png");
		homeBtn.setBounds(0, 0, 242, 40);
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSideBarButtonsUnselectedFormat();
				sideBarButtonSelected(homeBtn);
				setPanelsVisibleToFalse();
				offeringPanel.setVisible(true);
			}
		});

		subjectsBtn = new JButton("   Courses");
		sidebarButtonFormat(subjectsBtn, format.imagesPath + "scholarly.png");
		subjectsBtn.setBounds(0, 38, 242, 40);
		subjectsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSideBarButtonsUnselectedFormat();
				sideBarButtonSelected(subjectsBtn);
				setPanelsVisibleToFalse();
				subjectsPanel.setVisible(true);
			}
		});

		facultyBtn = new JButton("   Faculty");
		facultyBtn.setBounds(0, 76, 242, 40);
		sidebarButtonFormat(facultyBtn, format.imagesPath + "faculty.png");
		facultyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setPanelsVisibleToFalse();
				setSideBarButtonsUnselectedFormat();
				sideBarButtonSelected(facultyBtn);
				facultyPanel.setVisible(true);
			}
		});

		roomsBtn = new JButton("   Rooms");
		roomsBtn.setBounds(0, 114, 242, 40);
		sidebarButtonFormat(roomsBtn, format.imagesPath + "chair.png");
		roomsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSideBarButtonsUnselectedFormat();
				sideBarButtonSelected(roomsBtn);
				setPanelsVisibleToFalse();
				roomsPanel.setVisible(true);
			}
		});

		visualizationBtn = new JButton("   Visualization");
		visualizationBtn.setBounds(0, 152, 242, 40);
		sidebarButtonFormat(visualizationBtn, format.imagesPath + "graph.png");
		visualizationBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSideBarButtonsUnselectedFormat();
				sideBarButtonSelected(visualizationBtn);
				setPanelsVisibleToFalse();
				graphPanel.setVisible(true);
				graphPanel.addEdges();
			}
		});

		contentPane.add(sidebarPanel);
		sidebarPanel.add(homeBtn);
		sidebarPanel.add(subjectsBtn);
		sidebarPanel.add(facultyBtn);
		sidebarPanel.add(roomsBtn);
		sidebarPanel.add(visualizationBtn);
	}

	public void sidebarButtonFormat(JButton button, String icon) {
		button.setIcon(new ImageIcon(icon));
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("SansSerif", Font.PLAIN, 12));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(51, 51, 51));
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1),
				BorderFactory.createEmptyBorder(10, 20, 10, 10)));
	}

	public void sideBarButtonSelected(JButton btn){
		btn.setFont(new Font("SansSerif", Font.BOLD, 13));
		btn.setForeground(new Color(46, 139, 87));
	}
	
	public void setSideBarButtonsUnselectedFormat(){
		sideBarButtonUnselected(homeBtn);
		sideBarButtonUnselected(subjectsBtn);
		sideBarButtonUnselected(facultyBtn);
		sideBarButtonUnselected(roomsBtn);
		sideBarButtonUnselected(visualizationBtn);
	}
	
	public void sideBarButtonUnselected(JButton button){
		button.setFont(new Font("SansSerif", Font.PLAIN, 12));
		button.setForeground(Color.WHITE);
	}
	
	public void setPanelsVisibleToFalse(){
		offeringPanel.setVisible(false);
		formPanel.setVisible(false);
		roomsPanel.setVisible(false);
		subjectsPanel.setVisible(false);
		facultyPanel.setVisible(false);
		graphPanel.setVisible(false);
	}
}

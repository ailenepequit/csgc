package Graph.Views;

import Graph.Controllers.WindowController;
import Graph.Models.Course;
import Graph.Models.Graph;
import Graph.Models.GraphAdjacencyMatrix;
import Graph.Models.Room;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class GraphVisualization extends JFrame {
	private JPanel panel, drawPanel;
	private JTextField courseField, roomField;
	private JButton btnSolve, btnTimetable, btnShowRooms, btnShowCourses;
	private JComboBox<?> comboBox;
	private JTextArea textArea;
	private String vertex = null;
	private int x, y, n, x1, y1, x2, y2, numRooms;
	Course course = new Course();
	Room room = new Room();
	Graph g;
	GraphAdjacencyMatrix adjacencyMatrix;
	Graphics2D g2d;
	int Width;
	int result[];
	ArrayList<Course> c = course.courseList();
	private ArrayList<Course> clist;
	ArrayList<Course> noTimeslot = new ArrayList<Course>();
	ArrayList<Course> noRoom = new ArrayList<Course>();
	ArrayList<Room> r = room.roomList();
	ArrayList<Course>[] group;
	ArrayList timeslot_priority;
	WindowController w;

	Color[] timeColors = new Color[30];

	Color[] colors = { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, new Color(75, 0, 130),
			new Color(238, 130, 238), Color.WHITE, Color.GRAY, Color.BLACK, new Color(139, 69, 19),
			new Color(255, 228, 196) };

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GraphVisualization() {
		panel = new JPanel();
		panel.setBackground(new Color(51, 51, 51));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);

		
		randomColor();
		components();
		setVisible(true);
		setSize(1340, 740);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Graph Visualization");
	}

	private void components() {
		drawPanel = new JPanel();
		drawPanel.setBorder(null);
		drawPanel.setBackground(new Color(255, 255, 255));
		drawPanel.setBounds(20, 11, 940, 687);
		drawPanel.setLayout(null);
		JScrollPane scrollFrame = new JScrollPane();
		scrollFrame.setBounds(302, 11, 832, 689);
		scrollFrame.setViewportView(drawPanel);
		drawPanel.setAutoscrolls(true);
		panel.add(scrollFrame);

		courseField = new JTextField();
		courseField.setBounds(1157, 107, 57, 25);
		courseField.setFont(new Font("SansSerif", courseField.getFont().getStyle(), courseField.getFont().getSize()));
		panel.add(courseField);
		courseField.setColumns(10);
		courseField.setText(Integer.toString(course.count()));

		roomField = new JTextField();
		roomField.setBounds(1156, 143, 57, 25);
		roomField.setFont(new Font("SansSerif", courseField.getFont().getStyle(), courseField.getFont().getSize()));
		panel.add(roomField);
		roomField.setColumns(10);
		roomField.setText(Integer.toString(room.count()));
		numRooms = Integer.parseInt(roomField.getText());

		String[] sort = { "Subject", "Faculty", "Class Size", "Degree" };
		comboBox = new JComboBox(sort);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().equals("Subject")) {
					sortCourse("Subject");
				}
				if (comboBox.getSelectedItem().equals("Class Size")) {
					sortCourse("Class Size");
				}
				if (comboBox.getSelectedItem().equals("Faculty")) {
					sortCourse("Faculty");
				}
				if (comboBox.getSelectedItem().equals("Degree")) {
					sortCourse("Degree");
				}
			}
		});
		comboBox.setFont(new Font("SansSerif", Font.PLAIN, 12));
		comboBox.setBounds(1156, 54, 156, 25);
		panel.add(comboBox);

		btnSolve = new JButton("Solve");
		btnSolve.setBounds(1157, 209, 155, 25);
		btnSolve.setFont(new Font("SansSerif", btnSolve.getFont().getStyle(), 12));
		btnSolve.setForeground(Color.WHITE);
		btnSolve.setBackground(Color.BLACK);
		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				
			try{
				assignTimeslotToMThTF();
				assignTimeslotToWSa();
			}catch(Exception e)
			{
				System.err.println(e.getMessage());
			}
			clist = course.offeringsList();
			getDegree();
			if (comboBox.getSelectedItem().equals("Degree"))
				sortCourse("Degree");
				JOptionPane.showMessageDialog(null, "Successfully assigned timeslots to courses.");
				createGraph(drawPanel.getGraphics());
			}
		});

		btnSolve.setFocusPainted(false);
		panel.add(btnSolve);

		btnTimetable = new JButton("Timetable");
		btnTimetable.setBounds(1159, 294, 153, 25);
		btnTimetable.setFont(new Font("SansSerif", btnSolve.getFont().getStyle(), 12));
		btnTimetable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// dispose();
				Course course = new Course();
				HomeView tv = new HomeView("no");
				w = new WindowController(course, tv);
				w.viewHomePage(tv);
			}
		});

		btnTimetable.setForeground(Color.WHITE);
		btnTimetable.setBackground(Color.BLACK);
		btnTimetable.setFocusPainted(false);
		panel.add(btnTimetable);

		btnShowRooms = new JButton("Rooms");
		btnShowRooms.setBounds(1223, 143, 90, 25);
		btnShowRooms.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnShowRooms.setForeground(Color.WHITE);
		btnShowRooms.setBackground(Color.BLACK);
		btnShowRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Room room = new Room();
				RoomView rv = new RoomView();
				w = new WindowController(room, rv);
				w.viewRoomPage(rv);
			}
		});

		btnShowRooms.setFocusPainted(false);
		panel.add(btnShowRooms);

		btnShowCourses = new JButton("Courses");
		btnShowCourses.setBounds(1223, 107, 90, 25);
		btnShowCourses.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnShowCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CourseView cv = new CourseView();
				w = new WindowController(course, cv);
				w.viewCoursePage(cv);
			}
		});

		btnShowCourses.setForeground(Color.WHITE);
		btnShowCourses.setBackground(Color.BLACK);
		btnShowCourses.setFocusPainted(false);
		panel.add(btnShowCourses);

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(1197, 245, 80, 23);
		btnClear.setFont(new Font("SansSerif", btnSolve.getFont().getStyle(), 12));
		btnClear.setForeground(Color.WHITE);
		btnClear.setBackground(new Color(102, 102, 102));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawPanel.repaint();
				drawPanel.revalidate();
				textArea.setText("");
			}
		});
		panel.add(btnClear);

		JLabel lblSortCoursesBy = new JLabel("Sort Courses by");
		lblSortCoursesBy.setForeground(new Color(255, 255, 255));
		lblSortCoursesBy.setFont(new Font("SansSerif", lblSortCoursesBy.getFont().getStyle(), 12));
		lblSortCoursesBy.setBounds(1156, 29, 120, 14);
		panel.add(lblSortCoursesBy);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("SansSerif", lblSortCoursesBy.getFont().getStyle(), 10));
		textArea.setLineWrap(false);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(10, 11, 267, 689);
		scrollPane.setViewportView(textArea);
		panel.add(scrollPane);
	}

	public void createGraph(Graphics gg) {
		
		sortRoomsByCapacity();
		for (int i = 0; i < numRooms; i++) {
			r.get(i).setColor(i);
		}

		n = Integer.parseInt(courseField.getText());
		g = new Graph(n);
		adjacencyMatrix = new GraphAdjacencyMatrix(n);

		// assignTimeSlot();
		drawPanel.paintComponents(gg);
		g2d = (Graphics2D) gg;
		Width = drawPanel.getWidth();

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		drawVertices();
	}

	private void calculateXY(int xy) {
		int Width = drawPanel.getWidth();
		int Height = drawPanel.getHeight();

		x = Width / 2 + (int) (Width * Math.cos(2 * xy * Math.PI / n) / 4.0);
		y = Height / 2 + (int) (Width * Math.sin(2 * xy * Math.PI / n) / 4.0);
	}

	public void drawVertices() {
		for (int k = 0; k < n; k++) {
			vertex = clist.get(k).getSubject();
			
			// System.out.println(clist.get(k).compatibleRooms());
			calculateXY(k);
			g2d.fillOval(x, y, (Width / 2) / n, (Width / 2) / n);
			g2d.drawString(vertex, x, y);
		}

		JOptionPane.showMessageDialog(null, n + " vertices/courses created.");
		drawTimeslotEdges();
	}

	public void drawTimeslotEdges() {
		for (int i = 0; i < n; i++) {
			calculateXY(i);

			x1 = x + (Width / 2) / n / 2;
			y1 = y + (Width / 2) / n / 2;

			for (int j = 0; j < n; j++) {
				calculateXY(j);
				x2 = x + (Width / 2) / n / 2;
				y2 = y + (Width / 2) / n / 2;
				if (i != j) {

//					if (clist.get(i).getFaculty().toString().equals(clist.get(j).getFaculty().toString())) {
//						g.addEdge(i, j);
//						adjacencyMatrix.setEdge(i, j, 1);
//						g2d.setColor(Color.gray);
//						g2d.drawLine(x1, y1, x2, y2);
//						g2d.setFont(new Font("Arial", Font.ITALIC, 10));
//						g2d.drawString(clist.get(i).getFaculty(), ((x1 + x2) / 2), ((y1 + y2) / 2));
//					}
					 String start1 = clist.get(i).getStartTime().toString();
					 String start2 = clist.get(j).getStartTime().toString();
					 String end1 = clist.get(i).getEndTime().toString();
					 String end2 = clist.get(j).getEndTime().toString();
					 Date c1_s = toTime(start1);
					 Date c1_e = toTime(end1);
					 String c1_day = clist.get(i).getDay();
					
					 Date c2_s = toTime(start2);
					 Date c2_e = toTime(end2);
					 String c2_day = clist.get(i).getDay();
					/// System.out.println(c1_s + " " + c2_s);
					if (clist.get(i).getTimeslot().equals(clist.get(j).getTimeslot())) {
						if(clist.get(i).getDay().equals(clist.get(j).getDay())){
							g.addEdge(i, j);
							adjacencyMatrix.setEdge(i, j, 1);
							g2d.setColor(Color.gray);
							g2d.drawLine(x1, y1, x2, y2);
							g2d.setFont(new Font("Arial", Font.ITALIC, 10));
							g2d.drawString(clist.get(i).getTimeslot() + clist.get(i).getDay(), ((x1 + x2) / 2), ((y1 + y2) / 2));
							//g2d.drawString(c2_day + start2 + "-" + end2, ((x1 + x2) / 2), ((y1 + y2) / 2) + 8);
						}
					}


				}
			}
		}
		JOptionPane.showMessageDialog(null, g.getNumEdges() + " edges/conflicts created.");
		colorGraphByTimeslot();
	}

	public void drawFacultyEdges() {
		for (int i = 0; i < n; i++) {
			calculateXY(i);

			x1 = x + (Width / 2) / n / 2;
			y1 = y + (Width / 2) / n / 2;

			for (int j = 0; j < n; j++) {
				calculateXY(j);
				x2 = x + (Width / 2) / n / 2;
				y2 = y + (Width / 2) / n / 2;
				if (i != j) {

					if (clist.get(i).getFaculty().toString().equals(clist.get(j).getFaculty().toString())) {
						g.addEdge(i, j);
						adjacencyMatrix.setEdge(i, j, 1);
						g2d.setColor(Color.gray);
						g2d.drawLine(x1, y1, x2, y2);
						g2d.setFont(new Font("Arial", Font.ITALIC, 10));
						g2d.drawString(clist.get(i).getFaculty(), ((x1 + x2) / 2), ((y1 + y2) / 2));
					}

				}
			}
		}
		JOptionPane.showMessageDialog(null, g.getNumEdges() + " edges/conflicts created.");
		colorGraphByTimeslot();
	}

	public void colorGraphByTimeslot() {
		g.greedyColoring();
		adjacencyMatrix.graphColoring(12);
		textArea.setText("Results:\n");

		System.out.print(g.getResult());
		int[] c = adjacencyMatrix.getSolution();
		int[] d = g.color();
		for (int k = 0; k < n; k++) {
			if (k < d.length) {
				vertex = Integer.toString(clist.get(k).getID());
				calculateXY(k);
				g2d.setColor(timeColors[d[k]]);
				clist.get(k).setAssignedColor(d[k]);
				clist.get(k).setRoom(clist.get(k).getID(), r.get(d[k]).getID());
				textArea.append(clist.get(k).getSubject() + " -> " + r.get(d[k]).getName() + "\n");
				try {
					Thread.sleep(100);
					g2d.fillOval(x, y, (Width / 2) / n, (Width / 2) / n);
					g2d.setFont(new Font("Arial", Font.ITALIC, 12));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Completely coloring unsuccessful.\n Maximum number of rooms used. \nNumber of colored courses: "
								+ c.length + "\n");
			}
		}
//		 coloredGroups();
//		 String colored="";
//		 for(int j = 0; j < g.numColorsUsed(); j++){
//		 colored += "Group[" + j +"] : " + group[j] + "\n";
//		 }

		JOptionPane.showMessageDialog(null,
				"Coloring graph successful.\n Number of rooms used: " + g.numColorsUsed() + "\n");
	}

	public void sortCourse(String sort) {
		Collections.sort(clist, new Comparator<Course>() {
			@Override
			public int compare(Course course1, Course course2) {
				String s = sort;
				int num = 0;
				switch (s) {
				case "Subject":
					return course1.getSubject().compareTo(course2.getSubject());

				case "Class Size":
					return Integer.compare(course2.getClassSize(), course1.getClassSize());

				case "Faculty":
					return course1.getFaculty().compareTo(course2.getFaculty());

				case "Degree":
					return Integer.compare(course2.getDegree(), course1.getDegree());

				case "Units":
					return Double.compare(course2.getUnits(), course1.getUnits());

				default:
					return num;
				}
			}
		});
	}

	public void sortRoomsByCapacity() {
		Collections.sort(r, new Comparator<Room>() {
			@Override
			public int compare(Room r1, Room r2) {
				return Integer.compare(r2.getCapacity(), r1.getCapacity());
			}
		});
	}

	private void getDegree() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i != j) {
					//

//					if (clist.get(i).getFaculty().toString().equals(clist.get(j).getFaculty().toString())) {
//						g.addEdge(i, j);
//						adjacencyMatrix.setEdge(i, j, 1);
//					}

					 String s1 = clist.get(i).getTimeslot().toString() + " " +
					 clist.get(i).getDay();
					 String s2 = clist.get(j).getTimeslot().toString() + " " +
					 clist.get(j).getDay();
					 if (s1.equals(s2)) {
					 if (s1.contains("null-null")) {
					
					 } else {
					 g.addEdge(i, j);
					 adjacencyMatrix.setEdge(i, j, 1);
					 }
					 }
				}
			}
		}
		for (int k = 0; k < n; k++) {
			clist.get(k).setDegree(adjacencyMatrix.degree(k));
		}
	}

	public void assignTimeSlot() {
		String[] timeslot_p = { "07:30 AM", "09:00 AM", "10:30 AM", "12:00 PM", "01:30 PM", "03:00 PM", "04:30 PM",
				"06:00 PM" };
		String[] timeslot_wsa = { "07:00 AM", "09:00 AM", "11:00 AM", "01:00 PM", "03:00 PM", "05:00 PM", "07:00 PM" };
		String[] timeslot_u = { "07:30 PM" };
		//
		// for (int i = 0; i < n; i++) {
		// if (clist.get(i).getTimeslot().contains("null-null")) {
		// noTimeslot.add(clist.get(i));
		// }
		// if (clist.get(i).getRoom().equals(null) ||
		// clist.get(i).getRoom().contains("")) {
		// noRoom.add(clist.get(i));
		// }
		// if (clist.get(i).getSubject().contains("Lab")) {
		// String start_time = "";
		// String end_time +=
		try {
			Calendar now = Calendar.getInstance();
			// add hours to current date using Calendar.add method
			now.add(Calendar.HOUR, 10);
			now.add(Calendar.MINUTE, 30);

			// System.out.println("New time after adding 10 hours : " +
			// now.get(Calendar.HOUR_OF_DAY) + ":"
			// + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND));
			String dateStr = "07:30 AM";
			DateFormat formatter = new SimpleDateFormat("HH:mm aa");
			Date startDate = (Date) formatter.parse(dateStr);
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.HOUR_OF_DAY, 1);
			startDate = cal.getTime();
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
		// }
		// }
	}

	public String printResult() {
		String res = "";
		for (int u = 0; u < n; u++)
			res += "Course " + clist.get(u).getSubject() + " --->  Color " + clist.get(u).getAssignedColor() + "\n";
		return res;
	}

	public void groupByColor() {
		ArrayList<Course> group;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < colors.length; j++) {
				if (clist.get(i).getAssignedColor() == j) {
					group = new ArrayList<Course>();
					group.add(clist.get(i));
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void coloredGroups() {
		group = (ArrayList<Course>[]) new ArrayList[numRooms];
		noRoom = clist;

		for (int j = 0; j < g.numColorsUsed() - 1; j++) {
			for (int i = 0; i < n; i++) {
				if (noRoom.isEmpty() == false) {
					if (clist.get(i).getAssignedColor() == j) {
						group[j].add(clist.get(i));
						noRoom.remove(clist.get(i));
					}
				}
				System.out.print("Group[" + j + "] : " + group[j]);
			}
		}
	}

	public Date toTime(String time) {
		DateFormat df = new SimpleDateFormat("HH:mm aa");
		Date date = null;
		try {
			date = df.parse(time);
			DateFormat _df = new SimpleDateFormat("hh:mm:ss");
			time = _df.format(date);
			date = _df.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public void randomColor() {
		int red;
		int green;
		int blue;
		for (int i = 0; i < 30; i++) {
			red = (int) (Math.random() * 256);
			green = (int) (Math.random() * 256);
			blue = (int) (Math.random() * 256);
			timeColors[i] = new Color(red, green, blue);
		}
	}
	
	
	public void assignTimeslotToMThTF(){
		
		ArrayList<Course> twiceAWeekCourses = course.courseListByUnits(">");
		String[] timeslot_p = { "07:30 AM", "09:00 AM", "10:30 AM", "12:00 PM", "01:30 PM", "03:00 PM", "04:30 PM" };
		String[] days_p = {"MTh", "TuF"};
		
		String[] timeslot_u = {"06:00 PM", "07:30 PM" };
		int n = twiceAWeekCourses.size();
		int m = 0;
		int count = 0;
		while (m < n) {
			for (int d = 0; d < days_p.length; d++) {

				for (int t = 0; t < timeslot_p.length; t++) {
					if (count == room.count()) {
						count = 0;
						break;
					}
					if (count < room.count()) {

						// for (Course c : twiceAWeekCourses) {
						if(t < timeslot_p.length)
							twiceAWeekCourses.get(m).setStartTime(timeslot_p[t]);
						if (t == 6)
							twiceAWeekCourses.get(m).setEndTime("06:00 PM");
						else
							twiceAWeekCourses.get(m).setEndTime(timeslot_p[t + 1]);
						twiceAWeekCourses.get(m).setDay(days_p[d]);

						System.out.println(m + " " + twiceAWeekCourses.get(m).getSubject() + " "
								+ twiceAWeekCourses.get(m).getStartTime() + "-" + twiceAWeekCourses.get(m).getEndTime()
								+ " " + twiceAWeekCourses.get(m).getDay());

						course.addDaySched(twiceAWeekCourses.get(m).getID(),
								twiceAWeekCourses.get(m).getDaycode(twiceAWeekCourses.get(m).getDay()),
								to24hr(twiceAWeekCourses.get(m).getStartTime().toString()),
								to24hr(twiceAWeekCourses.get(m).getEndTime().toString()));
						twiceAWeekCourses.remove(c);
						m++;
						count++;

					}
					if (m == n)
						break;
				}
			}
		}
		System.out.println(n);
		
	}
	
	public void assignTimeslotToWSa(){
		ArrayList<Course> twoHrCourses = course.courseListByUnits("=");
		String[] days_p = {"W", "Sa"};
		String[] timeslot_wsa = { "07:00 AM", "09:00 AM", "11:00 AM", "01:00 PM", "03:00 PM", "05:00 PM", "7:00 PM"};
		int n = twoHrCourses.size();
		int m = 0;
		int count = 0;
		while (m < n) {
			for (int d = 0; d < days_p.length; d++) {

				for (int t = 0; t < timeslot_wsa.length; t++) {

					// for (Course c : twoHrCourses) {
					if (count == 12) {
						count = 0;
						break;
					}
					if (count < room.count()) {

						if(t < timeslot_wsa.length)
							twoHrCourses.get(m).setStartTime(timeslot_wsa[t]);
						if (t == 6)
							twoHrCourses.get(m).setEndTime("09:00 PM");
						else
							twoHrCourses.get(m).setEndTime(timeslot_wsa[t + 1]);
						twoHrCourses.get(m).setDay(days_p[d]);

						System.out.println(
								m + " " + twoHrCourses.get(m).getSubject() + " " + twoHrCourses.get(m).getStartTime()
										+ "-" + twoHrCourses.get(m).getEndTime() + " " + twoHrCourses.get(m).getDay());
						course.addDaySched(twoHrCourses.get(m).getID(),
								twoHrCourses.get(m).getDaycode(twoHrCourses.get(m).getDay()),
								to24hr(twoHrCourses.get(m).getStartTime().toString()),
								to24hr(twoHrCourses.get(m).getEndTime().toString()));
						twoHrCourses.remove(c);
						m++;
						count++;
					}
				}
				if (m == n)
					break;
			}
		}
		System.out.println(n);
	}

	public String to24hr(String time) {
		DateFormat df = new SimpleDateFormat("hh:mm aa");
		Date date = null;
		try {
			date = df.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		DateFormat _df = new SimpleDateFormat("HH:mm:ss");
		//System.out.println(_df.format(date));
		return _df.format(date);
	}
}

package Graph.Views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Graph.Controllers.WindowController;
import Graph.Models.Offering;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField userField;
	private JPasswordField passwordField;
	static Offering offering = new Offering();
	static ArrayList<Offering> c = offering.offeringsList();;

	public static void main(String[] args) {	
//		c = offering.offeringsList();
//		for (int i = 0; i < c.size(); i++) {
//			c.get(i).setRoom(i + 1, 0);
//		}
//		offering.deleteAllDayscheds();
		new Login();
	}
	
	public Login() {
		setResizable(false);
		setBounds(100, 100, 450, 305);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		userField = new JTextField();
		userField.setForeground(Color.BLACK);
		userField.setFont(new Font("SansSerif", Font.PLAIN, 13));
		userField.setBounds(177, 96, 170, 23);
		userField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(userField);
		userField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.BLACK);
		passwordField.setBounds(177, 128, 170, 23);
		passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(passwordField);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblUsername.setBounds(87, 99, 80, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblPassword.setBounds(87, 130, 80, 14);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = userField.getText();
				@SuppressWarnings("deprecation")
				String pwd = passwordField.getText();
				if(verifyUser(user,pwd) == true){
					HomeView tv = new HomeView("clear");
					WindowController w = new WindowController(offering, tv);
					dispose();
					w.viewHomePage(tv);
				} else
					JOptionPane.showMessageDialog(null, "Invalid username or password.",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnLogin.setBounds(174, 185, 89, 30);
		contentPane.add(btnLogin);
		
		JLabel header = new JLabel("ABSkeD");
		header.setFont(new Font("SansSerif", Font.BOLD, 20));
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setForeground(Color.WHITE);
		header.setBackground(Color.BLACK);
		header.setOpaque(true);
		header.setBounds(0, 0, 444, 48);
		contentPane.add(header);
		
		JLabel footer = new JLabel("");
		footer.setBackground(Color.BLACK);
		footer.setOpaque(true);
		footer.setBounds(0, 252, 444, 24);
		contentPane.add(footer);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 444, 276);
		label.setBackground(new Color(46, 139, 87));
		label.setOpaque(true);
		contentPane.add(label);
		label.setOpaque(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Login");
		setContentPane(contentPane);
	}

	public boolean verifyUser(String user, String pwd) {
		if(user.equals("admin") && pwd.equals("admin123"))
			return true;

		return false;
	}
}

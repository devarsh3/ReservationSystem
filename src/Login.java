 //Devarsh Patwa
//Dec 2018
//this is the login page for the user

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener{

	private String username;
	private String password;
	private JPasswordField passField;
	private JTextField userField;
	private TextPicture userText, passText, heading, info;
	private JCheckBox show;
	private JButton btnLogin, btnBack;
	private WelcomePage welcomePage;


	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param the pasword to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	

	public Login() throws IOException {
		super("Login Window");
		//create components
		userField = new JTextField();
		passField = new JPasswordField();
		userText = new TextPicture();
		passText = new TextPicture();
		show = new JCheckBox("Show Password");
		btnLogin = new JButton("Login");
		btnBack = new JButton("Back");
		heading = new TextPicture();
		info = new TextPicture();

		String id[] = new String[2]; //to put the info
		id = Loader.loadFile("Login.txt"); //
		setUsername(id[0]);
		setPassword(id[1]);
		
		
		//set the user text
		userText.setTitle("Username");
		userText.setColor(Color.WHITE);
		userText.setBounds(130,150,150,30);
		userText.setFont(new Font("Monospaced", Font.BOLD, 18));

		//set the password text
		passText.setTitle("Password");
		passText.setColor(Color.WHITE);
		passText.setBounds(130,250,150,30);
		passText.setFont(new Font("Monospaced", Font.BOLD, 18));

		//set the heading
		heading.setTitle("Login using credentials");
		heading.setColor(Color.WHITE);
		heading.setBounds(70,100,450,40);
		heading.setFont(new Font("Monospaced", Font.BOLD, 18));

		info.setTitle("");
		info.setColor(Color.WHITE);
		info.setBounds(30,450,450,40);
		info.setFont(new Font("Monospaced", Font.BOLD, 18));

		//add the text
		add(userText);
		add(passText);
		add(heading);
		add(info);
		
		//set the fields
		userField.setBounds(160,190,150,30);
		add(userField);

		passField.setBounds(160,290,150,30);
		add(passField);

		//add check box for show password
		show.setBounds(260,325,150,25);
		show.setBackground(new Color(51,153,255));
		show.setForeground(Color.WHITE);
		add(show);

		//add button
		btnLogin.setBounds(170,360,120,40);
		btnLogin.setBackground(Color.WHITE); //set button white color
		add(btnLogin);

		btnBack.setBounds(10,10,70,40);
		btnBack.setBackground(Color.WHITE);
		add(btnBack);

		//listen to buttons
		btnLogin.addActionListener(this);
		show.addActionListener(this);
		btnBack.addActionListener(this);
		
		

		setLayout(null); //set the layout to null
		setSize(500,600); //set the window size
		// centers the frame on screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		// changes background color of frame
		getContentPane().setBackground(new Color (51,153,255));
		
		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {

		if(show.isSelected()) { //if show password is selected
			passField.setEchoChar((char)0); //show the password
		}
		else { //if it not selected
			passField.setEchoChar('\u2022'); //hide the password
		}

		if(e.getSource()==btnLogin) {

			if(userField.getText().equals(getUsername()) && passField.getText().equals(getPassword())) { //if username and password is correct
				try {
					Admin gui = new Admin();
					dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"ERROR");
				}

			}
			else {
				info.setTitle("Wrong Username or Password"); //if incorrect
			}
		}

		if(e.getSource()==btnBack) {
			welcomePage = new WelcomePage();
			dispose();
		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Login gui = new Login();

	}



}

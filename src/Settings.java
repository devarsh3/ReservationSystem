//Devarsh Patwa
//Jan 2019
//This the gui where the manager can change the help files, change timings, and change username password

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Settings extends JFrame implements ActionListener{

	private JButton btnBack,btnAdminHelp, btnHelp, btnChange,btnTime,btnMenu;
	private JTextField userField;
	private JPasswordField passField;
	private TextPicture txtUser,txtPass, txtHeading, txtEdit;
	private JCheckBox show;
	private ImageIcon image;
	private ImagePicture pic;
	private Admin admingui;

	public Settings() {
		super("Privacy Settings");
		//create components
		btnBack = new JButton("Back");
		btnAdminHelp = new JButton("Admin Help Page");
		btnHelp = new JButton("Customer Help Page");
		btnChange = new JButton("Save");
		btnTime = new JButton("Timings");
		userField = new JTextField();
		passField = new JPasswordField();
		txtUser = new TextPicture("Set New Username");
		txtPass = new TextPicture("Set New Password");
		txtHeading = new TextPicture("Change Username/Password");
		txtEdit = new TextPicture("Edit:");
		image = new ImageIcon("src/Settings.jpg");
		show = new JCheckBox("Show Password");
		btnMenu = new JButton("Menu");
		pic = new ImagePicture(image);

		//set color white
		txtHeading.setColor(Color.WHITE);
		txtUser.setColor(Color.WHITE);
		txtPass.setColor(Color.WHITE);
		txtEdit.setColor(Color.WHITE);
		btnBack.setBackground(Color.WHITE);
		btnAdminHelp.setBackground(Color.WHITE);
		btnHelp.setBackground(Color.WHITE);
		btnChange.setBackground(Color.WHITE);
		btnTime.setBackground(Color.WHITE);
		btnMenu.setBackground(Color.WHITE);
		show.setBackground(new Color(51,153,255));
		show.setForeground(Color.WHITE);

		//set bounds
		pic.setBounds(20,-100,400,400);
		txtHeading.setBounds(30,150,300,40);
		txtUser.setBounds(30,200,200,40);
		userField.setBounds(50,240,200,25);
		txtPass.setBounds(30,280,200,40);
		passField.setBounds(50,320,200,25);
		btnChange.setBounds(280,280,80,30);
		txtEdit.setBounds(30,360,150,40);
		btnAdminHelp.setBounds(10,410,130,30);
		btnHelp.setBounds(145,410,150,30);
		btnTime.setBounds(300,410,80,30);
		btnBack.setBounds(190,480,100,30);
		btnMenu.setBounds(385,410,80,30);
		show.setBounds(200,350,150,25);
		
		//add
		add(pic);
		add(btnBack);
		add(btnAdminHelp);
		add(btnHelp);
		add(btnChange);
		add(userField);
		add(passField);
		add(txtUser);
		add(txtPass);
		add(txtHeading);
		add(txtEdit);
		add(btnTime);
		add(btnMenu);
		add(show);
		
		//make buttons listen
		btnBack.addActionListener(this);
		btnChange.addActionListener(this);
		btnAdminHelp.addActionListener(this);
		btnHelp.addActionListener(this);
		btnTime.addActionListener(this);
		show.addActionListener(this);
		btnMenu.addActionListener(this);
		
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnBack) { //if back button clicked
			try {
				dispose();
				admingui = new Admin();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,"ERROR");
			}//open the admin window
		}
		
		if(show.isSelected()) { //if show password is selected
			passField.setEchoChar((char)0); //show the password
		}
		else { //if it not selected
			passField.setEchoChar('\u2022'); //hide the password
		}

		if(e.getSource()==btnChange) { //if save button is clicked

			if(userField.getText().equals("")||passField.getText().equals("")) { //if any field is empty
				txtHeading.setTitle("Please Fill in all the information"); //set heading
			}
			else{
				String id[] = new String[2];
				id[0]= userField.getText();
				id[1] = passField.getText(); //get the username and password
				try {
					Loader.writeFile("Login.txt", id); //save them into a file
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"ERROR");
				}//end else if
				txtHeading.setTitle("Information Saved"); //set heading
			} //end else
		}//end btnchange
		
		if(e.getSource()==btnTime) { //if timings is clicked
			ProcessBuilder pb = new ProcessBuilder("Notepad.exe","src/time.txt"); //find the file in notepad
			try {
				pb.start(); //open the file in notepad which has the timings
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,"ERROR");
			}
		}
		if(e.getSource()==btnAdminHelp) { //if admin help page is clicked
			ProcessBuilder pb = new ProcessBuilder("Notepad.exe","src/AdminHelpText.txt"); //find the file in notepad
			try {
				pb.start(); //open the file in notepad 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,"ERROR");
			}
		}
		
		if(e.getSource()==btnHelp) { //if timings is clicked
			ProcessBuilder pb = new ProcessBuilder("Notepad.exe","src/Help.txt"); //find the file in notepad
			try {
				pb.start(); //open the file in notepad 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,"ERROR");
			}
		}
		if(e.getSource()==btnMenu) { //if timings is clicked
			ProcessBuilder pb = new ProcessBuilder("Notepad.exe","src/Menu.txt"); //find the file in notepad
			try {
				pb.start(); //open the file in notepad 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,"ERROR");
			}
		}
		

	}

	public static void main(String[] args) {
		Settings gui = new Settings();

	}



}

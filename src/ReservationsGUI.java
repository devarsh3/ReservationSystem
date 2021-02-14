//Devarsh Patwa
//Dec 2018
//This is the user interface to make a reservation where the customer can put in information and book

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ReservationsGUI extends JFrame implements ActionListener{

	private JTextField txtName, txtPhone, txtPeople, txtTime;
	private JTextArea rArea;
	private JButton btnBook, btnBack;
	private TextPicture name,phone,people,time, info;
	private ImageIcon image;
	private ImagePicture bg;
	private String[] timeOptions;
	private JComboBox t;
	private ReservationRecord record;
	private WelcomePage welcomegui;

	public ReservationsGUI() throws IOException {
		super("Reservation Page");
		//create
		txtName = new JTextField();
		txtPhone = new JTextField();
		txtPeople = new JTextField();
		txtTime = new JTextField();
		rArea = new JTextArea();
		btnBook = new JButton("Book Reservation");
		btnBack = new JButton("Back");
		name = new TextPicture();
		phone = new TextPicture();
		people = new TextPicture();
		time = new TextPicture();
		info = new TextPicture();
		image = new ImageIcon("src/reservation.png");
		bg = new ImagePicture(image);
		record = new ReservationRecord();

		String[] timeOpt = Loader.loadFile("time.txt"); //load the time options
		timeOptions = new String[timeOpt.length]; //create the options to dsiplay
		ReservationRecord[] timeRecords = new ReservationRecord[timeOpt.length]; //create records to get time
		for(int i = 0;i<timeOpt.length;i++) {
			timeRecords[i] = new ReservationRecord(); //create records 
			timeRecords[i].setTime(Integer.parseInt(timeOpt[i])); //set time of each record
			timeOpt[i] = timeRecords[i].timeMeridian(timeRecords[i].getTime()); //get the time in the time options
		}

		t = new JComboBox(timeOpt); //display the time options in the drop down menu

		//set title	
		name.setTitle("Name");
		phone.setTitle("Phone Number");
		people.setTitle("Number Of People (Max 40)");
		time.setTitle("Select Time");
		info.setTitle("");

		//set font	
		name.setFont(new Font("Times New Roman",Font.BOLD,20));
		phone.setFont(new Font("Times New Roman",Font.BOLD,20));
		people.setFont(new Font("Times New Roman",Font.BOLD,20));
		time.setFont(new Font("Times New Roman",Font.BOLD,20));
		info.setFont(new Font("Times New Roman",Font.BOLD,20));
		btnBook.setFont(new Font("Times New Roman",Font.BOLD,18));
		btnBack.setFont(new Font("Times New Roman",Font.BOLD,18));

		//set color 
		name.setColor(Color.WHITE);
		phone.setColor(Color.WHITE);
		people.setColor(Color.WHITE);
		time.setColor(Color.WHITE);
		info.setColor(Color.WHITE);
		btnBook.setBackground(Color.WHITE);
		btnBack.setBackground(Color.WHITE);

		//set positions
		name.setBounds(30,150,100,40);
		txtName.setBounds(60,190,300,30);
		phone.setBounds(30,230,180,40);
		txtPhone.setBounds(60,270,300,30);
		people.setBounds(30,310,350,40);
		txtPeople.setBounds(60,350,300,30);
		time.setBounds(30,390,280,40);
		t.setBounds(60,440,100,30);
		rArea.setBounds(450,180,300,300);
		info.setBounds(300,500,500,50);
		btnBook.setBounds(150,650,200,40);
		btnBack.setBounds(650,650,100,40);
		bg.setBounds(80,-100,600,300);

		//add the components
		add(name);
		add(txtName);
		add(phone);
		add(txtPhone);
		add(people);
		add(txtPeople);
		add(time);
		add(t);
		add(rArea);
		add(info);
		add(btnBook);
		add(btnBack);
		add(bg);

		rArea.setEditable(false);//set the output are not editable

		//listen to buttons
		btnBook.addActionListener(this);
		btnBack.addActionListener(this);
		t.addActionListener(this);


		setLayout(null); //set layout
		setSize(900,800); //set size
		setLocation(100,100); //set the starting location on screen
		setVisible(true); //set it visible
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// centers the frame on screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		getContentPane().setBackground(new Color(51,153,255));
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource()==btnBack) {
			welcomegui = new WelcomePage(); //open the welcome page
			dispose();
		}

		if(e.getSource()==btnBook) {
			if(txtName.getText().equals("")||txtPhone.getText().equals("")||txtPeople.getText().equals("")) { //if any info is left empty
				info.setTitle("Please Fill in all the information"); //display
			}
			else {
				if(Integer.parseInt(txtPeople.getText())>40) { //if number of ppl is more than 40
					info.setTitle("No space Available"); 
				}
				else { 
					record = new ReservationRecord(); //create new record	
					record.setName(txtName.getText()); //set name
					record.setPhoneNumber(txtPhone.getText()); //set phone number
					record.setPeople(Integer.parseInt(txtPeople.getText())); //set people
					String time = (String)t.getSelectedItem(); //get the selected time
					switch(time) { //set the time
					case "11:00am":
						record.setTime(1100);
						break;
					case "11:15am":
						record.setTime(1115);
						break;
					case "11:30am":
						record.setTime(1130);
						break;
					case "11:45am":
						record.setTime(1145);
						break;
					case "12:00pm":
						record.setTime(1200);
						break;
					case "12:15pm":
						record.setTime(1215);
						break;
					case "12:30pm":
						record.setTime(1230);
						break;
					case "12:45pm":
						record.setTime(1245);
						break;
					case "1:00pm":
						record.setTime(1300);
						break;
					case "1:15pm":
						record.setTime(1315);
						break;
					case "1:30pm":
						record.setTime(1330);
						break;
					case "1:45pm":
						record.setTime(1345);
						break;
					case "2:00pm":
						record.setTime(1400);
						break;
					case "2:15pm":
						record.setTime(1415);
						break;
					case "2:30pm":
						record.setTime(1430);
						break;
					case "2:45pm":
						record.setTime(1445);
						break;
					}

					rArea.setText(record.toStringReservation()); //display the reservation 
					info.setTitle("Reservation Booked! Proceed to Order!"); 
					btnBook.setEnabled(false); //disable the button

					try {
						Loader.writeFile("FinalReservations.txt",record); //add the reservation into a file
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"ERROR");
					} //end try catch
				}
			}//end else
		}//end button

	}//end actionlistener

	public static void main(String[] args) throws IOException {
		ReservationsGUI gui = new ReservationsGUI();

	}



}

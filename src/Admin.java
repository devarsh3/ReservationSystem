//Devarsh Patwa
//Jan 2019
//This the main gui for the admin where he can see all the reservations/Takeouts and access settings


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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Admin extends JFrame implements ActionListener {

	private JTextArea resArea;
	private JTextField delete;
	private JButton btnLog, btnSort[], btnClear[],btnDelete[],btnHelp,btnExit,btnSettings, btnRes,btnTakeouts;
	private TextPicture txtEdit,txtHeading, txtSettings;
	private ImagePicture pic;
	private ReservationList rList;
	private ReservationList tList;
	private JScrollPane scroller;


	public Admin() throws IOException {
		super("Admin");
		//create components
		resArea = new JTextArea();
		delete = new JTextField("Enter name");
		btnLog = new JButton("Log Out");
		btnSort = new JButton[2];
		btnSort[0] = new JButton("Sort by time");
		btnSort[1] = new JButton("Sort by time");
		btnClear = new JButton[2];
		btnClear[0] = new JButton("Clear Reservations");
		btnClear[1] = new JButton("Clear Takeouts");
		btnDelete = new JButton[2];
		btnDelete[0] = new JButton ("Delete");
		btnDelete[1] = new JButton("Paid");
		btnExit = new JButton("Exit");
		btnHelp = new JButton("Help");
		btnRes = new JButton("Reservations");
		btnTakeouts = new JButton("Takeouts");
		btnSettings = new JButton("Privacy Settings");
		txtEdit = new TextPicture("Edit Reservations");
		txtSettings = new TextPicture("Settings");
		txtHeading = new TextPicture("Control Panel");
		pic = new ImagePicture(new ImageIcon("src/admin.gif"));
		rList = new ReservationList(); 
		tList = new ReservationList();
		scroller = new JScrollPane(resArea);

		//set font and color		
		txtEdit.setFont(new Font("Monospaced", Font.BOLD, 22));
		txtEdit.setColor(Color.WHITE);

		txtSettings.setFont(new Font("Monospaced", Font.BOLD, 22));
		txtSettings.setColor(Color.WHITE);

		txtHeading.setFont(new Font("Monospaced", Font.BOLD, 25));
		txtHeading.setColor(Color.WHITE);

		//set Buttons color
		btnLog.setBackground(Color.WHITE);
		btnRes.setBackground(Color.WHITE);
		btnTakeouts.setBackground(Color.WHITE);
		for (int i = 0;i<2;i++) {
			btnSort[i].setBackground(Color.WHITE);
			btnClear[i].setBackground(Color.WHITE);
			btnDelete[i].setBackground(Color.WHITE);
		}
		btnHelp.setBackground(Color.WHITE);
		btnExit.setBackground(Color.WHITE);
		btnSettings.setBackground(Color.WHITE);



		//set bounds
		scroller.setBounds(450, 100, 375, 600);
		btnRes.setBounds(500,50,140,30);
		btnTakeouts.setBounds(650,50,140,30);
		txtEdit.setBounds(20,100,250,30);
		txtHeading.setBounds(60,50,250,30);
		txtSettings.setBounds(20,250,150,40);
		for (int i = 0;i<2;i++) {
			btnSort[i].setBounds(30,150,130,30);
			btnDelete[i].setBounds(250,200,100,30);
			btnClear[i].setBounds(180,150,150,30);
		}
		delete.setBounds(30,200,200,30);
		btnHelp.setBounds(180,650,100,30);
		btnExit.setBounds(330,650,100,30);
		btnLog.setBounds(40,650,100,30);
		pic.setBounds(-15,300,400,400);
		btnSettings.setBounds(30,300,150,30);

		//add components
		add(txtEdit);
		add(txtHeading);
		add(btnRes);
		add(btnTakeouts);
		add(delete);
		add(btnLog);
		add(btnSort[0]);
		add(btnClear[0]);
		add(btnDelete[0]);
		add(btnSort[1]);
		add(btnClear[1]);
		add(btnDelete[1]);
		add(btnHelp);
		add(btnExit);
		add(pic);
		add(txtSettings);
		add(btnSettings);
		add(scroller);

		btnSort[1].setVisible(false); //set visibility to false
		btnClear[1].setVisible(false);
		btnDelete[1].setVisible(false);

		//make buttons listen
		btnLog.addActionListener(this);
		for (int i = 0;i<2;i++) {
			btnClear[i].addActionListener(this);
			btnDelete[i].addActionListener(this);
			btnSort[i].addActionListener(this);
		}
		btnHelp.addActionListener(this);
		btnExit.addActionListener(this);
		btnSettings.addActionListener(this);
		btnRes.addActionListener(this);
		btnTakeouts.addActionListener(this);


		resArea.setEditable(false); //not editable



		String output[];
		output = Loader.loadFile("FinalReservations.txt"); //load the file into a string
	
		ReservationRecord record[] = new ReservationRecord[output.length]; //creates array of reservation
		for (int i = 0;i<output.length;i++) { //until it's less than lines in the file
			record[i] = new ReservationRecord();//create new record
			record[i].processReservation(output[i]); //process the information from string into record
			rList.insert(record[i]); //insert records
		}
		resArea.setText(rList.toString());

		String output1[];
		try {
			output1 = Loader.loadFile("TakeOut.txt");
			ReservationRecord record1[] = new ReservationRecord[output1.length]; //creates array of reservation
			for (int i = 0;i<output1.length;i++) { //until it's less than lines in the file
				record1[i] = new ReservationRecord();//create new record
				record1[i].processTakeout(output1[i]); //process the information from string into record
				tList.insert(record1[i]); //insert records
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"ERROR");
		}

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
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnRes) {
			resArea.setText(rList.toString()); //display reservations
			//change the buttons
			btnSort[1].setVisible(false); 
			btnClear[1].setVisible(false);
			btnDelete[1].setVisible(false);
			btnSort[0].setVisible(true);
			btnClear[0].setVisible(true);
			btnDelete[0].setVisible(true);
			txtEdit.setTitle("Edit Reservations"); //change text
		}
		if(e.getSource()==btnTakeouts) {
			resArea.setText(tList.toStringTakeout());
			btnSort[0].setVisible(false);
			btnClear[0].setVisible(false);
			btnDelete[0].setVisible(false);
			btnSort[1].setVisible(true);
			btnClear[1].setVisible(true);
			btnDelete[1].setVisible(true);
			txtEdit.setTitle("Edit Takeouts");
		}

		if(e.getSource()==btnClear[1]){
			try {
				Loader.clearFile("TakeOut.txt");//clear the file
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,"ERROR");
			}
			resArea.setText("");
		}

		if (e.getSource()==btnSort[1]) { //if sort clicked
			tList.shellSort();
			resArea.setText(tList.toStringTakeout());
		}

		if(e.getSource()==btnDelete[1]) {
			tList.insertionSort();
			int location = tList.binarySearch(delete.getText());
			if(location >=0) {//binary search if record found
				tList.delete(tList.getList()[location]);
				resArea.setText(tList.toStringTakeout()); //display
				try {
					Loader.clearFile("Takeout.txt"); //clear the file
					Loader.writeFile(tList.getList(), "Takeout.txt",tList.getSize()); //save
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"ERROR");
				}
			}
			else {//if the record is not found
				JOptionPane.showMessageDialog(null,"Record not found");
			}
		}


		if (e.getSource()==btnLog) {
			try {
				Login gui = new Login(); //go back to login page
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,"ERROR");
			}
			dispose();
		}


		if (e.getSource()==btnSort[0]) { //if sort clicked
			rList.shellSort(); //Sort reservations by time
			resArea.setText(rList.toString()); //display the sorted reservations
		}

		if(e.getSource()==btnDelete[0]) {
			rList.insertionSort();
			int location = rList.binarySearch(delete.getText());
			if(location >=0) {//binary search if record found
				rList.delete(rList.getList()[location]);
				resArea.setText(rList.toString());
				try {
					Loader.clearFile("FinalReservations.txt"); //clear the file
					Loader.writeFile("FinalReservations.txt", rList.getList(),rList.getSize()); //save
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"ERROR");
				}
			}
			else {//if the record is not found
				JOptionPane.showMessageDialog(null,"Record not found");
			}
		}

		if (e.getSource()==btnClear[0]) {
			try {
				Loader.clearFile("FinalReservations.txt");//clear the file
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,"ERROR");
			}
			resArea.setText("");
		}

		if(e.getSource()==btnSettings) {
			Settings gui = new Settings(); //open the settings gui
			dispose();
		}

		if(e.getSource()==btnExit) {
			System.exit(0); //exit the system
		}

		if(e.getSource()==btnHelp) {
			try {
				AdminHelp gui = new AdminHelp(); 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,"ERROR");
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Admin gui = new Admin();

	}



}

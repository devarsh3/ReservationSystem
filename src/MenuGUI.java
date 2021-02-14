import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Mahrshi Patel
 * Jan 2019
 * 
 *
 */
public class MenuGUI extends JFrame implements ActionListener{

	private JTextArea menuTA, orderTA, nameTA, searchTA, phoneTA, subTotalTA, taxTA, totalTA;
	private MenuList menuList;
	private JButton btnBack, btnOrder, btnAdd;
	private ImagePicture title;
	private ImageIcon image;
	private TextPicture tax, subTotal, totaL, search, phoneNum, name, time, orderConfirm;
	private JScrollPane scroller, orderScroller;
	private JPanel btnPanel;
	private ReservationRecord order;
	private NumberFormat money;
	private String[] timeOptions;
    private JComboBox t;
    private WelcomePage welcomePage;
    

	public MenuGUI() throws IOException {
		// TODO Auto-generated constructor stub
		super("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		//centers the frame on the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		getContentPane().setBackground(new Color(51,153,255));
		
		String[] timeOpt = Loader.loadFile("time.txt"); //load the time options
        timeOptions = new String[timeOpt.length]; //create the options to dsplay
        ReservationRecord[] timeRecords = new ReservationRecord[timeOpt.length]; //create records to get time
        for(int i = 0;i<timeOpt.length;i++) {
            timeRecords[i] = new ReservationRecord(); //create records 
            timeRecords[i].setTime(Integer.parseInt(timeOpt[i])); //set time of each record
            timeOpt[i] = timeRecords[i].timeMeridian(timeRecords[i].getTime()); //get the time in the time options
        }

        t = new JComboBox(timeOpt); //display the time options in the drop down menu

		//creates Components
		btnBack = new JButton("Back");
		btnOrder = new JButton("Order");
		btnAdd = new JButton("Add");
		menuTA = new JTextArea();
		orderTA = new JTextArea();
		nameTA = new JTextArea();
		searchTA = new JTextArea();
		phoneTA = new JTextArea();
		subTotalTA = new JTextArea();
		taxTA = new JTextArea();
		totalTA = new JTextArea();
		tax = new TextPicture();
		subTotal = new TextPicture();
		totaL = new TextPicture();
		search = new TextPicture();
		phoneNum = new TextPicture();
		name = new TextPicture();
		time = new TextPicture();
		orderConfirm = new TextPicture();
		image = new ImageIcon("src/MenuTitle.png");
		title = new ImagePicture(image);
		menuList = new MenuList();
		new MenuList();
		scroller = new JScrollPane(menuTA);
		orderScroller = new JScrollPane(orderTA);
		btnPanel = new JPanel();
		order = new ReservationRecord();
		money = NumberFormat.getCurrencyInstance();
		
		//Loads a file
		String fileInfo[] = Loader.loadFile("Menu.txt"), total="";
		
		MenuItem item[] = new MenuItem[fileInfo.length];
		for(int i = 0; i<fileInfo.length;i++) {
			item[i] = new MenuItem();
			item[i].processRecord(fileInfo[i]);
			menuList.insert(item[i]);
		}
		
		//set text area
		menuTA.setTabSize(15);
		menuTA.setText("Item\tPrice\n"+menuList.toString());
		scroller.setBounds(50, 150, 250, 350);
		add(scroller);
		menuTA.setEditable(false);
		
		orderTA.setTabSize(15);
		orderTA.setText("Item\tPrice\n");
		orderScroller.setBounds(350, 150, 250, 300);
		add(orderScroller);
		orderTA.setEditable(false);
		
		nameTA.setBounds(650, 300, 200, 20);
		add(nameTA);
		
		searchTA.setBounds(650, 200, 200, 20);
		add(searchTA);
		
		phoneTA.setBounds(650, 350, 100, 20);
		add(phoneTA);
		
		subTotalTA.setBounds(450, 500, 150, 20);
		subTotalTA.setText("$0.00");
		subTotalTA.setEditable(false);
		add(subTotalTA);
		
		taxTA.setBounds(450, 550, 150, 20);
		taxTA.setText("$0.00");
		taxTA.setEditable(false);
		add(taxTA);
		
		totalTA.setBounds(450, 600, 150, 20);
		totalTA.setText("$0.00");
		totalTA.setEditable(false);
		add(totalTA);
		
		//Setting Text Picture
		tax.setTitle("Tax:");
		tax.setFont(new Font("Monospaced", Font.BOLD, 15));
		tax.setColor(Color.WHITE);
		tax.setBounds(330, 535, 500, 50);
		add(tax);
		
		subTotal.setTitle("Subtotal:");
		subTotal.setFont(new Font("Monospaced", Font.BOLD, 15));
		subTotal.setColor(Color.WHITE);
		subTotal.setBounds(330, 485, 500, 50);
		add(subTotal);
		
		totaL.setTitle("Total:");
		totaL.setFont(new Font("Monospaced", Font.BOLD, 15));
		totaL.setColor(Color.WHITE);
		totaL.setBounds(330, 585, 500, 50);
		add(totaL);
		
		orderConfirm.setTitle("");
		orderConfirm.setFont(new Font("Monospaced", Font.BOLD, 40));
		orderConfirm.setColor(Color.BLACK);
		orderConfirm.setBounds(300, 20, 1000, 100);
		add(orderConfirm);
		
		search.setTitle("Search Item to add");
		search.setFont(new Font("Monospaced", Font.BOLD, 15));
		search.setColor(Color.WHITE);
		search.setBounds(620,150,500,50);
		add(search);
		
		phoneNum.setTitle("Phone Number:");
		phoneNum.setFont(new Font("Monospaced", Font.BOLD, 15));
		phoneNum.setColor(Color.WHITE);
		phoneNum.setBounds(620,305,500,50);
		add(phoneNum);
		
		name.setTitle("Name:");
		name.setFont(new Font("Monospaced", Font.BOLD, 15));
		name.setColor(Color.WHITE);
		name.setBounds(620,255, 500,50);
		add(name);
		
		time.setTitle("Time:");
		time.setFont(new Font("Monospaced", Font.BOLD, 15));
		time.setColor(Color.WHITE);
		time.setBounds(620,355,500,50);
		add(time);
		
		t.setBounds(650,400,100,30);
        add(t);
		
		//adds button
		btnAdd.setBounds(875, 195, 60, 30);
		add(btnAdd);
		
		//adds panel to frame
		btnPanel.setBounds(600, 600, 400, 100);
		add(btnPanel);
		
		//disable button
		btnOrder.setEnabled(false);
		
		//adds button
		btnPanel.add(btnBack);
		btnPanel.add(btnOrder);
		
		//panel background
		btnPanel.setBackground(new Color(51,153,255));
		
		//set button color
		btnOrder.setBackground(Color.WHITE);
		btnBack.setBackground(Color.WHITE);
		btnAdd.setBackground(Color.WHITE);
		
		//set title
		title.setBounds(80,-100, 1000, 1000);
		add(title);
		
		//adds to ActionListener
		btnBack.addActionListener(this);
		btnOrder.addActionListener(this);
		btnAdd.addActionListener(this);
		
		//set size and layout
		setSize(1000,700);
		setLocation(200,25);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnBack){
			//goes back to welcome page
			dispose();
			welcomePage = new WelcomePage();
		}
		else if(e.getSource()==btnAdd) {
			//gets string from text area
			int num = menuList.binarySearch(searchTA.getText());
			
			//clears search bar
			searchTA.setText("");
			if(num>=0) {
				//searchs for item in the menu
				MenuItem item = menuList.getList()[num];
				order.insert(item);
				orderTA.setText("Item\tPrice\n" + order.getOrder().toString());
				
				//calculates the subtotal and prints it
				order.calcSubtotal();
				subTotalTA.setText("" + money.format(order.getSubtotal()));
				
				//calculates the tax and prints it
				order.calcTax();
				taxTA.setText("" + money.format(order.getTax()));
				
				//calculates the total and prints it
				order.calcTotal();
				totalTA.setText("" + money.format(order.getTotal()));
				
				btnOrder.setEnabled(true);
			}
			else {
				//error message if item can't be found
				JOptionPane.showMessageDialog(null, "Please Try Again");
			}
		
		}
		else if(e.getSource()==btnOrder) {
			if((nameTA.getText().equals("")||phoneTA.getText().equals("")||t.getSelectedItem()==null)==false) {
				//I got this from here: https://stackoverflow.com/questions/8763075/change-the-ok-cancel-string-in-joptionpane/8763349#8763349
				int confirm = JOptionPane.showOptionDialog(null, 
				        "Do you want to confirm your order", "Confirm Order",
				        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, 
				        null, new String[]{"Yes", "No"}, // this is the array
				        "default");
				
				if(confirm==0) {
					try {
						//gets name and adds it to object
						order.setName(nameTA.getText());
						
						//gets phone number and adds it to object
						order.setPhoneNumber(phoneTA.getText());
						
						String time = (String)t.getSelectedItem(); //get the selected time
	                    switch(time) { //set the time
	                    case "11:00am":
	                        order.setTime(1100);
	                        break;
	                    case "11:15am":
	                        order.setTime(1115);
	                        break;
	                    case "11:30am":
	                        order.setTime(1130);
	                        break;
	                    case "11:45am":
	                        order.setTime(1145);
	                        break;
	                    case "12:00pm":
	                        order.setTime(1200);
	                        break;
	                    case "12:15pm":
	                        order.setTime(1215);
	                        break;
	                    case "12:30pm":
	                        order.setTime(1230);
	                        break;
	                    case "12:45pm":
	                        order.setTime(1245);
	                        break;
	                    case "1:00pm":
	                        order.setTime(1300);
	                        break;
	                    case "1:15pm":
	                        order.setTime(1315);
	                        break;
	                    case "1:30pm":
	                        order.setTime(1330);
	                        break;
	                    case "1:45pm":
	                        order.setTime(1345);
	                        break;
	                    case "2:00pm":
	                        order.setTime(1400);
	                        break;
	                    case "2:15pm":
	                        order.setTime(1415);
	                        break;
	                    case "2:30pm":
	                        order.setTime(1430);
	                        break;
	                    case "2:45pm":
	                        order.setTime(1445);
	                        break;
	                    }
	                    
	                    //prints to a file
						Loader.writeFile(order, "TakeOut.txt");
						
						//resets every thing except the menu
						order = new ReservationRecord();
						nameTA.setText("");
						phoneTA.setText("");
						orderTA.setText("Item\tPrice\n");
						totalTA.setText("$0.00");
						taxTA.setText("$0.00");
						subTotalTA.setText("$0.00");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,e1.toString());
					}

					//shows that order has been confirmed
					orderConfirm.setTitle("Order has Been Confirmed");
					
					//disable button
					btnOrder.setEnabled(false);
					
				}
				else if(confirm==1) {
					//does nothing
				}
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Please enter all information");
			}
			
			
		}
		
	}


	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new MenuGUI();
	}

}

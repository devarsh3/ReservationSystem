import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/*
 * Mahrshi Patel
 * Jan 2019
 * the class will display the help page
 */

public class HelpPage extends JFrame implements ActionListener{

	//create Components
	TextPicture title;
	JTextArea textArea;
	JButton btnBack;
	
	public HelpPage() throws IOException {
		super("Help Page");
		setLayout(null);
		
		//centers the frame on the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2,dim.height/2-this.getSize().height/2);
		getContentPane().setBackground(new Color(51,153,255));
		
		//Create Components
		title = new TextPicture();
		textArea = new JTextArea();
		btnBack = new JButton("Close");
		
		//set Text
		title.setTitle("Help Page");
		title.setColor(Color.WHITE);
		title.setFont(new Font("Georgia", Font.BOLD, 30));
		title.setBounds(100, 20, 200, 40);
		add(title);
		
		//Get text From text file
		String text[] = Loader.loadFile("Help.txt"), completeText = "";
		
		for(int i = 0; text.length>i;i++) {
			completeText = completeText + text[i]+"\n";
		}
		//set text area
		textArea.setBounds(40, 80, 350, 320);
		textArea.setBackground(new Color(51,153,255));
		textArea.setForeground(Color.WHITE);
		textArea.setText(completeText);
		add(textArea);
		textArea.setEditable(false);
		
		//set Button
		btnBack.setBounds(170, 400, 80, 30);
		btnBack.setFont(new Font("Monospaced", 0, 12));
		btnBack.setBackground(Color.WHITE);
		add(btnBack);
		
		btnBack.addActionListener(this);
		
		
		
		//set size and layout
		setSize(450,500);
		setLocation(400,100);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnBack) {
			dispose();
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new HelpPage();
	}

	
}

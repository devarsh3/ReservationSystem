import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author p0067706
 *
 */
public class ImagePicture extends Picture{

	/*
	 * class instance data
	 */
	private ImageIcon image;
	
	/**
	 * constructor with an image passed in
	 */
	public ImagePicture(ImageIcon img) {
		super();
		this.image = img;
		setMyWidth(img.getIconWidth()) ;
		setMyHeight(img.getIconHeight());
		repaint();		
	}

	public ImagePicture(ImageIcon img, int x, int y) {
		super();
		this.image = img;
		setxPos(x);
		setyPos(y);
		setMyWidth(img.getIconWidth()) ;
		setMyHeight(img.getIconHeight());
		repaint();		
	}

	
	public void paint (Graphics g) {
		this.image.paintIcon(this, g, getxPos(), getyPos());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create  a frame
		JFrame f = new JFrame("Testing");
		
		// create an ImagePicture object
		ImagePicture p = new ImagePicture(new ImageIcon("minion.png"));
		
		f.setSize(400, 350);
		f.add(p);
		f.setVisible(true);
		
		JOptionPane.showMessageDialog(null,"wait");
		p.setxPos(20);
		p.setyPos(15);
		
		

	}

}

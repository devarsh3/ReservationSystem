import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Jainil Patel
 *
 */
public class Picture extends JComponent {
	// instant variables for color, location and dimension of Picture
	private Color c;
	private int xPos, yPos, width, height;
	
	// default constructor
	public Picture() {
		this.c = new Color(212, 175, 55);
		this.xPos = 150;
		this.yPos = 125;
		this.width = 100;
		this.height = 50;
		repaint();
	}
	
	// new more useful overloaded constructor
	public Picture (int x, int y, int w, int h) {
		this.c = new Color(212, 175, 55);
		this.xPos = x;
		this.yPos = y;
		this.width = w;
		this.height = h;
		repaint();
	}
	
	// setter and getter methods for color
	public void setColor(Color c) {
		this.c = c;
		repaint();
	}
	
	// overload setColor method
		public void setColor(int r, int g, int b) {
			this.c = new Color(r,g,b);
			repaint();
		}
	
	public Color getColor() {
		return c;
	}
	
	// setter and getter methods for location and size
	public void setxPos (int xPos) {
		this.xPos = xPos;
		repaint();
	}
	
	public int getxPos() {
		return xPos;
	}
	
	public void setyPos (int yPos) {
		this.yPos = yPos;
		repaint();
	}

	public int getyPos() {
		return yPos;
	}
	
	public void setMyWidth (int w) {
		this.width = w;
		repaint();
	}
	
	public int getMyWidth() {
		return width;
	}
	
	public void setMyHeight (int h) {
		this.height = h;
		repaint();
	}
	
	public int getMyHeight() {
		return height;
	}
	
	// my paint method
	public void paint (Graphics g) {
		g.setColor(this.c);
		g.drawRect(this.xPos, this.yPos, this.width, this.height);
		g.drawOval(this.xPos, this.yPos, this.width, this.height);
		g.fillOval(this.xPos + this.width/3, this.yPos, this.width/3, this.height);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame("Testing");
		
		// create a picture object
		Picture p = new Picture();
		
		f.setSize(400, 350);
		f.add(p);
		f.setVisible(true);
		
		JOptionPane.showMessageDialog(null, "Wait.");
		
		p.setxPos(50);
		p.setyPos(50);
		p.setMyWidth(200);
		p.setMyHeight(100);
		f.setVisible(true);
	
	}

}

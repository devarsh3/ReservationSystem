import java.text.NumberFormat;

/**
 * 
 */

/**
 * @author Mahrshi
 * Dec 17 2018
 * stores item name and price in a object
 *
 */
public class MenuItem {

	private String name;
	private double price;
	static NumberFormat money = NumberFormat.getCurrencyInstance();
	
	public MenuItem() {
		name = "";
		price = 0;
	}
	
	//setters and getters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	//processes the string input into the object
	public void processRecord(String word) {
		String process[] =word.split("/");
		name = process[0];
		price = Double.parseDouble(process[1]);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  name +"\t"+ money.format(price) ;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MenuItem n = new MenuItem();
		
		n.processRecord("Veg. Soup/4.00");
		
		System.out.println(n.toString());
		
		n.setPrice(3.50);
		
		System.out.println(n.toString());
	}

}

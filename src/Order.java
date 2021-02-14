import java.io.IOException;
import java.text.NumberFormat;

/*
 * Mahrshi Patel
 * 
 */

public class Order {

	//private static MenuItem item[];
	private MenuList list;
	private double subtotal, tax, total, hst;
	private NumberFormat money;

	public Order() {
		list = new MenuList();
		//item = new MenuItem[list.getMaxSize()];
		subtotal = 0;
		tax = 0;
		total = 0;
		hst = 0.13;
		money = NumberFormat.getCurrencyInstance();
	}


	/**
	 * @return the hst
	 */
	public double getHst() {
		return hst;
	}

	/**
	 * @param hst the hst to set
	 */
	public void setHst(double hst) {
		this.hst = hst;
	}


	/**
	 * @return the order
	 */
	public MenuList getOrder() {
		return this.list;
	}



	/**
	 * @param order the order to set
	 */
	public void setOrder(MenuList order) {
		this.list = order;
	}

	public void insert(MenuItem item) {
		this.list.insert(item);
	}


	/**
	 * @return the subtotal
	 */
	public double getSubtotal() {
		return subtotal;
	}



	/**
	 * @param subtotal the subtotal to set
	 */
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}



	/**
	 * @return the tax
	 */
	public double getTax() {
		return tax;
	}



	/**
	 * @param tax the tax to set
	 */
	public void setTax(double tax) {
		this.tax = tax;
	}



	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}



	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	public void calcTax() {
		double tax;
		tax = subtotal*getHst();
		setTax(tax);
	}

	public void calcSubtotal() {
		double sub = 0;
		for (int i = 0;i<list.getSize();i++) {
			sub = sub + list.getList()[i].getPrice();
		}
		
		setSubtotal(sub);
		
	}
	
	public void calcTotal() {
		double total = 0;
		total = subtotal+tax;
		setTotal(total);
	}
	
	public String toString() {
		
		String x="";
		for(int i = 0; i<list.getSize();i++)
			x=x+list.getList()[i].toString()+"\n";
		calcSubtotal();
		calcTax();
		calcTotal();
		x += ("SubTotal: "+ money.format(getSubtotal())+"\nTax: "+money.format(getTax())+"\nTotal: "+ money.format(getTotal()));
		return x;
	}

	public static void main(String[] args) throws IOException {
		Order o = new Order();
				
		String output[] = null;

		//loads file
		output = Loader.loadFile("Menu.txt");
		MenuList it= new MenuList();
		for(int i = 0; i<output.length; i++) {
			it.getList()[i] = new MenuItem();
			it.getList()[i].processRecord(output[i]);
			o.getOrder().insert(it.getList()[i]);//insert Record		
		}

		System.out.println(o.toString());
		
		
		

	}

}

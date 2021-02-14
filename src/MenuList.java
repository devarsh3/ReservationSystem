/*
 * Mahrshi Patel
 * Dec 2018
 * creates a menu item array that has the necessary data stored inside the object
 * 
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MenuList {

	private MenuItem list[];
	private int maxSize, size;

	public MenuList() {
		this.maxSize = 1000;
		this.size = 0;
		this.list = new MenuItem[this.maxSize];
	}

	public MenuList(int maxSize) {
		this.maxSize = maxSize;
		this.size = 0;
		this.list = new MenuItem[this.maxSize];
	}

	/**
	 * @return the list
	 */
	public MenuItem[] getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(MenuItem[] list) {
		this.list = list;
	}

	/**
	 * @return the maxSize
	 */
	public int getMaxSize() {
		return maxSize;
	}

	/**
	 * @param maxSize the maxSize to set
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	//the insert method
	public boolean insert (MenuItem item) {
		if(this.size<this.maxSize) {
			size++;
			list[size-1]=item;
			return true;//success
		}
		return false;//no more space
	}

	public boolean delete(MenuItem item) {
		insertionSort();

		int loc = binarySearch(item.getName());

		if(loc>= 0) {
			list[loc-1] = list[size-1];//placing it at the end

			size--;//decrease the size

			insertionSort();

			return true;
		}

		return false;//could not delete
	}
	
	//Deletes item with a string input
	public boolean delete(String item) {
		insertionSort();

		int loc = binarySearch(item);

		if(loc>= 0) {
			list[loc-1] = list[size-1];//placing it at the end

			size--;//decrease the size

			insertionSort();

			return true;
		}

		return false;//could not delete
	}

	//replace the old item with a new item
	public boolean change (MenuItem oldI,MenuItem newI) {
		if (delete(oldI)) {
			insertionSort();
			return insert(newI);//able to change the items
		}
		return false;//could not change
	}

	//searches for an item
	public int binarySearch(String name) {
		int low = 0;
		int high = this.size - 1;
		int middle;
		insertionSort();
		while(low <=high) {
			middle = (high+low)/2;

			//if equals
			if(name.compareToIgnoreCase(list[middle].getName())==0) {
				return middle; //return location found
			}
			//if in the lower part of the list
			else if(name.compareToIgnoreCase(list[middle].getName())<0) {
				high = middle - 1; //change the high on the list
			}
			else { //if in the higher part of the list
				low = middle +1; //change the low end of the list
			}
		}

		return -1; //not found
	}

	public void insertionSort() {
		for(int top =  1; top<this.size;top++) {
			MenuItem item  = list[top];
			int i = top;
			while(i > 0 && item.getName().compareToIgnoreCase(list[i-1].getName())<0) {
				list[i] = list[i-1];
				i--;
			}
			list[i]=item;
		}
	}


	public String toString() {
		String theList = "";
		for (int i = 0; i < size; i++) {
			theList += list[i].toString() + "\n";

		}
		return theList; //returns the reservation organized

	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MenuList n = new MenuList();
		MenuItem item = new MenuItem();
		item.processRecord("Milk/2.00");
		
		String output[] = null;

		//loads file
		output = Loader.loadFile("Menu.txt");

		MenuItem itemList[] = new MenuItem[output.length];
		for(int i = 0; i<output.length; i++) {
			itemList[i] = new MenuItem();
			itemList[i].processRecord(output[i]);
			n.insert(itemList[i]);//insert Record
		}

		System.out.println(n.toString());
		
		//insert new item
		n.insert(item);
		System.out.println("Insert\n\n"+n.toString());
		
		//sort
		n.insertionSort();
		System.out.println("Sort\n\n"+n.toString());
		
		//delete
		if(n.delete(item)) {
			System.out.println("Item deleted");
		}
		else {
			System.out.println("Item not Found");
		}
		
		
		
	}

}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*Devarsh Patwa
 *Dec 2018
 *Description: This class has a list which contains reservations. Has methods to insert, delete, and change
 *Mehtod List:
 *public ReservationList()
 *public ReservationRecord[] getList()
 *public void setList(ReservationRecord[] list)
 *public int getMaxSize()
 *public void setMaxSize(int maxSize)
 *public int getSize()
 *public int getSize()
 *public boolean insert(ReservationRecord record)
 *public boolean delete(ReservationRecord record)
 *public void shellSort()
 *public void insertionSort()
 *public int binarySearch(String make)
 *public static String[] loadFile (String fileName)
 *public static void writeFile (ReservationRecord phrases[])
 *public String toString()
*/

public class ReservationList {

	private ReservationRecord list[]; //array of reservation records
	private int maxSize; //max number of records
	private int size; //current size of records

	//default constructor
	public ReservationList() {
		this.maxSize = 100;
		this.size = 0;
		list = new ReservationRecord[maxSize];
	}



	/**
	 * @return the list
	 */
	public ReservationRecord[] getList() {
		return list;
	}



	/**
	 * @param list the list to set
	 */
	public void setList(ReservationRecord[] list) {
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

	/*
	 * Method to insert a record into my list Checks if size is below the maximum
	 * size increases the size adds it to the location just below the maximum size
	 * return true if it is able to add
	 */
	public boolean insert(ReservationRecord record) {
		if (this.size < maxSize) {
			size++;
			list[size - 1] = record;
			return true; // success
		}
		return false; // no more space
	}

	//delete method using binary search
	public boolean delete(ReservationRecord record) {
		//loop through my list
		int location;
		location = binarySearch(record.getName());
		for (int i = 0;i<size;i++) {
			if(location>=0) {
				if(list[location]==list[i]) {
					list[i] = list[size-1]; //place the last element in my record//overwrite my record
					size--; //decrease the size
					
					return true; //successfull delete

				}
			}

		}
		return false; //could not delete
	}


	//method to sort records by year
	public void shellSort() {
		int n = this.getSize();
		int gap;
		//start with the big gap, then reduce the gap
		for(gap = n/2;gap>0; gap/=2){
			//do a gapped insertion sort for this gap size
			//the first gap elements a[0..gap-1] are already in gapped order
			//keep adding one more element until array is sorted

			for(int i = gap ;i<n;i++) {
				//add a[i] to the elements that have been gap
				//sorted save a[i] in temp and make a hole at position i
				ReservationRecord temp;
				temp = list[i];

				//shift earlier gap sorted elements up untill the correct location
				//for a[i] is found
				int j;
				for(j=i;j>=gap && list[j-gap].getTime()>temp.getTime();j-=gap){
					list[j] = list[j-gap];
				}
				list[j]= temp;
			}
		}
	}

	//insertion sort
	//sorts it alphabetically
	public void insertionSort() {
		for (int top = 1;top<this.size;top++) {
			ReservationRecord item = list[top];
			int i =top;
			while(i>0 && item.getName().compareToIgnoreCase(list[i-1].getName())<0) {
				list[i] = list[i-1];
				i--;
			}
			list[i] = item;
		}
	}

	/*
	 * The Binary Search Method
	 */
	public int binarySearch(String make) {
		int low = 0;
		int high = this.size - 1;
		int middle;

		while(low <=high) {
			middle = (high+low)/2;

			//if equals
			if(make.compareToIgnoreCase(list[middle].getName())==0) {
				return middle; //return location found
			}
			//if in the lower part of the list
			else if(make.compareToIgnoreCase(list[middle].getName())<0) {
				high = middle - 1; //change the high on the list
			}
			else { //if in the higher part of the list
				low = middle +1; //change the low end of the list
			}
		}


		return -1; //not found
	}




	/*
	 * toString() method
	 */
	public String toString() {
		String theList = "";
		for (int i = 0; i < size; i++) {
			theList += "Reservation " + (i + 1) + ": \n" + list[i].toStringReservation() + "\n\n";

		}
		return theList; //returns the reservation organized
	}
	
	public String toStringTakeout() {
		String theList = "";
		for (int i = 0; i < size; i++) {
			theList += "Takeout " + (i + 1) + ": \n" + list[i].toStringTakeout() + "\n\n";

		}
		return theList; //returns the reservation organized
	}


	public static void main(String[] args) throws IOException {

		ReservationList r = new ReservationList(); //create

		String output[];
		output = Loader.loadFile("TestReservations.txt"); //load the file into a string

		ReservationRecord record[] = new ReservationRecord[output.length]; //creates array of reservation
		for (int i = 0;i<output.length;i++) { //until it's less than lines in the file
			record[i] = new ReservationRecord();//create new record
			record[i].processReservation(output[i]); //process the information from string into record
			r.insert(record[i]); //insert records
		}
		
		System.out.println(r.toString()); //print the reservations
		
		System.out.println("Sorted by Time\n");
		r.shellSort(); //sort them according to time
		System.out.println(r.toString());
		
		System.out.println("Sorted by Name\n");
		r.insertionSort(); //sort them alphabetically by name
		System.out.println(r.toString());
		
		System.out.println("Delete\n");
		r.delete(record[1]); //delete a record
		System.out.println(r.toString());
		
		System.out.println("Insert\n");
		r.insert(record[1]); //insert a record
		System.out.println(r.toString());

		//test binary search if record found
		int location = r.binarySearch("Devarsh Patwa");
		if(location >=0) {
			System.out.println(r.getList()[location].toStringReservation());
		}
		else {
			System.out.println("Record not found");
		}

		//test binary search if record is not found
		location = r.binarySearch("Tony Campos");
		if(location >=0) {
			System.out.println(r.getList()[location].toStringReservation());
		}
		else {
			System.out.println("Record not found");
		}
		
		//test write file
		Loader.writeFile("TestOutput.txt", r.getList(),r.getSize());






	}

}

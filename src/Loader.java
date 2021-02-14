//Devarsh Patwa, Mahrshi Patel
//Dec 2018
//This class has methods that overload each other to load the file and write a new file
/* Method List:
 * public static String[] loadFile (String fileName)
 * public static void writeFile (String fileName, ReservationRecord phrases[],int size)
 * public static void writeFile(ReservationRecord order[], String fileName, int size)
 * public static void writeFile (String fileName, ReservationRecord record)
 * public static void writeFile (String fileName, String info[]
 * public static void clearFile(String fileName)
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Loader {

	//private static Picture title = new Picture();;
	
	public static String[] loadFile(String fileName) throws IOException{
		
		/*ClassLoader classLoader = Loader.class.getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		
		System.out.println(file.getPath());
		*/
		//opens the file to read
        //InputStreamReader phrases = new InputStreamReader(null, file.getPath());
		FileReader phrases = new FileReader("src/"+fileName);
		BufferedReader input = new BufferedReader(phrases);

		String fileInfo[], line = null;
		int lineCount = 0; //to count the lines
		line = input.readLine();
		while (line !=null) { //runs till the line is blank
			lineCount++;
			line = input.readLine(); 

		} //end while
		fileInfo = new String[lineCount]; //create the array 
		input.close(); //close the file
		
		phrases = new FileReader("src/"+fileName);
		input = new BufferedReader(phrases);


		//loop to fill the array
		for (int i = 0;i<lineCount;i++) {

			fileInfo[i] = input.readLine(); //get the info

		}
		//close file
		input.close();


		return fileInfo;

	}

	//method to write a file
	public static void writeFile (String fileName, ReservationRecord records[],int size) throws IOException{
		//creating another file to save the output
		FileWriter fileOutput = new FileWriter("src/"+fileName,true);
		PrintWriter output = new PrintWriter(fileOutput);
		String r[] = new String[records.length]; //create a string to hold the info

		for (int x = 0;x<size;x++) {
			r[x] = (records[x].getName()+"/"+records[x].getPhoneNumber()+"/"
					+ records[x].getPeople()+"/" + records[x].getTime());
		}

		for (int i = 0;i<records.length;i++) {
			if (r[i]==null) {
				break;
			}
			output.println(r[i]); //print the info onto the file
		}
		fileOutput.close();
	}

	//method to save the takeout order
	public static void writeFile(ReservationRecord order[], String fileName, int size) throws IOException{
		//creating another file to save the output
		//JOptionPane.showMessageDialog(null,title.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()+"/res/"+fileName);
		FileWriter fileOutput = new FileWriter("src/"+fileName,true);
		PrintWriter output = new PrintWriter(fileOutput);

		//System.out.println(title.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()+fileName);
		
		String r[] = new String[order.length];
		for(int x = 0;x<size;x++) {
			r[x] = order[x].getName() + "/" + order[x].getPhoneNumber() + "/" + order[x].getTime() + ","; //correct order to print

			for(int i = 0; i < order[x].getOrder().getSize();i++) {
				r[x]+=order[x].getOrder().getList()[i].getName() + "/" +order[x].getOrder().getList()[i].getPrice() + ";"; //get all info
			}
			output.println(r[x]); //print
		}

		fileOutput.close(); //close
	}

	//method to write a file
	public static void writeFile (String fileName, ReservationRecord record) throws IOException{
		//creating another file to save the output
		FileWriter fileOutput = new FileWriter("src/"+fileName,true);
		PrintWriter output = new PrintWriter(fileOutput);

		String r; //correct order of info
		r = (record.getName()+"/"+record.getPhoneNumber()+"/"
				+ record.getPeople()+"/" + record.getTime());
		output.println(r); //print
		fileOutput.close();

	}

	//method to save the takeout order
	public static void writeFile(ReservationRecord order, String fileName) throws IOException{
		//creating another file to save the output
		//System.out.println(title.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()+"/res/"+fileName);
		FileWriter fileOutput = new FileWriter("src/"+fileName,true);
		PrintWriter output = new PrintWriter(fileOutput);

		
		
		String r; //correct order of info
		r = order.getName() + "/" + order.getPhoneNumber() + "/" + order.getTime() + ",";

		for(int i = 0; i < order.getOrder().getSize();i++) {
			r+=order.getOrder().getList()[i].getName() + "/" +order.getOrder().getList()[i].getPrice() + ";";
		}
		output.println(r); //print
		fileOutput.close();
	}


	//method to write a string into a file
	public static void writeFile (String fileName, String info[]) throws IOException{
		//opening the file
		FileWriter fileOutput = new FileWriter("src/"+fileName);
		PrintWriter output = new PrintWriter(fileOutput);

		for(int i = 0;i<info.length;i++) {
			output.println(info[i]);
		}

		fileOutput.close();
	}

	//clears the file
	public static void clearFile(String fileName) throws IOException{
		FileWriter fileOutput = new FileWriter("src/"+fileName); //open the file which will empty the file
		fileOutput.close(); //close without printing anything
	}

	
	public static void main(String[] args) throws IOException {

		ReservationList r = new ReservationList(); //create
		//test loadfile(filename)
		String output[];
		output = Loader.loadFile("TestReservations.txt"); //test the loadfile method

		ReservationRecord record[] = new ReservationRecord[output.length]; //creates array of reservation
		for (int i = 0;i<output.length;i++) { //until it's less than lines in the file
			record[i] = new ReservationRecord();//create new record
			record[i].processReservation(output[i]); //process the information from string into record
			r.insert(record[i]); //insert records
		}

		//test writefile(filename,record[],size)
		r.shellSort(); //sort them according to time
		Loader.writeFile("TestOutput.txt", r.getList(),r.getSize()); //test write file method

		//test writefile(filename, record)
		ReservationRecord r2 = new ReservationRecord("Devarsh Patwa/6474723421/5/1400");
		Loader.writeFile("TestOutput2.txt",r2);

		//test writefile(order,filename)
		ReservationRecord order = new ReservationRecord();
		order.processTakeout("Dev/911/1430,Beef Soup/4.00;Apple Crisp/4.00;Pasta/5.00");
		Loader.writeFile(order, "TestOutput3.txt");
		
		//test writefile(order[],filename,size)
		ReservationList tList;
		tList = new ReservationList();
		output = Loader.loadFile("TestTakeout.txt");
		ReservationRecord record1[] = new ReservationRecord[output.length]; //creates array of reservation
		for (int i = 0;i<output.length;i++) { //until it's less than lines in the file
			record1[i] = new ReservationRecord();//create new record
			record1[i].processTakeout(output[i]); //process the information from string into record
			tList.insert(record1[i]); //insert records
		}
		Loader.writeFile(tList.getList(), "TestOutput4.txt",tList.getSize()); //save
		
		//test writefile(filename,info[])
		String info[] = new String[2];
		info[0] = "Fully";
		info[1] = "Tested";
		Loader.writeFile("TestOutput5.txt", info);
		
		//test clear file
		Loader.writeFile("TestOutput6.txt", info);
		Loader.clearFile("TestOutput6.txt");
		
	}
}

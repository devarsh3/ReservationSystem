import javax.swing.JOptionPane;

/*
 *Devarsh Patwa
 *Dec 2018
 *This class makes the reservation storing all necessary data
 *Method List:
 * public ReservationRecord()
 * public ReservationRecord(String name,String pNum, int time,int ppl)
 * public ReservationRecord(String record)
 * public void processReservation(String record)
 * public String getName()
 * public void setName(String name)
 * public String getPhoneNumber()
 * public int getTime()
 * public void setTime(int time)
 * public int getPeople()
 * public void setPeople(int people)
 */

public class ReservationRecord extends Order {

	private String name, phoneNumber;
	private int time, people;

	public ReservationRecord() { //default constructor
		this.name = null;
		this.phoneNumber = null;
		this.time = 0;
		this.people = 0;
	}

	//more useful overloaded constructor
	public ReservationRecord(String name,String pNum, int ppl,int time) {
		this.name = name;
		this.phoneNumber = pNum;
		this.time = time;
		this.people = ppl;
	}

	//more useful overloaded constructor
	public ReservationRecord(String record) {
		processReservation(record);
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * @return the people
	 */
	public int getPeople() {
		return people;
	}

	/**
	 * @param people the people to set
	 */
	public void setPeople(int people) {
		this.people = people;
	}

	//Method to split a string into objects
	public void processReservation(String record) {
		String reservation[];
		reservation = record.split("/");
		setName(reservation[0]);
		setPhoneNumber(reservation[1]);
		setPeople(Integer.parseInt(reservation[2]));
		setTime(Integer.parseInt(reservation[3]));
	}
	
	public void processTakeout1(String record) { //split the information
		String reservation[];
		reservation = record.split("/");
		setName(reservation[0]);
		setPhoneNumber(reservation[1]);
		setTime(Integer.parseInt(reservation[2]));
	}
	
	
	public void processTakeout(String takeout) { //split the items and prcoess them
		String word[] = takeout.split(",");
		processTakeout1(word[0]);
		String word1[] = word[1].split(";");
		for(int i = 0; i<word1.length;i++) {
			getOrder().getList()[i] = new MenuItem();
			getOrder().getList()[i].processRecord(word1[i]);
			getOrder().insert(getOrder().getList()[i]);
		}
		
	}

	//toString method for reservation
	public String toStringReservation() {
		String reservation;

		reservation = ("Name: "+getName()+"\nPhone Number: "+getPhoneNumber()
		+"\nNumber of people: "+getPeople()+"\nTime "+ timeMeridian(getTime())+"\n" );
		return reservation;
	}
	
	//toString method for takeouts
	public String toStringTakeout() {
		String takeout;
		
		takeout = ("Name: "+getName()+"\nPhone Number: "+getPhoneNumber()+"\nTime "+ timeMeridian(getTime()) + "\n" );
		
	   return takeout += toString();
	}

	//converting 24hour time into actual time
	public String timeMeridian (int time) {
		String meridian = null;
		switch(time) {
		case 1100:{
			meridian = "11:00am"; 
			break;
		}
		case 1115:{
			meridian = "11:15am"; 
			break;
		}
		case 1130:{
			meridian = "11:30am";
			break;
		}
		case 1145:{ 
			meridian = "11:45am"; 
			break;
		}
		case 1200:{ 
			meridian = "12:00pm";
			break;
		}
		case 1215:{
			meridian = "12:15pm"; 
			break;
		}
		case 1230:{
			meridian = "12:30pm"; 
			break;
		}
		case 1245:{ 
			meridian = "12:45pm"; 
			break;
		}
		case 1300:{ 
			meridian = "1:00pm"; 
			break;
		}
		case 1315:{
			meridian = "1:15pm"; 
			break;
		}
		case 1330:{
			meridian = "1:30pm"; 
			break;
		}
		case 1345:{ 
			meridian = "1:45pm"; 
			break;
		}
		case 1400:{ 
			meridian = "2:00pm"; 
			break;
		}
		case 1415:{
			meridian = "2:15pm"; 
			break;
		}
		case 1430:{
			meridian = "2:30pm"; 
			break;
		}
		case 1445:{ 
			meridian = "2:45pm"; 
			break;
		}
		default:{
			meridian = " Store is closed";
		}
		
		}
		return meridian; //return it
	}//end method


	public static void main(String[] args) {

		ReservationRecord r = new ReservationRecord();
		//testing default constructor
		System.out.println(r.toStringReservation());

		System.out.println(); //add empty line

		//test the overloaded constructor
		r = new ReservationRecord("Devarsh","911",5,1130);
		System.out.println(r.toStringReservation());

		System.out.println(); //add empty line

		//test the other overloaded constructor
		r = new ReservationRecord("Devarsh/123456789/4/1400");
		System.out.println(r.toStringReservation());
		
		ReservationRecord t = new ReservationRecord();
		t.processTakeout("Dev/911/1430,Beef Soup/4.00;Apple Crisp/4.00;Pasta/5.00");
		System.out.println(t.toStringTakeout());
	}
}

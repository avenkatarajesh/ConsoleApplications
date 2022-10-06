import java.util.ArrayList;

public class Pasangers {
	private static int ID = 1;
	private int PasangerID;
	private String pasangerName;
	private int age;
	private int numOfTickets;
	private int flightId;
	private double ticketCost;
	private ArrayList<Pasangers> passengerList ;
	public Pasangers() {

	}

	public Pasangers(String pasangerName, int age, int numOfTickets , int fligthId) {
		super();
		PasangerID = ID;
		ID = ID + 1;
		this.pasangerName = pasangerName;
		this.age = age;
		this.numOfTickets = numOfTickets;
		this.flightId = fligthId;
	}
	public Pasangers(String pasangerName, int age, int numOfTickets , int fligthId ,ArrayList<Pasangers> coPasanger ) {
		super();
		PasangerID = ID;
		ID = ID + 1;
		this.pasangerName = pasangerName;
		this.age = age;
		this.numOfTickets = numOfTickets;
		this.flightId = fligthId;
		this.passengerList = coPasanger;
	}

	public int pasangerId() {
		return PasangerID;
	}
	
	public String getPasangerName() {
		return pasangerName;
	}

	public void setPasangerName(String pasangerName) {
		this.pasangerName = pasangerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getNumOfTickets() {
		return numOfTickets;
	}

	public void setNumOfTickets(int numOfTickets) {
		this.numOfTickets = numOfTickets;
	}
	
	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public double getTicketCost() {
		return ticketCost;
	}

	public void setTicketCost(double ticketCost) {
		this.ticketCost = ticketCost;
	}

	public ArrayList<Pasangers> getCoPasanger() {
		return passengerList;
	}

	public void setCoPasanger(ArrayList<Pasangers> coPasanger) {
		this.passengerList = coPasanger;
	}


}

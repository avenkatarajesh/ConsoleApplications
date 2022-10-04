import java.util.ArrayList;

public class FlightPasangerDataBase {

	private static FlightPasangerDataBase flight;
	ArrayList<Flight> flightList;
	ArrayList<Pasangers> pasangerList;

	FlightPasangerDataBase() {
		flightList = new ArrayList<>();
		pasangerList = new ArrayList<>();
	}

	public static FlightPasangerDataBase getInstance() {
		if (flight == null) {
			flight = new FlightPasangerDataBase();
		}
		return flight;
	}

	private void setFlight(Flight f) {
		FlightPasangerDataBase.getInstance().flightList.add(f);
	}

	private Flight getFlight(int iD) {
		for (int i = 0; i < FlightPasangerDataBase.getInstance().flightList.size(); i++) {
			if (FlightPasangerDataBase.getInstance().flightList.get(i).getFlightId() == iD) {
				return FlightPasangerDataBase.getInstance().flightList.get(i);
			}
		}
		return null;
	}

	public Flight findFlight(int iD) {
		return getFlight(iD);
	}

	public void addFlight(Flight f) {
		setFlight(f);
	}
	
	public void flightDetails() {
		System.out.println("FlightID   FlightCapacity    From       To       TicketCost");
		for(int i =0 ; i<flightList.size() ; i++) {
		System.out.println(flightList.get(i).getFlightId()+"          "+flightList.get(i).getCapacity()+"                "+flightList.get(i).getPlaceFrom()+"    "+flightList.get(i).getPlaceTo()+"    "+flightList.get(i).getTicketPrice());
		}
	}

	private void setPasanger(Pasangers p) {
		FlightPasangerDataBase.getInstance().pasangerList.add(p);
	}

	private Pasangers getPasanger(int iD) {
		for (int i = 0; i < FlightPasangerDataBase.getInstance().pasangerList.size(); i++) {
			if (FlightPasangerDataBase.getInstance().pasangerList.get(i).pasangerId() == iD) {
				return FlightPasangerDataBase.getInstance().pasangerList.get(i);
			}
		}
		return null;
	}

	public void addPasanger(Pasangers p) {
		setPasanger(p);
	}

	public Pasangers searchPasanger(int iD) {
		return getPasanger(iD);
	}

	public void removePasanger(int id) {
		Pasangers p = getPasanger(id);
		if (p != null) {
			FlightPasangerDataBase.getInstance().pasangerList.remove(p);
		}
	}

}

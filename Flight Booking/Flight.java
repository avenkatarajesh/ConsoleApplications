public class Flight {
	private static int Id=1;
	private int flightID;
	private int capacity;
	private double ticketPrice;
	private String placeFrom;
	private String placeTo;
	
	public Flight( String from , String to , double price) {
		super();
		flightID=Id;
		capacity = 50;
		ticketPrice = price;
		Id++;
		placeFrom = from;
		placeTo = to;
	}
	
	public String getPlaceFrom() {
		return placeFrom;
	}

	public void setPlaceFrom(String placeFrom) {
		this.placeFrom = placeFrom;
	}

	public String getPlaceTo() {
		return placeTo;
	}

	public void setPlaceTo(String placeTo) {
		this.placeTo = placeTo;
	}

	public int getFlightId() {
		return flightID;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
	
}

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookingFlightTicket {
	public Scanner scan = new Scanner(System.in);

	public void view() {
		System.out.println("Welcome To R Flight Ticket Booking ");
		System.out.printf("1 - Book Ticket \n2 - Cancel Ticket \n3 - Flights Details \n4 - Exit");
		int choice = 0;
		try {
			choice = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input");
		}
		if (choice < 0 || choice > 4)
			System.out.println("Invalid");

		FlightPasangerDataBase fd = FlightPasangerDataBase.getInstance();
		switch (choice) {
		case 1:
			System.out.println("Flight Id ");
			int flightId = 0;
			try {
				flightId = scan.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input");
			}
			Flight currFlight = fd.findFlight(flightId);
			if (currFlight == null) {
				System.out.println("invalid ID");
				break;
			}
			System.out.println("Enter the number of ticket to book ");
			int ticketcount = scan.nextInt();
			int check = currFlight.getCapacity();
			if (ticketcount > check || check < 0) {
				System.out.println("tickets not availble");
				break;
			}
			System.out.println("Enter the name ");
			String name = scan.next();
			System.out.println("Enter the age ");
			int age = scan.nextInt();
			Pasangers p1 = new Pasangers(name, age, ticketcount, flightId);
			fd.addPasanger(p1);
			bookFlight(currFlight, ticketcount, p1);
			view();
			break;

		case 2:
			System.out.println("Enter FlightID : ");
			int fId =0;
			try {
			fId = scan.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("Invalid input");
			}
			Flight cancelingFlightTicket = fd.findFlight(fId);
			if (cancelingFlightTicket == null) {
				System.out.println("Entered ID Invalid");
				break;
			}
			System.out.println("Enter the passanger ID");
			int pId = scan.nextInt();
			Pasangers pa = new Pasangers();
			pa = fd.searchPasanger(pId);
			if (pa == null) {
				System.out.println("Entered pasanger Id Invalid");
				break;
			}

			int count = pa.getNumOfTickets();
			cancelingFlightTicket.setCapacity(cancelingFlightTicket.getCapacity() + count);
			cancelingFlightTicket.setTicketPrice(cancelingFlightTicket.getTicketPrice() - count * 200);
			fd.removePasanger(pId);

			System.out.println("Ticket Cancelled and refund Initiated");
			view();
			break;
		case 3:
			fd.flightDetails();
			view();
			break;
		case 4:
			System.out.println("Thank You.");
			break;
		}
	}

	public void bookFlight(Flight currFlight, int ticketcount, Pasangers p1) {
		double totalTicketPrice = currFlight.getTicketPrice() * ticketcount;
		currFlight.setCapacity(currFlight.getCapacity() - ticketcount);
		currFlight.setTicketPrice(currFlight.getTicketPrice() + 200 * ticketcount);
		System.out.println("**********************");
		System.out.println("pasanger ID " + p1.pasangerId());
		System.out.println("total cost " + totalTicketPrice);
		System.out.println("Booked Successfully");
		System.out.println("**********************");
		view();
	}
	
	public void bookFlightInBuild(Flight currFlight, int ticketcount, Pasangers p1) {
		currFlight.setCapacity(currFlight.getCapacity() - ticketcount);
		currFlight.setTicketPrice(currFlight.getTicketPrice() + 200 * ticketcount);
	}

	public void settupFlight() {
		Flight f1 = new Flight("Chennai", "Delhi", 7000);
		Flight f2 = new Flight("Chennai", "Andra", 8000);
		Flight f3 = new Flight("Chennai", "Madurai", 3500);
		FlightPasangerDataBase fd = FlightPasangerDataBase.getInstance();
		fd.flightList.add(f1);
		fd.flightList.add(f2);
		fd.flightList.add(f3);
	}

	public void settupPasangers() {
		Pasangers p1 = new Pasangers("Rajesh", 22, 3, 1);
		Pasangers p2 = new Pasangers("vimal", 25, 6, 2);
		Pasangers p3 = new Pasangers("Manoj", 23, 3, 3);
		FlightPasangerDataBase fd = FlightPasangerDataBase.getInstance();
		fd.addPasanger(p1);
		fd.addPasanger(p2);
		fd.addPasanger(p3);
		bookFlightInBuild(fd.findFlight(p1.getFlightId()), p1.getNumOfTickets(), p1);
		bookFlightInBuild(fd.findFlight(p2.getFlightId()), p2.getNumOfTickets(), p2);
		bookFlightInBuild(fd.findFlight(p3.getFlightId()), p3.getNumOfTickets(), p3);
	}

	public static void main(String[] args) {
		BookingFlightTicket customer = new BookingFlightTicket();
		customer.settupFlight();
		customer.settupPasangers();
		customer.view();
	}
}

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookingFlightTicket {
	public Scanner scan = new Scanner(System.in);

	public void view() throws InterruptedException {
		Thread.sleep(1000);
		System.out.print("Loading.");
		for (int index = 0; index < 3; index++) {
			System.out.print(".");
			Thread.sleep(750);
		}
		System.out.println();
		System.out.println("Welcome To R Flight Ticket Booking ");
		System.out.printf("1 - Book Ticket \n2 - Cancel Ticket \n3 - Flights Details \n4 - Exit \n");
		System.out.println("if you didn't know about Flight details .First go and check it  :) ");
		System.out.println("Enter the Choice : ");
		int choice = 0;
		try {
			choice = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input");
		}
		if (choice < 0 || choice > 4)
			System.out.println("Invalid");

		FlightPasangerDataBase flightObj = FlightPasangerDataBase.getInstance();
		switch (choice) {
		case 1:
			System.out.println("Enter the Flight Id : ");
			int flightId = 0;
			try {
				flightId = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input");
				view();
			}
			Flight currFlight = flightObj.findFlight(flightId);
			if (currFlight == null) {
				System.out.println("invalid ID");
				view();
			}
			System.out.println("Enter the number of ticket to book ");
			int ticketcount = scan.nextInt();
			if (ticketcount == 0) {
				System.out.println("Entered input wrong .");
				view();
			}
			int check = currFlight.getCapacity();
			if (ticketcount > check || check < 0) {
				System.out.println("tickets not availble");
				view();
			}
			byte count = 1;
			System.out.println("Pasanger Details " + count);
			System.out.println("Enter the name : ");
			String name = scan.next();
			System.out.println("Enter the age :");
			byte age = 0;
			try {
				age = scan.nextByte();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input.");
			}
			Pasangers booker = new Pasangers(name, age, ticketcount, flightId);
			flightObj.addPasanger(booker);
			bookFlightInBuild(currFlight, ticketcount, booker);
			double totalTicketPrice = currFlight.getTicketPrice() * ticketcount;
			ArrayList<Pasangers> passengerList = new ArrayList<>();
			passengerList.add(booker);
			count++;
			while (count <= ticketcount) {
				System.out.println("Pasanger Details " + count);
				System.out.println("Enter the name ");
				name = scan.next();
				System.out.println("Enter the age ");
				age = scan.nextByte();
				Pasangers coPas = new Pasangers(name, age, ticketcount, flightId);
				flightObj.addPasanger(coPas);
				bookFlightInBuild(currFlight, ticketcount, coPas);
				passengerList.add(coPas);
				count++;
			}
			if (passengerList.size() > 0) {
				booker.setCoPasanger(passengerList);
			}
			System.out.println("**********************");
			System.out.println("pasanger ID " + booker.pasangerId());
			System.out.println("total cost " + totalTicketPrice);
			System.out.println("Booked Successfully");
			System.out.println("**********************");
			System.out.println();
			System.out.println("1. Back to Main .");
			System.out.println("2. Exit.");
			byte select = scan.nextByte();
			if (select == 1) {
				view();
			} else {
				System.out.println("Thank you");
			}
			break;
		case 2:
			System.out.println("Enter FlightID : ");
			int fId = 0;
			try {
				fId = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input");
			}
			Flight cancelingFlightTicket = flightObj.findFlight(fId);
			if (cancelingFlightTicket == null) {
				System.out.println("Entered ID Invalid");
				view();
			}
			System.out.println("Enter the passenger b ID : ");
			int pasenId = scan.nextInt();
			Pasangers passenger = flightObj.searchPasanger(pasenId);
			if (passenger == null) {
				System.out.println("Entered pasanger Id Invalid");
				view();
			}
			int cancelCount = 1;
			System.out.println("Enter the number of ticket to cancel :");
			cancelCount = scan.nextInt();
			if (cancelCount > passenger.getNumOfTickets() || cancelCount <= 0) {
				System.out.println("Invalid count.");
				view();
			} else if (cancelCount > 1) {
				ArrayList<Pasangers> list = passenger.getCoPasanger();
				System.out.printf(" %-10s %-13s %-3s %-8s \n", "PasangerID", "pasangerName", "age", "flightId");
				for (int index = 0; index < list.size(); index++) {
					System.out.printf("%-10s %-13s %-3s %-8s\n", list.get(index).pasangerId(),
							list.get(index).getPasangerName(), list.get(index).getAge(), list.get(index).getFlightId());
				}
				System.out.println();
				System.out.println("Enter passenger ID to delete :");
				for (int index = 0; index < cancelCount; index++) {
					System.out.println("Enter the Passenger ID : ");
					int Id = scan.nextInt();
					FlightPasangerDataBase.getInstance().removePasanger(Id);
				}
			}
			cancelingFlightTicket.setCapacity(cancelingFlightTicket.getCapacity() + cancelCount);
			cancelingFlightTicket.setTicketPrice(cancelingFlightTicket.getTicketPrice() - cancelCount * 200);
			flightObj.removePasanger(pasenId);
			System.out.println("Ticket Cancelled and refund Initiated");
			System.out.println("1. Back to Main .");
			System.out.println("2. Exit.");
			byte select1 = scan.nextByte();
			if (select1 == 1) {
				view();
			} else {
				System.out.println("Thank you");
			}
			break;
		case 3:
			flightObj.flightDetails();
			System.out.println("1. Back to Main .");
			System.out.println("2. Exit.");
			byte select2 = scan.nextByte();
			if (select2 == 1) {
				view();
			} else {
				System.out.println("Thank you");
			}
			break;
		case 4:
			System.out.println("Thank You.");
			break;
		}
	}

	public void bookFlight(Flight currFlight, int ticketcount, Pasangers p1) throws InterruptedException {
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
		Flight flight1 = new Flight("Chennai", "Delhi", 7000);
		Flight flight2 = new Flight("Chennai", "Andra", 8000);
		Flight flight3 = new Flight("Chennai", "Madurai", 3500);
		FlightPasangerDataBase fd = FlightPasangerDataBase.getInstance();
		fd.flightList.add(flight1);
		fd.flightList.add(flight2);
		fd.flightList.add(flight3);
	}

	public void settupPasangers() {
		Pasangers passen1 = new Pasangers("Rajesh", 22, 1, 1);
		Pasangers passen2 = new Pasangers("vimal", 25, 1, 2);
		Pasangers passen3 = new Pasangers("Manoj", 23, 1, 3);
		FlightPasangerDataBase fd = FlightPasangerDataBase.getInstance();
		fd.addPasanger(passen1);
		fd.addPasanger(passen2);
		fd.addPasanger(passen3);
		bookFlightInBuild(fd.findFlight(passen1.getFlightId()), passen1.getNumOfTickets(), passen1);
		bookFlightInBuild(fd.findFlight(passen2.getFlightId()), passen2.getNumOfTickets(), passen2);
		bookFlightInBuild(fd.findFlight(passen3.getFlightId()), passen3.getNumOfTickets(), passen3);
	}

	public static void main(String[] args) throws InterruptedException {
		BookingFlightTicket customer = new BookingFlightTicket();
		customer.settupFlight();
		customer.settupPasangers();
		customer.view();
	}
}

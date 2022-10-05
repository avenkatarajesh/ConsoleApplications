package bank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankLoginPage {
	Scanner scan = new Scanner(System.in);
	static Map<String, Customer> customerMap = new HashMap<>();

	public BankLoginPage() {
		customerMap = new HashMap<String, Customer>();
	}

	public void display() throws InterruptedException {

		Customer customer;
		String userName, password;
		double amount = 0;
		BankLoginPage bank = new BankLoginPage();
		outer: while (true) {
			Thread.sleep(2000);
			System.out.println("      Welcome to R Bank");
			System.out.println(" 1. Existing Customer.");
			System.out.println(" 2. New Customer.");
			System.out.println(" 3. About us.");
			System.out.println(" 4. Exit.");
			System.out.println("-------------------------");
			System.out.println("Select the choice : ");
			int choice = scan.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter your UserName :");
				userName = scan.next();
				scan.nextLine();
				System.out.println("Enter PassWord : ");
				password = scan.next();
				if (bank.customerMap.containsKey(userName)) {
					customer = bank.customerMap.get(userName);
					if (customer.passWord.equals(password)) {
						inner: while (true) {
							System.out.println("WELCOME " + customer.getCusName());
							System.out.println("1. Money WithDraw");
							System.out.println("2. Money Deposit");
							System.out.println("3. Balance Enquiry.");
							System.out.println("4. Mini statement.");
							System.out.println("5. Account Details");
							System.out.println("6. Logout");
							System.out.println(" Enter your Choice :");
							choice = scan.nextInt();
							Bank_Management bankCustomer = new Bank_Management(customer);
							scan.nextLine();
							switch (choice) {
							case 1:
								System.out.println("Enter the amount :");
								amount = scan.nextDouble();
								bankCustomer.withdraw(amount);
								break;
							case 2:
								System.out.println("Enter the amount :");
								amount = scan.nextDouble();
								bankCustomer.deposit(amount, customer);
								break;
							case 3:
								bankCustomer.balanceEnquiry();
								break;
							case 4:
								bankCustomer.miniStatement();
								break;
							case 5:
								bankCustomer.accountDetails();
								break;
							case 6:
								break inner;
							default:
								System.out.println("Invalid Choice , Retry.");
							}
						}
					} else {
						System.out.println("Wrong Password");
						scan.nextLine();
					}
				} else {
					System.out.println("UserName not Found.");
					scan.nextLine();
				}
				break;
			case 2:
				System.out.println("Willing to Open an Account :");
				System.out.println("1. yes");
				System.out.println("2. No");
				choice = scan.nextInt();
				if (choice == 1) {
					System.out.print("Enter Your Name :");
					String name = scan.next();
					scan.nextLine();
					System.out.print("Enter Your Phone No. :");
					String phoneNo = scan.next();
					System.out.print("Enter Account Type(Saving / current) : ");
					String type = scan.next();
					scan.nextLine();
					System.out.println("Enter the Amount to intial deposit : ");
					amount = scan.nextDouble();
					scan.nextLine();
					System.out.println("Set UserName :");
					userName = scan.next();
					while (bank.customerMap.containsKey(userName)) {
						System.out.println("User Name Already exist. Try Again");
						System.out.println("Set UserName :");
						userName = scan.next();
					}
					System.out.println(
							"Set the password (minimum 8 chars; minimum 1 digit, 1 lowercase, 1 uppercase, 1 special character[!@#$%^&*_]) :");
					password = scan.next();

					while (!password.matches((("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,}")))) {
						System.out.println("Password Not Satisfied the Condtion. Try Again.");
						password = scan.next();
					}
					Bank_Management newCustomer = new Bank_Management(name, type, amount, phoneNo, password);
					newCustomer.accountDetails();
					customer = newCustomer.getCustomer();
					bank.customerMap.put(userName, customer);
					System.out.println("Account Created SuccessFully");
					break;
				} else {
					System.out.println("Thank You.");
				}
				break;
			case 3:
				System.out.println("R Bank ");
				System.out.println("Since 2022");
				System.out.println("Founder Rajesh.");
				Bank_Management.getCustomers();
				break;
			case 4:
				System.out.println("Thank you.");
				break outer;
			default:
				System.out.println("Invalid Try Again.");
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		BankLoginPage blp = new BankLoginPage();
		blp.display();
	}

}

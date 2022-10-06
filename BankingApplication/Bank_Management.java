package bank;

import java.util.ArrayList;

public class Bank_Management {
	private static Long count = 101010L;
	private Customer customer;
	static ArrayList<Customer> CustomerList = new ArrayList<>();
	private int CusListIndex = 0;

	public Bank_Management(Customer cus) {
		this.customer = cus;
		CusListIndex = CustomerList.indexOf(cus);
	}

	public Bank_Management(String cusName, String accType, double accBalance, String phoneNo, String passWord) {
		customer = new Customer(cusName, count, accType, accBalance, phoneNo, passWord);
		CustomerList.add(this.customer);
		CusListIndex++;
		count++;
	}

	public void deposit(double amount, Customer cus) {
		cus.setAccBalance(amount);
		cus.setTransaction(amount + " Added to your Account");
		balanceEnquiry();
	}

	public void withdraw(double amount) {
		balanceEnquiry();
		String debited = CustomerList.get(CusListIndex).withDraw(amount);
		System.out.println(debited);
		balanceEnquiry();
	}

	public void accountDetails() {
		customer.displaydetails();
	}

	public void balanceEnquiry() {
		System.out.println(" Available Balance " + CustomerList.get(CusListIndex).getAccBalance());
	}

	public void miniStatement() {
		ArrayList<String> transaction = CustomerList.get(CusListIndex).getTransaction();
		if (transaction.size() < 0) {
			System.out.println("No Transaction Available.");
			return;
		}
		for (int index = transaction.size() - 1; index >= 0; index--) {
			System.out.println((transaction.size() - index + 1) + " " + transaction.get(index));
		}
	}

	public Customer getCustomer() {
		return customer;
	}

	public static void getCustomers() {
		System.out.println(CustomerList);
	}

}

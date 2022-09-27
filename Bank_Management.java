package bank;

import java.util.ArrayList;

public class Bank_Management {
	static Long count = 100000L;
	Customer customer;
	static ArrayList<Customer> CustomerList = new ArrayList<>();
	int index = 0;

	public Bank_Management(Customer cus) {
		this.customer = cus;
		index = CustomerList.indexOf(cus);
	}

	public Bank_Management(String cusName, String accType, double accBalance, String phoneNo, String passWord) {
		customer = new Customer(cusName, count, accType, accBalance, phoneNo, passWord);
		CustomerList.add(this.customer);
		index++;
		count++;
	}

	public void deposit(double amount , Customer cus) {
		cus.setAccBalance(amount);
		cus.setTransaction(amount + " Added to your Account");
		balanceEnquiry();
	}

	public void withdraw(double amount) {
		String s = CustomerList.get(index).withDraw(amount);
		balanceEnquiry();
		System.out.println(s);
	}

	public void accountDetails() {
		index = CustomerList.indexOf(customer);
		CustomerList.get(index).displaydetails();
	}

	public void balanceEnquiry() {
		System.out.println(" Available Balance " +CustomerList.get(index).getAccBalance());
	}

	public void miniStatement() {
		ArrayList transaction = CustomerList.get(index).getTransaction();
		if(transaction.size() < 0) {
			System.out.println("No Transaction Available.");
			return;
		}
		for (int i = transaction.size() - 1; i >= 0; i--) {
			System.out.println((transaction.size() - i + 1)+" "+ transaction.get(i));
		}
	}

	public Customer getCustomer() {
		return customer;
	}
	public static void getCustomers() {
		System.out.println(CustomerList);
	}
}

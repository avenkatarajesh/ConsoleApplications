package bank;

import java.util.ArrayList;

public class Customer {

	private static String bankName = "R bank";
	private String cusName;
	private long accNum;
	private String accType;
	private double accBalance = 0;
	private String phoneNo;
	public String passWord;
	ArrayList<String> transaction = new ArrayList<>();

	public ArrayList<String> getTransaction() {
		return transaction;
	}

	public void setTransaction(String t) {
		transaction.add(t);
	}

	public Customer(String cusName, long accNum, String accType, double accBalance, String phoneNo, String passWord) {
		this.cusName = cusName;
		this.accNum = accNum;
		this.accType = accType;
		this.accBalance = accBalance;
		this.phoneNo = phoneNo;
		this.passWord = passWord;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public long getAccNum() {
		return accNum;
	}

	public void setAccNum(long accNum) {
		this.accNum = accNum;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public double getAccBalance() {
		return accBalance;
	}

	public void setAccBalance(double amount) {
		this.accBalance += amount;
		System.out.println("Money Credited");
	}

	public String withDraw(double amount) {
		if (amount > accBalance) {
			return "Insufficient Balance";
		}
		accBalance -= amount;
		setTransaction(amount + "debited");
		return "collect the Money";
	}

	public static String getBankNme() {
		return bankName;
	}

	public void displaydetails() {
		System.out.println("Account Holder Name :" + cusName);
		System.out.println("Account Number :" + accNum);
		System.out.println("Account Type :" + accType);
		System.out.println("Available Balance : " + accBalance);
		System.out.println("Phone no. " + phoneNo);
	}

	@Override
	public String toString() {
		return "Customer [cusName=" + cusName + ", accNum=" + accNum + "]";
	}

}

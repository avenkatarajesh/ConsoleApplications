package view;

import java.util.Scanner;

import controler.ManageRoleController;
import controler.ManageUserController;

public class RoleOfHierarchy {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		RoleOfHierarchy Roletree = new RoleOfHierarchy();
		Roletree.gettingRootRole();
	}

	public ManageRoleController getInstance() {
		ManageRoleController mrc = new ManageRoleController();
		return mrc;
	}

	public void gettingRootRole() throws Exception {
		ManageRoleController mrc = new ManageRoleController();
		mrc.setRootRole();
		operation();
	}

	public void addSubRole() throws Exception {
		ManageRoleController mrc = new ManageRoleController();
		mrc.setSubRole();
		operation();
	}

	public void displayRoles() throws Exception {
		ManageRoleController mrc = new ManageRoleController();
		mrc.totalRoles();
		operation();
	}

	public void deleteRoles() throws Exception {
		ManageRoleController mrc = new ManageRoleController();
		mrc.removeRole();
		operation();
	}

	public void addUser() throws Exception {
		ManageUserController muc = new ManageUserController();
		muc.setUser();
		operation();
	}

	public void displayUser() throws Exception {
		ManageUserController muc = new ManageUserController();
		muc.showUsers();
		operation();
	}

	public void displayUserAndSubUser() throws Exception {
		ManageUserController muc = new ManageUserController();
		muc.allUsers();
		operation();
	}

	public void deleteUsers() throws Exception {
		ManageUserController muc = new ManageUserController();
		muc.removeUser();
		operation();
	}

	public void operation() throws Exception {
		System.out.println("Loading....");
		Thread.sleep(2000);
		System.out.println();
		int choice = 0;
		System.out.println("Operation to be performed.");
		System.out.println("(1) Add Sub Role.");
		System.out.println("(2) Display Roles.");
		System.out.println("(3) Delete Roles.");
		System.out.println("(4) Add User.");
		System.out.println("(5) Display Users.");
		System.out.println("(6) Display User and Sub Users.");
		System.out.println("(7) Delete User.");
//		System.out.println("(8) Number of User from Top.");
//		System.out.println("(9) Height of Role Hierarchy.");
//		System.out.println("(10) Common boss of users");
		System.out.println("(0) Exit.");

		choice = scan.nextInt();
		switch (choice) {
		case 1:
			addSubRole();
			break;
		case 2:
			displayRoles();
			break;
		case 3:
			deleteRoles();
			break;
		case 4:
			addUser();
			break;
		case 5:
			displayUser();
			break;
		case 6:
			displayUserAndSubUser();
			break;
		case 7:
			deleteUsers();
			break;
//		case 8:
//			numberOfUserFromTop();
//			break;
//		case 9:
//			heightOfRoleHierarchy();
//			break;
//		case 10:
//			commonBossOfUsers();
//			break;
		case 0:
			break;
		default:
			System.out.println("Invalid Operation");
			operation();
			break;
		}
	}

	public void commonBossOfUsers() {

	}

	public void heightOfRoleHierarchy() {

	}

	public void numberOfUserFromTop() {
	}

}
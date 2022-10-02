package view;

import java.util.ArrayList;
import java.util.Scanner;
import controler.RolesManager;
import controler.UsersManager;
import model.Role;
import model.User;

public class RoleOfHierarchy {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		RoleOfHierarchy Roletree = new RoleOfHierarchy();
		Roletree.gettingRootRole();

	}

	public void gettingRootRole() {
		System.out.println("Enter the root role name :");
		Role role1 = new Role(scan.next(), true);
		RolesManager.getRolesManager().setRootRole(role1);
		RolesManager.getRolesManager().addRole(role1);
		operation();
	}

	public void addSubRole() {
		System.out.println("Enter the sub role name :");
		String subRoleName = scan.next();
		Role searchedsubRole = RolesManager.getRolesManager().searchRole(subRoleName);
		if (searchedsubRole != null) {
			System.out.println("Role already exists ,Try new role");
		} else {
			Role subRole = new Role(subRoleName, false);
			System.out.println("Enter the reporting to role name : ");
			String reportingTo = scan.next();
			Role reportingRole = RolesManager.getRolesManager().searchRole(reportingTo);
			if (reportingRole == null) {
				System.out.println("Reporting Role Not Found Try Again");
				operation();
			} else {
				reportingRole.addSubordinateRole(subRole);
				Role rootRole = RolesManager.getRolesManager().getRootRole();
				if (reportingRole != rootRole) {
					rootRole.addSubordinateRole(subRole);
				}
				subRole.setReportingTo(reportingRole);
				RolesManager.getRolesManager().addRole(subRole);
			}
		}
		System.out.println();
		operation();
	}

	public void displayRoles() {
		ArrayList<Role> displayingRoles = RolesManager.getRolesManager().getRoles();
		for (int i = 0; i < displayingRoles.size(); i++) {
			System.out.println(displayingRoles.get(i).getName());
		}
		System.out.println();
		operation();
	}

	public void deleteRoles() {
		System.out.println("Enter the role to be deleted : ");
		String deletingRole = scan.next();
		Role searchedDeletingRole = RolesManager.getRolesManager().searchRole(deletingRole);
		if (searchedDeletingRole.equals(null)) {
			System.out.println("Role does not exists ,Try again");
			operation();
		} else {
			System.out.println("Enter the role to be transferred :");
			String changingParent = scan.next();
			Role searchedTransferRole = RolesManager.getRolesManager().searchRole(changingParent);
			if (searchedTransferRole.equals(null)) {
				System.out.println("Role does not exists ,Try again");
				operation();
			} else {
				ArrayList<Role> subordinates = searchedDeletingRole.getSubOrdinateRoles();
				for (int i = 0; i < subordinates.size(); i++) {
					subordinates.get(i).setReportingTo(searchedTransferRole);
				}
				searchedTransferRole.addSubordinateRoles(subordinates);
				User user = UsersManager.getInstance().searchUser(searchedDeletingRole);
				if (user != null) {
					user.setUserRole(searchedTransferRole);
				}
				searchedDeletingRole.getReporting().removeSubordinate(searchedDeletingRole);
				RolesManager.getRolesManager().removeRole(searchedDeletingRole);
			}
		}
		System.out.println();
		operation();
	}

	public void addUser() {
		System.out.println("Enter User Name : ");
		String userName = scan.next();
		System.out.println("Enter Role : ");
		String userRole = scan.next();
		Role searchedUserRole = RolesManager.getRolesManager().searchRole(userRole);
		if (searchedUserRole == null) {
			System.out.println("Entered User Role does not exists ,Try again");
		} else {
			User user = new User(userName, searchedUserRole);
			System.out.println("This is Your Emplyee ID - " + user.getiD());
			UsersManager userManager = UsersManager.getInstance();
			userManager.addUser(user);
		}
		System.out.println();
		operation();
	}

	public void displayUser() {
		ArrayList<User> displayingUser = UsersManager.getInstance().getUsersList();
		if(displayingUser.size() == 0) {
			System.out.println("User not setuped");
		}
		for (User us : displayingUser) {
			System.out.println(us.getUserName() + " - " + us.getUserRole().getName());
		}
		operation();
	}

	public void displayUserAndSubUser() {
		ArrayList<Role> displayingRoles = RolesManager.getRolesManager().getRoles();
		for (int i = 0; i < displayingRoles.size(); i++) {
			ArrayList<Role> roles = displayingRoles.get(i).getSubOrdinateRoles();
			User head = UsersManager.getInstance().searchUser(displayingRoles.get(i));
			if (head != null) {
				String userName = head.getUserName();
				System.out.print(userName + " - ");
				for (int j = 0; j < roles.size(); j++) {
					User user = UsersManager.getInstance().searchUser(roles.get(j));
					if (user != null) {
						System.out.print(user.getUserName());
					}
					if (j < roles.size() - 1) {
						System.out.print(" ,");
					}
				}
				System.out.print(".");
				System.out.println();
			}
		}
		System.out.println();
		operation();
	}

	public void deleteUsers() {
		System.out.println("Enter username to be deleted : ");
		String name = scan.next();
		ArrayList<User> userList = UsersManager.getInstance().searchUser(name);
		if (userList.size() == 0) {
			System.out.println("User not Found , Try again.");
		} else if (userList.size() == 1) {
			UsersManager.getInstance().remove(userList.get(0));
			System.out.println("Success fully Removed.");
		} else {
			System.out.println("Enter the Id :");
			int iD = scan.nextInt();
			for (User us : userList) {
				if (us.getiD() == iD) {
					UsersManager.getInstance().remove(iD);
					System.out.println("Success fully Removed.");
					break;
				}
			}
		}
		System.out.println();
		operation();
	}

	public void operation() {
		int choice = 0;
		System.out.println("Operation to be performed.");
		System.out.println("(1) Add Sub Role.");
		System.out.println("(2) Display Roles.");
		System.out.println("(3) Delete Roles.");
		System.out.println("(4) Add User.");
		System.out.println("(5) Display Users.");
		System.out.println("(6) Display User and Sub Users.");
		System.out.println("(7) Delete User.");
		System.out.println("(8) Number of User from Top.");
		System.out.println("(9) Height of Role Hierarchy.");
		System.out.println("(10) Common boss of users");
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
		case 8:
			numberOfUserFromTop();
			break;
		case 9:
			heightOfRoleHierarchy();
			break;
		case 10:
			commonBossOfUsers();
			break;
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

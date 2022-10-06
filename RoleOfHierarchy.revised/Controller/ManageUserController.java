package controler;

import java.util.ArrayList;
import java.util.Scanner;

import model.Role;
import model.RolesManager;
import model.User;
import model.UsersManager;

public class ManageUserController {
	Scanner scan = new Scanner(System.in);

	public void setUser() {
		System.out.println("Enter User Name : ");
		String userName = scan.next();
		System.out.println("Enter Role : ");
		String userRole = scan.next();
		model.Role searchedUserRole = RolesManager.getRolesManager().searchRole(userRole);
		if (searchedUserRole == null) {
			System.out.println("Entered User Role does not exists ,Try again");
		} else {
			User user = new User(userName, searchedUserRole);
			System.out.println("This is Your Emplyee ID - " + user.getiD());
			UsersManager userManager = UsersManager.getInstance();
			userManager.addUser(user);
		}
		System.out.println("Done.");
	}

	public void showUsers() {
		if (UsersManager.getInstance().getUsersList().size() == 0) {
			System.out.println("User not setup");
		} else {
			ArrayList<User> displayingUser = UsersManager.getInstance().getUsersList();
			if (displayingUser.size() == 0) {
				System.out.println("User not setuped");
			}
			for (User user : displayingUser) {
				System.out.println(user.getUserName() + " - " + user.getUserRole().getName());
			}
		}
		System.out.println("Done.");
	}

	public void removeUser() {
		if (UsersManager.getInstance().getUsersList().size() == 0) {
			System.out.println("User not setup");
		} else {
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
				for (User user : userList) {
					if (user.getiD() == iD) {
						UsersManager.getInstance().remove(iD);
						System.out.println("Success fully Removed.");
						break;
					}
				}
			}
		}
		System.out.println("Done.");
	}

	public void allUsers() {
		if (UsersManager.getInstance().getUsersList().size() == 0) {
			System.out.println("User not setup");
		} else {
			ArrayList<Role> displayingRoles = RolesManager.getRolesManager().getRoles();
			for (int index = 0; index < displayingRoles.size(); index++) {
				ArrayList<Role> roles = displayingRoles.get(index).getSubOrdinateRoles();
				User mainUser = UsersManager.getInstance().searchUser(displayingRoles.get(index));
				if (mainUser != null) {
					String userName = mainUser.getUserName();
					System.out.print(userName + " - ");
					for (int j = 0; j < roles.size(); j++) {
						User user = UsersManager.getInstance().searchUser(roles.get(j));
						if (user != null) {
							System.out.print(user.getUserName());
						}
						if (j < roles.size() - 1) {
							System.out.print(", ");
						}
					}
					System.out.print(".");
					System.out.println();
				}
			}
		}
		System.out.println("Done.");
	}

}

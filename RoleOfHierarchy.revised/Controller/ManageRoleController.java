package controler;

import java.util.ArrayList;
import java.util.Scanner;

import model.Role;
import model.RolesManager;
import model.User;
import model.UsersManager;

public class ManageRoleController {
	Scanner scan = new Scanner(System.in);
	
	public void setRootRole() {
		System.out.println("Enter the root role name :");
		Role rootRole = new Role(scan.next(), true);
		RolesManager.getRolesManager().setRootRole(rootRole);
		RolesManager.getRolesManager().addRole(rootRole);
		System.out.println("Done.");
	}
	
	public void setSubRole() {
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
		System.out.println("Done.");
	}
	
	public void totalRoles() {
		ArrayList<Role> displayingRoles = RolesManager.getRolesManager().getRoles();
		for (int i = 0; i < displayingRoles.size(); i++) {
			System.out.println(displayingRoles.get(i).getName());
		}
		System.out.println("Done.");
	}
	
	public void removeRole() {
		System.out.println("Enter the role to be deleted : ");
		String deletingRole = scan.next();
		Role searchedDeletingRole = RolesManager.getRolesManager().searchRole(deletingRole);
		if (searchedDeletingRole.equals(null)) {
			System.out.println("Role does not exists ,Try again");
		} else {
			System.out.println("Enter the role to be transferred :");
			String changingParent = scan.next();
			Role searchedTransferRole = RolesManager.getRolesManager().searchRole(changingParent);
			if (searchedTransferRole.equals(null)) {
				System.out.println("Role does not exists ,Try again");
			} else {
				ArrayList<Role> subordinates = searchedDeletingRole.getSubOrdinateRoles();
				for (int index = 0; index < subordinates.size(); index++) {
					subordinates.get(index).setReportingTo(searchedTransferRole);
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
		System.out.println("Done.");
	}
}

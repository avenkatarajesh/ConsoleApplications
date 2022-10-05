package model;
import java.util.ArrayList;
public class Role {
	@Override
	public String toString() {
		return "Role [roleName=" + roleName + "]";
	}

	private String roleName;
	private Role reportingTo;
	private Boolean root;
	private ArrayList<Role> subOrdinateRoles = new ArrayList<Role>();

	public Role(String roleName, Boolean root) {
		this.roleName = roleName;
		this.root = root;
	}

	public Boolean isRoot() {
		return root;
	}

	public String getName() {
		return roleName;
	}

	public void setReportingTo(Role reportingTo) {
		this.reportingTo = reportingTo;
	}

	public Role getReporting() {
		return reportingTo;
	}

	public void addSubordinateRole(Role subRole) {
		this.subOrdinateRoles.add(subRole);
	}

	public ArrayList<Role> getSubOrdinateRoles() {
		return subOrdinateRoles;
	}

	public void removeSubordinate(Role deletingRole) {
		subOrdinateRoles.remove(deletingRole);
	}

	public void addSubordinateRoles(ArrayList<Role> subRoles) {
		this.subOrdinateRoles.addAll(subRoles);
	}
}

package model;

public class User {
	private String userName;
	private Role userRole;
	private int iD;
	static private int count = 1;

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public User(String userName, Role userRole) {
		this.userName = userName;
		this.userRole = userRole;
		this.iD = count;
		count++;
	}

	public String getUserName() {
		return userName;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserName(String name) {
		this.userName = name;
	}

	public void setUserRole(Role role) {
		this.userRole = role;
	}
	
	@Override
	public String toString() {
		return "User [userName=" + userName + ", userRole=" + userRole + ", iD=" + iD + "]";
	}


}

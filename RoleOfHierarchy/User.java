import java.util.ArrayList;
import java.util.HashMap;

public class User {
	private String userName;
	private Role userRole;

	User(String userName, Role userRole) {
		this.userName = userName;
		this.userRole = userRole;
	}

	public String getUserName() {
		return userName;
	}

	public Role getUserRole() {
		return userRole;
	}

//	public void setUsersList(String userList, Role role) {
//		UsersList.put(userList, role);
//	}
//
//	public HashMap<String, Role> getUsersList() {
//		return UsersList;
//	}

}

import java.util.HashMap;

public class UsersManager {

	static HashMap<String, Role> userList = new HashMap<String, Role>();

	public HashMap<String, Role> getUserList() {
		return userList;
	}

	public void setUserList(HashMap<String, Role>userList) {
		UsersManager.userList = userList;
	}

	public void addUsers(String userName , Role role) {
		userList.put(userName , role);
	}

	public HashMap<String, Role> getUser() {
		return userList;
	}

	public boolean searchName(String name) {
		if (userList.containsValue(name) == true) {
			return true;
		} else
			return false;
	}

}
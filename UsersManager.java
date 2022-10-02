package controler;

import java.util.ArrayList;

import model.Role;
import model.User;

public class UsersManager {

	static UsersManager userDataBase;
	ArrayList<User> usersList = new ArrayList<>();

	public static UsersManager getInstance() {
		if (userDataBase == null) {
			userDataBase = new UsersManager();
		}
		return userDataBase;
	}

	public ArrayList<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(ArrayList<User> usersList) {
		this.usersList = usersList;
	}

	public void addUser(User user) {
		this.usersList.add(user);
	}

	public void remove(User user) {
		this.usersList.remove(user);
	}

	public void remove(int id) {
		User user = searchUser(id);
		this.usersList.remove(user);
	}

	public ArrayList<User> searchUser(String name) {
		ArrayList<User> returnUser = new ArrayList<>();
		for(int i = 0 ; i < UsersManager.getInstance().usersList.size() ; i++ ) {
			if( UsersManager.getInstance().usersList.get(i).getUserName().equalsIgnoreCase(name)) {
				returnUser.add(UsersManager.getInstance().usersList.get(i));
			}
		}
		return returnUser;
	}

	
	public User searchUser(Role role) {
		for (User us : usersList) {
			if (us.getUserRole() == role) {
				return us;
			}
		}
		return null;
	}

	public User searchUser(int id) {
		for (User us : usersList) {
			if (us.getiD() == id) {
				return us;
			}
		}
		return null;
	}

}
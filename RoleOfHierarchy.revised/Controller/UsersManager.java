package controler;
import java.util.ArrayList;
import java.util.HashMap;

import model.Role;
import model.User;

public class UsersManager {
	
	static UsersManager userDataBase ;
	ArrayList<User> usersList = new ArrayList<>();
	
	public static UsersManager getInstance() {
		if(userDataBase == null) {
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
	
	public User searchUser(String name) {
		for(User us : usersList) {
			if(us.getUserName() == name) {
				return us;
			}
		}
		return null;
	}
	
	public User searchUser(Role role) {
		for(User us : usersList) {
			if(us.getUserRole() == role) {
				return us;
			}
		}
		return null;
	}

}
package controler;
import model.Role;
import java.util.ArrayList;
import java.util.Iterator;

public class RolesManager {

	public Role rootRole;
	private static final  ArrayList<Role> ROLES = new ArrayList<Role>();
	private static final RolesManager ROLE_MANAGER = new RolesManager();

	public static RolesManager getRolesManager() {
		return ROLE_MANAGER;
	}

	public void addRole(Role addingRole) {
		ROLES.add(addingRole);
	}

	public ArrayList<Role> getRoles() {
		return ROLES;
	}

	public void setRootRole(Role rootRole) {
		this.rootRole = rootRole;
	}

	public Role getRootRole() {
		return rootRole;
	}

	public Role searchRole(String roleName) {
		for (int i = 0; i < ROLES.size(); i++) {
			if (ROLES.get(i).getName().equals(roleName)) {
				return ROLES.get(i);
			}
		}
		return null;
	}

	public void removeRole(Role removingRole) {
		ROLES.remove(removingRole);
	}
}
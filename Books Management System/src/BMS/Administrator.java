package BMS;

import java.util.HashSet;
import java.util.Iterator;

public class Administrator extends User {

	public Administrator(String id, String password) {
		super(id, password);
	}
	public User getUser(String id,HashSet<User> users) {
		Iterator<User> iterator = users.iterator();
		User user;
		while(iterator.hasNext()) {
			user=iterator.next();
			if(user.getId().equals(id))
				return user;
		}
		return null;
	}
	public User removeUser(User user,HashSet<User> users) {
		if (users.remove(user)) {
			return user;
		} else {
			return null;
		}
	}
	@Override
	public String toString() {
		return "π‹¿Ì‘±" + this.id;
	}
}

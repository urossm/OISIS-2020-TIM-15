package org.infsys.pharmacy.util;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.model.User;

public class Authentication {

	/**
	 * Authenticate given username and password.
	 * @param username
	 * @param password
	 * @return If user with given username and password is found, return his instance. Otherwise, return null.
	 */
	public static User authenticate(String username, String password) {
		if (ApplicationSingleton.getInstance().getUsers().containsKey(username)) {
			User user = ApplicationSingleton.getInstance().getUsers().get(username);
			if (password.equals(user.getPassword())) {
				return user;
			} else {
				return null;
			}
		}
		return null;
	}
}
package org.infsys.pharmacy;

import java.util.HashMap;
import java.util.Map;

import org.infsys.pharmacy.model.User;
import org.infsys.pharmacy.view.LoginFrame;
import org.infsys.pharmacy.view.MainFrame;

/**
 * Class which serves as a Singleton pattern in our application. 
 * It provides single instance of itself and also single instances of other classes which we believe should have only one instance.
 */
public class ApplicationSingleton {

	private static final ApplicationSingleton instance = new ApplicationSingleton();
	private LoginFrame loginFrame;
	private MainFrame mainFrame;
	private Map<String, User> users;
	private User loggedInUser;
	
	private ApplicationSingleton() {	
	}

	public static ApplicationSingleton getInstance() {
		return instance;
	}

	public LoginFrame getLoginFrame() {
		return this.loginFrame;
	}

	public void setLoginFrame(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}
	
	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	public Map<String, User> getUsers() {
		if (this.users == null) {
			this.users = new HashMap<>();
		}
		return this.users;
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
}
package org.infsys.pharmacy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.infsys.pharmacy.model.Bill;
import org.infsys.pharmacy.model.Medication;
import org.infsys.pharmacy.model.Prescription;
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
	private Map<String, Medication> medications;
	private List<Prescription> prescriptions;
	private List<Bill> bills;
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
	
	public Map<String, Medication> getMedications() {
		if (this.medications == null) {
			this.medications = new HashMap<>();
		}
		return medications;
	}

	public List<Prescription> getPrescriptions() {
		if (this.prescriptions == null) {
			this.prescriptions = new ArrayList<>();
		}
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public List<Bill> getBills() {
		if (this.bills == null) {
			this.bills = new ArrayList<>();
		}
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
}
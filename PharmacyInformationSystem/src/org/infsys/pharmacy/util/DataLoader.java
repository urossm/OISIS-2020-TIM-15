package org.infsys.pharmacy.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.model.Medication;
import org.infsys.pharmacy.model.User;

public class DataLoader {

	/**
	 * Read files to retrieve all users, medicines and prescriptions 
	 */
	public static void loadData() {
		
		//Retrieve users
		File file = new File(Constants.USERS_PATH);
		
		if (file.exists()) {
			@SuppressWarnings("unchecked")
			List<User> users = (List<User>) FileUtil.loadObjectFromFile(file);
			
			for (User user : users) {
				ApplicationSingleton.getInstance().getUsers().put(user.getUsername(), user);
			}
		} else {
			List<User> users = new ArrayList<>();
			users.add(Constants.ADMINISTRATOR);
			ApplicationSingleton.getInstance().getUsers().put(Constants.ADMINISTRATOR.getUsername(), Constants.ADMINISTRATOR);
			FileUtil.saveObjectInFile(users, file);
		}
		
		//Retrieve medications
		file = new File(Constants.MEDICINES_PATH);
		
		if (file.exists()) {
			@SuppressWarnings("unchecked")
			List<Medication> medications = (List<Medication>) FileUtil.loadObjectFromFile(file);
			for (Medication medication : medications) {
				ApplicationSingleton.getInstance().getMedications().put(medication.getCode(), medication);				
			}
		} else {
			List<Medication> medications = new ArrayList<>();
			FileUtil.saveObjectInFile(medications, file);
		}
		
	}
}
package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.model.User;
import org.infsys.pharmacy.model.UserType;
import org.infsys.pharmacy.util.Constants;
import org.infsys.pharmacy.util.FileUtil;
import org.infsys.pharmacy.view.UserRegistrationForm;

public class AddUserAction extends AbstractAction {
	
	private static final long serialVersionUID = -3283704382238240057L;
	private UserRegistrationForm userRegistrationForm;

	public AddUserAction(String name, UserRegistrationForm userRegistrationForm) {
		putValue(NAME, name);
		this.userRegistrationForm = userRegistrationForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Check if there is already user with entered username. If there is, don't add user and show error message.
		//Otherwise, add user into list and save list in file
		if (ApplicationSingleton.getInstance().getUsers().containsKey(userRegistrationForm.getUsernameTextField().getText())) {
			JOptionPane.showMessageDialog(null, Constants.USER_EXISTS_ERROR_MSG, Constants.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
		} else {
			User user = new User(userRegistrationForm.getUsernameTextField().getText(),
					userRegistrationForm.getPasswordTextField().getText(),
					userRegistrationForm.getFirstNameTextField().getText(),
					userRegistrationForm.getLastNameTextField().getText(),
					(UserType) userRegistrationForm.getUserTypeComboBox().getSelectedItem());
			
			ApplicationSingleton.getInstance().getUsers().put(user.getUsername(), user);
			FileUtil.saveObjectInFile(new ArrayList<>(ApplicationSingleton.getInstance().getUsers().values()), new File(Constants.USERS_PATH));
			ApplicationSingleton.getInstance().getMainFrame().getUserDatabasePanel().addRow(user);
			userRegistrationForm.dispose();
		}
	}
}
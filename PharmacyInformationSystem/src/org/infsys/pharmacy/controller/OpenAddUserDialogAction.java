package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.infsys.pharmacy.view.UserRegistrationForm;

public class OpenAddUserDialogAction extends AbstractAction {
	
	private static final long serialVersionUID = -3283704382238240057L;

	public OpenAddUserDialogAction(String name) {
		putValue(NAME, name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		UserRegistrationForm userRegistrationForm = new UserRegistrationForm();
		userRegistrationForm.setVisible(true);
	}
}
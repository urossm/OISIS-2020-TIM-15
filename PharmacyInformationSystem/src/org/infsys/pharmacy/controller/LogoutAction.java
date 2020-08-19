package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.view.LoginFrame;

public class LogoutAction extends AbstractAction {
	
	private static final long serialVersionUID = -1341217339222995706L;

	public LogoutAction(String name) {
		putValue(NAME, name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ApplicationSingleton.getInstance().setLoggedInUser(null);
		ApplicationSingleton.getInstance().getMainFrame().dispose();
		
		LoginFrame loginFrame = ApplicationSingleton.getInstance().getLoginFrame();
		loginFrame.getLoginForm().resetForm();
		loginFrame.setVisible(true);
	}
}
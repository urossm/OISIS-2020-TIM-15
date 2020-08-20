package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.model.User;
import org.infsys.pharmacy.util.Authentication;
import org.infsys.pharmacy.util.Constants;
import org.infsys.pharmacy.view.LoginFrame;
import org.infsys.pharmacy.view.MainFrame;

public class LoginAction extends AbstractAction {

	private static final long serialVersionUID = 3411659274723737424L;
	private int failedLoginAttemptsCounter;
	
	public LoginAction(String name) {
		putValue(NAME, name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		LoginFrame loginFrame = ApplicationSingleton.getInstance().getLoginFrame();
		String username = loginFrame.getLoginForm().getEnteredUsername();
		String password = loginFrame.getLoginForm().getEnteredPassword();
		
		if (username == null || username.isEmpty()) {
			JOptionPane.showMessageDialog(null, Constants.USERNAME_EMPTY_ERROR_MSG, Constants.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (password == null || password.isEmpty()) {
			JOptionPane.showMessageDialog(null, Constants.PASSWORD_EMPTY_ERROR_MSG, Constants.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		User user = Authentication.authenticate(username, password);
		
		if (user != null) {
			loginFrame.dispose();
			ApplicationSingleton.getInstance().setLoggedInUser(user);
			MainFrame mainFrame;
			
			mainFrame = new MainFrame();
			ApplicationSingleton.getInstance().setMainFrame(mainFrame);
			
			mainFrame.setVisible(true);
		} else {
			failedLoginAttemptsCounter++;
			
			if (failedLoginAttemptsCounter == 3) {
				loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));
			}
			
			JOptionPane.showMessageDialog(null, Constants.LOGIN_ERROR_MSG + " You have " + (3 - failedLoginAttemptsCounter) + " attempts remaining.", Constants.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
		}
	}
}
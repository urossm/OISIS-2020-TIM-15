package org.infsys.pharmacy.view;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.infsys.pharmacy.controller.LoginAction;
import org.infsys.pharmacy.util.Constants;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginForm extends JPanel {

	private static final long serialVersionUID = 3353536214124646989L;
	private JLabel logoLabel;
	private JTextField usernameTextField;
	private JLabel passwordLabel;
	private JLabel usernameLabel;
	private JTextField passwordTextField;
	private JButton loginButton;

	/**
	 * Create the panel for login form.
	 */
	public LoginForm() {
		setLayout(new MigLayout("insets 0 120", "[grow,center]", "[grow][][][][][grow]"));
		
		Image scaledLogoImage = Constants.BLUE_LOGO_IMAGE.getImage()
				.getScaledInstance(Constants.BLUE_LOGO_IMAGE.getIconWidth() / 3, Constants.BLUE_LOGO_IMAGE.getIconHeight() / 3, Image.SCALE_SMOOTH);
		logoLabel = new JLabel(new ImageIcon(scaledLogoImage));
		add(logoLabel, "cell 0 0,alignx center,aligny bottom");
		
		usernameLabel = new JLabel(Constants.USERNAME);
		usernameLabel.setFont(usernameLabel.getFont().deriveFont(24f));
		add(usernameLabel, "cell 0 1,alignx left,aligny center,gapy 30 0");
		
		usernameTextField = new JTextField();
		add(usernameTextField, "cell 0 2,growx,gapy 0 30");
		usernameTextField.setColumns(10);
		
		passwordLabel = new JLabel(Constants.PASSWORD);
		passwordLabel.setFont(passwordLabel.getFont().deriveFont(24f));
		add(passwordLabel, "cell 0 3,alignx left");
		
		passwordTextField = new JTextField();
		add(passwordTextField, "cell 0 4,growx,gapy 0 30");
		passwordTextField.setColumns(10);
		
		loginButton = new JButton(new LoginAction(Constants.LOGIN));
		loginButton.setBackground(Constants.LIGHT_BLUE);
		loginButton.setForeground(Color.WHITE);
		loginButton.setFont(Constants.CUSTOM_FONT_BOLD);
		loginButton.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		add(loginButton, "cell 0 5,alignx center,aligny center");
	}
	
	public String getEnteredUsername() {
		return usernameTextField.getText();
	}
	
	public String getEnteredPassword() {
		return passwordTextField.getText();
	}
	
	public void resetForm() {
		usernameTextField.setText("");
		passwordTextField.setText("");
	}
}
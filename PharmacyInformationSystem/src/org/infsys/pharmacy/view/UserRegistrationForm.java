package org.infsys.pharmacy.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.controller.AddUserAction;
import org.infsys.pharmacy.model.UserType;
import org.infsys.pharmacy.util.Constants;

import net.miginfocom.swing.MigLayout;

public class UserRegistrationForm extends JDialog {

	private static final long serialVersionUID = 8751259390533528433L;
	private final JPanel contentPanel = new JPanel();
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel userTypeLabel;
	private JComboBox<UserType> userTypeComboBox;
	private JPanel buttonPane;
	private JButton registerButton;

	/**
	 * Create the dialog which contains user registration form.
	 */
	public UserRegistrationForm() {
		super(ApplicationSingleton.getInstance().getMainFrame(), true);
		setTitle("User registration");
		setSize(Constants.SCREEN_WIDTH / 5, 2 * Constants.SCREEN_HEIGHT / 3);
		setMinimumSize(new Dimension(Constants.SCREEN_WIDTH / 5, 0));
		setLocationRelativeTo(ApplicationSingleton.getInstance().getMainFrame());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][]"));
		
		usernameLabel = new JLabel(Constants.USERNAME);
		usernameLabel.setFont(usernameLabel.getFont().deriveFont(16f));
		contentPanel.add(usernameLabel, "cell 0 0");
		
		usernameTextField = new JTextField();
		usernameTextField.setFont(usernameTextField.getFont().deriveFont(16f));
		contentPanel.add(usernameTextField, "cell 0 1,growx");
		usernameTextField.setColumns(10);
		
		passwordLabel = new JLabel(Constants.PASSWORD);
		passwordLabel.setFont(passwordLabel.getFont().deriveFont(16f));
		contentPanel.add(passwordLabel, "cell 0 2");
		
		passwordTextField = new JTextField();
		passwordTextField.setFont(passwordTextField.getFont().deriveFont(16f));
		contentPanel.add(passwordTextField, "cell 0 3,growx");
		passwordTextField.setColumns(10);
		
		firstNameLabel = new JLabel(Constants.FIRSTNAME);
		firstNameLabel.setFont(firstNameLabel.getFont().deriveFont(16f));
		contentPanel.add(firstNameLabel, "cell 0 4");
		
		firstNameTextField = new JTextField();
		firstNameTextField.setFont(firstNameTextField.getFont().deriveFont(16f));
		contentPanel.add(firstNameTextField, "cell 0 5,growx");
		firstNameTextField.setColumns(10);
		
		lastNameLabel = new JLabel(Constants.LASTNAME);
		lastNameLabel.setFont(lastNameLabel.getFont().deriveFont(16f));
		contentPanel.add(lastNameLabel, "cell 0 6");
		
		lastNameTextField = new JTextField();
		lastNameTextField.setFont(lastNameTextField.getFont().deriveFont(16f));
		contentPanel.add(lastNameTextField, "cell 0 7,growx");
		lastNameTextField.setColumns(10);
		
		userTypeLabel = new JLabel(Constants.USER_TYPE);
		userTypeLabel.setFont(userTypeLabel.getFont().deriveFont(16f));
		contentPanel.add(userTypeLabel, "cell 0 8");
		
		userTypeComboBox = new JComboBox<>(new UserType[] {UserType.DOCTOR, UserType.PHARMACIST});
		userTypeComboBox.setFont(userTypeComboBox.getFont().deriveFont(16f));
		contentPanel.add(userTypeComboBox, "cell 0 9,alignx left");
		
		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		registerButton = new JButton(new AddUserAction(Constants.REGISTER, this));
		registerButton.setBackground(Constants.LIGHT_BLUE);
		registerButton.setForeground(Color.WHITE);
		registerButton.setFont(Constants.CUSTOM_FONT_BOLD);
		buttonPane.add(registerButton);
		getRootPane().setDefaultButton(registerButton);
		
		pack();
	}

	public JTextField getUsernameTextField() {
		return usernameTextField;
	}

	public JTextField getPasswordTextField() {
		return passwordTextField;
	}

	public JTextField getFirstNameTextField() {
		return firstNameTextField;
	}

	public JTextField getLastNameTextField() {
		return lastNameTextField;
	}

	public JComboBox<UserType> getUserTypeComboBox() {
		return userTypeComboBox;
	}
}
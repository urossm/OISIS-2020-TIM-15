package org.infsys.pharmacy.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import org.infsys.pharmacy.model.User;
import org.infsys.pharmacy.model.UserType;

public class Constants {
	
	//Administrator
	public static final User ADMINISTRATOR = new User("admin", "admin", "Anonymous", "Admin", UserType.ADMINISTRATOR);
	
	//Strings
	public static final String LOGIN_FORM_TITLE = "Pharmacy Information System - Login";
	public static final String MAIN_WINDOW_TITLE = "Pharmacy Information System";
	public static final String USERNAME = "Username";
	public static final String PASSWORD = "Password";
	public static final String LOGIN = "Log in";
	public static final String LOGOUT = "Log out";
	public static final String ERROR_MSG_TITLE = "Error!";
	public static final String RESOURCES_ERROR_MSG = "Couldn't load some resources. Please, try again later.";
	public static final String LOGIN_ERROR_MSG = "Invalid username and/or password.";
	public static final String USERNAME_EMPTY_ERROR_MSG = "Username can not be empty!";
	public static final String PASSWORD_EMPTY_ERROR_MSG = "Password can not be empty!";
	public static final String USERS_PATH = "data/users.obj";
	public static final String MEDICINES_PATH = "data/medicines.obj";
	public static final String PRESCRIPTIONS_PATH = "data/prescriptions.obj";
	public static final String CATEGORIES = "Categories";
	public static final String MEDICATIONS = "Medications";
	public static final String PRESCRIPTIONS = "Prescriptions";
	public static final String CART = "Cart";
	public static final String ADMIN_PANEL = "Admin Panel";
	public static final String USER_DATABASE = "User database";
	public static final String REPORTS = "Reports";
	public static final String EMPTY_PANEL = "Empty panel";
	public static final String ADD_NEW_USER = "Add new user";
	public static final String NAME = "Name";
	public static final String FIRSTNAME = "First name";
	public static final String LASTNAME = "Last name";
	public static final String USER_TYPE = "User type";
	public static final String REGISTER = "Register";
	public static final String USER_EXISTS_ERROR_MSG = "User with entered username already exists!";
	
	//String arrays
	public static final String[] USER_SORT_TYPES = new String[] {NAME, LASTNAME, USER_TYPE};
	
	//Dimensions
	public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	//Colors
	public static final Color LIGHT_BLUE = new Color(0, 132, 255);
	public static final Color TRANSPARENT_WHITE = new Color(1.0f, 1.0f, 1.0f, 0.5f);
	
	//Images
	public static final ImageIcon BLUE_LOGO_IMAGE = new ImageIcon("resources/images/logo_blue.png");
	public static final ImageIcon WHITE_LOGO_IMAGE = new ImageIcon("resources/images/logo_white.png");
	public static final ImageIcon LOGIN_IMAGE = new ImageIcon("resources/images/login.png");
	public static final ImageIcon CART_IMAGE = new ImageIcon(new ImageIcon("resources/images/Cart.png").getImage().getScaledInstance(26, 32, Image.SCALE_SMOOTH));
	public static final ImageIcon MEDICATIONS_IMAGE = new ImageIcon(new ImageIcon("resources/images/Medications.png").getImage().getScaledInstance(26, 32, Image.SCALE_SMOOTH));
	public static final ImageIcon PRESCRIPTIONS_IMAGE = new ImageIcon(new ImageIcon("resources/images/Prescription.png").getImage().getScaledInstance(26, 32, Image.SCALE_SMOOTH));
	public static final ImageIcon REPORTS_IMAGE = new ImageIcon(new ImageIcon("resources/images/Reports.png").getImage().getScaledInstance(26, 32, Image.SCALE_SMOOTH));
	public static final ImageIcon USER_DATABASE_IMAGE = new ImageIcon(new ImageIcon("resources/images/User database.png").getImage().getScaledInstance(26, 32, Image.SCALE_SMOOTH));
	
	//Fonts
	public static Font CUSTOM_FONT;
	public static Font CUSTOM_FONT_BOLD;
	
	public static void setCustomFont(Font customFont) {
		Constants.CUSTOM_FONT = customFont;
	}
	public static void setCustomFontBold(Font customFontBold) {
		Constants.CUSTOM_FONT_BOLD = customFontBold;
	}
}
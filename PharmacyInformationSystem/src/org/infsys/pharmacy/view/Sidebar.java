package org.infsys.pharmacy.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.controller.LogoutAction;
import org.infsys.pharmacy.controller.NavigationButtonSelectedAction;
import org.infsys.pharmacy.model.UserType;
import org.infsys.pharmacy.util.Constants;

import net.miginfocom.swing.MigLayout;

public class Sidebar extends JPanel {

	private static final long serialVersionUID = -7016245543686356301L;
	private JLabel sidebarLogoLabel;
	private JButton medicationsButton;
	private JButton logoutButton;
	private JLabel categoriesLabel;
	private JLabel helloLabel;
	private JButton prescriptionsButton;
	private JButton cartButton;
	private JLabel adminPanelLabel;
	private JButton userDatabaseButton;
	private JButton reportsButton;
	private Map<String, JButton> navigationButtons;

	/**
	 * Create the sidebar panel with application logo, navigations buttons and logout button.
	 */
	public Sidebar() {
		setBackground(Constants.LIGHT_BLUE);
		setPreferredSize(new Dimension(Constants.SCREEN_WIDTH / 6, 4 * Constants.SCREEN_HEIGHT / 5));
		setLayout(new MigLayout("", "[grow,center]", "[][][][][][][][][][grow,bottom]"));
		
		Image scaledLogoImage = Constants.WHITE_LOGO_IMAGE.getImage()
				.getScaledInstance(5 * Constants.WHITE_LOGO_IMAGE.getIconWidth() / 7, 5 * Constants.WHITE_LOGO_IMAGE.getIconHeight() / 7, Image.SCALE_SMOOTH);
		
		sidebarLogoLabel = new JLabel(new ImageIcon(scaledLogoImage));
		add(sidebarLogoLabel, "cell 0 0,alignx center,gapy 20 0");
		
		helloLabel = new JLabel("Hello, " + ApplicationSingleton.getInstance().getLoggedInUser().getFirstName());
		helloLabel.setForeground(Color.WHITE);
		add(helloLabel, "cell 0 1,aligny top");
		
		categoriesLabel = new JLabel(Constants.CATEGORIES);
		categoriesLabel.setForeground(Constants.TRANSPARENT_WHITE);
		add(categoriesLabel, "cell 0 2,alignx left,gapy 20 10, gapx 25 0");
		
		navigationButtons = new HashMap<>();
		
		medicationsButton = this.createNavigationButton(Constants.MEDICATIONS, Constants.MEDICATIONS_IMAGE);
		add(medicationsButton, "cell 0 3,alignx left, gapx 10 0");
		navigationButtons.put(Constants.MEDICATIONS, medicationsButton);
		
		prescriptionsButton = this.createNavigationButton(Constants.PRESCRIPTIONS, Constants.PRESCRIPTIONS_IMAGE);
		add(prescriptionsButton, "cell 0 4,alignx left, gapx 10 0");
		navigationButtons.put(Constants.PRESCRIPTIONS, prescriptionsButton);
		
		if (ApplicationSingleton.getInstance().getLoggedInUser().getType() == UserType.PHARMACIST) {
			cartButton = this.createNavigationButton(Constants.CART, Constants.CART_IMAGE);
			add(cartButton, "cell 0 5,alignx left, gapx 10 0");
			navigationButtons.put(Constants.CART, cartButton);
		}
		
		if (ApplicationSingleton.getInstance().getLoggedInUser().getType() == UserType.ADMINISTRATOR) {
			adminPanelLabel = new JLabel(Constants.ADMIN_PANEL);
			adminPanelLabel.setForeground(Constants.TRANSPARENT_WHITE);
			add(adminPanelLabel, "cell 0 6,alignx left,gapy 10 10, gapx 25 0");
			
			userDatabaseButton = this.createNavigationButton(Constants.USER_DATABASE, Constants.USER_DATABASE_IMAGE);
			add(userDatabaseButton, "cell 0 7,alignx left, gapx 10 0");
			navigationButtons.put(Constants.USER_DATABASE, userDatabaseButton);
			
			reportsButton = this.createNavigationButton(Constants.REPORTS, Constants.REPORTS_IMAGE);
			add(reportsButton, "cell 0 8,alignx left, gapx 10 0");			
			navigationButtons.put(Constants.REPORTS, reportsButton);
		}
		
		logoutButton = new JButton(new LogoutAction(Constants.LOGOUT));
		logoutButton.setBackground(Color.WHITE);
		logoutButton.setForeground(Constants.LIGHT_BLUE);
		logoutButton.setFont(Constants.CUSTOM_FONT_BOLD);
		logoutButton.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		add(logoutButton, "cell 0 9,gapy 0 20");
	}
	
	public void selectDefaultButton() {
		//Select medications button to display medications panel by default
		medicationsButton.doClick();
	}
	
	public JButton createNavigationButton(String name, ImageIcon icon) {
		JButton navigationButton = new JButton(new NavigationButtonSelectedAction(name));
		navigationButton.setIcon(icon);
		this.setupNotSelectedNavigationButton(navigationButton);
		return navigationButton;
	}
	
	public JButton setupNotSelectedNavigationButton(JButton navigationButton) {
		navigationButton.setForeground(Constants.TRANSPARENT_WHITE);
		navigationButton.setContentAreaFilled(false);
		navigationButton.setBorderPainted(false);
		navigationButton.setFocusPainted(false);
		return navigationButton;
	}
	
	public void selectButton(String name) {		
		for (Map.Entry<String, JButton> entry : this.navigationButtons.entrySet()) {
			if (entry.getKey().equals(name)) {
				entry.getValue().setForeground(new Color(1.0f, 1.0f, 1.0f, 0.9f));
				entry.getValue().setFont(Constants.CUSTOM_FONT_BOLD.deriveFont(12f));
				entry.getValue().setBorderPainted(true);
				entry.getValue().setBorder(BorderFactory.createCompoundBorder(
						BorderFactory.createMatteBorder(0, 5, 0, 0, Color.WHITE), 
				        BorderFactory.createEmptyBorder(5, 25, 5, 5)));
			} else {
				this.setupNotSelectedNavigationButton(entry.getValue());
				entry.getValue().setFont(Constants.CUSTOM_FONT);
			}
		}		
	}
}
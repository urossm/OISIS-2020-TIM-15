package org.infsys.pharmacy.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.controller.LogoutAction;
import org.infsys.pharmacy.util.Constants;

import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;

public class Sidebar extends JPanel {

	private static final long serialVersionUID = -7016245543686356301L;
	private JLabel sidebarLogoLabel;

	/**
	 * Create the panel.
	 */
	public Sidebar() {
		setBackground(Constants.LIGHT_BLUE);
		setPreferredSize(new Dimension(Constants.SCREEN_WIDTH / 7, 4 * Constants.SCREEN_HEIGHT / 5));
		setLayout(new MigLayout("", "[grow,center]", "[][][grow,bottom]"));
		
		Image scaledLogoImage = Constants.WHITE_LOGO_IMAGE.getImage()
				.getScaledInstance(5 * Constants.WHITE_LOGO_IMAGE.getIconWidth() / 6, 5 * Constants.WHITE_LOGO_IMAGE.getIconHeight() / 6, Image.SCALE_SMOOTH);
		
		sidebarLogoLabel = new JLabel(new ImageIcon(scaledLogoImage));
		add(sidebarLogoLabel, "cell 0 0,alignx left,gapy 20 0");
		
		JLabel helloLabel = new JLabel("Hello, " + ApplicationSingleton.getInstance().getLoggedInUser().getFirstName());
		helloLabel.setFont(helloLabel.getFont().deriveFont(24f));
		helloLabel.setForeground(Color.WHITE);
		add(helloLabel, "cell 0 1");
		
		JButton logoutButton = new JButton(new LogoutAction(Constants.LOGOUT));
		logoutButton.setBackground(Color.WHITE);
		logoutButton.setForeground(Constants.LIGHT_BLUE);
		logoutButton.setFont(Constants.CUSTOM_FONT_BOLD.deriveFont(12f));
		logoutButton.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		add(logoutButton, "cell 0 2, gapy 0 20");
	}
}
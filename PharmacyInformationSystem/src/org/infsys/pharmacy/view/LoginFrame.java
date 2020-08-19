package org.infsys.pharmacy.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.infsys.pharmacy.util.Constants;

import net.miginfocom.swing.MigLayout;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 8704873228386492678L;
	private JPanel contentPane;
	private JPanel imagePanel;
	private LoginForm loginForm;
	private JLabel imageLabel;
	
	/**
	 * Create the login frame.
	 */
	public LoginFrame() {
		setTitle(Constants.LOGIN_FORM_TITLE);
		setIconImage(Constants.BLUE_LOGO_IMAGE.getImage().getScaledInstance(32, 27, Image.SCALE_SMOOTH));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(3 * Constants.SCREEN_WIDTH / 4, 4 * Constants.SCREEN_HEIGHT / 5);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("insets 0", "[grow][]", "[grow]"));
		
		loginForm = new LoginForm();
		contentPane.add(loginForm, "cell 0 0,grow");
		
		imagePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		imagePanel.setBackground(Constants.LIGHT_BLUE);
		imagePanel.setPreferredSize(new Dimension(3 * Constants.SCREEN_WIDTH / 8, 4 * Constants.SCREEN_HEIGHT / 5));
		contentPane.add(imagePanel, "cell 1 0,grow");
		
		Image scaledLoginImage = Constants.LOGIN_IMAGE.getImage().getScaledInstance(Constants.SCREEN_WIDTH / 3, 4 * Constants.SCREEN_HEIGHT / 7,Image.SCALE_SMOOTH);
		imageLabel = new JLabel(new ImageIcon(scaledLoginImage));
		imagePanel.add(imageLabel);
	}

	public LoginForm getLoginForm() {
		return loginForm;
	}
}
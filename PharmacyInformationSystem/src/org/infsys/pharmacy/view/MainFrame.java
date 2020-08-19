package org.infsys.pharmacy.view;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.infsys.pharmacy.util.Constants;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -3641529791186380004L;
	private JPanel contentPane;

	/**
	 * Create the application's main window.
	 */
	public MainFrame() {
		setTitle(Constants.MAIN_WINDOW_TITLE);
		setIconImage(Constants.BLUE_LOGO_IMAGE.getImage().getScaledInstance(32, 27, Image.SCALE_SMOOTH));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(3 * Constants.SCREEN_WIDTH / 4, 4 * Constants.SCREEN_HEIGHT / 5);
//		setSize(600, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Sidebar sidebar = new Sidebar();
		contentPane.add(sidebar, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
	}
}
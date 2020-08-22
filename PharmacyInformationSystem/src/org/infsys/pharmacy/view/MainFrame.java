package org.infsys.pharmacy.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.infsys.pharmacy.util.Constants;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -3641529791186380004L;
	private JPanel contentPane;
	private Sidebar sidebar;
	private JPanel centralPanel;
	private CardLayout cardLayout;
	private UserDatabasePanel userDatabasePanel;
	private MedicationsPanel medicationsPanel;
	private PrescriptionsPanel prescriptionsPanel;
	private CartPanel cartPanel;

	/**
	 * Create the application's main window.
	 */
	public MainFrame() {
		setTitle(Constants.MAIN_WINDOW_TITLE);
		setIconImage(Constants.BLUE_LOGO_IMAGE.getImage().getScaledInstance(32, 27, Image.SCALE_SMOOTH));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(3 * Constants.SCREEN_WIDTH / 4, 4 * Constants.SCREEN_HEIGHT / 5);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		sidebar = new Sidebar();
		contentPane.add(sidebar, BorderLayout.WEST);
		
		cardLayout = new CardLayout(40, 40);
		centralPanel = new JPanel(cardLayout);
		contentPane.add(centralPanel, BorderLayout.CENTER);
		
		userDatabasePanel = new UserDatabasePanel();
		medicationsPanel = new MedicationsPanel();
		prescriptionsPanel = new PrescriptionsPanel();
		cartPanel = new CartPanel();
		
		centralPanel.add(new JPanel(), Constants.EMPTY_PANEL);
		centralPanel.add(userDatabasePanel, Constants.USER_DATABASE);
		centralPanel.add(medicationsPanel, Constants.MEDICATIONS);
		centralPanel.add(prescriptionsPanel, Constants.PRESCRIPTIONS);
		centralPanel.add(cartPanel, Constants.CART);
	}
	
	public void showCard(String name) {
		this.cardLayout.show(this.centralPanel, name);
	}

	public Sidebar getSidebar() {
		return sidebar;
	}

	public UserDatabasePanel getUserDatabasePanel() {
		return userDatabasePanel;
	}

	public MedicationsPanel getMedicationsPanel() {
		return medicationsPanel;
	}

	public PrescriptionsPanel getPrescriptionsPanel() {
		return prescriptionsPanel;
	}
}
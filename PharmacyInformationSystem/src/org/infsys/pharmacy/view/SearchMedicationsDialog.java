package org.infsys.pharmacy.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.controller.SearchMedicationsAction;
import org.infsys.pharmacy.util.Constants;

import net.miginfocom.swing.MigLayout;

public class SearchMedicationsDialog extends JDialog {

	private static final long serialVersionUID = 3307241612663424407L;
	private final JPanel contentPanel = new JPanel();
	private JTextField medicationCodeTextField;
	private JTextField nameTextField;
	private JTextField manufacturerTextField;
	private JLabel medicationCodeLabel;
	private JLabel nameLabel;
	private JLabel manufacturerLabel;
	private JLabel priceLabel;
	private JButton searchByCodeButton;
	private JButton searchByNameButton;
	private JButton searchByManufacturerButton;
	private JButton searchByPriceButton;
	private JTextField minPriceTextField;
	private JTextField maxPriceTextField;
	private JLabel minPriceLabel;
	private JLabel maxPriceLabel;

	public SearchMedicationsDialog() {
		super(ApplicationSingleton.getInstance().getMainFrame(), true);
		setTitle("Add new medication");
		setSize(Constants.SCREEN_WIDTH / 5, 2 * Constants.SCREEN_HEIGHT / 3);
		setMinimumSize(new Dimension(Constants.SCREEN_WIDTH / 5, 0));
		setLocationRelativeTo(ApplicationSingleton.getInstance().getMainFrame());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow][]", "[][][][][][][][]"));
		
		medicationCodeLabel = new JLabel(Constants.SEARCH_BY_MEDICATION_CODE);
		medicationCodeLabel.setFont(medicationCodeLabel.getFont().deriveFont(16f));
		contentPanel.add(medicationCodeLabel, "cell 0 0");
		
		medicationCodeTextField = new JTextField();
		medicationCodeTextField.setFont(medicationCodeTextField.getFont().deriveFont(16f));
		contentPanel.add(medicationCodeTextField, "cell 0 1,growx");
		medicationCodeTextField.setColumns(10);
		
		searchByCodeButton = new JButton(new SearchMedicationsAction(Constants.SEARCH_IMAGE, Constants.CODE, this));
		searchByCodeButton.setBackground(Constants.LIGHT_BLUE);
		searchByCodeButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		contentPanel.add(searchByCodeButton, "cell 1 1");
		
		nameLabel = new JLabel(Constants.SEARCH_BY_NAME);
		nameLabel.setFont(nameLabel.getFont().deriveFont(16f));
		contentPanel.add(nameLabel, "cell 0 2");
		
		nameTextField = new JTextField();
		nameTextField.setFont(nameTextField.getFont().deriveFont(16f));
		contentPanel.add(nameTextField, "cell 0 3,growx");
		nameTextField.setColumns(10);
		
		searchByNameButton = new JButton(new SearchMedicationsAction(Constants.SEARCH_IMAGE, Constants.NAME, this));
		searchByNameButton.setBackground(Constants.LIGHT_BLUE);
		searchByNameButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		contentPanel.add(searchByNameButton, "cell 1 3");
		
		manufacturerLabel = new JLabel(Constants.SEARCH_BY_MANUFACTURER);
		manufacturerLabel.setFont(manufacturerLabel.getFont().deriveFont(16f));
		contentPanel.add(manufacturerLabel, "cell 0 4");
		
		manufacturerTextField = new JTextField();
		manufacturerTextField.setFont(manufacturerTextField.getFont().deriveFont(16f));
		contentPanel.add(manufacturerTextField, "cell 0 5,growx");
		manufacturerTextField.setColumns(10);
		
		searchByManufacturerButton = new JButton(new SearchMedicationsAction(Constants.SEARCH_IMAGE, Constants.MANUFACTURER, this));
		searchByManufacturerButton.setBackground(Constants.LIGHT_BLUE);
		searchByManufacturerButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		contentPanel.add(searchByManufacturerButton, "cell 1 5");
		
		priceLabel = new JLabel(Constants.PRICE_RANGE);
		priceLabel.setFont(priceLabel.getFont().deriveFont(16f));
		contentPanel.add(priceLabel, "cell 0 6");
		
		minPriceLabel = new JLabel(Constants.MAX);
		minPriceLabel.setFont(minPriceLabel.getFont().deriveFont(16f));
		contentPanel.add(minPriceLabel, "flowx,cell 0 7");
		
		minPriceTextField = new JTextField();
		minPriceTextField.setFont(minPriceTextField.getFont().deriveFont(16f));
		contentPanel.add(minPriceTextField, "cell 0 7,growx");
		minPriceTextField.setColumns(10);
		
		searchByPriceButton = new JButton(new SearchMedicationsAction(Constants.SEARCH_IMAGE, Constants.PRICE, this));
		searchByPriceButton.setBackground(Constants.LIGHT_BLUE);
		searchByPriceButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		contentPanel.add(searchByPriceButton, "cell 1 7");
		
		maxPriceLabel = new JLabel(Constants.MAX);
		maxPriceLabel.setFont(maxPriceLabel.getFont().deriveFont(16f));
		contentPanel.add(maxPriceLabel, "cell 0 7");
		
		maxPriceTextField = new JTextField();
		maxPriceTextField.setFont(maxPriceTextField.getFont().deriveFont(16f));
		contentPanel.add(maxPriceTextField, "cell 0 7");
		maxPriceTextField.setColumns(10);
		
		pack();
	}

	public JTextField getMedicationCodeTextField() {
		return medicationCodeTextField;
	}

	public JTextField getNameTextField() {
		return nameTextField;
	}

	public JTextField getManufacturerTextField() {
		return manufacturerTextField;
	}

	public JTextField getMinPriceTextField() {
		return minPriceTextField;
	}

	public JTextField getMaxPriceTextField() {
		return maxPriceTextField;
	}
}
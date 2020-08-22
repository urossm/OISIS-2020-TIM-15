package org.infsys.pharmacy.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.controller.AddMedicationAction;
import org.infsys.pharmacy.util.Constants;

import net.miginfocom.swing.MigLayout;

public class AddMedicationForm extends JDialog {

	private static final long serialVersionUID = 3307241612663424407L;
	private final JPanel contentPanel = new JPanel();
	private JTextField medicationCodeTextField;
	private JTextField nameTextField;
	private JTextField manufacturerTextField;
	private JTextField priceTextField;
	private JLabel medicationCodeLabel;
	private JLabel nameLabel;
	private JLabel manufacturerLabel;
	private JLabel priceLabel;
	private JCheckBox prescriptionNeededCheckBox;
	private JPanel buttonPane;
	private JButton saveButton;

	public AddMedicationForm() {
		super(ApplicationSingleton.getInstance().getMainFrame(), true);
		setTitle(Constants.ADD_NEW_MEDICATION);
		setSize(Constants.SCREEN_WIDTH / 5, 2 * Constants.SCREEN_HEIGHT / 3);
		setMinimumSize(new Dimension(Constants.SCREEN_WIDTH / 5, 0));
		setLocationRelativeTo(ApplicationSingleton.getInstance().getMainFrame());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][]"));
		
		medicationCodeLabel = new JLabel(Constants.MEDICATION_CODE);
		medicationCodeLabel.setFont(medicationCodeLabel.getFont().deriveFont(16f));
		contentPanel.add(medicationCodeLabel, "cell 0 0");
		
		medicationCodeTextField = new JTextField();
		medicationCodeTextField.setFont(medicationCodeTextField.getFont().deriveFont(16f));
		contentPanel.add(medicationCodeTextField, "cell 0 1,growx");
		medicationCodeTextField.setColumns(10);
		
		nameLabel = new JLabel(Constants.NAME);
		nameLabel.setFont(nameLabel.getFont().deriveFont(16f));
		contentPanel.add(nameLabel, "cell 0 2");
		
		nameTextField = new JTextField();
		nameTextField.setFont(nameTextField.getFont().deriveFont(16f));
		contentPanel.add(nameTextField, "cell 0 3,growx");
		nameTextField.setColumns(10);
		
		manufacturerLabel = new JLabel(Constants.MANUFACTURER);
		manufacturerLabel.setFont(manufacturerLabel.getFont().deriveFont(16f));
		contentPanel.add(manufacturerLabel, "cell 0 4");
		
		manufacturerTextField = new JTextField();
		manufacturerTextField.setFont(manufacturerTextField.getFont().deriveFont(16f));
		contentPanel.add(manufacturerTextField, "cell 0 5,growx");
		manufacturerTextField.setColumns(10);
		
		priceLabel = new JLabel(Constants.PRICE);
		priceLabel.setFont(priceLabel.getFont().deriveFont(16f));
		contentPanel.add(priceLabel, "cell 0 6");
		
		priceTextField = new JTextField();
		priceTextField.setFont(priceTextField.getFont().deriveFont(16f));
		contentPanel.add(priceTextField, "cell 0 7,growx");
		priceTextField.setColumns(10);
	
		prescriptionNeededCheckBox = new JCheckBox(Constants.IS_ON_PRESCRIPTION);
		prescriptionNeededCheckBox.setFont(prescriptionNeededCheckBox.getFont().deriveFont(16f));
		contentPanel.add(prescriptionNeededCheckBox, "cell 0 8,alignx left");
		
		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		saveButton = new JButton(new AddMedicationAction(Constants.SAVE, this));
		saveButton.setBackground(Constants.LIGHT_BLUE);
		saveButton.setForeground(Color.WHITE);
		saveButton.setFont(Constants.CUSTOM_FONT_BOLD);
		buttonPane.add(saveButton);
		getRootPane().setDefaultButton(saveButton);
		
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

	public JTextField getPriceTextField() {
		return priceTextField;
	}

	public JCheckBox getPrescriptionNeededCheckBox() {
		return prescriptionNeededCheckBox;
	}
}
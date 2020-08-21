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
import org.infsys.pharmacy.controller.EditMedicationAction;
import org.infsys.pharmacy.model.Medication;
import org.infsys.pharmacy.util.Constants;

import net.miginfocom.swing.MigLayout;

public class EditMedicationForm extends JDialog {

	private static final long serialVersionUID = -6710080185061926115L;
	private final JPanel contentPanel = new JPanel();
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

	public EditMedicationForm(Medication medicationToEdit) {
		super(ApplicationSingleton.getInstance().getMainFrame(), true);
		setTitle("Edit medication");
		setSize(Constants.SCREEN_WIDTH / 5, 2 * Constants.SCREEN_HEIGHT / 3);
		setMinimumSize(new Dimension(Constants.SCREEN_WIDTH / 5, 0));
		setLocationRelativeTo(ApplicationSingleton.getInstance().getMainFrame());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][][][][][][][]"));
		
		medicationCodeLabel = new JLabel("Medication code - " + medicationToEdit.getCode());
		medicationCodeLabel.setForeground(Constants.LIGHT_BLUE);
		medicationCodeLabel.setFont(Constants.CUSTOM_FONT_BOLD);
		contentPanel.add(medicationCodeLabel, "cell 0 0, alignx center, gapy 10 10");
		
		nameLabel = new JLabel(Constants.NAME);
		nameLabel.setFont(nameLabel.getFont().deriveFont(16f));
		contentPanel.add(nameLabel, "cell 0 1");
		
		nameTextField = new JTextField(medicationToEdit.getName());
		nameTextField.setFont(nameTextField.getFont().deriveFont(16f));
		contentPanel.add(nameTextField, "cell 0 2,growx");
		nameTextField.setColumns(10);
		
		manufacturerLabel = new JLabel(Constants.MANUFACTURER);
		manufacturerLabel.setFont(manufacturerLabel.getFont().deriveFont(16f));
		contentPanel.add(manufacturerLabel, "cell 0 3");
		
		manufacturerTextField = new JTextField(medicationToEdit.getManufacturer());
		manufacturerTextField.setFont(manufacturerTextField.getFont().deriveFont(16f));
		contentPanel.add(manufacturerTextField, "cell 0 4,growx");
		manufacturerTextField.setColumns(10);
		
		priceLabel = new JLabel(Constants.PRICE);
		priceLabel.setFont(priceLabel.getFont().deriveFont(16f));
		contentPanel.add(priceLabel, "cell 0 5");
		
		priceTextField = new JTextField(medicationToEdit.getPrice().toString());
		priceTextField.setFont(priceTextField.getFont().deriveFont(16f));
		contentPanel.add(priceTextField, "cell 0 6,growx");
		priceTextField.setColumns(10);

		prescriptionNeededCheckBox = new JCheckBox(Constants.IS_ON_PRESCRIPTION, medicationToEdit.isPrescriptionNeeded());
		prescriptionNeededCheckBox.setFont(prescriptionNeededCheckBox.getFont().deriveFont(16f));
		contentPanel.add(prescriptionNeededCheckBox, "cell 0 7,alignx left");
		
		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		saveButton = new JButton(new EditMedicationAction(Constants.SAVE, this, medicationToEdit));
		saveButton.setBackground(Constants.LIGHT_BLUE);
		saveButton.setForeground(Color.WHITE);
		saveButton.setFont(Constants.CUSTOM_FONT_BOLD);
		buttonPane.add(saveButton);
		getRootPane().setDefaultButton(saveButton);
		
		pack();
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
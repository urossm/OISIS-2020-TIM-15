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
import org.infsys.pharmacy.controller.AddMedicationToPrescriptionAction;
import org.infsys.pharmacy.util.Constants;

import net.miginfocom.swing.MigLayout;

public class AddMedicationToPrescriptionDialog extends JDialog {

	private static final long serialVersionUID = -6710080185061926115L;
	private final JPanel contentPanel = new JPanel();
	private JLabel chooseMedicationLabel;
	private JComboBox<String> medicationsComboBox;
	private JPanel buttonPane;
	private JButton addButton;
	private JTextField quantityTextField;

	public AddMedicationToPrescriptionDialog(CreatePrescriptionDialog createPrescriptionDialog) {
		super(ApplicationSingleton.getInstance().getMainFrame(), true);
		setTitle(Constants.ADD_MEDICATION);
		setSize(Constants.SCREEN_WIDTH / 6, 2 * Constants.SCREEN_HEIGHT / 3);
		setMinimumSize(new Dimension(Constants.SCREEN_WIDTH / 6, 0));
		setLocationRelativeTo(ApplicationSingleton.getInstance().getMainFrame());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][][][]"));

		chooseMedicationLabel = new JLabel(Constants.CHOOSE_MEDICATION);
		chooseMedicationLabel.setFont(chooseMedicationLabel.getFont().deriveFont(16f));
		contentPanel.add(chooseMedicationLabel, "cell 0 1,alignx center, gapy 20 0");
		
		medicationsComboBox = new JComboBox<>(ApplicationSingleton.getInstance().getMedications().keySet().toArray(new String[]{}));
		medicationsComboBox.setFont(medicationsComboBox.getFont().deriveFont(16f));
		contentPanel.add(medicationsComboBox, "cell 0 2,alignx center, gapy 10 10");
		
		quantityTextField = new JTextField();
		quantityTextField.setFont(quantityTextField.getFont().deriveFont(16f));
		contentPanel.add(quantityTextField, "cell 0 3,alignx center, gapy 10 20");
		
		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		addButton = new JButton(new AddMedicationToPrescriptionAction(Constants.ADD, createPrescriptionDialog, this));
		addButton.setBackground(Constants.LIGHT_BLUE);
		addButton.setForeground(Color.WHITE);
		addButton.setFont(Constants.CUSTOM_FONT_BOLD);
		buttonPane.add(addButton);
		getRootPane().setDefaultButton(addButton);
		
		pack();
	}

	public String getChosenCode() {
		return (String) medicationsComboBox.getSelectedItem();
	}
	
	public String getQuantity() {
		return quantityTextField.getText();
	}
}
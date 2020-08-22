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
import org.infsys.pharmacy.controller.SearchPrescriptionsAction;
import org.infsys.pharmacy.util.Constants;

import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;

public class SearchPrescriptionsDialog extends JDialog {

	private static final long serialVersionUID = 5399446149832996254L;
	private final JPanel contentPanel = new JPanel();
	private JTextField prescriptionCodeTextField;
	private JTextField doctorTextField;
	private JTextField personalNumberTextField;
	private JLabel prescriptionCodeLabel;
	private JLabel doctorLabel;
	private JLabel personalNumberLabel;
	private JLabel medicationLabel;
	private JButton searchByCodeButton;
	private JButton searchByDoctorButton;
	private JButton searchByPersonalNumberButton;
	private JButton searchByMedicationButton;
	private JComboBox<String> medicationsComboBox;

	public SearchPrescriptionsDialog() {
		super(ApplicationSingleton.getInstance().getMainFrame(), true);
		setTitle(Constants.SEARCH_PRESCRIPTIONS);
		setSize(Constants.SCREEN_WIDTH / 5, 2 * Constants.SCREEN_HEIGHT / 3);
		setMinimumSize(new Dimension(Constants.SCREEN_WIDTH / 5, 0));
		setLocationRelativeTo(ApplicationSingleton.getInstance().getMainFrame());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow][]", "[][][][][][][][]"));
		
		prescriptionCodeLabel = new JLabel(Constants.SEARCH_BY_PRESCRIPTION_CODE);
		prescriptionCodeLabel.setFont(prescriptionCodeLabel.getFont().deriveFont(16f));
		contentPanel.add(prescriptionCodeLabel, "cell 0 0");
		
		prescriptionCodeTextField = new JTextField();
		prescriptionCodeTextField.setFont(prescriptionCodeTextField.getFont().deriveFont(16f));
		contentPanel.add(prescriptionCodeTextField, "cell 0 1,growx");
		prescriptionCodeTextField.setColumns(10);
		
		searchByCodeButton = new JButton(new SearchPrescriptionsAction(Constants.SEARCH_IMAGE, Constants.CODE, this));
		searchByCodeButton.setBackground(Constants.LIGHT_BLUE);
		searchByCodeButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		contentPanel.add(searchByCodeButton, "cell 1 1");
		
		doctorLabel = new JLabel(Constants.SEARCH_BY_DOCTOR);
		doctorLabel.setFont(doctorLabel.getFont().deriveFont(16f));
		contentPanel.add(doctorLabel, "cell 0 2");
		
		doctorTextField = new JTextField();
		doctorTextField.setFont(doctorTextField.getFont().deriveFont(16f));
		contentPanel.add(doctorTextField, "cell 0 3,growx");
		doctorTextField.setColumns(10);
		
		searchByDoctorButton = new JButton(new SearchPrescriptionsAction(Constants.SEARCH_IMAGE, Constants.DOCTOR_ID, this));
		searchByDoctorButton.setBackground(Constants.LIGHT_BLUE);
		searchByDoctorButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		contentPanel.add(searchByDoctorButton, "cell 1 3");
		
		personalNumberLabel = new JLabel(Constants.SEARCH_BY_PERSONAL_NUMBER);
		personalNumberLabel.setFont(personalNumberLabel.getFont().deriveFont(16f));
		contentPanel.add(personalNumberLabel, "cell 0 4");
		
		personalNumberTextField = new JTextField();
		personalNumberTextField.setFont(personalNumberTextField.getFont().deriveFont(16f));
		contentPanel.add(personalNumberTextField, "cell 0 5,growx");
		personalNumberTextField.setColumns(10);
		
		searchByPersonalNumberButton = new JButton(new SearchPrescriptionsAction(Constants.SEARCH_IMAGE, Constants.PERSONAL_NUMBER, this));
		searchByPersonalNumberButton.setBackground(Constants.LIGHT_BLUE);
		searchByPersonalNumberButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		contentPanel.add(searchByPersonalNumberButton, "cell 1 5");
		
		medicationLabel = new JLabel(Constants.SEARCH_BY_MEDICATION);
		medicationLabel.setFont(medicationLabel.getFont().deriveFont(16f));
		contentPanel.add(medicationLabel, "cell 0 6");
		
		medicationsComboBox = new JComboBox<>(ApplicationSingleton.getInstance().getMedications().keySet().toArray(new String[]{}));
		medicationsComboBox.setFont(medicationsComboBox.getFont().deriveFont(16f));
		contentPanel.add(medicationsComboBox, "cell 0 7,alignx left");
		
		searchByMedicationButton = new JButton(new SearchPrescriptionsAction(Constants.SEARCH_IMAGE, Constants.MEDICATION, this));
		searchByMedicationButton.setBackground(Constants.LIGHT_BLUE);
		searchByMedicationButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		contentPanel.add(searchByMedicationButton, "cell 1 7");

		pack();
	}

	public JTextField getPrescriptionCodeTextField() {
		return prescriptionCodeTextField;
	}
	
	public JTextField getDoctorTextField() {
		return doctorTextField;
	}

	public JTextField getPersonalNumberTextField() {
		return personalNumberTextField;
	}

	public String getMedication() {
		return (String) medicationsComboBox.getSelectedItem();
	}
}
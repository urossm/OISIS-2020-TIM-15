package org.infsys.pharmacy.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.controller.SellConfirmationAction;
import org.infsys.pharmacy.model.Medication;
import org.infsys.pharmacy.model.Prescription;
import org.infsys.pharmacy.table.model.Field;
import org.infsys.pharmacy.table.model.Row;
import org.infsys.pharmacy.table.view.ScrollableTable;
import org.infsys.pharmacy.util.Constants;

import net.miginfocom.swing.MigLayout;

public class CartPanel extends JPanel {
	
	private static final long serialVersionUID = 4938793886379635472L;
	private JComboBox<String> availableMedications;
	private JLabel title;
	private ScrollableTable scrollableTable;
	private JTextField quantityTextField;
	private JButton addMedicationButton;
	private JButton addMedicationsFromPrescriptionButton;
	private JLabel medicationCodeLabel;
	private JLabel quantityLabel;
	private JButton confirmButton;
	private JButton cancelButton;
	private JLabel totalPriceLabel;
	private JLabel totalLabel;
	private JTextField prescriptionCodeTextField;
	private JLabel prescriptionCodeLabel;
	private Map<String, Integer> medicinesWithAmounts;

	public CartPanel() {
		setLayout(new MigLayout("", "[grow][grow][][]", "[][][][grow][][]"));
		
		title = new JLabel(Constants.SALE_OF_MEDICATIONS);
		title.setFont(Constants.CUSTOM_FONT_BOLD.deriveFont(24f));
		title.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.7f));
		add(title, "cell 0 0 4 1");
		
		medicinesWithAmounts = new HashMap<>();
		
		medicationCodeLabel = new JLabel(Constants.MEDICATION_CODE);
		medicationCodeLabel.setFont(medicationCodeLabel.getFont().deriveFont(18f));
		add(medicationCodeLabel, "cell 0 1,gapy 20 0");
		
		quantityLabel = new JLabel(Constants.QUANTITY);
		quantityLabel.setFont(quantityLabel.getFont().deriveFont(18f));
		add(quantityLabel, "cell 1 1,gapy 20 0");
		
		prescriptionCodeLabel = new JLabel(Constants.PRESCRIPTION_CODE);
		prescriptionCodeLabel.setFont(prescriptionCodeLabel.getFont().deriveFont(18f));
		add(prescriptionCodeLabel, "cell 3 1,gapy 20 0");
		
		Map<String, Medication> medications = ApplicationSingleton.getInstance().getMedications();
		List<String> availableMedicationCodes = medications.keySet()
				.stream()
				.filter(medicationCode -> !medications.get(medicationCode).isPrescriptionNeeded())
				.collect(Collectors.toList());
		
		availableMedications = new JComboBox<>(availableMedicationCodes.toArray(new String[] {}));
		availableMedications.setFont(availableMedications.getFont().deriveFont(18f));
		availableMedications.setForeground(Constants.LIGHT_BLUE);
		add(availableMedications, "cell 0 3,growx");
		
		quantityTextField = new JTextField();
		quantityTextField.setFont(quantityTextField.getFont().deriveFont(18f));
		add(quantityTextField, "cell 1 3,growx");
		quantityTextField.setColumns(10);
		
		addMedicationButton = new JButton(Constants.ADD);
		addMedicationButton.setBackground(Constants.LIGHT_BLUE);
		addMedicationButton.setForeground(new Color(1.0f, 1.0f, 1.0f, 0.9f));
		addMedicationButton.setFont(Constants.CUSTOM_FONT_BOLD);
		add(addMedicationButton, "cell 2 3,gapx 0 20");
		addMedicationButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String quantityString = quantityTextField.getText();
				
				if (quantityString.isEmpty()) {
					JOptionPane.showMessageDialog(null, Constants.QUANTITY_REQUIRED, Constants.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int quantity = 0;
				try {
					quantity = Integer.parseInt(quantityString);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, Constants.QUANTITY_FORMAT_ERROR_MSG, Constants.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				addRow((String) availableMedications.getSelectedItem(), quantity);
			}
		});
		
		prescriptionCodeTextField = new JTextField();
		prescriptionCodeTextField.setFont(prescriptionCodeTextField.getFont().deriveFont(18f));
		add(prescriptionCodeTextField, "cell 3 3");
		prescriptionCodeTextField.setColumns(10);
		
		addMedicationsFromPrescriptionButton = new JButton(Constants.ADD);
		addMedicationsFromPrescriptionButton.setBackground(Constants.LIGHT_BLUE);
		addMedicationsFromPrescriptionButton.setForeground(new Color(1.0f, 1.0f, 1.0f, 0.9f));
		addMedicationsFromPrescriptionButton.setFont(Constants.CUSTOM_FONT_BOLD);
		add(addMedicationsFromPrescriptionButton, "cell 3 3");
		addMedicationsFromPrescriptionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String prescriptionCodeString = prescriptionCodeTextField.getText();
				
				if (prescriptionCodeString.isEmpty()) {
					JOptionPane.showMessageDialog(null, Constants.PRESCRIPTION_CODE_REQUIRED, Constants.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int prescriptionCode = 0;
				try {
					prescriptionCode = Integer.parseInt(prescriptionCodeString);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, Constants.PRESCRIPTION_CODE_FORMAT_ERROR_MSG, Constants.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				for (Prescription prescription : ApplicationSingleton.getInstance().getPrescriptions()) {
					if (prescription.getCode() == prescriptionCode) {
						Map<String, Integer> medications = prescription.getMedicinesWithAmounts();
						for (String medicationCode : medications.keySet()) {
							addRow(medicationCode, medications.get(medicationCode));
						}
						return;
					}
				}
				
				JOptionPane.showMessageDialog(null, Constants.PRESCRIPTION_NOT_EXISTS, Constants.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
			}
		});
		
		scrollableTable = new ScrollableTable(new ArrayList<>());
		add(scrollableTable, "cell 0 4 4 1,grow");
		
		totalLabel = new JLabel(Constants.TOTAL);
		totalLabel.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.3f));
		add(totalLabel, "cell 0 5 4 1,alignx right, gapy 20 0");
		
		totalPriceLabel = new JLabel("$" + this.getTotalPrice());
		totalPriceLabel.setFont(Constants.CUSTOM_FONT_BOLD.deriveFont(11f));
		totalPriceLabel.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.8f));
		add(totalPriceLabel, "cell 0 5 4 1,alignx right,gapx 20 0, gapy 20 0");
		
		cancelButton = new JButton(Constants.CANCEL);
		cancelButton.setBackground(Color.WHITE);
		cancelButton.setForeground(new Color(1.0f, 0.0f, 0.0f, 0.9f));
		cancelButton.setFont(Constants.CUSTOM_FONT_BOLD);
		add(cancelButton, "cell 0 5 4 1,alignx right,gapx 40 0, gapy 20 0");
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (medicinesWithAmounts.isEmpty()) {
					return;
				}
				
				int result = JOptionPane.showConfirmDialog(null, Constants.ORDER_CANCEL_CONFIRMATION_MSG, Constants.CANCEL_CONFIRMATION_TITLE, JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.NO_OPTION) {
					return;
				}
				
				resetCart();
			}
		});
		
		confirmButton = new JButton(new SellConfirmationAction(Constants.CONFIRM, this));
		confirmButton.setBackground(Constants.LIGHT_BLUE);
		confirmButton.setForeground(new Color(1.0f, 1.0f, 1.0f, 0.9f));
		confirmButton.setFont(Constants.CUSTOM_FONT_BOLD);
		add(confirmButton, "cell 0 5 4 1,alignx right,gapx 10 0, gapy 20 0");
	}
	
	public void addRow(String medicationCode, int quantity) {
		
		if (medicinesWithAmounts.containsKey(medicationCode)) {
			medicinesWithAmounts.put(medicationCode, medicinesWithAmounts.get(medicationCode) + quantity);
			
			List<Row> rows = scrollableTable.getRows();
			rows.stream().forEach(row -> {
				boolean flag = false;
				for (Field field : row.getFields()) {
					if (field.getName().equals(Constants.CODE) && ((String) field.getValue()).equals(medicationCode)) {
						flag = true;
						continue;
					}
					
					if (flag && field.getName().equals(Constants.QUANTITY)) {
						field.setValue((Integer) field.getValue() + quantity);
					}
				}
			});
			
			this.scrollableTable.updateModel(rows);
			this.updateTotalPrice();
			return;
		}
		
		medicinesWithAmounts.put(medicationCode, quantity);
		
		Medication medication = ApplicationSingleton.getInstance().getMedications().get(medicationCode);
		
		Field medicationNameField = new Field(Constants.MEDICATION_NAME, medication.getName());
		Field codeField = new Field(Constants.CODE, medication.getCode());
		Field manufacturerField = new Field(Constants.MANUFACTURER, medication.getCode());
		Field quantityField = new Field(Constants.QUANTITY, quantity);
		Field priceField = new Field(Constants.PRICE, "$" + medication.getPrice());
		
		Row row = new Row();
		row.getFields().add(codeField);
		row.getFields().add(medicationNameField);
		row.getFields().add(manufacturerField);
		row.getFields().add(quantityField);
		row.getFields().add(priceField);
		
		this.scrollableTable.addRow(row);
		this.updateTotalPrice();
	}
	
	public void resetCart() {
		medicinesWithAmounts.clear();
		updateTotalPrice();
		scrollableTable.updateModel(new ArrayList<>());
	}
	
	public void updateTotalPrice() {
		this.totalPriceLabel.setText("$" + this.getTotalPrice());
		SwingUtilities.updateComponentTreeUI(this.totalPriceLabel);
	}
	
	public float getTotalPrice() {
		float totalPrice = 0.0f;
		for(String medicationCode : this.medicinesWithAmounts.keySet()) {
			Medication medication = ApplicationSingleton.getInstance().getMedications().get(medicationCode);
			totalPrice += medication.getPrice() * this.medicinesWithAmounts.get(medicationCode);
		}
		return BigDecimal.valueOf(totalPrice).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	public Map<String, Integer> getMedicinesWithAmounts() {
		return medicinesWithAmounts;
	}
}
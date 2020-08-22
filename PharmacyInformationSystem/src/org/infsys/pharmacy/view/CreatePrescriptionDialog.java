package org.infsys.pharmacy.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.controller.AddPrescriptionAction;
import org.infsys.pharmacy.controller.OpenAddMedicationToPrescriptionDialogAction;
import org.infsys.pharmacy.model.Medication;
import org.infsys.pharmacy.table.model.Field;
import org.infsys.pharmacy.table.model.Row;
import org.infsys.pharmacy.table.view.ScrollableTable;
import org.infsys.pharmacy.util.Constants;

import net.miginfocom.swing.MigLayout;

public class CreatePrescriptionDialog extends JDialog {

	private static final long serialVersionUID = 3051897525710882311L;
	private final JPanel contentPanel = new JPanel();
	private JTextField personalNumberTextField;
	private JLabel personalNumberLabel;
	private ScrollableTable scrollableTable;
	private JLabel receiptCodeLabel;
	private JLabel codeLabel;
	private JButton addMedicationButton;
	private JButton addReceiptButton;
	private Map<String, Integer> medicinesWithAmounts;

	public CreatePrescriptionDialog() {
		super(ApplicationSingleton.getInstance().getMainFrame(), true);
		setTitle(Constants.CREATE_PRESCRIPTION);
		setSize(Constants.SCREEN_WIDTH / 2, 2 * Constants.SCREEN_HEIGHT / 3);
		setMinimumSize(new Dimension(Constants.SCREEN_WIDTH / 2, 0));
		setLocationRelativeTo(ApplicationSingleton.getInstance().getMainFrame());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow,center]", "[][grow][]"));
		
		medicinesWithAmounts = new HashMap<>();
		
		personalNumberLabel = new JLabel(Constants.PATIENT_PERSONAL_NUMBER);
		personalNumberLabel.setFont(personalNumberLabel.getFont().deriveFont(18f));
		contentPanel.add(personalNumberLabel, "flowx,cell 0 0");
		
		personalNumberTextField = new JTextField();
		personalNumberTextField.setFont(personalNumberTextField.getFont().deriveFont(18f));
		contentPanel.add(personalNumberTextField, "cell 0 0,alignx center,gapx 10 0");
		personalNumberTextField.setColumns(10);
		
		scrollableTable = new ScrollableTable(new ArrayList<>());
		contentPanel.add(scrollableTable, "cell 0 1,grow");
		
		receiptCodeLabel = new JLabel(Constants.RECEIPT_CODE);
		receiptCodeLabel.setFont(receiptCodeLabel.getFont().deriveFont(18f));
		receiptCodeLabel.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.3f));
		contentPanel.add(receiptCodeLabel, "flowx,cell 0 2,alignx right");
		
		codeLabel = new JLabel(ApplicationSingleton.getInstance().getPrescriptions().size() + 1 + "");
		codeLabel.setFont(Constants.CUSTOM_FONT_BOLD.deriveFont(10f));
		codeLabel.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.8f));
		contentPanel.add(codeLabel, "cell 0 2,gapx 10 0");
		
		addMedicationButton = new JButton(new OpenAddMedicationToPrescriptionDialogAction(Constants.ADD_MEDICATION, this));
		addMedicationButton.setBackground(Color.WHITE);
		addMedicationButton.setForeground(Constants.LIGHT_BLUE);
		addMedicationButton.setFont(Constants.CUSTOM_FONT_BOLD);
		contentPanel.add(addMedicationButton, "cell 0 2,alignx right,gapx 40 0");
		
		addReceiptButton = new JButton(new AddPrescriptionAction(Constants.ADD_RECEIPT, this));
		addReceiptButton.setBackground(Constants.LIGHT_BLUE);
		addReceiptButton.setForeground(Color.WHITE);
		addReceiptButton.setFont(Constants.CUSTOM_FONT_BOLD);
		contentPanel.add(addReceiptButton, "cell 0 2,alignx right,gapx 10 0");
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
			return;
		}
		
		medicinesWithAmounts.put(medicationCode, quantity);
		
		Medication medication = ApplicationSingleton.getInstance().getMedications().get(medicationCode);
		
		Field medicationNameField = new Field(Constants.MEDICATION_NAME, medication.getName());
		Field codeField = new Field(Constants.CODE, medication.getCode());
		Field quantityField = new Field(Constants.QUANTITY, quantity);
		Field priceField = new Field(Constants.MEDICATIONS, medication.getPrice());
		
		Row row = new Row();
		row.getFields().add(codeField);
		row.getFields().add(medicationNameField);
		row.getFields().add(quantityField);
		row.getFields().add(priceField);
		
		this.scrollableTable.addRow(row);
	}

	public String getPersonalNumber() {
		return personalNumberTextField.getText();
	}

	public Map<String, Integer> getMedicinesWithAmounts() {
		return medicinesWithAmounts;
	}
}
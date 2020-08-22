package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.infsys.pharmacy.util.Constants;
import org.infsys.pharmacy.view.AddMedicationToPrescriptionDialog;
import org.infsys.pharmacy.view.CreatePrescriptionDialog;

public class AddMedicationToPrescriptionAction extends AbstractAction {
	
	private static final long serialVersionUID = -4610685155798251862L;
	private CreatePrescriptionDialog createPrescriptionDialog;
	private AddMedicationToPrescriptionDialog addMedicationToPrescriptionDialog;

	public AddMedicationToPrescriptionAction(String name, CreatePrescriptionDialog createPrescriptionDialog,
			AddMedicationToPrescriptionDialog addMedicationToPrescriptionDialog) {
		putValue(NAME, name);
		this.createPrescriptionDialog = createPrescriptionDialog;
		this.addMedicationToPrescriptionDialog = addMedicationToPrescriptionDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String quantityString = addMedicationToPrescriptionDialog.getQuantity();
		
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
		
		createPrescriptionDialog.addRow(addMedicationToPrescriptionDialog.getChosenCode(), quantity);
		addMedicationToPrescriptionDialog.dispose();
	}
}
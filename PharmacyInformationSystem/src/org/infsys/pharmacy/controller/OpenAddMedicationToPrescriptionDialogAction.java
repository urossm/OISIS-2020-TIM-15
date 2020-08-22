package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.infsys.pharmacy.view.AddMedicationToPrescriptionDialog;
import org.infsys.pharmacy.view.CreatePrescriptionDialog;

public class OpenAddMedicationToPrescriptionDialogAction extends AbstractAction {

	private static final long serialVersionUID = -3133027181058858924L;
	private CreatePrescriptionDialog createPrescriptionDialog;

	public OpenAddMedicationToPrescriptionDialogAction(String name, CreatePrescriptionDialog createPrescriptionDialog) {
		putValue(NAME, name);
		this.createPrescriptionDialog = createPrescriptionDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AddMedicationToPrescriptionDialog addMedicationToPrescriptionDialog = new AddMedicationToPrescriptionDialog(createPrescriptionDialog);
		addMedicationToPrescriptionDialog.setVisible(true);
	}
}
package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.model.Medication;
import org.infsys.pharmacy.view.ChooseMedicationCodeDialog;
import org.infsys.pharmacy.view.EditMedicationForm;

public class ChooseMedicationCodeAction extends AbstractAction {
	
	private static final long serialVersionUID = -3888764014106430168L;
	private ChooseMedicationCodeDialog chooseMedicationCodeDialog;

	public ChooseMedicationCodeAction(String name, ChooseMedicationCodeDialog chooseMedicationCodeDialog) {
		putValue(NAME, name);
		this.chooseMedicationCodeDialog = chooseMedicationCodeDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		chooseMedicationCodeDialog.dispose();
		Medication medicationToEdit = ApplicationSingleton.getInstance().getMedications().get(chooseMedicationCodeDialog.getChosenCode());
		EditMedicationForm editMedicationForm = new EditMedicationForm(medicationToEdit);
		editMedicationForm.setVisible(true);
	}
}
package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.infsys.pharmacy.view.AddMedicationForm;

public class OpenAddMedicationDialogAction extends AbstractAction {

	private static final long serialVersionUID = -3133027181058858924L;

	public OpenAddMedicationDialogAction(String name) {
		putValue(NAME, name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AddMedicationForm addMedicationForm = new AddMedicationForm();
		addMedicationForm.setVisible(true);
	}
}
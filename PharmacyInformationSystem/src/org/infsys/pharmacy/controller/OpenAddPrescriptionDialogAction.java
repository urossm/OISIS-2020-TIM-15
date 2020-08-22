package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.infsys.pharmacy.view.CreatePrescriptionDialog;

public class OpenAddPrescriptionDialogAction extends AbstractAction {

	private static final long serialVersionUID = 5783842264429396728L;

	public OpenAddPrescriptionDialogAction(String name) {
		putValue(NAME, name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CreatePrescriptionDialog createPrescriptionDialog = new CreatePrescriptionDialog();
		createPrescriptionDialog.setVisible(true);
	}
}
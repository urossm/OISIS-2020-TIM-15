package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.infsys.pharmacy.view.ChooseMedicationCodeDialog;

public class OpenChooseMedicationCodeDialog extends AbstractAction {

	private static final long serialVersionUID = 4249990419970697936L;

	public OpenChooseMedicationCodeDialog(String name) {
		putValue(NAME, name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ChooseMedicationCodeDialog chooseMedicationCodeDialog = new ChooseMedicationCodeDialog();
		chooseMedicationCodeDialog.setVisible(true);
	}
}
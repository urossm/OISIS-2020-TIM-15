package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.infsys.pharmacy.view.SearchMedicationsDialog;

public class OpenMedicationsDetailedSearchDialogAction extends AbstractAction {

	private static final long serialVersionUID = 5111269390838636889L;

	public OpenMedicationsDetailedSearchDialogAction(String name) {
		putValue(NAME, name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SearchMedicationsDialog dialog = new SearchMedicationsDialog();
		dialog.setVisible(true);
	}
}
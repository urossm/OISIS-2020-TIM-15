package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.infsys.pharmacy.view.SearchPrescriptionsDialog;

public class OpenPrescriptionsDetailedSearchDialogAction extends AbstractAction {

	private static final long serialVersionUID = -5360373204665796995L;

	public OpenPrescriptionsDetailedSearchDialogAction(String name) {
		putValue(NAME, name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SearchPrescriptionsDialog dialog = new SearchPrescriptionsDialog();
		dialog.setVisible(true);
	}
}
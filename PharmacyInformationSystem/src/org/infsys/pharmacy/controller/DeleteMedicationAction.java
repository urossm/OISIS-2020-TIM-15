package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.model.Prescription;
import org.infsys.pharmacy.table.model.Field;
import org.infsys.pharmacy.table.model.Row;
import org.infsys.pharmacy.table.view.ScrollableTable;
import org.infsys.pharmacy.util.Constants;
import org.infsys.pharmacy.util.FileUtil;

public class DeleteMedicationAction extends AbstractAction {
	
	private static final long serialVersionUID = -3440048017227729287L;
	private ScrollableTable scrollableTable;

	public DeleteMedicationAction(String name, ScrollableTable scrollableTable) {
		putValue(NAME, name);
		this.scrollableTable = scrollableTable;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Row row = scrollableTable.getSelectedRow();
		
		if (row == null) {
			JOptionPane.showMessageDialog(null, Constants.DELETE_NOT_SELECTED_ERROR_MSG, Constants.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int result = JOptionPane.showConfirmDialog(null, Constants.MEDICATION_DELETE_CONFIRMATION_MSG, Constants.DELETE_CONFIRMATION_TITLE, JOptionPane.YES_NO_OPTION);
		
		if (result == JOptionPane.NO_OPTION) {
			return;
		}
		
		for (Field field : row.getFields()) {
			if (field.getName().equals(Constants.CODE)) {
				String medicationCode = (String) field.getValue();
				ApplicationSingleton.getInstance().getMedications().get(medicationCode).setLogicallyDeleted(true);
				
				for (Prescription prescription : ApplicationSingleton.getInstance().getPrescriptions()) {
					if (prescription.getMedicinesWithAmounts().containsKey(medicationCode)) {
						prescription.setLogicallyDeleted(true);
					}
				}
				
				break;
			}
		}
		
		FileUtil.saveObjectInFile(new ArrayList<>(ApplicationSingleton.getInstance().getMedications().values()), new File(Constants.MEDICATIONS_PATH));
		ApplicationSingleton.getInstance().getMainFrame().getMedicationsPanel().updateRows();
		
		FileUtil.saveObjectInFile(ApplicationSingleton.getInstance().getPrescriptions(), new File(Constants.PRESCRIPTIONS_PATH));
		ApplicationSingleton.getInstance().getMainFrame().getPrescriptionsPanel().updateRows();
	}
}
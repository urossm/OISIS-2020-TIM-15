package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.model.Prescription;
import org.infsys.pharmacy.table.model.Field;
import org.infsys.pharmacy.table.model.Row;
import org.infsys.pharmacy.table.view.ScrollableTable;
import org.infsys.pharmacy.util.Constants;
import org.infsys.pharmacy.util.FileUtil;

public class DeletePrescriptionAction extends AbstractAction {

	private static final long serialVersionUID = 1848005635320768161L;
	private ScrollableTable scrollableTable;

	public DeletePrescriptionAction(String name, ScrollableTable scrollableTable) {
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
		
		int result = JOptionPane.showConfirmDialog(null, Constants.PRESCRIPTION_DELETE_CONFIRMATION_MSG, Constants.DELETE_CONFIRMATION_TITLE, JOptionPane.YES_NO_OPTION);
		
		if (result == JOptionPane.NO_OPTION) {
			return;
		}
		
		for (Field field : row.getFields()) {
			if (field.getName().equals(Constants.CODE)) {
				for (Prescription prescription : ApplicationSingleton.getInstance().getPrescriptions()) {
					int code = (int) field.getValue();
					if (prescription.getCode().equals(code)) {
						prescription.setLogicallyDeleted(true);
						break;
					}
				}
				break;
			}
		}
		
		FileUtil.saveObjectInFile(ApplicationSingleton.getInstance().getPrescriptions(), new File(Constants.PRESCRIPTIONS_PATH));
		ApplicationSingleton.getInstance().getMainFrame().getPrescriptionsPanel().updateRows();
	}
}
package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.table.model.Field;
import org.infsys.pharmacy.table.model.Row;
import org.infsys.pharmacy.table.view.ScrollableTable;
import org.infsys.pharmacy.util.Constants;
import org.infsys.pharmacy.util.FileUtil;

public class DeleteUserAction extends AbstractAction {
	
	private static final long serialVersionUID = 1608151586968583380L;
	private ScrollableTable scrollableTable;

	public DeleteUserAction(String name, ScrollableTable scrollableTable) {
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
		
		int result = JOptionPane.showConfirmDialog(null, Constants.USER_DELETE_CONFIRMATION_MSG, Constants.DELETE_CONFIRMATION_TITLE, JOptionPane.YES_NO_OPTION);
		
		if (result == JOptionPane.NO_OPTION) {
			return;
		}
		
		for (Field field : row.getFields()) {
			if (field.getName().equals(Constants.USERNAME)) {
				ApplicationSingleton.getInstance().getUsers().get((String) field.getValue()).setLogicallyDeleted(true);
				break;
			}
		}
		
		FileUtil.saveObjectInFile(new ArrayList<>(ApplicationSingleton.getInstance().getUsers().values()), new File(Constants.USERS_PATH));
		ApplicationSingleton.getInstance().getMainFrame().getUserDatabasePanel().updateRows();
	}
}
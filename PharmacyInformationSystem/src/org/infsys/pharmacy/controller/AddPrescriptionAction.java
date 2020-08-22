package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.model.Prescription;
import org.infsys.pharmacy.util.Constants;
import org.infsys.pharmacy.util.FileUtil;
import org.infsys.pharmacy.view.CreatePrescriptionDialog;

public class AddPrescriptionAction extends AbstractAction {
	
	private static final long serialVersionUID = -6715302186703638645L;
	private CreatePrescriptionDialog createPrescriptionDialog;

	public AddPrescriptionAction(String name, CreatePrescriptionDialog createPrescriptionDialog) {
		putValue(NAME, name);
		this.createPrescriptionDialog = createPrescriptionDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String personalNumber = createPrescriptionDialog.getPersonalNumber();
		
		if (personalNumber.isEmpty()) {
			JOptionPane.showMessageDialog(null, Constants.PERSONAL_NUMBER_REQUIRED, Constants.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Map<String, Integer> medicinesWithAmounts =  createPrescriptionDialog.getMedicinesWithAmounts();
		
		if (medicinesWithAmounts.isEmpty()) {
			JOptionPane.showMessageDialog(null, Constants.MEDICATION_REQUIRED, Constants.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Prescription prescription = new Prescription(personalNumber, medicinesWithAmounts);
		
		List<Prescription> prescriptions = ApplicationSingleton.getInstance().getPrescriptions();
		prescriptions.add(prescription);
		FileUtil.saveObjectInFile(prescriptions, new File(Constants.PRESCRIPTIONS_PATH));
		ApplicationSingleton.getInstance().getMainFrame().getPrescriptionsPanel().addRow(prescription);
		createPrescriptionDialog.dispose();
	}
}
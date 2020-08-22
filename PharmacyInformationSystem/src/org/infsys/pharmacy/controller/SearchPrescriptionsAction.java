package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.model.Prescription;
import org.infsys.pharmacy.table.model.Row;
import org.infsys.pharmacy.util.Constants;
import org.infsys.pharmacy.util.Filter;
import org.infsys.pharmacy.view.PrescriptionsPanel;
import org.infsys.pharmacy.view.SearchPrescriptionsDialog;

public class SearchPrescriptionsAction extends AbstractAction {

	private static final long serialVersionUID = -3880921984709009781L;
	private String searchBy;
	private SearchPrescriptionsDialog searchPrescriptionsDialog;

	public SearchPrescriptionsAction(ImageIcon icon, String searchBy, SearchPrescriptionsDialog searchPrescriptionsDialog) {
		putValue(LARGE_ICON_KEY, icon);
		this.searchBy = searchBy;
		this.searchPrescriptionsDialog = searchPrescriptionsDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PrescriptionsPanel prescriptionsPanel = ApplicationSingleton.getInstance().getMainFrame().getPrescriptionsPanel();
		List<Row> rows = prescriptionsPanel.extractRowsFromPrescriptionsList((String) prescriptionsPanel.getSortTypes().getSelectedItem());
		switch (searchBy) {
			case Constants.CODE:
				String code = searchPrescriptionsDialog.getPrescriptionCodeTextField().getText();
				rows = Filter.filterByString(rows, searchBy, code);
				break;
			case Constants.DOCTOR_ID:
				String doctor = searchPrescriptionsDialog.getDoctorTextField().getText();
				rows = Filter.filterByString(rows, searchBy, doctor); 
				break;
			case Constants.PERSONAL_NUMBER:
				String personalNumber = searchPrescriptionsDialog.getPersonalNumberTextField().getText();
				rows = Filter.filterByString(rows, searchBy, personalNumber); 
				break;
			case Constants.MEDICATION:
				String medication = searchPrescriptionsDialog.getMedication();
				List<Prescription> filteredPrescriptions = ApplicationSingleton.getInstance()
									.getPrescriptions()
									.stream()
									.filter(prescription -> prescription.getMedicinesWithAmounts().containsKey(medication))
									.collect(Collectors.toList());
				
				rows = new ArrayList<>();
				for (Prescription prescription : filteredPrescriptions) {
					rows.add(prescriptionsPanel.convertPrescriptionToRow(prescription));
				}
				break;
			default:
				return;
		}
		
		prescriptionsPanel.updateRows(rows);
		searchPrescriptionsDialog.dispose();
	}
}
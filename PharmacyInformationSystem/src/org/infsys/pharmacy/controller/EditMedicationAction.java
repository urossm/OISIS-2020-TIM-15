package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.model.Medication;
import org.infsys.pharmacy.util.Constants;
import org.infsys.pharmacy.util.FileUtil;
import org.infsys.pharmacy.view.EditMedicationForm;

public class EditMedicationAction extends AbstractAction {
	
	private static final long serialVersionUID = 9117710766645677927L;
	private EditMedicationForm editMedicationForm;
	private Medication medicationToEdit;

	public EditMedicationAction(String name, EditMedicationForm editMedicationForm, Medication medicationToEdit) {
		putValue(NAME, name);
		this.editMedicationForm = editMedicationForm;
		this.medicationToEdit = medicationToEdit;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String priceString = editMedicationForm.getPriceTextField().getText();
		
		float price = 0.0f;
		if (!priceString.isEmpty()) {
			try {
				price = Float.parseFloat(priceString);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, Constants.PRICE_FORMAT_ERROR_MSG, Constants.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
				return;
			}			
		}
		
		String name = editMedicationForm.getNameTextField().getText();
		if (!name.isEmpty()) {
			medicationToEdit.setName(name);
		}
		
		String manufacturer = editMedicationForm.getManufacturerTextField().getText();
		if (!manufacturer.isEmpty()) {
			medicationToEdit.setManufacturer(manufacturer);
		}
		
		medicationToEdit.setPrescriptionNeeded(editMedicationForm.getPrescriptionNeededCheckBox().isSelected());
		
		if (!priceString.isEmpty()) {
			medicationToEdit.setPrice(price);
		}
		
		ApplicationSingleton.getInstance().getMedications().put(medicationToEdit.getCode(), medicationToEdit);
		FileUtil.saveObjectInFile(new ArrayList<>(ApplicationSingleton.getInstance().getMedications().values()), new File(Constants.MEDICATIONS_PATH));
		ApplicationSingleton.getInstance().getMainFrame().getMedicationsPanel().updateRows();
		editMedicationForm.dispose();
	}
}
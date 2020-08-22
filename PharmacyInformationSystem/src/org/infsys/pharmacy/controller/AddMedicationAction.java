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
import org.infsys.pharmacy.view.AddMedicationForm;

public class AddMedicationAction extends AbstractAction {
	
	private static final long serialVersionUID = 9117710766645677927L;
	private AddMedicationForm addMedicationForm;

	public AddMedicationAction(String name, AddMedicationForm addMedicationForm) {
		putValue(NAME, name);
		this.addMedicationForm = addMedicationForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String priceString = addMedicationForm.getPriceTextField().getText();
		
		float price = 0.0f;
		try {
			price = Float.parseFloat(priceString);
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, Constants.PRICE_FORMAT_ERROR_MSG, Constants.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Medication medication = new Medication(addMedicationForm.getMedicationCodeTextField().getText(),
				addMedicationForm.getNameTextField().getText(),
				addMedicationForm.getManufacturerTextField().getText(),
				addMedicationForm.getPrescriptionNeededCheckBox().isSelected(),
				price);
		
		ApplicationSingleton.getInstance().getMedications().put(medication.getCode(), medication);
		FileUtil.saveObjectInFile(new ArrayList<>(ApplicationSingleton.getInstance().getMedications().values()), new File(Constants.MEDICATIONS_PATH));
		ApplicationSingleton.getInstance().getMainFrame().getMedicationsPanel().addRow(medication);
		addMedicationForm.dispose();
		
		ApplicationSingleton.getInstance().getMainFrame().getReportsPanel().updateManufacturersList();
	}
}
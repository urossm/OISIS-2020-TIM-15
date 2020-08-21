package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.table.model.Row;
import org.infsys.pharmacy.util.Constants;
import org.infsys.pharmacy.util.Filter;
import org.infsys.pharmacy.view.MedicationsPanel;
import org.infsys.pharmacy.view.SearchMedicationsDialog;

public class SearchMedicationsAction extends AbstractAction {

	private static final long serialVersionUID = -6618171001342193917L;
	private String searchBy;
	private SearchMedicationsDialog searchMedicationsDialog;

	public SearchMedicationsAction(ImageIcon icon, String searchBy, SearchMedicationsDialog searchMedicationsDialog) {
		putValue(LARGE_ICON_KEY, icon);
		this.searchBy = searchBy;
		this.searchMedicationsDialog = searchMedicationsDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MedicationsPanel medicationsPanel = ApplicationSingleton.getInstance().getMainFrame().getMedicationsPanel();
		List<Row> rows = medicationsPanel.extractRowsFromMedicationsList((String) medicationsPanel.getSortTypes().getSelectedItem());
		switch (searchBy) {
			case Constants.CODE:
				String code = searchMedicationsDialog.getMedicationCodeTextField().getText();
				rows = Filter.filterByString(rows, searchBy, code);
				break;
			case Constants.NAME:
				String name = searchMedicationsDialog.getNameTextField().getText();
				rows = Filter.filterByString(rows, searchBy, name); 
				break;
			case Constants.MANUFACTURER:
				String manufacturer = searchMedicationsDialog.getManufacturerTextField().getText();
				rows = Filter.filterByString(rows, searchBy, manufacturer); 
				break;
			case Constants.PRICE:
				String minPriceString = searchMedicationsDialog.getMinPriceTextField().getText();
				float minPrice = 0.0f;
				
				if (!minPriceString.isEmpty()) {
					try {
						minPrice = Float.parseFloat(minPriceString);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, Constants.PRICE_FORMAT_ERROR_MSG, Constants.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
						return;
					}					
				}
				
				String maxPriceString = searchMedicationsDialog.getMaxPriceTextField().getText();
				float maxPrice = 999.0f;
				
				if (!maxPriceString.isEmpty()) {
					try {
						maxPrice = Float.parseFloat(maxPriceString);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, Constants.PRICE_FORMAT_ERROR_MSG, Constants.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				rows = Filter.filterByFloatNumbersRange(rows, searchBy, minPrice, maxPrice);
				break;
			default:
				return;
		}
		
		medicationsPanel.updateRows(rows);
		searchMedicationsDialog.dispose();
	}
}
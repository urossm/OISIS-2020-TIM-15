package org.infsys.pharmacy.view;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.model.Bill;
import org.infsys.pharmacy.model.Medication;
import org.infsys.pharmacy.model.User;
import org.infsys.pharmacy.model.UserType;
import org.infsys.pharmacy.table.model.Field;
import org.infsys.pharmacy.table.model.Row;
import org.infsys.pharmacy.table.view.ScrollableTable;
import org.infsys.pharmacy.util.Constants;

import net.miginfocom.swing.MigLayout;

public class ReportsPanel extends JPanel {
	
	private static final long serialVersionUID = 4938793886379635472L;
	private JLabel title;
	private JLabel medicationsSoldByLabel;
	private JLabel selectManufacturerLabel;
	private JLabel selectPharamcistLabel;
	private JComboBox<String> reportTypes;
	private JComboBox<String> manufacturersList;
	private JComboBox<String> pharmacistsList;
	private JLabel totalPriceLabel;
	private JLabel totalLabel;
	private ScrollableTable scrollableTable;
	private Map<String, Integer> soldMedications;

	public ReportsPanel() {
		setLayout(new MigLayout("", "[grow][grow][grow]", "[][][][grow][][]"));
		
		title = new JLabel(Constants.MEDICATIONS_REPORT);
		title.setFont(Constants.CUSTOM_FONT_BOLD.deriveFont(24f));
		title.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.7f));
		add(title, "cell 0 0 4 1");
		
		medicationsSoldByLabel = new JLabel(Constants.MEDICATIONS_SOLD_BY);
		medicationsSoldByLabel.setFont(medicationsSoldByLabel.getFont().deriveFont(18f));
		add(medicationsSoldByLabel, "cell 0 1,gapy 20 0, gapx 0 20");
		
		selectManufacturerLabel = new JLabel(Constants.SELECT_MANUFACTURER);
		selectManufacturerLabel.setFont(selectManufacturerLabel.getFont().deriveFont(18f));
		add(selectManufacturerLabel, "cell 1 1,gapy 20 0, gapx 0 20");
		
		selectPharamcistLabel = new JLabel(Constants.SELECT_PHARMACIST);
		selectPharamcistLabel.setFont(selectPharamcistLabel.getFont().deriveFont(18f));
		add(selectPharamcistLabel, "cell 2 1,gapy 20 0");
		
		reportTypes = new JComboBox<>(Constants.REPORT_TYPES);
		reportTypes.setFont(reportTypes.getFont().deriveFont(18f));
		reportTypes.setForeground(Constants.LIGHT_BLUE);
		add(reportTypes, "cell 0 3,growx");
		
		manufacturersList = new JComboBox<>();
		manufacturersList.setFont(manufacturersList.getFont().deriveFont(18f));
		manufacturersList.setForeground(Constants.LIGHT_BLUE);
		manufacturersList.setEnabled(false);
		add(manufacturersList, "cell 1 3,growx");
		updateManufacturersList();
		
		pharmacistsList = new JComboBox<>();
		pharmacistsList.setFont(pharmacistsList.getFont().deriveFont(18f));
		pharmacistsList.setForeground(Constants.LIGHT_BLUE);
		pharmacistsList.setEnabled(false);
		add(pharmacistsList, "cell 2 3,growx");
		updatePharmacistsList();
		
		scrollableTable = new ScrollableTable(this.extractRowsFromBillsList(Constants.REPORT_TYPES[0]));
		add(scrollableTable, "cell 0 4 4 1,grow");
		
		totalLabel = new JLabel(Constants.TOTAL_PROFIT);
		totalLabel.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.3f));
		add(totalLabel, "cell 0 5 4 1,alignx right, gapy 20 0");
		
		totalPriceLabel = new JLabel("$" + this.getTotalPrice());
		totalPriceLabel.setFont(Constants.CUSTOM_FONT_BOLD.deriveFont(11f));
		totalPriceLabel.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.8f));
		add(totalPriceLabel, "cell 0 5 4 1,alignx right,gapx 20 20, gapy 20 0");
		
		reportTypes.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
			          updateRows();
			          switch ((String) e.getItem()) {
			          	case Constants.ALL:
			          		manufacturersList.setEnabled(false);
			          		pharmacistsList.setEnabled(false);
							break;
			          	case Constants.MANUFACTURER:
			          		manufacturersList.setEnabled(true);
			          		pharmacistsList.setEnabled(false);
							break;
			          	case Constants.PHARMACIST:
			          		manufacturersList.setEnabled(false);
			          		pharmacistsList.setEnabled(true);
			          }
		       }
			}
		});
		
		manufacturersList.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
			          updateRows();
		       }
			}
		});
		
		pharmacistsList.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
			          updateRows();
		       }
			}
		});
	}
	
	public void updateManufacturersList() {
		manufacturersList.removeAllItems();
		List<String> manufacturers = new ArrayList<>();
		for (Medication medication : ApplicationSingleton.getInstance().getMedications().values()) {
			if (!manufacturers.contains(medication.getManufacturer())) {
				manufacturers.add(medication.getManufacturer());
				manufacturersList.addItem(medication.getManufacturer());
			}
		}
	}
	
	public void updatePharmacistsList() {
		pharmacistsList.removeAllItems();
		List<String> pharmacists = new ArrayList<>();
		for (User user : ApplicationSingleton.getInstance().getUsers().values()) {
			if (!pharmacists.contains(user.getUsername()) && user.getType() == UserType.PHARMACIST) {
				pharmacists.add(user.getUsername());
				pharmacistsList.addItem(user.getUsername());
			}
		}
	}
	
	public void updateRows() {
		this.scrollableTable.updateModel(this.extractRowsFromBillsList((String) reportTypes.getSelectedItem()));
	}
	
	public List<Row> extractRowsFromBillsList(String reportType) {
		List<Row> rows = new ArrayList<>();
		List<Bill> bills = ApplicationSingleton.getInstance().getBills();
		
		soldMedications = new HashMap<>();
		
		for (Bill bill : bills) {
			if (reportType.equals(Constants.PHARMACIST) && !bill.getPharmacistId().equals((String) pharmacistsList.getSelectedItem())) {
				continue;
			}
			for (String medicationCode : bill.getMedicinesWithAmounts().keySet()) {
				if (soldMedications.containsKey(medicationCode)) {
					soldMedications.put(medicationCode, soldMedications.get(medicationCode) + bill.getMedicinesWithAmounts().get(medicationCode));
				} else {
					soldMedications.put(medicationCode, bill.getMedicinesWithAmounts().get(medicationCode));
				}
			}
		}
		
		for (String medicationCode : soldMedications.keySet()) {
			Medication medication = ApplicationSingleton.getInstance().getMedications().get(medicationCode);
			if (reportType.equals(Constants.MANUFACTURER) && !medication.getManufacturer().equals((String) manufacturersList.getSelectedItem())) {
				continue;
			}
			rows.add(createReportRow(medication, soldMedications.get(medicationCode)));
		}
		
		return rows;
	}
	
	public Row createReportRow(Medication medication, int quantity) {
		Field nameField = new Field(Constants.NAME, medication.getName());
		Field manufacturerField = new Field(Constants.MANUFACTURER, medication.getManufacturer());
		Field quantityField = new Field(Constants.QUANTITY_SOLD, quantity);
		Field priceField = new Field(Constants.TOTAL_PRICE, "$" + (medication.getPrice() * quantity));
		
		Row row = new Row();
		row.getFields().add(nameField);
		row.getFields().add(manufacturerField);
		row.getFields().add(quantityField);
		row.getFields().add(priceField);
		
		return row;
	}

	public void updateTotalPrice() {
		this.totalPriceLabel.setText("$" + this.getTotalPrice());
		SwingUtilities.updateComponentTreeUI(this.totalPriceLabel);
	}
	
	public float getTotalPrice() {
		float totalPrice = 0.0f;
		for(String medicationCode : this.soldMedications.keySet()) {
			Medication medication = ApplicationSingleton.getInstance().getMedications().get(medicationCode);
			totalPrice += medication.getPrice() * this.soldMedications.get(medicationCode);
		}
		return BigDecimal.valueOf(totalPrice).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
	}
}
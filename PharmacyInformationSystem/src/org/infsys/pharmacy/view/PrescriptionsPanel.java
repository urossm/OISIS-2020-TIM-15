package org.infsys.pharmacy.view;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.controller.DeletePrescriptionAction;
import org.infsys.pharmacy.controller.OpenAddPrescriptionDialogAction;
import org.infsys.pharmacy.controller.OpenPrescriptionsDetailedSearchDialogAction;
import org.infsys.pharmacy.model.Prescription;
import org.infsys.pharmacy.model.UserType;
import org.infsys.pharmacy.table.model.Field;
import org.infsys.pharmacy.table.model.Row;
import org.infsys.pharmacy.table.view.ScrollableTable;
import org.infsys.pharmacy.util.Constants;

import net.miginfocom.swing.MigLayout;

public class PrescriptionsPanel extends JPanel {
	
	private static final long serialVersionUID = 3370250841750583887L;
	private JButton detailedSearchButton;
	private JComboBox<String> sortTypes;
	private JLabel title;
	private ScrollableTable scrollableTable;
	private JButton addPrescriptionButton;
	private JButton deletePrescriptionButton;

	public PrescriptionsPanel() {
		setLayout(new MigLayout("", "[grow][grow]", "[][][grow][][]"));
		
		title = new JLabel(Constants.PRESCRIPTIONS);
		title.setFont(Constants.CUSTOM_FONT_BOLD.deriveFont(24f));
		title.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.7f));
		add(title, "cell 0 0 2 1");
		
		sortTypes = new JComboBox<>(Constants.PRESCRIPTIONS_SORT_TYPES);
		sortTypes.setForeground(Constants.LIGHT_BLUE);
		add(sortTypes, "cell 0 1,alignx left, gapy 20 20");
		
		detailedSearchButton = new JButton(new OpenPrescriptionsDetailedSearchDialogAction(Constants.DETAILED_SEARCH));
		detailedSearchButton.setBackground(Constants.LIGHT_BLUE);
		detailedSearchButton.setForeground(Color.WHITE);
		detailedSearchButton.setFont(Constants.CUSTOM_FONT_BOLD);
		detailedSearchButton.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		add(detailedSearchButton, "cell 1 1,alignx right, gapy 20 20");
		
		scrollableTable = new ScrollableTable(this.extractRowsFromPrescriptionsList(Constants.PRESCRIPTIONS_SORT_TYPES[0]));
		add(scrollableTable, "cell 0 2 2 1,grow");
		
		//Just doctor can create prescriptions so just doctor can see button for creating prescription
		if (ApplicationSingleton.getInstance().getLoggedInUser().getType() == UserType.DOCTOR) {
			addPrescriptionButton = new JButton(new OpenAddPrescriptionDialogAction(Constants.ADD_NEW_PRESCRIPTION));
			addPrescriptionButton.setBackground(Constants.LIGHT_BLUE);
			addPrescriptionButton.setForeground(Color.WHITE);
			addPrescriptionButton.setFont(Constants.CUSTOM_FONT_BOLD);
			addPrescriptionButton.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
			add(addPrescriptionButton, "flowx,cell 0 3 2 1, gapy 20 0");
			
			deletePrescriptionButton = new JButton(new DeletePrescriptionAction(Constants.DELETE_PRESCRIPTION, this.scrollableTable));
			deletePrescriptionButton.setBackground(Constants.LIGHT_BLUE);
			deletePrescriptionButton.setForeground(Color.WHITE);
			deletePrescriptionButton.setFont(Constants.CUSTOM_FONT_BOLD);
			deletePrescriptionButton.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
			add(deletePrescriptionButton, "flowx,cell 0 3 2 1, gapy 20 0");
		}
		
		sortTypes.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
			          String selectedSortType = (String) e.getItem();
			          scrollableTable.updateModel(extractRowsFromPrescriptionsList(selectedSortType));
		       }
			}
		});
	}
	
	public void addRow(Prescription prescription) {
		this.scrollableTable.addRow(this.convertPrescriptionToRow(prescription));
	}
	
	public void updateRows() {
		this.updateRows(extractRowsFromPrescriptionsList((String) sortTypes.getSelectedItem()));
	}
	
	public void updateRows(List<Row> rows) {
		this.scrollableTable.updateModel(rows);
	}
	
	public List<Row> extractRowsFromPrescriptionsList(String sortBy) {
		List<Row> rows = new ArrayList<>();
		List<Prescription> prescriptions = new ArrayList<Prescription>(ApplicationSingleton.getInstance().getPrescriptions());
		
		prescriptions.sort(new Comparator<Prescription>() {

			@Override
			public int compare(Prescription prescription1, Prescription prescription2) {
				switch(sortBy) {
					case Constants.NAME:
						return prescription1.getCode().compareTo(prescription2.getCode());
					case Constants.DOCTOR:
						return prescription1.getDoctorId().compareTo(prescription2.getDoctorId());
					default:
						return prescription1.getDateAndTime().compareTo(prescription2.getDateAndTime());
				}
			}
		});
		
		for (Prescription prescription : prescriptions) {
			if (ApplicationSingleton.getInstance().getLoggedInUser().getType() != UserType.ADMINISTRATOR && prescription.isLogicallyDeleted()) {
				continue;
			}
			rows.add(this.convertPrescriptionToRow(prescription));
		}
		
		return rows;
	}
	
	public Row convertPrescriptionToRow(Prescription prescription) {
		Field codeField = new Field(Constants.CODE, prescription.getCode());
		Field personalNumberField = new Field(Constants.PERSONAL_NUMBER, prescription.getPatientPersonalNumber());
		Field doctorIdField = new Field(Constants.DOCTOR_ID, prescription.getDoctorId());
		Field medicationsWithAmountsField = new Field(Constants.MEDICATIONS, prescription.medicationsWithAmountsToString());
		Field dateAndTimeField = new Field(Constants.DATE_TIME, prescription.getDateAndTime().format(Constants.DATE_FORMAT));
		Field totalPriceField = new Field(Constants.TOTAL_PRICE, "$" + prescription.getTotalPrice());
		
		Row row = new Row();
		row.getFields().add(codeField);
		row.getFields().add(personalNumberField);
		row.getFields().add(doctorIdField);
		row.getFields().add(medicationsWithAmountsField);
		row.getFields().add(dateAndTimeField);
		row.getFields().add(totalPriceField);
		
		if (ApplicationSingleton.getInstance().getLoggedInUser().getType() == UserType.ADMINISTRATOR) {
			Field deletedField = new Field(Constants.DELETED, prescription.isLogicallyDeleted());
			row.getFields().add(deletedField);
		}
		
		return row;
	}

	public JComboBox<String> getSortTypes() {
		return sortTypes;
	}
}
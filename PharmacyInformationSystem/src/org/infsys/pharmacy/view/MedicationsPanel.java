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
import org.infsys.pharmacy.controller.OpenAddMedicationDialogAction;
import org.infsys.pharmacy.controller.OpenChooseMedicationCodeDialog;
import org.infsys.pharmacy.controller.OpenMedicationsDetailedSearchDialogAction;
import org.infsys.pharmacy.model.Medication;
import org.infsys.pharmacy.model.UserType;
import org.infsys.pharmacy.table.model.Field;
import org.infsys.pharmacy.table.model.Row;
import org.infsys.pharmacy.table.view.ScrollableTable;
import org.infsys.pharmacy.util.Constants;

import net.miginfocom.swing.MigLayout;

public class MedicationsPanel extends JPanel {
	
	private static final long serialVersionUID = 8036157817726560184L;
	private JButton detailedSearchButton;
	private JComboBox<String> sortTypes;
	private JLabel title;
	private ScrollableTable scrollableTable;
	private JButton addMedicationButton;
	private JButton editMedicationButton;

	public MedicationsPanel() {
		setLayout(new MigLayout("", "[grow][grow]", "[][][grow][][]"));
		
		title = new JLabel(Constants.MEDICATIONS);
		title.setFont(Constants.CUSTOM_FONT_BOLD.deriveFont(24f));
		title.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.7f));
		add(title, "cell 0 0 2 1");
		
		sortTypes = new JComboBox<>(Constants.MEDICATIONS_SORT_TYPES);
		sortTypes.setForeground(Constants.LIGHT_BLUE);
		add(sortTypes, "cell 0 1,alignx left, gapy 20 20");
		
		detailedSearchButton = new JButton(new OpenMedicationsDetailedSearchDialogAction(Constants.DETAILED_SEARCH));
		detailedSearchButton.setBackground(Constants.LIGHT_BLUE);
		detailedSearchButton.setForeground(Color.WHITE);
		detailedSearchButton.setFont(Constants.CUSTOM_FONT_BOLD);
		detailedSearchButton.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		add(detailedSearchButton, "cell 1 1,alignx right, gapy 20 20");
		
		scrollableTable = new ScrollableTable(this.extractRowsFromMedicationsList(Constants.MEDICATIONS_SORT_TYPES[0]));
		add(scrollableTable, "cell 0 2 2 1,grow");
		
		if (ApplicationSingleton.getInstance().getLoggedInUser().getType() != UserType.DOCTOR) {
			addMedicationButton = new JButton(new OpenAddMedicationDialogAction(Constants.ADD_NEW_MEDICATION));
			addMedicationButton.setBackground(Constants.LIGHT_BLUE);
			addMedicationButton.setForeground(Color.WHITE);
			addMedicationButton.setFont(Constants.CUSTOM_FONT_BOLD);
			addMedicationButton.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
			add(addMedicationButton, "flowx,cell 0 3, gapy 20 0");
			
			editMedicationButton = new JButton(new OpenChooseMedicationCodeDialog(Constants.EDIT_MEDICATION));
			editMedicationButton.setBackground(Constants.LIGHT_BLUE);
			editMedicationButton.setForeground(Color.WHITE);
			editMedicationButton.setFont(Constants.CUSTOM_FONT_BOLD);
			editMedicationButton.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
			add(editMedicationButton, "cell 0 3, gapy 20 0");	
		}
		
		sortTypes.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
			          String selectedSortType = (String) e.getItem();
			          scrollableTable.updateModel(extractRowsFromMedicationsList(selectedSortType));
		       }
			}
		});
	}
	
	public void addRow(Medication medication) {
		this.scrollableTable.addRow(this.convertMedicationToRow(medication));
	}
	
	public void updateRows() {
		this.updateRows(extractRowsFromMedicationsList((String) sortTypes.getSelectedItem()));
	}
	
	public void updateRows(List<Row> rows) {
		this.scrollableTable.updateModel(rows);
	}
	
	public List<Row> extractRowsFromMedicationsList(String sortBy) {
		List<Row> rows = new ArrayList<>();
		List<Medication> medications = new ArrayList<Medication>(ApplicationSingleton.getInstance().getMedications().values());
		
		medications.sort(new Comparator<Medication>() {

			@Override
			public int compare(Medication medication1, Medication medication2) {
				switch(sortBy) {
					case Constants.NAME:
						return medication1.getName().compareTo(medication2.getName());
					case Constants.MANUFACTURER:
						return medication1.getManufacturer().compareTo(medication2.getManufacturer());
					default:
						return medication1.getPrice().compareTo(medication2.getPrice());
				}
			}
		});
		
		for (Medication medication : medications) {
			rows.add(this.convertMedicationToRow(medication));
		}
		
		return rows;
	}
	
	public Row convertMedicationToRow(Medication medication) {
		Field codeField = new Field(Constants.CODE, medication.getCode());
		Field nameField = new Field(Constants.NAME, medication.getName());
		Field manufacturerField = new Field(Constants.MANUFACTURER, medication.getManufacturer());
		Field priceField = new Field(Constants.PRICE, medication.getPrice());
		Field prescriptionNeededField = new Field(Constants.PRESCRIPTION_NEEDED, medication.isPrescriptionNeeded());
		
		Row row = new Row();
		row.getFields().add(codeField);
		row.getFields().add(nameField);
		row.getFields().add(manufacturerField);
		row.getFields().add(priceField);
		row.getFields().add(prescriptionNeededField);
		
		return row;
	}

	public JComboBox<String> getSortTypes() {
		return sortTypes;
	}
}
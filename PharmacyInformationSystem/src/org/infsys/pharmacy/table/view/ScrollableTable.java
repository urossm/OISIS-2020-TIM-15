package org.infsys.pharmacy.table.view;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import org.infsys.pharmacy.table.model.Row;
import org.infsys.pharmacy.table.model.TableModel;
import org.infsys.pharmacy.util.Constants;

public class ScrollableTable extends JScrollPane {

	private static final long serialVersionUID = -7392736692018133356L;
	private JTable table;
	private TableModel tableModel;

	public ScrollableTable(List<Row> rows) {
		this.tableModel = new TableModel(rows);
		this.table = new JTable(this.tableModel);
		this.table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.table.setFont(this.table.getFont().deriveFont(18f));
		this.table.getTableHeader().setForeground(Constants.LIGHT_BLUE);
		this.setViewportView(this.table);
	}
	
	public void updateModel(List<Row> rows) {
		this.tableModel.setRows(rows);
	}
	
	public void addRow(Row row) {
		this.tableModel.addRow(row);
	}
	
	public List<Row> getRows() {
		return this.tableModel.getRows();
	}

	public Row getSelectedRow() {
		int rowIndex = this.table.getSelectedRow();
		
		if (rowIndex == -1) {
			return null;
		}
		
		return this.getRows().get(rowIndex);
	}
}
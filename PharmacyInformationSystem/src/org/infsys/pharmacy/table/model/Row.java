package org.infsys.pharmacy.table.model;

import java.util.ArrayList;
import java.util.List;

public class Row {
	private List<Field> fields;

	public List<Field> getFields() {
		if (fields == null) {
			fields = new ArrayList<>();
		}
		return fields;
	}
}
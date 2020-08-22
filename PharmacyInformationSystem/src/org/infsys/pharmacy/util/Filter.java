package org.infsys.pharmacy.util;

import java.util.List;
import java.util.stream.Collectors;

import org.infsys.pharmacy.table.model.Field;
import org.infsys.pharmacy.table.model.Row;

public class Filter {

	public static List<Row> filterByString(List<Row> rows, String searchBy, String filter) {
		return rows.stream().filter(row -> { 
			for (Field field : row.getFields()) {
				if (field.getName().equals(searchBy) && field.getValue().toString().toLowerCase().contains(filter.toLowerCase())) {
					return true;
				}
			}
			return false;
		}).collect(Collectors.toList());
	}
	
	public static List<Row> filterByFloatNumbersRange(List<Row> rows, String searchBy, float minValue, float maxValue) {
		return rows.stream().filter(row -> { 
			for (Field field : row.getFields()) {
				if (field.getName().equals(searchBy)) {
					float floatNumber = Float.parseFloat(((String)field.getValue()).substring(1));
					if (minValue <= floatNumber && floatNumber <= maxValue) {
						return true;
					} else {
						return false;
					}
				}
			}
			return false;
		}).collect(Collectors.toList());
	}
}

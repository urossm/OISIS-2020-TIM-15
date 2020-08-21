package org.infsys.pharmacy.model;

import java.io.Serializable;
import java.util.Objects;

public class Medication implements Serializable  {

	private static final long serialVersionUID = -4745477683642502271L;
	private String code;
	private String name;
	private String manufacturer;
	private Boolean prescriptionNeeded;
	private Float price;
	private boolean logicallyDeleted;
	
	public Medication() {
	}

	public Medication(String code, String name, String manufacturer, boolean prescriptionNeeded, float price) {
		this.code = code;
		this.name = name;
		this.manufacturer = manufacturer;
		this.prescriptionNeeded = prescriptionNeeded;
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Boolean isPrescriptionNeeded() {
		return prescriptionNeeded;
	}

	public void setPrescriptionNeeded(boolean prescriptionNeeded) {
		this.prescriptionNeeded = prescriptionNeeded;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isLogicallyDeleted() {
		return logicallyDeleted;
	}

	public void setLogicallyDeleted(boolean logicallyDeleted) {
		this.logicallyDeleted = logicallyDeleted;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Medication)) {
			return false;
		}
		Medication other = (Medication) obj;
		return Objects.equals(code, other.code);
	}
}
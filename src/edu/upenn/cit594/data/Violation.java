package edu.upenn.cit594.data;

import java.util.Date;

/**
 * Represents a parking violation read from the input source
 */
public class Violation {
	public Violation(int id, Date date, int fine, String description, String vehicleId, String stateOfPlate,
			String zip) {
		this.id = id;
		this.date = date;
		this.fine = fine;
		this.description = description;
		this.vehicleId = vehicleId;
		this.stateOfPlate = stateOfPlate;
		this.zip = zip;
	}

	// uuid for this violation
	public int id;
	private Date date;
	private int fine;
	private String description;
	private String vehicleId;
	// 2 letter abbreviation
	private String stateOfPlate;
	private String zip;

	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public int getFine() {
		return fine;
	}

	public String getDescription() {
		return description;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public String getStateOfPlate() {
		return stateOfPlate;
	}

	public String getZip() {
		return zip;
	}
}

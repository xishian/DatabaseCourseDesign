package edu.swu.model;

public class MateHouse {
	private String MateHouseID;
	private String StaffID;
	private String MateHouseMstock;
	private String MateAmountNow;
	private String MateAmountLost;
	
	public MateHouse() {
		super();
	}
	public MateHouse(String mateHouseID) {
		super();
		MateHouseID = mateHouseID;
	}
	
	public MateHouse(String mateHouseID, String staffID, String mateHouseMstock, String mateAmountNow,
			String mateAmountLost) {
		super();
		MateHouseID = mateHouseID;
		StaffID = staffID;
		MateHouseMstock = mateHouseMstock;
		MateAmountNow = mateAmountNow;
		MateAmountLost = mateAmountLost;
	}
	public String getMateHouseID() {
		return MateHouseID;
	}
	public void setMateHouseID(String mateHouseID) {
		MateHouseID = mateHouseID;
	}
	public String getStaffID() {
		return StaffID;
	}
	public void setStaffID(String staffID) {
		StaffID = staffID;
	}
	public String getMateHouseMstock() {
		return MateHouseMstock;
	}
	public void setMateHouseMstock(String mateHouseMstock) {
		MateHouseMstock = mateHouseMstock;
	}
	public String getMateAmountNow() {
		return MateAmountNow;
	}
	public void setMateAmountNow(String mateAmountNow) {
		MateAmountNow = mateAmountNow;
	}
	public String getMateAmountLost() {
		return MateAmountLost;
	}
	public void setMateAmountLost(String mateAmountLost) {
		MateAmountLost = mateAmountLost;
	}		
}

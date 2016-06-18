package edu.swu.model;

public class ProdHouse {
	private String ProdHouseID;
	private String StaffID;
	private String ProdID;
	private String ProdHouseMstock;
	private String ProdAmountNow;
	private String ProdAmountLost;
	
	public ProdHouse() {
		super();
	}
	public ProdHouse(String prodHouseID) {
		super();
		ProdHouseID = prodHouseID;
	}
	
	public ProdHouse(String prodHouseID, String staffID, String prodID, String prodHouseMstock,
			String prodAmountNow, String prodAmountLost) {
		super();
		ProdHouseID = prodHouseID;
		StaffID = staffID;
		ProdID = prodID;
		ProdHouseMstock = prodHouseMstock;
		ProdAmountNow = prodAmountNow;
		ProdAmountLost = prodAmountLost;
	}
	public String getProdHouseID() {
		return ProdHouseID;
	}
	public void setProdHouseID(String prodHouseID) {
		ProdHouseID = prodHouseID;
	}
	public String getStaffID() {
		return StaffID;
	}
	public void setStaffID(String staffID) {
		StaffID = staffID;
	}
	public String getProdID() {
		return ProdID;
	}
	public void setProdID(String prodID) {
		ProdID = prodID;
	}
	public String getProdHouseMstock() {
		return ProdHouseMstock;
	}
	public void setProdHouseMstock(String prodHouseMstock) {
		ProdHouseMstock = prodHouseMstock;
	}
	public String getProdAmountNow() {
		return ProdAmountNow;
	}
	public void setProdAmountNow(String prodAmountNow) {
		ProdAmountNow = prodAmountNow;
	}
	public String getProdAmountLost() {
		return ProdAmountLost;
	}
	public void setProdAmountLost(String prodAmountLost) {
		ProdAmountLost = prodAmountLost;
	}
	
}

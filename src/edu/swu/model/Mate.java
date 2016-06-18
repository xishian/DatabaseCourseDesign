package edu.swu.model;


public class Mate {
	private String MateID;
	private String MateName;
	private String MateUnitPrice;
	private String MateSpec;
	private String MateHouseID;
	private String MateDesc;
	
	public Mate() {
		super();
	}
	public Mate(String mateID) {
		super();
		MateID = mateID;
	}
	
	public Mate(String mateID, String mateName, String mateUnitPrice, String mateSpec, String mateHouseID,
			String mateDesc) {
		super();
		MateID = mateID;
		MateName = mateName;
		MateUnitPrice = mateUnitPrice;
		MateSpec = mateSpec;
		MateHouseID = mateHouseID;
		MateDesc = mateDesc;
	}
	public String getMateID() {
		return MateID;
	}
	public void setMateID(String mateID) {
		MateID = mateID;
	}
	public String getMateName() {
		return MateName;
	}
	public void setMateName(String mateName) {
		MateName = mateName;
	}
	public String getMateUnitPrice() {
		return MateUnitPrice;
	}
	public void setMateUnitPrice(String mateUnitPrice) {
		MateUnitPrice = mateUnitPrice;
	}
	public String getMateSpec() {
		return MateSpec;
	}
	public void setMateSpec(String mateSpec) {
		MateSpec = mateSpec;
	}
	public String getMateHouseID() {
		return MateHouseID;
	}
	public void setMateHouseID(String mateHouseID) {
		MateHouseID = mateHouseID;
	}
	public String getMateDesc() {
		return MateDesc;
	}
	public void setMateDesc(String mateDesc) {
		MateDesc = mateDesc;
	}
	
}

package edu.swu.model;

public class ProductInfo {
	private String ProdID;
	private String ProdName;
	private String ProdUnitPrice;
	private String ProdCost;
	private String ProdDesc;
	private String ProdHouseID;
	
	public ProductInfo() {
		super();
	}
	public ProductInfo(String prodID) {
		super();
		ProdID = prodID;
	}
	
	public ProductInfo(String prodID, String prodName, String prodUnitPrice, String prodCost, String prodDesc,
			String prodHouseID) {
		super();
		ProdID = prodID;
		ProdName = prodName;
		ProdUnitPrice = prodUnitPrice;
		ProdCost = prodCost;
		ProdDesc = prodDesc;
		ProdHouseID = prodHouseID;
	}
	public String getProdID() {
		return ProdID;
	}
	public void setProdID(String prodID) {
		ProdID = prodID;
	}
	public String getProdName() {
		return ProdName;
	}
	public void setProdName(String prodName) {
		ProdName = prodName;
	}
	public String getProdUnitPrice() {
		return ProdUnitPrice;
	}
	public void setProdUnitPrice(String prodUnitPrice) {
		ProdUnitPrice = prodUnitPrice;
	}
	public String getProdCost() {
		return ProdCost;
	}
	public void setProdCost(String prodCost) {
		ProdCost = prodCost;
	}
	public String getProdDesc() {
		return ProdDesc;
	}
	public void setProdDesc(String prodDesc) {
		ProdDesc = prodDesc;
	}
	public String getProdHouseID() {
		return ProdHouseID;
	}
	public void setProdHouseID(String prodHouseID) {
		ProdHouseID = prodHouseID;
	}

}

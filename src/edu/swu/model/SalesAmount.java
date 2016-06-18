package edu.swu.model;

//销售收入表

public class SalesAmount {
	private String id;
	private String clientId;
	private String prodName;
	private String unitPrice;
	private String prodAmount;
	private String prodToprice;
	private String sellTime;
	private String staffId;
	private String memo;
	
	public SalesAmount() {
		super();
	}
	public SalesAmount(String id) {
		super();
		this.id = id;
	}
	public SalesAmount(String id, String clientId, String prodName, String unitPrice, String prodAmount,
			String prodToprice, String sellTime, String staffId, String memo) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.prodName = prodName;
		this.unitPrice = unitPrice;
		this.prodAmount = prodAmount;
		this.prodToprice = prodToprice;
		this.sellTime = sellTime;
		this.staffId = staffId;
		this.memo = memo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getProdAmount() {
		return prodAmount;
	}
	public void setProdAmount(String prodAmount) {
		this.prodAmount = prodAmount;
	}
	public String getProdToprice() {
		return prodToprice;
	}
	public void setProdToprice(String prodToprice) {
		this.prodToprice = prodToprice;
	}
	public String getSellTime() {
		return sellTime;
	}
	public void setSellTime(String sellTime) {
		this.sellTime = sellTime;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	

}

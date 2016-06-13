package edu.swu.model;

import java.util.Date;

//订单类，记录了一个订单的详细信息

public class Order {
	private String id;
	private String clientId;
	private String prodId;
	private String prodName;
	private int prodAmount;
	private Date indentTime;
	private Date pickTime;
	private String staffId;
	private String meme = null;
	
	public Order(String id) {
		super();
		this.id = id;
	}

	public Order() {
		super();
	}

	public Order(String id, String clientId, String prodId, String prodName, int prodAmount, Date indentTime,
			Date pickTime, String staffId, String meme) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodAmount = prodAmount;
		this.indentTime = indentTime;
		this.pickTime = pickTime;
		this.staffId = staffId;
		this.meme = meme;
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

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public int getProdAmount() {
		return prodAmount;
	}

	public void setProdAmount(int prodAmount) {
		this.prodAmount = prodAmount;
	}

	public Date getIndentTime() {
		return indentTime;
	}

	public void setIndentTime(Date indentTime) {
		this.indentTime = indentTime;
	}

	public Date getPickTime() {
		return pickTime;
	}

	public void setPickTime(Date pickTime) {
		this.pickTime = pickTime;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getMeme() {
		return meme;
	}

	public void setMeme(String meme) {
		this.meme = meme;
	}
	
	
	

}

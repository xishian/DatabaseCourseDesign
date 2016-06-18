package edu.swu.model;

public class Reim {
	
	private String id;
	private String departId;
	private String reimTime; //报账时间
	private String amount;  //报账金额
	private String staffId;
	private String reason;
	private String memo;
	
	
	
	public Reim(String id) {
		super();
		this.id = id;
	}
	public Reim() {
		super();
	}
	public Reim(String id, String departId, String reimTime, String amount, String staffId, String reason, String memo) {
		super();
		this.id = id;
		this.departId = departId;
		this.reimTime = reimTime;
		this.amount = amount;
		this.staffId = staffId;
		this.reason = reason;
		this.memo = memo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public String getReimTime() {
		return reimTime;
	}
	public void setReimTime(String reimTime) {
		this.reimTime = reimTime;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
	

}

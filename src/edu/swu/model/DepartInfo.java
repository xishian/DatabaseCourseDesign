package edu.swu.model;

public class DepartInfo {
	private String id;
	private String name;
	private String staffId; //主管
	private String duty; //部门职责
	private int number;
	public DepartInfo() {
		super();
	}
	public DepartInfo(String id) {
		super();
		this.id = id;
	}
	public DepartInfo(String id, String name, String staffId, String duty, int number) {
		super();
		this.id = id;
		this.name = name;
		this.staffId = staffId;
		this.duty = duty;
		this.number = number;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}



}

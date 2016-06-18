package edu.swu.model;

public class EmployeeInfo {
	private String id; //staffId
	private String departId;
	private String name;
	private String sex;
	private int age;
	private String duty; //职责
	private String degree; //学历
	private String birthday;
	private String status;
	private String comeTime;
	private String state; //现在的状况
	
	private String beseSalary;  //基本工资
	private String bonus; //奖金
	private String withHold; //代扣
	private String exactSalary; // 实发工资
	private String memo;
	public EmployeeInfo() {
		super();
	}
	public EmployeeInfo(String id) {
		super();
		this.id = id;
	}
	public EmployeeInfo(String id, String departId, String name, String sex, int age, String duty, String degree,
			String birthday, String status, String comeTime, String state, String beseSalary, String bonus,
			String withHold, String exactSalary, String memo) {
		super();
		this.id = id;
		this.departId = departId;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.duty = duty;
		this.degree = degree;
		this.birthday = birthday;
		this.status = status;
		this.comeTime = comeTime;
		this.state = state;
		this.beseSalary = beseSalary;
		this.bonus = bonus;
		this.withHold = withHold;
		this.exactSalary = exactSalary;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComeTime() {
		return comeTime;
	}
	public void setComeTime(String comeTime) {
		this.comeTime = comeTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBeseSalary() {
		return beseSalary;
	}
	public void setBeseSalary(String beseSalary) {
		this.beseSalary = beseSalary;
	}
	public String getBonus() {
		return bonus;
	}
	public void setBonus(String bonus) {
		this.bonus = bonus;
	}
	public String getWithHold() {
		return withHold;
	}
	public void setWithHold(String withHold) {
		this.withHold = withHold;
	}
	public String getExactSalary() {
		return exactSalary;
	}
	public void setExactSalary(String exactSalary) {
		this.exactSalary = exactSalary;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	

}

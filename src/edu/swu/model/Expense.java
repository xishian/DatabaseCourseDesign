package edu.swu.model;

public class Expense {
	
	private String ExpenseID;
	private String MateID;
	private String MateName;
	private String MateUnitPrice;
	private String MateAmount;
	private String MateToPrice;
	private String BuyTime;
	private String StaffID;
	private String Memo;
	
	public Expense() {
		super();
	}
	public Expense(String expenseID) {
		super();
		ExpenseID = expenseID;
	}
	
	public Expense(String expenseID, String mateID, String mateName, String mateUnitPrice,
			String mateAmount, String MateToPrice, String buyTime, String staffID, String memo) {
		super();
		ExpenseID = expenseID;
		MateID = mateID;
		MateName = mateName;
		MateUnitPrice = mateUnitPrice;
		MateAmount = mateAmount;
		this.MateToPrice = MateToPrice;
		BuyTime = buyTime;
		StaffID = staffID;
		Memo = memo;
	}
	public String getExpenseID() {
		return ExpenseID;
	}
	public void setExpenseID(String expenseID) {
		ExpenseID = expenseID;
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
	public String getMateAmount() {
		return MateAmount;
	}
	public void setMateAmount(String mateAmount) {
		MateAmount = mateAmount;
	}
	public String getMateToPrice() {
		return MateToPrice;
	}
	public void setMateToPrice(String mateToPrice) {
		MateToPrice = mateToPrice;
	}
	public String getBuyTime() {
		return BuyTime;
	}
	public void setBuyTime(String buyTime) {
		BuyTime = buyTime;
	}
	public String getStaffID() {
		return StaffID;
	}
	public void setStaffID(String staffID) {
		StaffID = staffID;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	
	
}

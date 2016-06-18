package edu.swu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.swu.model.ClientInfo;
import edu.swu.model.DepartInfo;
import edu.swu.model.EmployeeInfo;
import edu.swu.model.Expense;
import edu.swu.model.Mate;
import edu.swu.model.MateHouse;
import edu.swu.model.Order;
import edu.swu.model.ProdHouse;
import edu.swu.model.ProductInfo;
import edu.swu.model.Rank;
import edu.swu.model.Reim;
import edu.swu.model.SalesAmount;
import edu.swu.model.User;

//连接数据库db_ESMS的数据库访问对象，封装了一系列数据库访问操作，可以连接sql server数据库
//以获得数据，也可以为其他对象提供执行sql语句的连接对象

public class EsmsDAO {

	private static final String driverClassname = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String dbUrl = "jdbc:sqlserver://localhost:1433;" + "DatabaseName=db_ESMS;SelectMethod=Cursor";
	private static String userName = "sa";
	private static String userPass = "13659.";

	public static Connection conn = null;

	static {
		try {
			if (conn == null) {
				Class.forName(driverClassname).newInstance();
				conn = DriverManager.getConnection(dbUrl, userName, userPass);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}

	public static User getUser(String name, String pass) throws SQLException {
		String sql = "select * from tb_users where User_Name='"+name+"' and User_Pass='"+pass+"'";
		ResultSet rs = query(sql);
		if(rs.next())
			return new User(name,pass);
		else 
			return null;
	}

	public static boolean addClient(ClientInfo client) {
		// 将client加入到数据库中
		if (client == null)
			return false;
		String temp = "insert into Clientinfo values('" + client.getId() + "','" + client.getName() + "','"
				+ client.getSex() + "','" + client.getDepart() + "','" + client.getSite() + "','" + client.getTel()
				+ "','" + client.getMail() + "')";
		System.out.println(temp);
		return insert(temp);

	}

	public static boolean addOrder(Order order) {
		// 将订单添加到数据库中
		if (order == null)
			return false;
		return insert(
				"insert into Orders values('" + order.getId() + "','" + order.getClientId() + "','" + order.getProdId()
						+ "','" + order.getProdName() + "','" + order.getProdAmount() + "','" + order.getIndentTime()
						+ "','" + order.getPickTime() + "','" + order.getStaffId() + "','" + order.getMemo() + "')");
	}

	public static boolean addSale(SalesAmount sale) {
		// 将销售记录添加到数据库中
		if (sale == null)
			return false;
		return insert("insert into Sales_Amount values('" + sale.getId() + "','" + sale.getClientId() + "','"
				+ sale.getProdName() + "','" + sale.getUnitPrice() + "','" + sale.getProdAmount() + "','"
				+ sale.getProdToprice() + "','" + sale.getSellTime() + "','" + sale.getStaffId() + "','"
				+ sale.getMemo() + "')");
	}

	public static boolean addMateHouse(MateHouse mateHouse) {
		// 将销售记录添加到数据库中
		if (mateHouse == null)
			return false;
		return insert("insert into tb_mate_house values('" + mateHouse.getMateHouseID() + "','" + mateHouse.getStaffID()
				+ "','" + mateHouse.getMateHouseMstock() + "','" + mateHouse.getMateAmountNow() + "','"
				+ mateHouse.getMateAmountLost() + "')");
	}

	public static boolean addMate(Mate mate) {
		// 将销售记录添加到数据库中
		if (mate == null)
			return false;
		return insert("insert into tb_mate values('" + mate.getMateID() + "','" + mate.getMateName() + "','"
				+ mate.getMateUnitPrice() + "','" + mate.getMateSpec() + "','" + mate.getMateHouseID() + "','"
				+ mate.getMateDesc() + "')");
	}

	public static boolean addExpense(Expense exp) {
		// 将销售记录添加到数据库中
		if (exp == null)
			return false;
		return insert("insert into tb_expense values('" + exp.getExpenseID() + "','" + exp.getMateID() + "','"
				+ exp.getMateName() + "','" + exp.getMateUnitPrice() + "','" + exp.getMateAmount() + "','"
				+ exp.getMateToPrice() + "','" + exp.getBuyTime() + "','" + exp.getStaffID() + "','" + exp.getMemo()
				+ "')");
	}

	public static boolean addReim(Reim reim) {
		// 将销售记录添加到数据库中
		if (reim == null)
			return false;
		return insert("insert into Reim values('" + reim.getId() + "','" + reim.getDepartId() + "','"
				+ reim.getReimTime() + "','" + reim.getAmount() + "','" + reim.getStaffId() + "','" + reim.getReason()
				+ "','" + reim.getMemo() + "')");
	}

	public static boolean addEmployee(EmployeeInfo emp) {
		// 将销售记录添加到数据库中
		if (emp == null)
			return false;
		return insert("insert into Employeeinfo values('" + emp.getId() + "','" + emp.getDepartId() + "','"
				+ emp.getName() + "','" + emp.getSex() + "','" + emp.getAge() + "','" + emp.getDuty() + "','"
				+ emp.getDegree() + "','" + emp.getBirthday() + "','" + emp.getStatus() + "','" + emp.getComeTime()
				+ "','" + emp.getState() + "','" + emp.getBeseSalary() + "','" + emp.getBonus() + "','"
				+ emp.getWithHold() + "','" + emp.getExactSalary() + "','" + emp.getMemo() + "')");
	}

	public static boolean addDepart(DepartInfo dep) {
		// 将销售记录添加到数据库中
		if (dep == null)
			return false;
		return insert("insert into Departinfo values('" + dep.getId() + "','" + dep.getName() + "','" + dep.getStaffId()
				+ "','" + dep.getNumber() + "','" + dep.getDuty() + "')");
	}

	public static boolean addProd(ProductInfo prod) {
		// 将销售记录添加到数据库中
		if (prod == null)
			return false;
		return insert("insert into tb_prod values('" + prod.getProdID() + "','" + prod.getProdName() + "','"
				+ prod.getProdUnitPrice() + "','" + prod.getProdCost() + "','" + prod.getProdDesc() + "','"
				+ prod.getProdHouseID() + "')");
	}

	public static boolean addProdHouse(ProdHouse prod) {
		// 将销售记录添加到数据库中
		if (prod == null)
			return false;
		return insert("insert into tb_prod_house values('" + prod.getProdHouseID() + "','" + prod.getStaffID() + "','"
				+ prod.getProdHouseMstock() + "','" + prod.getProdAmountNow() + "','" + prod.getProdAmountLost()
				+ "')");
	}

	public static boolean addRank(Rank rank) {
		// 将销售记录添加到数据库中
		if (rank == null)
			return false;
		return insert("insert into tb_rank values('" + rank.getRankID() + "','" + rank.getRankName() + "','"
				+ rank.getMateID() + "','" + rank.getMateConsume() + "')");
	}

	// 下面是给combo box填充数据要用的函数
	public static List<String> getList(String tbName, String idName) throws SQLException {
		List<String> list = new ArrayList<String>();
		String sql = "select " + idName + " from " + tbName;
		ResultSet rs = query(sql);
		while (rs.next()) {
			// 第一列是id
			list.add(rs.getString(1));
		}
		return list;
	}

	// 重载
	public static List<String> getList(String tbName, String idName, String name) throws SQLException {
		List<String> list = new ArrayList<String>();
		String sql = "select " + idName + "," + name + " from " + tbName;
		ResultSet rs = query(sql);
		while (rs.next()) {
			// 第一列是id,第二列是名字（名字对人来说才是有意义的）
			list.add(rs.getString(1) + " " + rs.getString(2));
		}
		return list;
	}

	public static ClientInfo getClientInfo(String name) throws SQLException {
		ClientInfo client = new ClientInfo();
		String sql = "select * from Clientinfo where Client_Name='" + name + "'";
		ResultSet rs = query(sql);
		if (rs.next()) {
			client.setId(rs.getString(1));
			client.setName(rs.getString(2));
			client.setSex(rs.getString(3));
			client.setDepart(rs.getString(4));
			client.setSite(rs.getString(5));
			client.setTel(rs.getString(6));
			client.setMail(rs.getString(7));
		}
		return client;
	}

	public static SalesAmount getSaleInfos(String selectedItem) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	// 执行一个insert语句
	public static boolean insert(String sql) {
		boolean result = false;
		try {
			Statement stmt = conn.createStatement();
			result = stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 执行一个select语句
	public static ResultSet query(String sql) {
		if (conn == null)
			return null;
		ResultSet rs = null;
		try {
			Statement stmt = null;
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	// update和delete本质上是相同的，因此执行SQL的语句也是相同的
	public static int update(String sql) {
		int result = 0;
		try {
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static int update(String tbName, String clsName, Object o) {
		if (clsName.compareTo("ClientInfo") == 0) {
			delete(tbName, "Client_ID", ((ClientInfo) o).getId());
			addClient((ClientInfo) o);
		} else if (clsName.compareTo("DepartInfo") == 0) {
			delete(tbName, "Depart_ID", ((DepartInfo) o).getId());
			addDepart((DepartInfo) o);
		} else if (clsName.compareTo("EmplyeeInfo") == 0) {
			delete(tbName, "Staff_ID", ((EmployeeInfo) o).getId());
			addEmployee((EmployeeInfo) o);
		} else if (clsName.compareTo("Expense") == 0) {
			delete(tbName, "Expense_ID", ((Expense) o).getExpenseID());
			addExpense((Expense) o);
		} else if (clsName.compareTo("Mate") == 0) {
			delete(tbName, "Mate_ID", ((Mate) o).getMateID());
			addMate((Mate) o);
		} else if (clsName.compareTo("MateHouse") == 0) {
			delete(tbName, "Mate_house_ID", ((MateHouse) o).getMateHouseID());
			addMateHouse((MateHouse) o);
		} else if (clsName.compareTo("Order") == 0) {
			delete(tbName, "Indent_ID", ((Order) o).getId());
			addOrder((Order) o);
		} else if (clsName.compareTo("ProdHouse") == 0) {
			delete(tbName, "Prod_house_ID", ((ProdHouse) o).getProdHouseID());
			addProdHouse((ProdHouse) o);
		} else if (clsName.compareTo("ProductInfo") == 0) {
			delete(tbName, "Prod_ID", ((ProductInfo) o).getProdID());
			addProd((ProductInfo) o);
		} else if (clsName.compareTo("Rank") == 0) {
			delete(tbName, "Rank", ((Rank) o).getRankID());
			addRank((Rank) o);
		} else if (clsName.compareTo("Reim") == 0) {
			delete(tbName, "Reim_ID", ((Reim) o).getId());
			addReim((Reim) o);
		} else if (clsName.compareTo("SalesAmount") == 0) {
			delete(tbName, "Sale_ID", ((SalesAmount) o).getId());
			addSale((SalesAmount) o);
		}
		return 1;
	}

	public static int delete(String sql) {
		return update(sql);
	}

	public static int delete(String tbName, String idName, String id) {
		String sql = "delete " + tbName + " where " + idName + "='" + id + "'";
		return delete(sql);
	}

}

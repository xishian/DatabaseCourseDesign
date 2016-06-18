package edu.swu.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.swu.dao.EsmsDAO;
import edu.swu.model.Expense;

//import model.TbExpense;
public class BuyMatExpModDelPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8130783292858842868L;
	private JTextField ExpenseID;
	private JTextField MateID;
	private JTextField MateName;
	private JTextField MateUnitPrice;
	private JTextField MateAmount;
	private JTextField MateToPrice;
	private JTextField BuyTime;
	private JTextField StaffID;
	private JTextField Memo;
	@SuppressWarnings("rawtypes")
	private JComboBox Expense;
	private JButton modifyButton;
	private JButton delButton;
	@SuppressWarnings("rawtypes")
	public BuyMatExpModDelPanel() {
		super();
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);
			
		setupComponet(new JLabel("材料支出编号："),0,0,1,0,false);
		ExpenseID = new JTextField();
		setupComponet(ExpenseID, 1, 0, 1, 100, true);
		
		setupComponet(new JLabel("材料编号："), 2, 0, 1, 0, false); 
		MateID = new JTextField();
		setupComponet(MateID, 3, 0, 1, 100, true);

		setupComponet(new JLabel("材料名称："), 0, 1, 1, 0, false);
		MateName = new JTextField();
		setupComponet(MateName, 1, 1, 1,100, true);
//		MateUnitPrice.addKeyListener(new InputKeyListener());
		
		setupComponet(new JLabel("材料单价："), 2, 1, 1, 0, false);
		MateUnitPrice = new JTextField();
		setupComponet(MateUnitPrice, 3, 1, 1, 100, true);

		setupComponet(new JLabel("材料数量："), 0, 2, 1, 0, false);
		MateAmount = new JTextField();
		setupComponet(MateAmount, 1, 2, 1, 100, true);
//		MateHouseID.addKeyListener(new InputKeyListener());
		
		setupComponet(new JLabel("材料总价："), 2, 2, 1, 0, false);
		MateToPrice = new JTextField();
		setupComponet(MateToPrice, 3, 2, 1, 100, true);
		
		setupComponet(new JLabel("购买时间："), 0, 3, 1, 0, false);
		BuyTime = new JTextField();
		setupComponet(BuyTime, 1, 3, 1, 100, true);
		
		setupComponet(new JLabel("处理员工编号："), 2, 3, 1, 0, false);
		StaffID = new JTextField();
		setupComponet(StaffID, 3, 3, 1, 100, true);
		
		setupComponet(new JLabel("备注："), 0, 4, 1, 0, false);
		Memo = new JTextField();
		setupComponet(Memo, 1, 4, 3, 350, true);

		setupComponet(new JLabel("选择消费项目："), 0, 7, 1, 0, false);
		Expense = new JComboBox();
		Expense.setPreferredSize(new Dimension(230, 21));
		//
		initComboBox();
		Expense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 selectItem();
			}
		});
		// 定位客户信息的下拉选择框
		setupComponet(Expense, 1, 7, 2, 0, true);
		modifyButton = new JButton("修改");
		delButton = new JButton("删除");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(delButton);
		// 定位按钮
		setupComponet(panel, 3, 7, 1, 0, false);
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)Expense.getSelectedItem();
				
				if (item == null)
					return;
				int confirm = JOptionPane.showConfirmDialog(BuyMatExpModDelPanel.this, "确认删除支出信息吗？");
				 if (confirm == JOptionPane.YES_OPTION) {
					 int rs = EsmsDAO.delete("tb_expense", "Expense_ID", item);
					
				 if (rs > 0) {
					 JOptionPane.showMessageDialog(null,
					 "开销记录：" + item + "。删除成功");
					 Expense.removeItem(item);
				 }
			   }
			}
		});
		// 处理修改按钮的单击事件
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String item = (String) Expense.getSelectedItem();
				 
				 Expense purMatExp = new Expense();
				 purMatExp.setExpenseID(item);
				 purMatExp.setMateID(MateID.getText().trim());
				 purMatExp.setMateName(MateName.getText().trim());
				 purMatExp.setMateUnitPrice(MateUnitPrice.getText().trim());
				 purMatExp.setMateAmount(MateAmount.getText().trim());
				 purMatExp.setMateToPrice(MateToPrice.getText().trim());
				 purMatExp.setBuyTime(BuyTime.getText().trim());
				 purMatExp.setStaffID(StaffID.getText().trim());
				 purMatExp.setMemo(Memo.getText().trim());
				if (EsmsDAO.update("tb_expense", "Expense", purMatExp) == 1)
						JOptionPane.showMessageDialog(null, "修改完成");
				else
						JOptionPane.showMessageDialog(null, "修改失败");
				
				initComboBox();
			}
		
		});
	}

	// 初始化客户下拉选择框
	@SuppressWarnings("unchecked")
	public void initComboBox() {
		List<String> list =null;
		try {
			list = EsmsDAO.getList("tb_expense", "Expense_ID");
		} catch (SQLException e) { e.printStackTrace(); }
		Expense.removeAllItems();
		for(String item:list)
			Expense.addItem(item);
		selectItem();
	}

	// // 设置组件位置并添加到容器中
	private void setupComponet(JComponent component, int gridx, int gridy, int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		add(component, gridBagConstrains);
	}

	 private void selectItem() {
		 String selectedItem;
		 selectedItem = (String) Expense.getSelectedItem();
		 if(selectedItem == null)
				return;
		 String sql = "select * from tb_expense where Expense_ID='"+selectedItem+"'";
		 ResultSet rs = EsmsDAO.query(sql);
		 try {
			rs.next();
			ExpenseID.setText(rs.getString(1));
			MateID.setText(rs.getString(2));
			MateName.setText(rs.getString(3));
			MateUnitPrice.setText(rs.getString(4));
			MateAmount.setText(rs.getString(5));
			MateToPrice.setText(rs.getString(6));
			BuyTime.setText(rs.getString(7));
			StaffID.setText(rs.getString(8));
			Memo.setText(rs.getString(9));
		 } catch (SQLException e) {
			e.printStackTrace();
		 }
		
		 
	 }
}

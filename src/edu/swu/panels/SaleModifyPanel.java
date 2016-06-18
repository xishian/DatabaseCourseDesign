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
import edu.swu.model.SalesAmount;

public class SaleModifyPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1130964100634324575L;

	private JTextField clientId;
	private JTextField prodName;
	private JTextField unitPrice;
	private JTextField prodAmount;
	private JTextField prodToprice;
	private JTextField sellTime;
	private JTextField staffId;
	private JTextField memo;
	
	private JButton modifyButton;
	private JButton deleteButton;
	@SuppressWarnings("rawtypes")
	private JComboBox box;
	@SuppressWarnings("rawtypes")
	public SaleModifyPanel() {
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);

		final JLabel clientIdLabel = new JLabel();
		clientIdLabel.setText("客户编号：");
		setupComponet(clientIdLabel, 0, 0, 1, 0, false);
		clientId = new JTextField();
		setupComponet(clientId, 1, 0, 3, 350, true);
		
		final JLabel jc = new JLabel();
		jc.setText("产品名称：");
		setupComponet(jc, 0, 2, 1, 0, false);
		prodName = new JTextField();
		setupComponet(prodName, 1, 2, 1, 100, true);
		
		setupComponet(new JLabel("产品数量："), 2, 2, 1, 0, false);
		prodAmount = new JTextField();
		setupComponet(prodAmount, 3, 2, 1, 100, true);
		
//	youZhengBianMa.addKeyListener(new InputKeyListener());
		setupComponet(new JLabel("产品单价："), 0, 3, 1, 0, false);
		unitPrice = new JTextField();
		setupComponet(unitPrice, 1, 3, 1, 100, true);
		
		setupComponet(new JLabel("产品总价："), 2, 3, 1, 0, false);
		prodToprice = new JTextField();
		// 定位传真文本框
		setupComponet(prodToprice, 3, 3, 1, 100, true);
		
		
		setupComponet(new JLabel("销售时间："), 0, 5, 1, 0, false);
		sellTime = new JTextField();
		// 定位E-Mail文本框
		setupComponet(sellTime, 1, 5, 1, 100, true);
		setupComponet(new JLabel("处理人员编号："), 2, 5, 1, 0, false);
		staffId = new JTextField();
		// 定位银行账号文本框
		setupComponet(staffId, 3, 5, 1, 100, true);
		
		setupComponet(new JLabel("备注："), 0, 6, 1, 0, false);
		memo = new JTextField();
		// 定位银行账号文本框
		setupComponet(memo, 1, 6, 3, 100, true);

		setupComponet(new JLabel("选择收入项"), 0, 7, 1, 0, false);
		box = new JComboBox();
		box.setPreferredSize(new Dimension(230, 21));
		initComboBox();// 初始化下拉选择框
		// 处理客户信息的下拉选择框的选择事件
		box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectItem();
			}
		});
		// 定位客户信息的下拉选择框
		setupComponet(box, 1, 7, 2, 0, true);
		
		deleteButton = new JButton("删除");
		JPanel panel = new JPanel();
		
		// 定位按钮
		setupComponet(panel, 3, 7, 1, 0, false);
		// 处理删除按钮的单击事件
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)box.getSelectedItem();
				
				if (item == null)
					return;
				int confirm = JOptionPane.showConfirmDialog(null,"确认删除信销售息吗？");
				 if (confirm == JOptionPane.YES_OPTION) {
					 int rs = EsmsDAO.delete("Sales_Amount", "Sale_ID", item);
					
				 if (rs > 0) {
					 JOptionPane.showMessageDialog(null,
					 "销售记录：" + item + "。删除成功");
					 box.removeItem(item);
				 }
			   }
			}
		});
	
		modifyButton = new JButton("修改");
		panel.add(modifyButton);
		panel.add(deleteButton);
		
		
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String item = (String) box.getSelectedItem();
				 SalesAmount sale = new SalesAmount();
					sale.setId(item);
					sale.setClientId(clientId.getText().trim());
					
					sale.setMemo(memo.getText().trim());
					sale.setProdAmount(prodAmount.getText().trim());
					sale.setProdName(prodName.getText().trim());
					sale.setProdToprice(prodToprice.getText().trim());
					sale.setStaffId(staffId.getText().trim());
					sale.setUnitPrice(unitPrice.getText().trim());
					sale.setSellTime(sellTime.getText().trim());
					if (EsmsDAO.update("Sales_Amount", "SalesAmount", sale) == 1)
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
				list = EsmsDAO.getList("Sales_Amount", "Sale_ID");
			} catch (SQLException e) { e.printStackTrace(); }
			box.removeAllItems();
			for(String item:list)
				box.addItem(item);
			selectItem();
		}
	
	// 设置组件位置并添加到容器中
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
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
		 selectedItem = (String) box.getSelectedItem();
		 if(selectedItem == null)
				return;
		 String sql = "select * from Sales_Amount where Sale_ID='"+selectedItem+"'";
		 ResultSet rs = EsmsDAO.query(sql);
		 try {
			rs.next();
			clientId.setText(rs.getString(2));
			prodName.setText(rs.getString(3));
			unitPrice.setText(rs.getString(4));
			prodAmount.setText(rs.getString(5));
			prodToprice.setText(rs.getString(6));
			sellTime.setText(rs.getString(7));
			staffId.setText(rs.getString(8));
			memo.setText(rs.getString(9));
			
		 } catch (SQLException e) {
			e.printStackTrace();
		 }
		
		
	}

}

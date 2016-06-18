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
import edu.swu.model.ProductInfo;
@SuppressWarnings("serial")
public class ProdInfoModDelPanel extends JPanel {
	private JTextField ProdID;
	private JTextField ProdName;
	private JTextField ProdUnitPrice;
	private JTextField ProdCost;
	private JTextField ProdDesc;
	private JTextField ProdHouseID;
	private JButton modifyButton;
	private JButton delButton;
	@SuppressWarnings("rawtypes")
	private JComboBox prod;
	@SuppressWarnings("rawtypes")
	public ProdInfoModDelPanel() {
		super();
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);

		setupComponet(new JLabel("产品名称："), 0, 0, 1, 0, false);
		ProdName = new JTextField();
		setupComponet(ProdName, 1, 0, 3, 350, true);
		
		setupComponet(new JLabel("产品编号："), 0, 1, 1, 0, false);
		ProdID = new JTextField();
		setupComponet(ProdID, 1, 1, 1, 100, true);
		
		setupComponet(new JLabel("产品单价："), 2, 1, 1, 0, false);
		ProdUnitPrice = new JTextField();
		setupComponet(ProdUnitPrice, 3, 1, 1, 100, true);
		
		setupComponet(new JLabel("产品成本 ："), 0, 2, 1, 0, false);
		ProdCost = new JTextField();
		setupComponet(ProdCost, 1, 2, 1, 100, true);
		
		setupComponet(new JLabel("产品仓库编号："), 2, 2, 1, 0, false);
		ProdHouseID = new JTextField();
		setupComponet(ProdHouseID, 3, 2, 1, 100, true);

		setupComponet(new JLabel("产品描述："), 0, 3, 1, 0, false);
		ProdDesc = new JTextField();
		setupComponet(ProdDesc, 1, 3, 3, 350, true);

		setupComponet(new JLabel("选择产品"), 0, 7, 1, 0, false);
		prod = new JComboBox();
		prod.setPreferredSize(new Dimension(230, 21));
		initComboBox();// 初始化下拉选择框
		prod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectItem();
			}
		});
		// 定位客户信息的下拉选择框
		setupComponet(prod, 1, 7, 2, 0, true);
		modifyButton = new JButton("修改");
		delButton = new JButton("删除");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(delButton);
		// 定位按钮
		setupComponet(panel, 3, 7, 1, 0, false);
		// 处理删除按钮的单击事件
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)prod.getSelectedItem();
				
				if (item == null)
					return;
				int confirm = JOptionPane.showConfirmDialog(null, "确认删除产品信息吗？");
				 if (confirm == JOptionPane.YES_OPTION) {
					 int rs = EsmsDAO.delete("tb_prod", "Prod_ID", item.substring(0, item.indexOf(' ')));
					
				 if (rs > 0) {
					 JOptionPane.showMessageDialog(null,
					 "产品信息：" + item + "。删除成功");
					 prod.removeItem(item);
				 }
			   }
			}
		});
		// 处理修改按钮的单击事件
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String item = (String) prod.getSelectedItem();
				 ProductInfo prodInfo = new ProductInfo();
					prodInfo.setProdID(item.substring(0, item.indexOf(' ')));
					prodInfo.setProdName(ProdName.getText().trim());
					prodInfo.setProdUnitPrice(ProdUnitPrice.getText().trim());
					prodInfo.setProdCost(ProdCost.getText().trim());
					prodInfo.setProdDesc(ProdDesc.getText().trim());
					prodInfo.setProdHouseID(ProdHouseID.getText().trim());
					if (EsmsDAO.update("tb_prod", "ProductInfo", prodInfo) == 1)
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
				list = EsmsDAO.getList("tb_prod", "Prod_ID","Prod_Name");
			} catch (SQLException e) { e.printStackTrace(); }
			prod.removeAllItems();
			for(String item:list)
				prod.addItem(item);
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
		 selectedItem = (String) prod.getSelectedItem();
		 if(selectedItem == null)
				return;
		 String sql = "select * from tb_prod where Prod_ID='"
				+selectedItem.substring(0, selectedItem.indexOf(' '))+"'";
		 ResultSet rs = EsmsDAO.query(sql);
		 try {
			rs.next();
			ProdID.setText(rs.getString(1));
			ProdName.setText(rs.getString(2));
			ProdUnitPrice.setText(rs.getString(3));
			ProdCost.setText(rs.getString(4));
			ProdDesc.setText(rs.getString(5));
			ProdHouseID.setText(rs.getString(6));
			
			
		 } catch (SQLException e) {
			e.printStackTrace();
		 }
	}
}

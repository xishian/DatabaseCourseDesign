package edu.swu.panels;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.swu.dao.EsmsDAO;
import edu.swu.model.ProdHouse;

public class ProdHouseAddPanel extends JPanel {

	private static final long serialVersionUID = -3663042641698413279L;
	
	private JTextField StaffID;
	private JTextField ProdHouseMstock;
	private JTextField ProdAmountNow;
	private JTextField ProdAmountLost;
	private JButton saveButton;
	private JButton resetButton;
	public ProdHouseAddPanel() {
		super();
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);
		
		setupComponet(new JLabel("管理员工编号："), 0, 0, 1, 0, false);
		StaffID = new JTextField();
		setupComponet(StaffID, 1, 0, 3, 350, true);
		
		
		setupComponet(new JLabel("材料仓库最大库存："), 0, 1, 1, 0, false);
		ProdHouseMstock = new JTextField();
		setupComponet(ProdHouseMstock, 1, 1, 3, 350, true);
		
		setupComponet(new JLabel("现存量："), 0, 2, 1, 0, false);
		ProdAmountNow = new JTextField();
		setupComponet(ProdAmountNow, 1, 2, 1, 100, true);
		
		setupComponet(new JLabel("剩余库存量："), 2, 2, 1, 0, false);
		ProdAmountLost = new JTextField();
		setupComponet(ProdAmountLost, 3, 2, 1, 100, true);
		
		saveButton = new JButton("保存");
		// 定位保存按钮
		setupComponet(saveButton, 1, 7, 1, 0, false);
		saveButton.addActionListener(new SaveButtonActionListener());
		resetButton = new JButton("重置");
		// 定位重置按钮
		setupComponet(resetButton, 3, 7, 1, 0, false);
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				
				StaffID.setText("");
				ProdHouseMstock.setText("");
				ProdAmountNow.setText("");
				ProdAmountLost.setText("");
			}
		});
	}
	// 设置组件位置并添加到容器中
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		add(component, gridBagConstrains);
	}
	// 保存按钮的事件监听类
	private final class SaveButtonActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			if (StaffID.getText().equals("")
					|| ProdHouseMstock.getText().equals("")
					|| ProdAmountNow.getText().equals("")
					|| ProdAmountLost.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "信息不完整，请重新输入！");
				return;
			}
			
			ResultSet set = EsmsDAO.query("select max(Prod_house_ID) from tb_prod_house");
			String id = null;
			try {
				if (set != null && set.next()) {
					String sid = set.getString(1);
					if (sid == null)
						id = "1001";
					else {
						
						id = String.valueOf(Integer.parseInt(sid)+1);
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			ProdHouse prodHouse = new ProdHouse();
			prodHouse.setProdHouseID(id);
			prodHouse.setStaffID(StaffID.getText().trim());
			prodHouse.setProdHouseMstock(ProdHouseMstock.getText().trim());
			prodHouse.setProdAmountNow(ProdAmountNow.getText().trim());
			prodHouse.setProdAmountLost(ProdAmountLost.getText().trim());
			EsmsDAO.addProdHouse(prodHouse);
			JOptionPane.showMessageDialog(ProdHouseAddPanel.this, "已成功添加信息",
					"产品仓库添加信息", JOptionPane.INFORMATION_MESSAGE);
			resetButton.doClick();
		}
	}
	
}
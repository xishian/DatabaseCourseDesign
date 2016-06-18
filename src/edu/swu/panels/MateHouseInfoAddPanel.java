package edu.swu.panels;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//import edu.swu.dao.EsmsDAO;
import edu.swu.model.MateHouse;
public class MateHouseInfoAddPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4677004456369629443L;
	private JTextField MateHouseID;
	private JTextField StaffID;
	private JTextField MateHouseMstock;
	private JTextField MateAmountNow;
	private JTextField MateAmountLost;
	private JButton saveButton;
	private JButton resetButton;
	public MateHouseInfoAddPanel() {
		super();
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);
		setupComponet(new JLabel("材料仓库编号："), 0, 0, 1, 0, false);
		MateHouseID = new JTextField();
		setupComponet(MateHouseID, 1, 0, 3, 350, true);
		
		setupComponet(new JLabel("管理员工编号："), 0, 1, 1, 0, false);
		StaffID = new JTextField();
		setupComponet(StaffID, 1, 1, 1, 100, true);
		
		setupComponet(new JLabel("材料仓库最大库存："), 2, 1, 1, 0, false);
		MateHouseMstock = new JTextField();
		setupComponet(MateHouseMstock, 3, 1, 1, 100, true);
		
		setupComponet(new JLabel("现存量："), 0, 2, 1, 0, false);
		MateAmountNow = new JTextField();
		setupComponet(MateAmountNow, 1, 2, 1, 100, true);

		setupComponet(new JLabel("剩余库存量："), 2, 2, 1, 0, false);
		MateAmountLost = new JTextField();
		setupComponet(MateAmountLost, 3, 2, 1, 100, true);

		saveButton = new JButton("保存");
		setupComponet(saveButton, 1, 7, 1, 0, false);
		saveButton.addActionListener(new SaveButtonActionListener());
		resetButton = new JButton("重置");
		setupComponet(resetButton, 3, 7, 1, 0, false);
		resetButton.addActionListener(new ChongZheButtonActionListener());
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
			if (MateHouseID.getText().equals("")
					|| StaffID.getText().equals("")
					|| MateHouseMstock.getText().equals("")
					|| MateAmountNow.getText().equals("")
					|| MateAmountLost.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请填写全部信息");
				return;
			}
//			ResultSet haveUser = Dao
//					.query("select * from tb_mate_house where khname='"
//							+ keHuQuanCheng.getText().trim() + "'");
//			try {
//				if (haveUser.next()){
//					System.out.println("error");
//					JOptionPane.showMessageDialog(MateHouseInfoAddPanel.this,
//							"客户信息添加失败，存在同名客户", "客户添加信息",
//							JOptionPane.INFORMATION_MESSAGE);
//					return;
//				}
//			} catch (Exception er) {
//				er.printStackTrace();
//			}
//			ResultSet set = Dao.query("select max(id) from tb_khinfo");
//			String id = null;
//			try {
//				if (set != null && set.next()) {
//					String sid = set.getString(1);
//					if (sid == null)
//						id = "kh1001";
//					else {
//						String str = sid.substring(2);
//						id = "kh" + (Integer.parseInt(str) + 1);
//					}
//				}
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
			MateHouse matHouInfo = new MateHouse();
//			matHouInfo.setId(id);
			matHouInfo.setMateHouseID(MateHouseID.getText().trim());
			matHouInfo.setStaffID(StaffID.getText().trim());
			matHouInfo.setMateHouseMstock(MateHouseMstock.getText().trim());
			matHouInfo.setMateAmountNow(MateAmountNow.getText().trim());
			matHouInfo.setMateAmountLost(MateAmountLost.getText().trim());
//			Dao.addKeHu(matHouInfo);
			JOptionPane.showMessageDialog(MateHouseInfoAddPanel.this, "已成功添加仓库信息",
					"仓库添加信息", JOptionPane.INFORMATION_MESSAGE);
			resetButton.doClick();
		}
	}
	// 重置按钮的事件监听类
	private class ChongZheButtonActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			MateHouseID.setText("");
			StaffID.setText("");
			MateHouseMstock.setText("");
			MateAmountNow.setText("");
			MateAmountLost.setText("");
		}
	}
}
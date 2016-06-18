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
import edu.swu.model.Mate;
public class MateAddingPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6279458464335775615L;
	private JTextField MateName;
	private JTextField MateUnitPrice;
	private JTextField MateSpec;
	private JTextField MateHouseID;
	private JTextField MateDesc;
	private JButton saveButton; 
	private JButton resetButton;
	public MateAddingPanel() {
		super();
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);
		
		setupComponet(new JLabel("材料名称："), 0, 0, 1, 0, false); 
		MateName = new JTextField();
		setupComponet(MateName, 1, 0, 1, 100, true);

		setupComponet(new JLabel("材料单价："), 2, 0, 1, 0, false);
		MateUnitPrice = new JTextField();
		setupComponet(MateUnitPrice, 3, 0, 1, 100, true);
		
		setupComponet(new JLabel("材料规格："), 0, 1, 1, 0, false);
		MateSpec = new JTextField();
		setupComponet(MateSpec, 1, 1, 1, 100, true);

		setupComponet(new JLabel("材料仓库编号："), 2, 1, 1, 0, false);
		MateHouseID = new JTextField();
		setupComponet(MateHouseID, 3, 1, 1, 100, true);

		setupComponet(new JLabel("材料描述："), 0, 2, 1, 0, false);
		MateDesc = new JTextField();
		setupComponet(MateDesc, 1, 2, 3, 350, true);
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
			if (MateName.getText().trim().equals("")
					|| MateUnitPrice.getText().trim().equals("")
					|| MateHouseID.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "名称、单价、仓库编号不能为空！"
						,"Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (MateName.getText().trim().equals("")
					|| MateUnitPrice.getText().trim().equals("")
					|| MateSpec.getText().trim().equals("")
					|| MateHouseID.getText().trim().equals("")
					|| MateDesc.getText().trim().equals("")) {
				int right = JOptionPane.showConfirmDialog(null, "信息不完整！确认提交？");
				if(right==JOptionPane.OK_OPTION){}
				else
					return;
			}
			ResultSet haveUser = EsmsDAO
					.query("select * from tb_mate where Mate_Name='"
							+ MateName.getText().trim() + "'");
			try {
				if (haveUser.next()){
					System.out.println("error");
					JOptionPane.showMessageDialog(MateAddingPanel.this,
							"材料信息添加失败，存在同名材料", "材料添加信息",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			} catch (Exception er) {
				er.printStackTrace();
			}
			ResultSet set = EsmsDAO.query("select max(Mate_ID) from tb_mate");
			String id = null;
			try {
				if (set != null && set.next()) {
					String sid = set.getString(1);
					if (sid == null)
						id = "000000";
					else {
						id = String.valueOf(Integer.parseInt(sid)+1);
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			Mate mtInfo = new Mate();
			mtInfo.setMateID(id);
			mtInfo.setMateName(MateName.getText().trim());
			mtInfo.setMateUnitPrice(MateUnitPrice.getText().trim());
			mtInfo.setMateSpec(MateSpec.getText().trim());
			mtInfo.setMateHouseID(MateHouseID.getText().trim());
			mtInfo.setMateDesc(MateDesc.getText().trim());
			EsmsDAO.addMate(mtInfo);
			JOptionPane.showMessageDialog(MateAddingPanel.this, "已成功添加材料",
					"材料添加", JOptionPane.INFORMATION_MESSAGE);
			
			resetButton.doClick();
		}
	}
	// 重置按钮的事件监听类
	private class ChongZheButtonActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			MateName.setText("");
			MateUnitPrice.setText("");
			MateSpec.setText("");
			MateHouseID.setText("");
			MateDesc.setText("");
		}
	}
}
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
import edu.swu.model.Reim;
public class ReimAddPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3358670129560914118L;
	private JTextField departId;
	private JTextField reimTime;
	private JTextField amount;
	private JTextField staffId;
	private JTextField reason;
	private JTextField memo;
	private JButton saveButton;
	private JButton resetButton;
	public ReimAddPanel() {
		super();
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);
		
		setupComponet(new JLabel("部门编号："), 0, 0, 1, 0, false);
		departId = new JTextField();
		setupComponet(departId, 1, 0, 1, 100, true);
		
		setupComponet(new JLabel("报账时间："), 2, 0, 1, 0, false);
		reimTime = new JTextField();
		setupComponet(reimTime, 3, 0, 1, 0, true);
		
		setupComponet(new JLabel("报账金额："), 0, 1, 1, 0, false);
		amount = new JTextField();
		setupComponet(amount, 1, 1, 1, 100, true);
		
		setupComponet(new JLabel("处理人员编号："), 2, 1, 1, 0, false);
		staffId = new JTextField();
		setupComponet(staffId, 3, 1, 1, 100, true);
		
		setupComponet(new JLabel("报账原因："), 0, 2, 1, 0, false);
		reason = new JTextField();
		setupComponet(reason, 1, 2, 3, 350, true);
		
		setupComponet(new JLabel("备注："), 0, 3, 1, 0, false);
		memo = new JTextField();
		setupComponet(memo, 1, 3, 3, 350, true);

		saveButton = new JButton("保存");
		setupComponet(saveButton, 1, 7, 1, 0, false);
		saveButton.addActionListener(new SaveButtonActionListener());
		resetButton = new JButton("重置");
		setupComponet(resetButton, 3, 7, 1, 0, false);
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				departId.setText("");
				reimTime.setText("");
				amount.setText("");
				staffId.setText("");
				reason.setText("");
				memo.setText("");
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
			if (departId.getText().equals("")|| reimTime.getText().equals("")
				|| amount.getText().equals("")|| staffId.getText().equals("")|| reason.getText().equals("")
			){
			int right = JOptionPane.showConfirmDialog(null, "信息不完整！确认提交？");
			if(right==JOptionPane.OK_OPTION){}
			else
				return;
			}
			
//			ResultSet haveUser = EsmsDAO
//					.query("select * from Reim where Depart_ID='"
//							+ departId.getText().trim() + "'");
//			try {
//				if (haveUser.next()){
//					System.out.println("error");
//					JOptionPane.showMessageDialog(ReimAddPanel.this,
//							"信息添加失败，部门已存在", "报账信息添加",
//							JOptionPane.INFORMATION_MESSAGE);
//					return;
//				}
//			} catch (Exception er) {
//				er.printStackTrace();
//			}
			ResultSet set = EsmsDAO.query("select max(Reim_ID) from Reim");
			String id = null;
			try {
				if (set != null && set.next()) {
					String sid = set.getString(1);
					if (sid == null)
						id = "100001";
					else {
						id = String.valueOf(Integer.parseInt(sid)+1);
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			Reim reInfo = new Reim();
			reInfo.setId(id);
			reInfo.setAmount(amount.getText().trim());
			reInfo.setDepartId(departId.getText().trim());
			reInfo.setReimTime(reimTime.getText().trim());
			reInfo.setStaffId(staffId.getText().trim());
			reInfo.setReason(reason.getText().trim());
			EsmsDAO.addReim(reInfo);
			JOptionPane.showMessageDialog(ReimAddPanel.this, "已成功添加报账信息",
					"报账信息添加", JOptionPane.INFORMATION_MESSAGE);
			resetButton.doClick();
		}
	}
	
}
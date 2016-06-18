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
import edu.swu.model.Reim;
public class ReimModifyPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1897786818267060477L;
	private JTextField departId;
	private JTextField reimTime;
	private JTextField amount;
	private JTextField staffId;
	private JTextField reason;
	private JTextField memo;
	private JButton modifyButton;
	private JButton delButton;
	@SuppressWarnings("rawtypes")
	private JComboBox reim;
	@SuppressWarnings("rawtypes")
	public ReimModifyPanel() {
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

		setupComponet(new JLabel("选择客户"), 0, 7, 1, 0, false);
		reim = new JComboBox();
		reim.setPreferredSize(new Dimension(230, 21));
		initComboBox();// 初始化下拉选择框
		reim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectItem();
			}
		});
		// 定位客户信息的下拉选择框
		setupComponet(reim, 1, 7, 2, 0, true);
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
				String item = (String)reim.getSelectedItem();
				
				if (item == null)
					return;
				int confirm = JOptionPane.showConfirmDialog(null, "确认删除报账信息吗？");
				 if (confirm == JOptionPane.YES_OPTION) {
					 int rs = EsmsDAO.delete("Reim", "Reim_ID", item);
					
				 if (rs > 0) {
					 JOptionPane.showMessageDialog(null,
					 "报账记录：" + item + "。删除成功");
					 reim.removeItem(item);
				 }
			   }
			}
		});
		// 处理修改按钮的单击事件
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String item = (String) reim.getSelectedItem();
				 Reim reimInfo = new Reim();
				 reimInfo.setAmount(amount.getText().trim());
				 reimInfo.setDepartId(departId.getText().trim());
				 reimInfo.setId(item);
				 reimInfo.setMemo(memo.getText().trim());
				 reimInfo.setReason(reason.getText().trim());
				 reimInfo.setReimTime(reimTime.getText().trim());
				 reimInfo.setStaffId(staffId.getText().trim());
				
					if (EsmsDAO.update("Reim", "Reim_ID", reimInfo) == 1)
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
				list = EsmsDAO.getList("Reim", "Reim_ID");
			} catch (SQLException e) { e.printStackTrace(); }
			reim.removeAllItems();
			for(String item:list)
				reim.addItem(item);
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
		 selectedItem = (String) reim.getSelectedItem();
		 if(selectedItem == null)
				return;
		 String sql = "select * from Reim where Reim_ID='"+selectedItem+"'";
		 ResultSet rs = EsmsDAO.query(sql);
		 try {
			rs.next();
			departId.setText(rs.getString(2));
			reimTime.setText(rs.getString(3));
			amount.setText(rs.getString(4));
			staffId.setText(rs.getString(5));
			reason.setText(rs.getString(6));
			memo.setText(rs.getString(7));
			
		 } catch (SQLException e) {
			e.printStackTrace();
		 }
	}
}

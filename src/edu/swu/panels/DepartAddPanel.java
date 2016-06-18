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
import edu.swu.model.DepartInfo;
public class DepartAddPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1644935147542879532L;
	private JTextField name;
	private JTextField staffId;
	private JTextField duty;
	private JTextField number;
	private JButton saveButton; 
	private JButton resetButton;
	public DepartAddPanel() {
		super();
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);
		
		setupComponet(new JLabel("部门名称："), 0, 0, 1, 0, false);
		name = new JTextField();
		setupComponet(name, 1, 0, 3, 350, true);
		
		setupComponet(new JLabel("主管编号："), 0, 1, 1, 0, false);
		staffId = new JTextField();
		setupComponet(staffId, 1, 1, 1, 100, true);
		
		setupComponet(new JLabel("部门人数："), 2, 1, 1, 0, false);
		number = new JTextField();
		setupComponet(number, 3, 1, 1, 100, true);
		
		setupComponet(new JLabel("部门职责："), 0, 2, 1, 0, false);
		duty = new JTextField();
		setupComponet(duty, 1, 2, 3, 350, true);
		
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
			if (name.getText().trim().equals("")
					|| staffId.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "部门名称、主管编号未输入请重新输入！"
						,"Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (name.getText().equals("")
					|| staffId.getText().equals("")
					|| duty.getText().equals("")
					|| number.getText().equals("")) {
				int right = JOptionPane.showConfirmDialog(null, "信息不完整！确认提交？");
				if(right==JOptionPane.OK_OPTION){}
				else
					return;
			}
			ResultSet haveUser = EsmsDAO
					.query("select * from Departinfo where Depart_Name='"
							+ name.getText().trim() + "'");
			try {
				if (haveUser.next()){
					System.out.println("error");
					JOptionPane.showMessageDialog(DepartAddPanel.this,
							"部门信息添加失败，存在同名部门", "部门添加信息",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			} catch (Exception er) {
				er.printStackTrace();
			}
			ResultSet set =  EsmsDAO.query("select max(Depart_ID) from Departinfo");
			String id = null;
			try {
				if (set != null && set.next()) {
					String sid = set.getString(1);
					if (sid == null)
						id = "100000";
					else {
						id = String.valueOf(Integer.parseInt(sid)+1);
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			DepartInfo deInfo = new DepartInfo();
			deInfo.setId(id);
			deInfo.setName(name.getText().trim());
			deInfo.setStaffId(staffId.getText().trim());
			deInfo.setDuty(duty.getText().trim());
			deInfo.setNumber(Integer.parseInt(number.getText().trim()));
			EsmsDAO.addDepart(deInfo);
			JOptionPane.showMessageDialog(DepartAddPanel.this, "已成功添加部门",
					"部门添加信息", JOptionPane.INFORMATION_MESSAGE);
			resetButton.doClick();
		}
	}
	// 重置按钮的事件监听类
	private class ChongZheButtonActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			name.setText("");
			staffId.setText("");
			duty.setText("");
			number.setText("");
		}
	}
}

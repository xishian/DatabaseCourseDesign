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
import edu.swu.model.EmployeeInfo;

public class StaffAddPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8482826929237627L;
	private JTextField departId;
	private JTextField name;
	private JTextField sex;
	private JTextField age;
	private JTextField duty;
	private JTextField degree;
	
	private JTextField birthday;
	private JTextField status;
	private JTextField comeTime;
	private JTextField state;
	private JTextField baseSalary;
	private JTextField bonus;
	
	private JTextField withHold;
	private JTextField realPay;
	private JTextField memo;

	private JButton resetButton;
	
	public StaffAddPanel() {
		super();
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);
		
		//布局
		
		final JLabel staffName = new JLabel();
		staffName.setText("员工姓名：");
		setupComponet(staffName, 0, 0, 1, 0, false);
		
		name = new JTextField();
		setupComponet(name, 1, 0, 1, 100, true);
		
		
		final JLabel addressLabel = new JLabel("部门编号：");
		setupComponet(addressLabel, 2, 0, 1, 0, false);
		departId = new JTextField();
		
		setupComponet(departId, 3, 0, 1,100, true);
		
		
		final JLabel jc = new JLabel();
		jc.setText("员工性别：");
		setupComponet(jc, 0, 2, 1, 0, false);
		sex = new JTextField();
		
		setupComponet(sex, 1, 2, 1, 100, true);
		
		setupComponet(new JLabel("年龄："), 2, 2, 1, 0, false);
		age = new JTextField();
		
		setupComponet(age, 3, 2, 1, 100, true);
	
		
		setupComponet(new JLabel("职责："), 0, 3, 1, 0, false);
		duty = new JTextField();
		setupComponet(duty, 1, 3, 1, 100, true);
		
		
		setupComponet(new JLabel("学历："), 2, 3, 1, 0, false);
		degree = new JTextField();
		setupComponet(degree, 3, 3, 1, 100, true);
		
		setupComponet(new JLabel("生日："), 0,4, 1, 0, false);
		birthday = new JTextField();
		setupComponet(birthday, 1,4, 1, 100, true);
		
		
		setupComponet(new JLabel("政治面貌："), 2,4, 1, 0, false);
		status = new JTextField();
		setupComponet(status, 3, 4, 1, 100, true);
		
		setupComponet(new JLabel("进入时间："), 0, 5, 1, 0, false);
		comeTime = new JTextField();
		setupComponet(comeTime, 1, 5,1, 100, true);
		
		
		setupComponet(new JLabel("目前职位："), 2, 5, 1, 0, false);
		state = new JTextField();
		setupComponet(state, 3,5, 1, 100, true);
		
		setupComponet(new JLabel("基本工资："), 0, 6, 1, 0, false);
		baseSalary = new JTextField();
		setupComponet(baseSalary, 1, 6, 1, 100, true);
		
		
		setupComponet(new JLabel("奖金："), 2, 6, 1, 0, false);
		bonus = new JTextField();
		setupComponet(bonus, 3, 6, 1, 100, true);
		
		setupComponet(new JLabel("扣除："), 0, 7, 1, 0, false);
		withHold = new JTextField();
		setupComponet(withHold, 1, 7, 1, 100, true);
		
		
		setupComponet(new JLabel("实际工资："), 2, 7, 1, 0, false);
		realPay = new JTextField();
		setupComponet(realPay, 3, 7, 1, 100, true);
		
		setupComponet(new JLabel("备注："), 0, 8, 1, 0, false);
		memo = new JTextField();
		setupComponet(memo, 1, 8, 3, 350, true);
		
		
		
		final JButton saveButton = new JButton("保存");
		setupComponet(saveButton, 1, 9, 1, 0, false);
		saveButton.addActionListener(new SaveActionListener());
		
		resetButton = new JButton("清空");
		setupComponet(resetButton, 3, 9, 1, 0, false);
		resetButton.addActionListener(new ActionListener() {
			//将所有内容设为空值
			@Override
			public void actionPerformed(ActionEvent e) {
				departId.setText("");
				name.setText("");
				sex.setText("");
				age.setText("");
				duty.setText("");
				degree.setText("");
				birthday.setText("");
				status.setText("");
				comeTime.setText("");
				state.setText("");
				baseSalary.setText("");
				bonus.setText("");
				withHold.setText("");
				realPay.setText("");
				memo.setText("");
			}
		});
		
	}


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
	
	// 代码过长，所以分开来写
	private final class SaveActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			
			if (departId.getText().trim().equals("")
					|| name.getText().trim().equals("")
					|| sex.getText().trim().equals("")
					|| age.getText().trim().equals("")
					|| duty.getText().trim().equals("")
					|| degree.getText().trim().equals("")
					|| birthday.getText().trim().equals("")
					|| status.getText().trim().equals("")
					|| comeTime.getText().trim().equals("")
					|| state.getText().trim().equals("")
					|| baseSalary.getText().trim().equals("")
					|| bonus.getText().trim().equals("")
					|| withHold.getText().trim().equals("")
					|| realPay.getText().trim().equals("")
					|| memo.getText().trim().equals("")
				) {
				JOptionPane.showMessageDialog(null, "信息不完整，请继续输入！");
				return;
				
			}
			
			
			
			
			ResultSet set = EsmsDAO.query("select max(Staff_ID) from Employeeinfo");
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
			
			EmployeeInfo info = new  EmployeeInfo();
			info.setAge(Integer.parseInt(age.getText().trim()));
			info.setBeseSalary(baseSalary.getText().trim());
			info.setBirthday(birthday.getText().trim());
			info.setBonus(bonus.getText().trim());
			info.setComeTime(comeTime.getText().trim());
			info.setDegree(degree.getText().trim());
			info.setDepartId(departId.getText().trim());
			info.setDuty(duty.getText().trim());
			info.setWithHold(withHold.getText().trim());
			info.setStatus(status.getText().trim());
			info.setSex(sex.getText().trim());
			info.setState(state.getText().trim());
			info.setName(name.getText().trim());
			info.setMemo(memo.getText().trim());
			info.setId(id);
			info.setExactSalary(realPay.getText().trim());
			
			EsmsDAO.addEmployee(info);
			JOptionPane.showMessageDialog(null, "已成功添加员工信息",
					"添加信息", JOptionPane.INFORMATION_MESSAGE);
		
			resetButton.doClick();
		}
	}
}

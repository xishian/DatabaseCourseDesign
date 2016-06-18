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
import edu.swu.model.DepartInfo;
public class DepartModifyPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2010057584941956544L;
	private JTextField name;
	private JTextField staffId;
	private JTextField duty;
	private JTextField number;
	private JButton modifyButton; 
	private JButton delButton;
	@SuppressWarnings("rawtypes")
	private JComboBox depart;
	@SuppressWarnings("rawtypes")
	public DepartModifyPanel() {
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

		setupComponet(new JLabel("选择部门"), 0, 7, 1, 0, false);
		depart = new JComboBox();
		depart.setPreferredSize(new Dimension(230, 21));
		initComboBox();// 初始化下拉选择框
		depart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectItem();
			}
		});
		setupComponet(depart, 1, 7, 2, 0, true);
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
				String item = (String)depart.getSelectedItem();
				
				if (item == null)
					return;
				int confirm = JOptionPane.showConfirmDialog(null, "确认删除部门信息吗？");
				 if (confirm == JOptionPane.YES_OPTION) {
					 int rs = EsmsDAO.delete("Departinfo", "Depart_ID", item.substring(0, item.indexOf(' ')));
					
				 if (rs > 0) {
					 JOptionPane.showMessageDialog(null,
					 "部门：" + item + "。删除成功");
					 depart.removeItem(item);
				 }
			   }
			}
		});

		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String) depart.getSelectedItem();
				DepartInfo deInfo = new DepartInfo();
				deInfo.setId(item.substring(0, item.indexOf(' ')));
				deInfo.setName(name.getText().trim());
				deInfo.setStaffId(staffId.getText().trim());
				deInfo.setDuty(duty.getText().trim());
				deInfo.setNumber(Integer.parseInt(number.getText().trim()));
				if (EsmsDAO.update("Departinfo", "DepartInfo", deInfo) == 1)
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
			list = EsmsDAO.getList("Departinfo", "Depart_ID","Depart_Name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		depart.removeAllItems();
		
		for(String item:list){
			depart.addItem(item);
		}
			
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
		 selectedItem = (String) depart.getSelectedItem();
		 if(selectedItem == null)
				return;
		 String sql = "select * from Departinfo where Depart_ID='"
				+selectedItem.substring(0, selectedItem.indexOf(' '))+"'";
		 ResultSet rs = EsmsDAO.query(sql);
		 try {
			rs.next();
			//1是id，不需要
			name.setText(rs.getString(2));
			staffId.setText(rs.getString(3));
			number.setText(rs.getString(4));
			duty.setText(rs.getString(5));
		
		 } catch (SQLException e) {
			e.printStackTrace();
		 }
		
	}
}


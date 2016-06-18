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
import edu.swu.model.EmployeeInfo;

public class StaffModifyPanel extends JPanel {

	private static final long serialVersionUID = -1062761194620742776L;
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

	private JButton modifyButton;
	private JButton deleteButton;
	private JComboBox<String> staffs;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public StaffModifyPanel() {
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);
		
		final JLabel staffName = new JLabel();
		staffName.setText("Ա��������");
		setupComponet(staffName, 0, 0, 1, 0, false);
		
		name = new JTextField();
		setupComponet(name, 1, 0, 1, 100, true);
		
		
		final JLabel addressLabel = new JLabel("���ű�ţ�");
		setupComponet(addressLabel, 2, 0, 1, 0, false);
		departId = new JTextField();
		
		setupComponet(departId, 3, 0, 1,100, true);
		
		
		final JLabel jc = new JLabel();
		jc.setText("Ա���Ա�");
		setupComponet(jc, 0, 2, 1, 0, false);
		sex = new JTextField();
		
		setupComponet(sex, 1, 2, 1, 100, true);
		
		setupComponet(new JLabel("���䣺"), 2, 2, 1, 0, false);
		age = new JTextField();
		setupComponet(age, 3, 2, 1, 100, true);
	
		
		setupComponet(new JLabel("ְ��"), 0, 3, 1, 0, false);
		duty = new JTextField();
		setupComponet(duty, 1, 3, 1, 100, true);
		
		
		setupComponet(new JLabel("ѧ����"), 2, 3, 1, 0, false);
		degree = new JTextField();
		setupComponet(degree, 3, 3, 1, 100, true);
		
		setupComponet(new JLabel("���գ�"), 0,4, 1, 0, false);
		birthday = new JTextField();
		setupComponet(birthday, 1,4, 1, 100, true);
		
		
		setupComponet(new JLabel("������ò��"), 2,4, 1, 0, false);
		status = new JTextField();
		setupComponet(status, 3, 4, 1, 100, true);
		
		setupComponet(new JLabel("����ʱ�䣺"), 0, 5, 1, 0, false);
		comeTime = new JTextField();
		setupComponet(comeTime, 1, 5,1, 100, true);
		
		
		setupComponet(new JLabel("�����"), 2, 5, 1, 0, false);
		state = new JTextField();
		setupComponet(state, 3,5, 1, 100, true);
		
		setupComponet(new JLabel("�������ʣ�"), 0, 6, 1, 0, false);
		baseSalary = new JTextField();
		setupComponet(baseSalary, 1, 6, 1, 100, true);
		
		
		setupComponet(new JLabel("����"), 2, 6, 1, 0, false);
		bonus = new JTextField();
		setupComponet(bonus, 3, 6, 1, 100, true);
		
		setupComponet(new JLabel("�۳���"), 0, 7, 1, 0, false);
		withHold = new JTextField();
		setupComponet(withHold, 1, 7, 1, 100, true);
		
		
		setupComponet(new JLabel("ʵ�ʹ��ʣ�"), 2, 7, 1, 0, false);
		realPay = new JTextField();
		setupComponet(realPay, 3, 7, 1, 100, true);
		
		setupComponet(new JLabel("��ע��"), 0, 8, 1, 0, false);
		memo = new JTextField();
		setupComponet(memo, 1, 8, 3, 350, true);

		setupComponet(new JLabel("ѡ��ͻ�"), 0, 9, 1, 0, false);
		staffs = new JComboBox();
		staffs.setPreferredSize(new Dimension(230, 21));
		initComboBox();

		staffs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectItem();
			}
		});
		setupComponet(staffs, 1, 9, 2, 0, true);
		modifyButton = new JButton("�޸�");
		deleteButton = new JButton("ɾ��");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(deleteButton);
		setupComponet(panel, 3,9, 1, 0, false);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = (String)staffs.getSelectedItem();
				if(temp == null)
					return ;
				int confirm = JOptionPane.showConfirmDialog(
						null, "ȷ��ɾ��Ա����Ϣ��");
				if (confirm == JOptionPane.YES_OPTION) {
					int rs = EsmsDAO.delete("Employeeinfo", "Staff_ID", temp.substring(0, temp.indexOf(' ')));
					if (rs > 0) {
						JOptionPane.showMessageDialog(null,
								"Ա����Ϣ��" + temp.substring(temp.indexOf(' ')+1)+ "ɾ���ɹ�");
						
						staffs.removeItem(temp);
					}
				}
			}
		});
		// �����޸İ�ť�ĵ����¼�
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = (String)staffs.getSelectedItem();
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
				info.setId(temp.substring(0, temp.indexOf(' ')));
				info.setExactSalary(realPay.getText().trim());
				if (EsmsDAO.update("Employeeinfo", "EmployeeInfo", info) == 1)
					JOptionPane.showMessageDialog(StaffModifyPanel.this, "�޸����");
				else
					JOptionPane.showMessageDialog(StaffModifyPanel.this, "�޸�ʧ��");
				initComboBox();
				
			}
		});
	}
	// ��ʼ���ͻ�����ѡ���
	public void initComboBox() {
		List<String> list = null;
		try {
			list = EsmsDAO.getList("Employeeinfo", "Staff_ID", "Name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		staffs.removeAllItems();
		
		for(String item:list){
			staffs.addItem(item);
		}
			
		selectItem();
	}
	// �������λ�ò���ӵ�������
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
		 selectedItem = (String) staffs.getSelectedItem();
		 if(selectedItem == null)
				return;
		 String sql = "select * from Employeeinfo where Staff_ID='"
				+selectedItem.substring(0, selectedItem.indexOf(' '))+"'";
		 ResultSet rs = EsmsDAO.query(sql);
		 try {
			rs.next();
			//1��id������Ҫ
			departId.setText(rs.getString(2));
			name.setText(rs.getString(3));
			sex.setText(rs.getString(4));
			age.setText(rs.getString(5));
			duty.setText(rs.getString(6));
			degree.setText(rs.getString(7));
			birthday.setText(rs.getString(8));
			status.setText(rs.getString(9));
			comeTime.setText(rs.getString(10));
			state.setText(rs.getString(11));
			baseSalary.setText(rs.getString(12));
			bonus.setText(rs.getString(13));
			withHold.setText(rs.getString(14));
			realPay.setText(rs.getString(15));
			memo.setText(rs.getString(16));
			
		 } catch (SQLException e) {
			e.printStackTrace();
		 }
	}
}

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
import edu.swu.model.MateHouse;
public class MateHouseInfoModDelPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1189809267602590761L;
	private JTextField MateHouseID;
	private JTextField StaffID;
	private JTextField MateHouseMstock;
	private JTextField MateAmountNow;
	private JTextField MateAmountLost;
	private JButton modifyButton;
	private JButton delButton;
	@SuppressWarnings("rawtypes")
	private JComboBox HouseID;
	@SuppressWarnings("rawtypes")
	public MateHouseInfoModDelPanel() {
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);

		setupComponet(new JLabel("���ϲֿ��ţ�"), 0, 0, 1, 0, false);
		MateHouseID = new JTextField();
		setupComponet(MateHouseID, 1, 0, 3, 350, true);
		
		setupComponet(new JLabel("����Ա����ţ�"), 0, 1, 1, 0, false);
		StaffID = new JTextField();
		setupComponet(StaffID, 1, 1, 1, 100, true);
		
		setupComponet(new JLabel("���ϲֿ�����棺"), 2, 1, 1, 0, false);
		MateHouseMstock = new JTextField();
		setupComponet(MateHouseMstock, 3, 1, 1, 100, true);
		
		setupComponet(new JLabel("�ִ�����"), 0, 2, 1, 0, false);
		MateAmountNow = new JTextField();
		setupComponet(MateAmountNow, 1, 2, 1, 100, true);

		setupComponet(new JLabel("ʣ��������"), 2, 2, 1, 0, false);
		MateAmountLost = new JTextField();
		setupComponet(MateAmountLost, 3, 2, 1, 100, true);
		
		setupComponet(new JLabel("ѡ��ֿ��"), 0, 4, 1, 0, false);
		HouseID = new JComboBox();
		HouseID.setPreferredSize(new Dimension(230, 21));
		initComboBox();// ��ʼ������ѡ���
		HouseID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectItem();
			}
		});
		setupComponet(HouseID, 1, 4, 2, 0, true);
		modifyButton = new JButton("�޸�");
		delButton = new JButton("ɾ��");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(delButton);
		// ��λ��ť
		setupComponet(panel, 3, 4, 1, 0, false);
		
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)HouseID.getSelectedItem();
				
				if (item == null)
					return;
				int confirm = JOptionPane.showConfirmDialog(null, "ȷ��ɾ��֧����Ϣ��");
				 if (confirm == JOptionPane.YES_OPTION) {
					 int rs = EsmsDAO.delete("tb_mate_house", "Mate_house_ID", item);
					
				 if (rs > 0) {
					 JOptionPane.showMessageDialog(null,
					 "������¼��" + item + "��ɾ���ɹ�");
					 HouseID.removeItem(item);
				 }
			   }
			}
		});

		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String item = (String) HouseID.getSelectedItem();
				 MateHouse matHouInfo = new MateHouse();
				 
				matHouInfo.setMateHouseID(item);
				matHouInfo.setStaffID(StaffID.getText().trim());
				matHouInfo.setMateHouseMstock(MateHouseMstock.getText().trim());
				matHouInfo.setMateAmountNow(MateAmountNow.getText().trim());
				matHouInfo.setMateAmountLost(MateAmountLost.getText().trim());
				if (EsmsDAO.update("tb_mate_house", "MateHouse", matHouInfo) == 1)
						JOptionPane.showMessageDialog(null, "�޸����");
					else
						JOptionPane.showMessageDialog(null, "�޸�ʧ��");		
				initComboBox();
			}
		
		});
	}
	// ��ʼ������ѡ���
	@SuppressWarnings("unchecked")
	public void initComboBox() {
		List<String> list =null;
		try {
			list = EsmsDAO.getList("tb_mate_house", "Mate_house_ID");
		} catch (SQLException e) { e.printStackTrace(); }
		HouseID.removeAllItems();
		for(String item:list)
			HouseID.addItem(item);
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
		 selectedItem = (String) HouseID.getSelectedItem();
		 if(selectedItem == null)
				return;
		 String sql = "select * from tb_mate_house where Mate_house_ID='"+selectedItem+"'";
		 ResultSet rs = EsmsDAO.query(sql);
		 try {
			rs.next();
			MateHouseID.setText(rs.getString(1));
			StaffID.setText(rs.getString(2));
			MateHouseMstock.setText(rs.getString(3));
			MateAmountNow.setText(rs.getString(4));
			MateAmountLost.setText(rs.getString(5));
			
		 } catch (SQLException e) {
			e.printStackTrace();
		 }
		
	}
}

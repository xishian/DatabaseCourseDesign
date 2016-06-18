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
import edu.swu.model.ProdHouse;
public class ProdHouseModDelPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6960999271089825409L;
	private JTextField ProdHouseID;
	private JTextField StaffID;
	private JTextField ProdHouseMstock;
	private JTextField ProdAmountNow;
	private JTextField ProdAmountLost;
	@SuppressWarnings("rawtypes")
	private JComboBox prodHouse;
	private JButton modifyButton;
	private JButton delButton; 
	@SuppressWarnings("rawtypes")
	public ProdHouseModDelPanel() {
		super();
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);
		
		setupComponet(new JLabel("��Ʒ�ֿ���"), 0, 0, 1, 0, false);
		ProdHouseID = new JTextField();
		setupComponet(ProdHouseID, 1, 0, 1, 100, true);
		
		setupComponet(new JLabel("����Ա����ţ�"), 2, 0, 1, 0, false);
		StaffID = new JTextField();
		setupComponet(StaffID, 3, 0, 1, 0, true);
		
		
		setupComponet(new JLabel("���ϲֿ�����棺"), 0, 1, 1, 0, false);
		ProdHouseMstock = new JTextField();
		setupComponet(ProdHouseMstock, 1, 1, 3,350, true);
		
		setupComponet(new JLabel("�ִ�����"), 0, 2, 1, 0, false);
		ProdAmountNow = new JTextField();
		setupComponet(ProdAmountNow, 1, 2, 1, 100, true);
		
		setupComponet(new JLabel("ʣ��������"), 2, 2, 1, 0, false);
		ProdAmountLost = new JTextField();
		setupComponet(ProdAmountLost, 3, 2, 1, 100, true);

		setupComponet(new JLabel("ѡ��ͻ�"), 0, 7, 1, 0, false);
		prodHouse = new JComboBox();
		prodHouse.setPreferredSize(new Dimension(230, 21));
		initComboBox();// ��ʼ������ѡ���
		prodHouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectItem();
			}
		});
		setupComponet(prodHouse, 1, 7, 2, 0, true);
		modifyButton = new JButton("�޸�");
		delButton = new JButton("ɾ��");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(delButton);
		// ��λ��ť
		setupComponet(panel, 3, 7, 1, 0, false);
		// ����ɾ����ť�ĵ����¼�
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)prodHouse.getSelectedItem();
				
				if (item == null)
					return;
				int confirm = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����Ʒ�ֿ���Ϣ��");
				 if (confirm == JOptionPane.YES_OPTION) {
					 int rs = EsmsDAO.delete("tb_prod_house", "Prod_house_ID", item);
					
				 if (rs > 0) {
					 JOptionPane.showMessageDialog(null,
					 "��Ʒ�ֿ⣺" + item + "��ɾ���ɹ�");
					 prodHouse.removeItem(item);
				 }
			   }
			}
		});
		// �����޸İ�ť�ĵ����¼�
		
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String item = (String) prodHouse.getSelectedItem();
				 ProdHouse prodHouse = new ProdHouse();
					prodHouse.setProdHouseID(item);
					prodHouse.setStaffID(StaffID.getText().trim());
					prodHouse.setProdHouseMstock(ProdHouseMstock.getText().trim());
					prodHouse.setProdAmountNow(ProdAmountNow.getText().trim());
					prodHouse.setProdAmountLost(ProdAmountLost.getText().trim());
					if (EsmsDAO.update("tb_prod_house", "ProdHouse", prodHouse) == 1)
						JOptionPane.showMessageDialog(null, "�޸����");
					else
						JOptionPane.showMessageDialog(null, "�޸�ʧ��");
					
					initComboBox();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public void initComboBox() {
		List<String> list =null;
		try {
			list = EsmsDAO.getList("tb_prod_house", "Prod_house_ID");
		} catch (SQLException e) { e.printStackTrace(); }
		prodHouse.removeAllItems();
		for(String item:list)
			prodHouse.addItem(item);
		selectItem();
	}
	
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
		 selectedItem = (String) prodHouse.getSelectedItem();
		 if(selectedItem == null)
				return;
		 String sql = "select * from tb_prod_house where Prod_house_ID='"+selectedItem+"'";
		 ResultSet rs = EsmsDAO.query(sql);
		 try {
			rs.next();
			ProdHouseID.setText(rs.getString(1));
			StaffID.setText(rs.getString(2));
			ProdHouseMstock.setText(rs.getString(3));
			ProdAmountNow.setText(rs.getString(4));
			ProdAmountLost.setText(rs.getString(5));
			
		 } catch (SQLException e) {
			e.printStackTrace();
		 }
	}
}

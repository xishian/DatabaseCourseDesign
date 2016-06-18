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
import edu.swu.model.ProductInfo;
@SuppressWarnings("serial")
public class ProdInfoModDelPanel extends JPanel {
	private JTextField ProdID;
	private JTextField ProdName;
	private JTextField ProdUnitPrice;
	private JTextField ProdCost;
	private JTextField ProdDesc;
	private JTextField ProdHouseID;
	private JButton modifyButton;
	private JButton delButton;
	@SuppressWarnings("rawtypes")
	private JComboBox prod;
	@SuppressWarnings("rawtypes")
	public ProdInfoModDelPanel() {
		super();
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);

		setupComponet(new JLabel("��Ʒ���ƣ�"), 0, 0, 1, 0, false);
		ProdName = new JTextField();
		setupComponet(ProdName, 1, 0, 3, 350, true);
		
		setupComponet(new JLabel("��Ʒ��ţ�"), 0, 1, 1, 0, false);
		ProdID = new JTextField();
		setupComponet(ProdID, 1, 1, 1, 100, true);
		
		setupComponet(new JLabel("��Ʒ���ۣ�"), 2, 1, 1, 0, false);
		ProdUnitPrice = new JTextField();
		setupComponet(ProdUnitPrice, 3, 1, 1, 100, true);
		
		setupComponet(new JLabel("��Ʒ�ɱ� ��"), 0, 2, 1, 0, false);
		ProdCost = new JTextField();
		setupComponet(ProdCost, 1, 2, 1, 100, true);
		
		setupComponet(new JLabel("��Ʒ�ֿ��ţ�"), 2, 2, 1, 0, false);
		ProdHouseID = new JTextField();
		setupComponet(ProdHouseID, 3, 2, 1, 100, true);

		setupComponet(new JLabel("��Ʒ������"), 0, 3, 1, 0, false);
		ProdDesc = new JTextField();
		setupComponet(ProdDesc, 1, 3, 3, 350, true);

		setupComponet(new JLabel("ѡ���Ʒ"), 0, 7, 1, 0, false);
		prod = new JComboBox();
		prod.setPreferredSize(new Dimension(230, 21));
		initComboBox();// ��ʼ������ѡ���
		prod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectItem();
			}
		});
		// ��λ�ͻ���Ϣ������ѡ���
		setupComponet(prod, 1, 7, 2, 0, true);
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
				String item = (String)prod.getSelectedItem();
				
				if (item == null)
					return;
				int confirm = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����Ʒ��Ϣ��");
				 if (confirm == JOptionPane.YES_OPTION) {
					 int rs = EsmsDAO.delete("tb_prod", "Prod_ID", item.substring(0, item.indexOf(' ')));
					
				 if (rs > 0) {
					 JOptionPane.showMessageDialog(null,
					 "��Ʒ��Ϣ��" + item + "��ɾ���ɹ�");
					 prod.removeItem(item);
				 }
			   }
			}
		});
		// �����޸İ�ť�ĵ����¼�
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String item = (String) prod.getSelectedItem();
				 ProductInfo prodInfo = new ProductInfo();
					prodInfo.setProdID(item.substring(0, item.indexOf(' ')));
					prodInfo.setProdName(ProdName.getText().trim());
					prodInfo.setProdUnitPrice(ProdUnitPrice.getText().trim());
					prodInfo.setProdCost(ProdCost.getText().trim());
					prodInfo.setProdDesc(ProdDesc.getText().trim());
					prodInfo.setProdHouseID(ProdHouseID.getText().trim());
					if (EsmsDAO.update("tb_prod", "ProductInfo", prodInfo) == 1)
						JOptionPane.showMessageDialog(null, "�޸����");
					else
						JOptionPane.showMessageDialog(null, "�޸�ʧ��");
					initComboBox();
			}
		});
	}
	// ��ʼ���ͻ�����ѡ���
		@SuppressWarnings("unchecked")
		public void initComboBox() {
			List<String> list =null;
			try {
				list = EsmsDAO.getList("tb_prod", "Prod_ID","Prod_Name");
			} catch (SQLException e) { e.printStackTrace(); }
			prod.removeAllItems();
			for(String item:list)
				prod.addItem(item);
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
		 selectedItem = (String) prod.getSelectedItem();
		 if(selectedItem == null)
				return;
		 String sql = "select * from tb_prod where Prod_ID='"
				+selectedItem.substring(0, selectedItem.indexOf(' '))+"'";
		 ResultSet rs = EsmsDAO.query(sql);
		 try {
			rs.next();
			ProdID.setText(rs.getString(1));
			ProdName.setText(rs.getString(2));
			ProdUnitPrice.setText(rs.getString(3));
			ProdCost.setText(rs.getString(4));
			ProdDesc.setText(rs.getString(5));
			ProdHouseID.setText(rs.getString(6));
			
			
		 } catch (SQLException e) {
			e.printStackTrace();
		 }
	}
}

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
import edu.swu.model.Order;

public class OrderModifyPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7941622773102571874L;
	private JTextField clientId;
	private JTextField prodId;
	private JTextField prodName;
	private JTextField prodAmount;
	private JTextField indentTime;
	private JTextField pickTime;
	private JTextField staffId;
	private JTextField memo;
	private JButton modifyButton;
	private JButton deleteButton;
	@SuppressWarnings("rawtypes")
	private JComboBox orders;
	@SuppressWarnings("rawtypes")
	public OrderModifyPanel() {
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);

		final JLabel khName = new JLabel();
		khName.setText("�ͻ���ţ�");
		setupComponet(khName, 0, 0, 1, 0, false);
		clientId = new JTextField();
		setupComponet(clientId, 1, 0, 3, 350, true);
		
		final JLabel addressLabel = new JLabel("��Ʒ��ţ�");
		setupComponet(addressLabel, 0, 1, 1, 0, false);
		prodId = new JTextField();
		setupComponet(prodId, 1, 1, 3, 0, true);
		
		final JLabel jc = new JLabel();
		jc.setText("��Ʒ���ƣ�");
		setupComponet(jc, 0, 2, 1, 0, false);
		prodName = new JTextField();
		setupComponet(prodName, 1, 2, 1, 100, true);
		
		setupComponet(new JLabel("��Ʒ������"), 2, 2, 1, 0, false);
		prodAmount = new JTextField();
		setupComponet(prodAmount, 3, 2, 1, 100, true);
		
//	youZhengBianMa.addKeyListener(new InputKeyListener());
		setupComponet(new JLabel("ǩ�����ڣ�"), 0, 3, 1, 0, false);
		indentTime = new JTextField();
		setupComponet(indentTime, 1, 3, 1, 100, true);
		
		setupComponet(new JLabel("ԤԼ���ڣ�"), 2, 3, 1, 0, false);
		pickTime = new JTextField();
		// ��λ�����ı���
		setupComponet(pickTime, 3, 3, 1, 100, true);
		
		
		setupComponet(new JLabel("������Ա��ţ�"), 0, 5, 1, 0, false);
		staffId = new JTextField();
		// ��λE-Mail�ı���
		setupComponet(staffId, 1, 5, 1, 100, true);

		setupComponet(new JLabel("��ע��"), 2, 5, 1, 0, false);
		memo = new JTextField();
		// ��λ�����˺��ı���
		setupComponet(memo, 3, 5, 1, 100, true);

		setupComponet(new JLabel("ѡ�񶩵�"), 0, 7, 1, 0, false);
		orders = new JComboBox();
		orders.setPreferredSize(new Dimension(230, 21));
		initComboBox();// ��ʼ������ѡ���
		// ����ͻ���Ϣ������ѡ����ѡ���¼�
		orders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectItem();
			}
		});
		// ��λ�ͻ���Ϣ������ѡ���
		setupComponet(orders, 1, 7, 2, 0, true);
		modifyButton = new JButton("�޸�");
		deleteButton = new JButton("ɾ��");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(deleteButton);
		// ��λ��ť
		setupComponet(panel, 3, 7, 1, 0, false);
		// ����ɾ����ť�ĵ����¼�
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)orders.getSelectedItem();
				
				if (item == null)
					return;
				int confirm = JOptionPane.showConfirmDialog(null, "ȷ��ɾ��������Ϣ��");
				 if (confirm == JOptionPane.YES_OPTION) {
					 int rs = EsmsDAO.delete("Orders", "Indent_ID", item);
					
				 if (rs > 0) {
					 JOptionPane.showMessageDialog(null,
					 "������¼��" + item + "��ɾ���ɹ�");
					 orders.removeItem(item);
				 }
			   }
			}
		});
		// �����޸İ�ť�ĵ����¼�
		
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 String item = (String) orders.getSelectedItem();
			 	Order order = new Order();
				order.setId(item);
				order.setClientId(clientId.getText()); //Ҫ��
				order.setIndentTime(indentTime.getText());
				order.setPickTime(pickTime.getText());
				order.setProdAmount(Integer.parseInt(prodAmount.getText().trim()));
				order.setProdId(prodId.getText());
				order.setProdName(prodName.getText());
				order.setStaffId(staffId.getText());
				order.setMemo(memo.getText());
				if (EsmsDAO.update("Orders", "Order", order) == 1)
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
			list = EsmsDAO.getList("Orders", "Indent_ID");
		} catch (SQLException e) { e.printStackTrace(); }
		orders.removeAllItems();
		for(String item:list)
			orders.addItem(item);
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
		 selectedItem = (String) orders.getSelectedItem();
		 if(selectedItem == null)
				return;
		 String sql = "select * from Orders where Indent_ID='"+selectedItem+"'";
		 ResultSet rs = EsmsDAO.query(sql);
		 try {
			rs.next();
			//orderId.setText(rs.getString(1));
			clientId.setText(rs.getString(2));
			prodId.setText(rs.getString(3));
			prodName.setText(rs.getString(4));
			prodAmount.setText(rs.getString(5));
			indentTime.setText(rs.getString(6));
			pickTime.setText(rs.getString(7));
			staffId.setText(rs.getString(8));
			memo.setText(rs.getString(9));
		
		 } catch (SQLException e) {
			e.printStackTrace();
		 }
	}

}

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
import edu.swu.model.SalesAmount;

public class SaleAddPanel extends JPanel {
	
	
	private static final long serialVersionUID = 9116513414479445918L;

	
	private JTextField clientId;
	private JTextField prodName;
	private JTextField unitPrice;
	private JTextField prodAmount;
	private JTextField prodToprice;
	private JTextField sellTime;
	private JTextField staffId;
	private JTextField memo;
	
	private JButton resetButton;
	public SaleAddPanel() {
		super();
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);
		
		
		final JLabel clientIdLabel = new JLabel();
		clientIdLabel.setText("�ͻ���ţ�");
		setupComponet(clientIdLabel, 0, 0, 1, 0, false);
		clientId = new JTextField();
		setupComponet(clientId, 1, 0, 3, 350, true);
		
		final JLabel jc = new JLabel();
		jc.setText("��Ʒ���ƣ�");
		setupComponet(jc, 0, 2, 1, 0, false);
		prodName = new JTextField();
		setupComponet(prodName, 1, 2, 1, 100, true);
		
		setupComponet(new JLabel("��Ʒ������"), 2, 2, 1, 0, false);
		prodAmount = new JTextField();
		setupComponet(prodAmount, 3, 2, 1, 100, true);
		

		setupComponet(new JLabel("��Ʒ���ۣ�"), 0, 3, 1, 0, false);
		unitPrice = new JTextField();
		setupComponet(unitPrice, 1, 3, 1, 100, true);
		
		setupComponet(new JLabel("��Ʒ�ܼۣ�"), 2, 3, 1, 0, false);
		prodToprice = new JTextField();
		// ��λ�����ı���
		setupComponet(prodToprice, 3, 3, 1, 100, true);
		
		
		setupComponet(new JLabel("����ʱ�䣺"), 0, 5, 1, 0, false);
		sellTime = new JTextField();
		// ��λE-Mail�ı���
		setupComponet(sellTime, 1, 5, 1, 100, true);
		setupComponet(new JLabel("������Ա��ţ�"), 2, 5, 1, 0, false);
		staffId = new JTextField();
		// ��λ�����˺��ı���
		setupComponet(staffId, 3, 5, 1, 100, true);
		
		setupComponet(new JLabel("��ע��"), 0, 6, 1, 0, false);
		memo = new JTextField();
		// ��λ�����˺��ı���
		setupComponet(memo, 1, 6, 3, 100, true);
		
		final JButton saveButton = new JButton("����");
		// ��λ���水ť
		setupComponet(saveButton, 1, 7, 1, 0, false);
		saveButton.addActionListener(new SaveButtonActionListener());
		resetButton = new JButton("����");
		// ��λ���ð�ť
		setupComponet(resetButton, 3, 7, 1, 0, false);
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	// �������λ�ò���ӵ�������
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
	// ���水ť���¼�������
	private final class SaveButtonActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			if (clientId.getText().trim().equals("")
					|| unitPrice.getText().equals("")
					|| prodName.getText().equals("")
					|| prodAmount.getText().equals("")
					|| sellTime.getText().equals("")
					|| memo.getText().equals("")
					|| staffId.getText().equals("")
					) {
				JOptionPane.showMessageDialog(null, "����дȫ����Ϣ");
				return;
			}
			
			ResultSet set = EsmsDAO.query("select max(Sale_ID) from Sales_Amount");
			String id = null;
			try {
				if (set != null && set.next()) {
					String sid = set.getString(1);
					if (sid == null)
						id = "000000";
					else {
						id = String.valueOf(Integer.parseInt(sid)+1);
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			SalesAmount sale = new SalesAmount();
			sale.setId(id);
			sale.setClientId(clientId.getText().trim());
			
			sale.setMemo(memo.getText().trim());
			sale.setProdAmount(prodAmount.getText().trim());
			sale.setProdName(prodName.getText().trim());
			sale.setProdToprice(prodToprice.getText().trim());
			sale.setStaffId(staffId.getText().trim());
			sale.setUnitPrice(unitPrice.getText().trim());
			sale.setSellTime(sellTime.getText().trim());
			
			EsmsDAO.addSale(sale);
			JOptionPane.showMessageDialog(SaleAddPanel.this, "�ѳɹ��������������Ϣ",
					"����������Ϣ���", JOptionPane.INFORMATION_MESSAGE);
			resetButton.doClick();
		}
	}
	

}

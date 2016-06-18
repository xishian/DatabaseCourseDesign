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
import edu.swu.model.ProductInfo;
public class ProdInfoAddPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 30877407374453685L;
	private JTextField ProdName;
	private JTextField ProdUnitPrice;
	private JTextField ProdCost;
	private JTextField ProdDesc;
	private JTextField ProdHouseID;
	private JButton saveButton;
	private JButton resetButton;
	public ProdInfoAddPanel() {
		super();
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);

		setupComponet(new JLabel("��Ʒ���ƣ�"), 0, 0, 1, 0, false);
		ProdName = new JTextField();
		setupComponet(ProdName, 1, 0, 3, 100, true);
		
		setupComponet(new JLabel("��Ʒ���ۣ�"), 2, 0, 1, 0, false);
		ProdUnitPrice = new JTextField();
		setupComponet(ProdUnitPrice, 3, 0, 1, 100, true);
		
		setupComponet(new JLabel("��Ʒ�ɱ� ��"), 0, 1, 1, 0, false);
		ProdCost = new JTextField();
		setupComponet(ProdCost, 1, 1, 1, 100, true);
		
		setupComponet(new JLabel("��Ʒ�ֿ��ţ�"), 2, 1, 1, 0, false);
		ProdHouseID = new JTextField();
		setupComponet(ProdHouseID, 3, 1, 1, 100, true);

		setupComponet(new JLabel("��Ʒ������"), 0, 2, 1, 0, false);
		ProdDesc = new JTextField();
		setupComponet(ProdDesc, 1, 2, 3, 350, true);

		saveButton=new JButton("����");
		setupComponet(saveButton, 1, 7, 1, 0, false);
		saveButton.addActionListener(new SaveButtonActionListener());
		resetButton = new JButton("����");
		setupComponet(resetButton, 3, 7, 1, 0, false);
		resetButton.addActionListener(new ChongZheButtonActionListener());
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
			if (ProdName.getText().trim().equals("")
					|| ProdUnitPrice.getText().trim().equals("")
					|| ProdCost.getText().trim().equals("")
					|| ProdHouseID.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "��Ʒ���ơ����ۡ��ɱ����ֿ��Ų���Ϊ�գ�������������룡"
						,"Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (ProdName.getText().equals("")
					|| ProdUnitPrice.getText().equals("")
					|| ProdCost.getText().equals("")
					|| ProdDesc.getText().equals("")
					|| ProdHouseID.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "����дȫ����Ϣ");
				return;
			}
			ResultSet haveUser = EsmsDAO
					.query("select * from tb_prod where Prod_Name='"
							+ ProdName.getText().trim() + "'");
			try {
				if (haveUser.next()){
					System.out.println("error");
					JOptionPane.showMessageDialog(ProdInfoAddPanel.this,
							"��Ʒ��Ϣ���ʧ�ܣ�����ͬ����Ʒ", "��Ʒ�����Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			} catch (Exception er) {
				er.printStackTrace();
			}
			ResultSet set = EsmsDAO.query("select max(Prod_ID) from tb_prod");
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
			ProductInfo prodInfo = new ProductInfo();
			prodInfo.setProdID(id);
			prodInfo.setProdName(ProdName.getText().trim());
			prodInfo.setProdUnitPrice(ProdUnitPrice.getText().trim());
			prodInfo.setProdCost(ProdCost.getText().trim());
			prodInfo.setProdDesc(ProdDesc.getText().trim());
			prodInfo.setProdHouseID(ProdHouseID.getText().trim());
			EsmsDAO.addProd(prodInfo);
			JOptionPane.showMessageDialog(ProdInfoAddPanel.this, "�ѳɹ���Ӳ�Ʒ",
					"��Ʒ�����Ϣ", JOptionPane.INFORMATION_MESSAGE);
			resetButton.doClick();
		}
	}
	// ���ð�ť���¼�������
	private class ChongZheButtonActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			ProdName.setText("");
			ProdUnitPrice.setText("");
			ProdCost.setText("");
			ProdDesc.setText("");
			ProdHouseID.setText("");
		}
	}
}
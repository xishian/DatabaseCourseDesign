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
import edu.swu.model.Order;

public class OrderAddPanel extends JPanel{
	private static final long serialVersionUID = -1641665111365461564L;
	private JTextField clientId;
	private JTextField prodId;
	private JTextField prodName;
	private JTextField prodAmount;
	private JTextField indentTime;
	private JTextField pickTime;
	private JTextField staffId;
	
	private JButton resetButton;
	public OrderAddPanel() {
		super();
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
		
		setupComponet(new JLabel("ǩ�����ڣ�"), 0, 3, 1, 0, false);
		indentTime = new JTextField();
		setupComponet(indentTime, 1, 3, 1, 100, true);
		
		setupComponet(new JLabel("ԤԼ���ڣ�"), 2, 3, 1, 0, false);
		pickTime = new JTextField();
		setupComponet(pickTime, 3, 3, 1, 100, true);
		
		
		setupComponet(new JLabel("������Ա��ţ�"), 0, 5, 1, 0, false);
		staffId = new JTextField();
		setupComponet(staffId, 1, 5, 3, 350, true);
		
		final JButton saveButton = new JButton("����");
		setupComponet(saveButton, 1, 7, 1, 0, false);
		saveButton.addActionListener(new SaveButtonActionListener());
		resetButton = new JButton("����");
		setupComponet(resetButton, 3, 7, 1, 0, false);
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clientId.setText("");
				prodId.setText("");
				prodName.setText("");
				prodAmount.setText("");
				indentTime.setText("");
				pickTime.setText("");
				staffId.setText("");
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
					|| prodId.getText().equals("")
					|| prodName.getText().equals("")
					|| prodAmount.getText().equals("")
					|| indentTime.getText().equals("")
					|| pickTime.getText().equals("")
					|| staffId.getText().equals("")
					) {
				int right = JOptionPane.showConfirmDialog(null, "��Ϣ��������ȷ���ύ��");
				if(right==JOptionPane.OK_OPTION){}
				else
					return;
			}
			
			ResultSet set = EsmsDAO.query("select max(Indent_ID) from Orders");
			String id = "";
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
			Order order = new Order();
			order.setId(id);
			order.setClientId(clientId.getText()); 
			order.setIndentTime(indentTime.getText());
			order.setPickTime(pickTime.getText());
			order.setProdAmount(Integer.parseInt(prodAmount.getText().trim()));
			order.setProdId(prodId.getText());
			order.setProdName(prodName.getText());
			order.setStaffId(staffId.getText());
			order.setMemo("");
			
			EsmsDAO.addOrder(order);
			JOptionPane.showMessageDialog(OrderAddPanel.this, "�ѳɹ���Ӷ���",
					"���������Ϣ", JOptionPane.INFORMATION_MESSAGE);
			resetButton.doClick();
		}
	}
	
	

}

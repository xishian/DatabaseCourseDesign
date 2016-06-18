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
import edu.swu.model.ClientInfo;

public class ClientAddPanel extends JPanel {
	
	private static final long serialVersionUID = 4989024741208492678L;
	private JTextField clientName;
	private JTextField clientSex;
	private JTextField clientDepart;
	private JTextField clientMail;
	private JTextField clientSite;
	private JTextField clientTel;

	private JButton resetButton;
	
	public ClientAddPanel() {
		super();
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);
		
		//����
		
		final JLabel khName = new JLabel();
		khName.setText("�ͻ�������");
		setupComponet(khName, 0, 0, 1, 0, false);
		clientName = new JTextField();
		
		setupComponet(clientName, 1, 0, 3, 350, true);
		final JLabel addressLabel = new JLabel("��λ��ַ��");
		setupComponet(addressLabel, 0, 1, 1, 0, false);
		clientSite = new JTextField();
		
		setupComponet(clientSite, 1, 1, 3, 0, true);
		final JLabel jc = new JLabel();
		jc.setText("�ͻ��Ա�");
		setupComponet(jc, 0, 2, 1, 0, false);
		clientSex = new JTextField();
		
		setupComponet(clientSex, 1, 2, 1, 100, true);
		
		setupComponet(new JLabel("�ͻ���λ��"), 2, 2, 1, 0, false);
		clientDepart = new JTextField();
		
		setupComponet(clientDepart, 3, 2, 1, 100, true);
	
		
		setupComponet(new JLabel("�绰���룺"), 0, 3, 1, 0, false);
		clientTel = new JTextField();
		setupComponet(clientTel, 1, 3, 1, 100, true);
		
		
		setupComponet(new JLabel("�ͻ����䣺"), 2, 3, 1, 0, false);
		clientMail = new JTextField();
		setupComponet(clientMail, 3, 3, 1, 100, true);
		
		final JButton saveButton = new JButton("����");
		setupComponet(saveButton, 1, 7, 1, 0, false);
		saveButton.addActionListener(new SaveActionListener());
		
		resetButton = new JButton("���");
		setupComponet(resetButton, 3, 7, 1, 0, false);
		resetButton.addActionListener(new ActionListener() {
			//������������Ϊ��ֵ
			@Override
			public void actionPerformed(ActionEvent e) {
				clientDepart.setText("");
				clientMail.setText("");
				clientName.setText("");
				clientSex.setText("");
				clientSite.setText("");
				clientTel.setText("");
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
	
	// ������������Էֿ���д
	private final class SaveActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			if (clientName.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "�ͻ�����δ�������������룡"
						,"����ȱʧ",JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (clientDepart.getText().trim().equals("")
					|| clientMail.getText().trim().equals("")
					|| clientName.getText().trim().equals("")
					|| clientSex.getText().trim().equals("")
					|| clientSite.getText().trim().equals("")
					|| clientTel.getText().trim().equals("")
				) {
				int right = JOptionPane.showConfirmDialog(null, "��Ϣ��������ȷ���ύ��");
				if(right==JOptionPane.OK_OPTION){}
				else
					return;
				
			}
			
			ResultSet haveUser = EsmsDAO
					.query("select * from ClientInfo where Client_Name='"
							+ clientName.getText().trim() + "'");
			try {
				if (haveUser.next()){ //�Ѿ����˸��û�
					System.out.println("error");
					JOptionPane.showMessageDialog(ClientAddPanel.this,
							"�ͻ���Ϣ���ʧ�ܣ�����ͬ���ͻ�", "�ͻ������Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			} catch (Exception er) {
				er.printStackTrace();
			}
			
			
			ResultSet set = EsmsDAO.query("select max(Client_ID) from Clientinfo");
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
			
			
			ClientInfo client = new ClientInfo();
			client.setId(id);
			client.setDepart(clientDepart.getText().trim());
			client.setMail(clientMail.getText().trim());
			client.setName(clientName.getText().trim());
			client.setSex(clientSex.getText().trim());
			client.setSite(clientSite.getText().trim());
			client.setTel(clientTel.getText().trim());
		
			EsmsDAO.addClient(client);
		//	EsmsDAO.add
			
			JOptionPane.showMessageDialog(ClientAddPanel.this, "�ѳɹ���ӿͻ�",
					"�ͻ������Ϣ", JOptionPane.INFORMATION_MESSAGE);
		
			resetButton.doClick();
		}
	}
}

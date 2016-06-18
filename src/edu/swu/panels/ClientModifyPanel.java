package edu.swu.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import edu.swu.model.ClientInfo;

public class ClientModifyPanel extends JPanel {
	
	private static final long serialVersionUID = -1062761194620742776L;
	private JTextField clientName;
	private JTextField clientSex;
	private JTextField clientDepart;
	private JTextField clientMail;
	private JTextField clientSite;
	private JTextField clientTel;

	private JButton modifyButton;
	private JButton deleteButton;
	private JComboBox<String> clients;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ClientModifyPanel() {
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);
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

		setupComponet(new JLabel("ѡ��ͻ�"), 0, 7, 1, 0, false);
		clients = new JComboBox();
		clients.setPreferredSize(new Dimension(230, 21));
		initComboBox();// ��ʼ������ѡ���
		// ����ͻ���Ϣ������ѡ����ѡ���¼�
		clients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectItem();
			}
		});
		// ��λ�ͻ���Ϣ������ѡ���
		setupComponet(clients, 1, 7, 2, 0, true);
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
				String temp = (String)clients.getSelectedItem();
				int  index = clients.getSelectedIndex();
				if(temp == null)
					return ;
				int confirm = JOptionPane.showConfirmDialog(
						ClientModifyPanel.this, "ȷ��ɾ���ͻ���Ϣ��");
				if (confirm == JOptionPane.YES_OPTION) {
					int rs = EsmsDAO.delete("Clientinfo", "Client_ID", temp.substring(0, temp.indexOf(' ')));
					if (rs > 0) {
						JOptionPane.showMessageDialog(ClientModifyPanel.this,
								"�ͻ���" + temp.substring(temp.indexOf(' ')+1)+ "��ɾ���ɹ�");
						
						clients.remove(index);
					}
				}
			}
		});
		// �����޸İ�ť�ĵ����¼�
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = (String)clients.getSelectedItem();
				ClientInfo client = new ClientInfo();
				client.setId(temp.substring(0, temp.indexOf(' ')));
				client.setDepart(clientDepart.getText().trim());
				client.setMail(clientMail.getText().trim());
				client.setName(clientName.getText().trim());
				client.setSex(clientSex.getText().trim());
				client.setSite(clientSite.getText().trim());
				client.setTel(clientTel.getText().trim());
				if (EsmsDAO.update("Clientinfo", "ClientInfo", client) == 1)
					JOptionPane.showMessageDialog(ClientModifyPanel.this, "�޸����");
				else
					JOptionPane.showMessageDialog(ClientModifyPanel.this, "�޸�ʧ��");
				initComboBox();
			}
		});
	}
	// ��ʼ���ͻ�����ѡ���
	public void initComboBox() {
		List<String> list = null;
		try {
			list = EsmsDAO.getList("Clientinfo", "Client_ID", "Client_Name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		clients.removeAllItems();
		
		for(String item:list){
			clients.addItem(item);
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
		selectedItem = (String) clients.getSelectedItem();
		if(selectedItem == null)
			return;
		ClientInfo client = new ClientInfo();
		try {
			client = EsmsDAO.getClientInfo(selectedItem.substring(selectedItem.indexOf(' ')+1));
		} catch (SQLException e) {e.printStackTrace();}
		clientName.setText(client.getName());
		clientDepart.setText(client.getDepart());
		clientMail.setText(client.getMail());
		clientSex.setText(client.getSex());
		clientSite.setText(client.getSite());
		clientTel.setText(client.getTel());
	}

}

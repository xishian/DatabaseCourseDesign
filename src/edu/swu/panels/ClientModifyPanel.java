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
		khName.setText("客户姓名：");
		setupComponet(khName, 0, 0, 1, 0, false);
		clientName = new JTextField();
		
		setupComponet(clientName, 1, 0, 3, 350, true);
		final JLabel addressLabel = new JLabel("单位地址：");
		setupComponet(addressLabel, 0, 1, 1, 0, false);
		clientSite = new JTextField();
		
		setupComponet(clientSite, 1, 1, 3, 0, true);
		final JLabel jc = new JLabel();
		jc.setText("客户性别：");
		setupComponet(jc, 0, 2, 1, 0, false);
		clientSex = new JTextField();
		
		setupComponet(clientSex, 1, 2, 1, 100, true);
		
		setupComponet(new JLabel("客户单位："), 2, 2, 1, 0, false);
		clientDepart = new JTextField();
		
		setupComponet(clientDepart, 3, 2, 1, 100, true);
	
		
		setupComponet(new JLabel("电话号码："), 0, 3, 1, 0, false);
		clientTel = new JTextField();
		setupComponet(clientTel, 1, 3, 1, 100, true);
		
		
		setupComponet(new JLabel("客户邮箱："), 2, 3, 1, 0, false);
		clientMail = new JTextField();
		setupComponet(clientMail, 3, 3, 1, 100, true);

		setupComponet(new JLabel("选择客户"), 0, 7, 1, 0, false);
		clients = new JComboBox();
		clients.setPreferredSize(new Dimension(230, 21));
		initComboBox();// 初始化下拉选择框
		// 处理客户信息的下拉选择框的选择事件
		clients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectItem();
			}
		});
		// 定位客户信息的下拉选择框
		setupComponet(clients, 1, 7, 2, 0, true);
		modifyButton = new JButton("修改");
		deleteButton = new JButton("删除");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(deleteButton);
		// 定位按钮
		setupComponet(panel, 3, 7, 1, 0, false);
		// 处理删除按钮的单击事件
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = (String)clients.getSelectedItem();
				int  index = clients.getSelectedIndex();
				if(temp == null)
					return ;
				int confirm = JOptionPane.showConfirmDialog(
						ClientModifyPanel.this, "确认删除客户信息吗？");
				if (confirm == JOptionPane.YES_OPTION) {
					int rs = EsmsDAO.delete("Clientinfo", "Client_ID", temp.substring(0, temp.indexOf(' ')));
					if (rs > 0) {
						JOptionPane.showMessageDialog(ClientModifyPanel.this,
								"客户：" + temp.substring(temp.indexOf(' ')+1)+ "。删除成功");
						
						clients.remove(index);
					}
				}
			}
		});
		// 处理修改按钮的单击事件
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
					JOptionPane.showMessageDialog(ClientModifyPanel.this, "修改完成");
				else
					JOptionPane.showMessageDialog(ClientModifyPanel.this, "修改失败");
				initComboBox();
			}
		});
	}
	// 初始化客户下拉选择框
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
	// 设置组件位置并添加到容器中
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

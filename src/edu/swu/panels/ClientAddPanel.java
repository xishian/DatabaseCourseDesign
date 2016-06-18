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
		
		//布局
		
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
		
		final JButton saveButton = new JButton("保存");
		setupComponet(saveButton, 1, 7, 1, 0, false);
		saveButton.addActionListener(new SaveActionListener());
		
		resetButton = new JButton("清空");
		setupComponet(resetButton, 3, 7, 1, 0, false);
		resetButton.addActionListener(new ActionListener() {
			//将所有内容设为空值
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
	
	// 代码过长，所以分开来写
	private final class SaveActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			if (clientName.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "客户姓名未输入请重新输入！"
						,"姓名缺失",JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (clientDepart.getText().trim().equals("")
					|| clientMail.getText().trim().equals("")
					|| clientName.getText().trim().equals("")
					|| clientSex.getText().trim().equals("")
					|| clientSite.getText().trim().equals("")
					|| clientTel.getText().trim().equals("")
				) {
				int right = JOptionPane.showConfirmDialog(null, "信息不完整！确认提交？");
				if(right==JOptionPane.OK_OPTION){}
				else
					return;
				
			}
			
			ResultSet haveUser = EsmsDAO
					.query("select * from ClientInfo where Client_Name='"
							+ clientName.getText().trim() + "'");
			try {
				if (haveUser.next()){ //已经有了该用户
					System.out.println("error");
					JOptionPane.showMessageDialog(ClientAddPanel.this,
							"客户信息添加失败，存在同名客户", "客户添加信息",
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
			
			JOptionPane.showMessageDialog(ClientAddPanel.this, "已成功添加客户",
					"客户添加信息", JOptionPane.INFORMATION_MESSAGE);
		
			resetButton.doClick();
		}
	}
}

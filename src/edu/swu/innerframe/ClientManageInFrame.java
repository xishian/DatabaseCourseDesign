package edu.swu.innerframe;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.swu.panels.*;

public class ClientManageInFrame extends JInternalFrame {

	private static final long serialVersionUID = -6738819642088459317L;

	public ClientManageInFrame() {

		setResizable(false);
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("客户管理");
		JTabbedPane tabPane = new JTabbedPane();
		
		final ClientAddPanel addPanel = new ClientAddPanel();
		final ClientModifyPanel modPanel = new ClientModifyPanel();
		final ClientModifyPanel qPanel = new ClientModifyPanel();
		
		qPanel.remove(qPanel.getComponent(qPanel.getComponentCount()-1));
		
		tabPane.addTab("添加客户", null, addPanel, "客户添加");
		tabPane.addTab("修改与删除客户", null, modPanel, "修改与删除");
		tabPane.addTab("查询客户", null, qPanel, "查询客户信息");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				modPanel.initComboBox();
				qPanel.initComboBox();
			}
		});
		pack();
		setVisible(true);
	}
}

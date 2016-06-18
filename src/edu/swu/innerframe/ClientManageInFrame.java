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
		setTitle("�ͻ�����");
		JTabbedPane tabPane = new JTabbedPane();
		
		final ClientAddPanel addPanel = new ClientAddPanel();
		final ClientModifyPanel modPanel = new ClientModifyPanel();
		final ClientModifyPanel qPanel = new ClientModifyPanel();
		
		qPanel.remove(qPanel.getComponent(qPanel.getComponentCount()-1));
		
		tabPane.addTab("��ӿͻ�", null, addPanel, "�ͻ����");
		tabPane.addTab("�޸���ɾ���ͻ�", null, modPanel, "�޸���ɾ��");
		tabPane.addTab("��ѯ�ͻ�", null, qPanel, "��ѯ�ͻ���Ϣ");
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

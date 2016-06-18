package edu.swu.innerframe;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.swu.panels.StaffAddPanel;
import edu.swu.panels.StaffModifyPanel;

public class StaffManageInFrame extends JInternalFrame {

	private static final long serialVersionUID = -5869695067925355700L;
	public StaffManageInFrame() {

		setResizable(false);
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("Ա������");
		JTabbedPane tabPane = new JTabbedPane();
		
		final StaffAddPanel addPanel = new StaffAddPanel();
		final StaffModifyPanel modPanel = new StaffModifyPanel();
		final StaffModifyPanel qPanel = new StaffModifyPanel();
		
		qPanel.remove(qPanel.getComponent(qPanel.getComponentCount()-1));
//		qPanel.getComponent(qPanel.getComponentCount()-1).setSize(120,20);
		tabPane.addTab("���Ա��", null, addPanel, "Ա�����");
		tabPane.addTab("�޸���ɾ��Ա��", null, modPanel, "�޸���ɾ��Ա��");
		tabPane.addTab("��ѯԱ����Ϣ", null, qPanel, "��ѯԱ����Ϣ");
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

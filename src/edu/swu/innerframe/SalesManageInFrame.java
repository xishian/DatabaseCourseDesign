package edu.swu.innerframe;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.swu.panels.SaleAddPanel;
import edu.swu.panels.SaleModifyPanel;

public class SalesManageInFrame extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1188167992086404664L;

	public SalesManageInFrame() {

		setResizable(false);
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("���ۼ�¼����");
		JTabbedPane tabPane = new JTabbedPane();
		
		final SaleAddPanel addPanel = new SaleAddPanel();
		final SaleModifyPanel modPanel = new SaleModifyPanel();
		final SaleModifyPanel qPanel = new SaleModifyPanel();
		
		qPanel.remove(qPanel.getComponent(qPanel.getComponentCount()-1));
//		qPanel.getComponent(qPanel.getComponentCount()-1).setSize(120,20);
		
		tabPane.addTab("������ۼ�¼", null, addPanel, "���ۼ�¼���");
		tabPane.addTab("�޸���ɾ�����ۼ�¼", null, modPanel, "�޸���ɾ��");
		tabPane.addTab("��ѯ���ۼ�¼", null, qPanel, "��ѯ���ۼ�¼");
		
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

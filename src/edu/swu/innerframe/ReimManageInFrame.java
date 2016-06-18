package edu.swu.innerframe;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.swu.panels.ReimAddPanel;
import edu.swu.panels.ReimModifyPanel;


/**
 * ���˼�¼
 */

public class ReimManageInFrame extends JInternalFrame {

	private static final long serialVersionUID = 7346866487650731817L;
	
	public ReimManageInFrame() {

		setResizable(false);
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("���˹���");
		JTabbedPane tabPane = new JTabbedPane();
		
		final ReimAddPanel addPanel = new ReimAddPanel();
		final ReimModifyPanel modPanel = new ReimModifyPanel();
		final ReimModifyPanel qPanel = new ReimModifyPanel();
		
		qPanel.remove(qPanel.getComponent(qPanel.getComponentCount()-1));
		qPanel.getComponent(qPanel.getComponentCount()-1).setSize(120,20);
		tabPane.addTab("��ӱ��˼�¼", null, addPanel, "��ӱ��˼�¼");
		tabPane.addTab("�޸���ɾ�����˼�¼", null, modPanel, "�޸���ɾ�����˼�¼");
		tabPane.addTab("��ѯ���˼�¼", null, qPanel, "��ѯ���˼�¼");
		
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

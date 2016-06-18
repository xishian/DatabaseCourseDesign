package edu.swu.innerframe;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.swu.panels.MateAddingPanel;
import edu.swu.panels.MateModDelPanel;
public class MateManageInFrame extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2065568252774376478L;

	public MateManageInFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("������Ϣ����");
		JTabbedPane tabPane = new JTabbedPane();
		final MateModDelPanel mateModDel = new MateModDelPanel();
		final MateAddingPanel mateAdding = new MateAddingPanel();
		final MateModDelPanel qPanel = new MateModDelPanel();
		qPanel.remove(qPanel.getComponent(qPanel.getComponentCount()-1));
//		qPanel.getComponent(qPanel.getComponentCount()-1).setSize(120,20);
		tabPane.addTab("������Ϣ���", null, mateAdding, "���");
		tabPane.addTab("������Ϣɾ�����޸�", null,  mateModDel, "ɾ�����޸�");
		tabPane.addTab("��ѯ������Ϣ", null,  qPanel, "��ѯ������Ϣ");
		
		
		
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				mateModDel.initComboBox();
				qPanel.initComboBox();
				
			}
		});
		pack();
		setVisible(true);
	}
}

package edu.swu.innerframe;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.swu.panels.MateHouseInfoAddPanel;
import edu.swu.panels.MateHouseInfoModDelPanel;
public class MateHouseInfoManageInFrame extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1480241874209344805L;

	public MateHouseInfoManageInFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("���ϲֿ���Ϣ����Ϣ����");
		JTabbedPane tabPane = new JTabbedPane();
		final  MateHouseInfoAddPanel matHouInfoAdd = new MateHouseInfoAddPanel();
		final MateHouseInfoModDelPanel matHouInfoModDel = new MateHouseInfoModDelPanel();
		
		final MateHouseInfoModDelPanel qPanel = new MateHouseInfoModDelPanel();
		
		qPanel.remove(qPanel.getComponent(qPanel.getComponentCount()-1));
//		qPanel.getComponent(qPanel.getComponentCount()-1).setSize(120,20);
		tabPane.addTab("���ϲֿ���Ϣ���", null, matHouInfoAdd, "���");
		tabPane.addTab("�޸���ɾ��", null, matHouInfoModDel, "�޸���ɾ��");
		tabPane.addTab("��ѯ���ϲֿ���Ϣ", null, qPanel, "��ѯ���ϲֿ���Ϣ");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				matHouInfoModDel.initComboBox();
				qPanel.initComboBox();
			}
		});
		pack();
		setVisible(true);
	}
}

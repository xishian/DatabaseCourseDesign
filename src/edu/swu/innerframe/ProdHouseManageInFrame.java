package edu.swu.innerframe;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.swu.panels.ProdHouseAddPanel;
import edu.swu.panels.ProdHouseModDelPanel;
public class ProdHouseManageInFrame extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 937887033023477622L;

	public ProdHouseManageInFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("��Ʒ�ֿ����");
		JTabbedPane tabPane = new JTabbedPane();
		final ProdHouseAddPanel prodHouseAdd = new ProdHouseAddPanel();
		final ProdHouseModDelPanel prodHouseModDel = new ProdHouseModDelPanel();
		final ProdHouseModDelPanel qPanel = new ProdHouseModDelPanel();
		qPanel.remove(qPanel.getComponent(qPanel.getComponentCount()-1));
//		qPanel.getComponent(qPanel.getComponentCount()-1).setSize(120,20);
		tabPane.addTab("��Ʒ�ֿ���Ϣ���", null, prodHouseAdd, "���");
		tabPane.addTab("��Ʒ�ֿ���Ϣ�޸���ɾ��", null, prodHouseModDel, "�޸���ɾ��");
		tabPane.addTab("��ѯ��Ʒ�ֿ���Ϣ", null, qPanel, "��ѯ��Ʒ�ֿ���Ϣ");
		
		
		
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				prodHouseModDel.initComboBox();
			}
		});
		pack();
		setVisible(true);
	}
}

package edu.swu.innerframe;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.swu.panels.BuyMatExpAddingPanel;
import edu.swu.panels.BuyMatExpModDelPanel;

public class ExpenseManageInFrame extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4286148295083264551L;

	public ExpenseManageInFrame() {
		setIconifiable(true);
		setClosable(true);
		setMaximizable(true);
		setTitle("�������֧������");
		JTabbedPane tabPane = new JTabbedPane();
		final BuyMatExpAddingPanel expAdding = new BuyMatExpAddingPanel();
		final BuyMatExpModDelPanel expModDel = new BuyMatExpModDelPanel();
		final BuyMatExpModDelPanel qPanel = new BuyMatExpModDelPanel();
		
		qPanel.remove(qPanel.getComponent(qPanel.getComponentCount()-1));
		
		tabPane.addTab("֧����Ϣ���", null, expAdding, "���");
		tabPane.addTab("֧����Ϣɾ�����޸�", null,  expModDel, "ɾ�����޸�");
		tabPane.addTab("��ѯ֧����Ϣ", null,  qPanel, "��ѯ֧����Ϣ");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				expModDel.initComboBox();
				qPanel.initComboBox();
			}
		});
		pack();
		setVisible(true);
	}
}

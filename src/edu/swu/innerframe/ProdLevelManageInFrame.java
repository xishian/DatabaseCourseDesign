package edu.swu.innerframe;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.swu.panels.ProdLevelAddPanel;
import edu.swu.panels.ProdLevelModDelPanel;
public class ProdLevelManageInFrame extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7778331310925477656L;

	public ProdLevelManageInFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("产品生产级别管理");
		JTabbedPane tabPane = new JTabbedPane();
		final ProdLevelAddPanel prodLevAdd = new ProdLevelAddPanel();
		final ProdLevelModDelPanel prodLevModDel = new ProdLevelModDelPanel();
		final ProdLevelModDelPanel qPanel = new ProdLevelModDelPanel();
		
		qPanel.remove(qPanel.getComponent(qPanel.getComponentCount()-1));
//		qPanel.getComponent(qPanel.getComponentCount()-1).setSize(120,20);
		
		tabPane.addTab("产品生产级别信息添加", null, prodLevAdd, "添加");
		tabPane.addTab("产品生产级别信息修改与删除", null, prodLevModDel, "修改与删除");
		tabPane.addTab("查询级别信息", null, qPanel, "查询级别信息");
		
		
		
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				prodLevModDel.initComboBox();
				qPanel.initComboBox();
			}
		});
		pack();
		setVisible(true);
	}
}

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
		setTitle("销售记录管理");
		JTabbedPane tabPane = new JTabbedPane();
		
		final SaleAddPanel addPanel = new SaleAddPanel();
		final SaleModifyPanel modPanel = new SaleModifyPanel();
		final SaleModifyPanel qPanel = new SaleModifyPanel();
		
		qPanel.remove(qPanel.getComponent(qPanel.getComponentCount()-1));
//		qPanel.getComponent(qPanel.getComponentCount()-1).setSize(120,20);
		
		tabPane.addTab("添加销售记录", null, addPanel, "销售记录添加");
		tabPane.addTab("修改与删除销售记录", null, modPanel, "修改与删除");
		tabPane.addTab("查询销售记录", null, qPanel, "查询销售记录");
		
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

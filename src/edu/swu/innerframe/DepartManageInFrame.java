package edu.swu.innerframe;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.swu.panels.DepartAddPanel;
import edu.swu.panels.DepartModifyPanel;

public class DepartManageInFrame extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 646546L;

	public DepartManageInFrame() {

		setResizable(false);
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("部门管理");
		JTabbedPane tabPane = new JTabbedPane();
		
		final DepartAddPanel addPanel = new DepartAddPanel();
		final DepartModifyPanel modPanel = new DepartModifyPanel();
		final DepartModifyPanel qPanel = new DepartModifyPanel();
		
		qPanel.remove(qPanel.getComponent(qPanel.getComponentCount()-1));
//		qPanel.getComponent(qPanel.getComponentCount()-1).setSize(120,20);
		
		tabPane.addTab("添加部门", null, addPanel, "添加部门");
		tabPane.addTab("修改与删除部门", null, modPanel, "修改与删除部门");
		tabPane.addTab("查询部门", null, qPanel, "查询部门");
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

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
		setTitle("员工管理");
		JTabbedPane tabPane = new JTabbedPane();
		
		final StaffAddPanel addPanel = new StaffAddPanel();
		final StaffModifyPanel modPanel = new StaffModifyPanel();
		final StaffModifyPanel qPanel = new StaffModifyPanel();
		
		qPanel.remove(qPanel.getComponent(qPanel.getComponentCount()-1));
//		qPanel.getComponent(qPanel.getComponentCount()-1).setSize(120,20);
		tabPane.addTab("添加员工", null, addPanel, "员工添加");
		tabPane.addTab("修改与删除员工", null, modPanel, "修改与删除员工");
		tabPane.addTab("查询员工信息", null, qPanel, "查询员工信息");
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

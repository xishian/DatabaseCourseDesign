package edu.swu.innerframe;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.swu.panels.ReimAddPanel;
import edu.swu.panels.ReimModifyPanel;


/**
 * 报账记录
 */

public class ReimManageInFrame extends JInternalFrame {

	private static final long serialVersionUID = 7346866487650731817L;
	
	public ReimManageInFrame() {

		setResizable(false);
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("报账管理");
		JTabbedPane tabPane = new JTabbedPane();
		
		final ReimAddPanel addPanel = new ReimAddPanel();
		final ReimModifyPanel modPanel = new ReimModifyPanel();
		final ReimModifyPanel qPanel = new ReimModifyPanel();
		
		qPanel.remove(qPanel.getComponent(qPanel.getComponentCount()-1));
		qPanel.getComponent(qPanel.getComponentCount()-1).setSize(120,20);
		tabPane.addTab("添加报账记录", null, addPanel, "添加报账记录");
		tabPane.addTab("修改与删除报账记录", null, modPanel, "修改与删除报账记录");
		tabPane.addTab("查询报账记录", null, qPanel, "查询报账记录");
		
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

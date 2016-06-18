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
		setTitle("材料仓库信息管理息管理");
		JTabbedPane tabPane = new JTabbedPane();
		final  MateHouseInfoAddPanel matHouInfoAdd = new MateHouseInfoAddPanel();
		final MateHouseInfoModDelPanel matHouInfoModDel = new MateHouseInfoModDelPanel();
		
		final MateHouseInfoModDelPanel qPanel = new MateHouseInfoModDelPanel();
		
		qPanel.remove(qPanel.getComponent(qPanel.getComponentCount()-1));
//		qPanel.getComponent(qPanel.getComponentCount()-1).setSize(120,20);
		tabPane.addTab("材料仓库信息添加", null, matHouInfoAdd, "添加");
		tabPane.addTab("修改与删除", null, matHouInfoModDel, "修改与删除");
		tabPane.addTab("查询材料仓库信息", null, qPanel, "查询材料仓库信息");
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

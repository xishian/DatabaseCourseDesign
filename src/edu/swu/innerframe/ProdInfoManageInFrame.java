package edu.swu.innerframe;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.swu.panels.ProdInfoAddPanel;
import edu.swu.panels.ProdInfoModDelPanel;
public class ProdInfoManageInFrame extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2355341791650752069L;

	public ProdInfoManageInFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("产品信息管理");
		JTabbedPane tabPane = new JTabbedPane();
		final ProdInfoAddPanel prodInfoAdd = new ProdInfoAddPanel();
		final  ProdInfoModDelPanel prodInfoModDel = new ProdInfoModDelPanel();
		final  ProdInfoModDelPanel qPanel = new ProdInfoModDelPanel();
		
		qPanel.remove(qPanel.getComponent(qPanel.getComponentCount()-1));
//		qPanel.getComponent(qPanel.getComponentCount()-1).setSize(120,20);
		
		tabPane.addTab("产品信息添加", null, prodInfoAdd, "产品添加");
		tabPane.addTab("产品信息修改与删除", null, prodInfoModDel, "修改与删除");
		tabPane.addTab("查询产品信息", null, qPanel, "查询产品信息");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				prodInfoModDel.initComboBox();
				qPanel.initComboBox();
			}
		});
		pack();
		setVisible(true);
	}
}

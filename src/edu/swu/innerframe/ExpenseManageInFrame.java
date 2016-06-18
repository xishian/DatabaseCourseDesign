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
		setTitle("购买材料支出管理");
		JTabbedPane tabPane = new JTabbedPane();
		final BuyMatExpAddingPanel expAdding = new BuyMatExpAddingPanel();
		final BuyMatExpModDelPanel expModDel = new BuyMatExpModDelPanel();
		final BuyMatExpModDelPanel qPanel = new BuyMatExpModDelPanel();
		
		qPanel.remove(qPanel.getComponent(qPanel.getComponentCount()-1));
		
		tabPane.addTab("支出信息添加", null, expAdding, "添加");
		tabPane.addTab("支出信息删除与修改", null,  expModDel, "删除与修改");
		tabPane.addTab("查询支出信息", null,  qPanel, "查询支出信息");
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

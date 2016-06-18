package edu.swu.innerframe;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.swu.panels.OrderAddPanel;
import edu.swu.panels.OrderModifyPanel;

public class OrderManageInFrame extends JInternalFrame {
	
	private static final long serialVersionUID = -4347960053342492120L;

	public OrderManageInFrame() {

		setResizable(false);
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("订单管理");
		JTabbedPane tabPane = new JTabbedPane();
		
		final OrderAddPanel addPanel = new OrderAddPanel();
		final OrderModifyPanel modPanel = new OrderModifyPanel();
		final OrderModifyPanel qPanel = new OrderModifyPanel();
		qPanel.remove(qPanel.getComponent(qPanel.getComponentCount()-1));
//		qPanel.getComponent(qPanel.getComponentCount()-1).setSize(120,20);
		tabPane.addTab("添加订单", null, addPanel, "订单添加");
		tabPane.addTab("修改与删除订单", null, modPanel, "修改与删除订单");
		tabPane.addTab("查询订单", null, qPanel, "查询订单");
		
		
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

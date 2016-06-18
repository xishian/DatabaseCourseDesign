package edu.swu.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.swu.dao.EsmsDAO;
import edu.swu.model.Mate;
public class MateModDelPanel extends JPanel {
	private static final long serialVersionUID = -4535;
	private JTextField MateID;
	private JTextField MateName;
	private JTextField MateUnitPrice;
	private JTextField MateSpec;
	private JTextField MateHouseID;
	private JTextField MateDesc;
	private JButton modifyButton;
	private JButton delButton;
	@SuppressWarnings("rawtypes")
	private JComboBox Mate;
	@SuppressWarnings("rawtypes")
	public MateModDelPanel(){
	setBounds(10, 10, 460, 300);
	setLayout(new GridBagLayout());
	setVisible(true);
		
	final JLabel mateID=new JLabel();
	mateID.setText("材料编号：");
	setupComponet(mateID,0,0,1,0,false);
	
	MateID = new JTextField();
	// 定位材料编号文本框
	setupComponet(MateID, 1, 0, 3, 350, true);
	
	setupComponet(new JLabel("材料名称："), 0, 1, 1, 0, false); 
	MateName = new JTextField();
	//定位材料名称文本框
	setupComponet(MateName, 1, 1, 3, 0, true);

	setupComponet(new JLabel("材料单价："), 0, 2, 1, 0, false);
	MateUnitPrice = new JTextField();
	// 定位材料单价文本框
	setupComponet(MateUnitPrice, 1, 2, 1, 130, true);
	
	setupComponet(new JLabel("材料规格："), 2, 2, 1, 0, false);
	MateSpec = new JTextField();
	setupComponet(MateSpec, 3, 2, 1, 100, true);

	setupComponet(new JLabel("材料仓库编号："), 0, 3, 1, 0, false);
	MateHouseID = new JTextField();
	// 定位材料仓库编号文本框
	setupComponet(MateHouseID, 1, 3, 1, 100, true);

	//setupComponet(new JLabel("材料描述："), 0, 4, 1, 0, true);
	MateDesc = new JTextField();
	//setupComponet(MateDesc, 1, 4, 3, 350, true);

	setupComponet(new JLabel("选择材料"), 0, 5, 1, 0, false);
	Mate = new JComboBox();
	Mate.setPreferredSize(new Dimension(230, 21));
//
	initComboBox();
	Mate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectItem();
			}
	});
	// 定位客户信息的下拉选择框
	setupComponet(Mate, 1, 5, 2, 0, true);
	modifyButton = new JButton("修改");
	delButton = new JButton("删除");
	JPanel panel = new JPanel();
	panel.add(modifyButton);
	panel.add(delButton);
	// 定位按钮
	setupComponet(panel, 3, 5, 1, 0, false);
	// 处理删除按钮的单击事件
	delButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String item = (String)Mate.getSelectedItem();
			
			if (item == null)
				return;
			int confirm = JOptionPane.showConfirmDialog(null, "确认删除支出信息吗？");
			 if (confirm == JOptionPane.YES_OPTION) {
				 int rs = EsmsDAO.delete("tb_mate", "Mate_ID", item.substring(0, item.indexOf(' ')));
				
			 if (rs > 0) {
				 JOptionPane.showMessageDialog(null,
				 "材料记录：" + item.substring(item.indexOf(' ')+1) + "删除成功");
				 Mate.removeItem(item);
			 }
		   }
		}
	});
	// 处理修改按钮的单击事件
	
	modifyButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			 String item = (String) Mate.getSelectedItem();
			 Mate mtInfo = new Mate();
				mtInfo.setMateID(item.substring(0, item.indexOf(' ')));
				mtInfo.setMateName(MateName.getText().trim());
				mtInfo.setMateUnitPrice(MateUnitPrice.getText().trim());
				mtInfo.setMateSpec(MateSpec.getText().trim());
				mtInfo.setMateHouseID(MateHouseID.getText().trim());
				mtInfo.setMateDesc(MateDesc.getText().trim());
				if (EsmsDAO.update("tb_mate", "Mate", mtInfo) == 1)
					JOptionPane.showMessageDialog(null, "修改完成");
				else
					JOptionPane.showMessageDialog(null, "修改失败");
				initComboBox();
		}
	});
}
			// 初始化客户下拉选择框
	@SuppressWarnings("unchecked")
	public void initComboBox() {

		List<String> list =null;
		try {
			list = EsmsDAO.getList("tb_mate", "Mate_ID","Mate_Name");
		} catch (SQLException e) { e.printStackTrace(); }
		Mate.removeAllItems();
		for(String item:list)
			Mate.addItem(item);
		selectItem();
	}
//			// 设置组件位置并添加到容器中
	private void setupComponet(JComponent component, int gridx, int gridy,
					int gridwidth, int ipadx, boolean fill) {
				final GridBagConstraints gridBagConstrains = new GridBagConstraints();
				gridBagConstrains.gridx = gridx;
				gridBagConstrains.gridy = gridy;
				if (gridwidth > 1)
					gridBagConstrains.gridwidth = gridwidth;
				if (ipadx > 0)
					gridBagConstrains.ipadx = ipadx;
				gridBagConstrains.insets = new Insets(5, 1, 3, 1);
				if (fill)
					gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
				add(component, gridBagConstrains);
			}
			
			private void selectItem() {
				 String selectedItem;
				 selectedItem = (String) Mate.getSelectedItem();
				 if(selectedItem == null)
						return;
				 String sql = "select * from tb_mate where Mate_ID='"
						+selectedItem.substring(0, selectedItem.indexOf(' '))+"'";
				 ResultSet rs = EsmsDAO.query(sql);
				 try {
					rs.next();
					MateID.setText(rs.getString(1));
					MateName.setText(rs.getString(2));
					MateUnitPrice.setText(rs.getString(3));
					MateSpec.setText(rs.getString(4));
					MateHouseID.setText(rs.getString(5));
					MateDesc.setText(rs.getString(6));
					
				 } catch (SQLException e) {
					e.printStackTrace();
				 }
			}
}

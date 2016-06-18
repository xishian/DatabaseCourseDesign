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
import edu.swu.model.Rank;
public class ProdLevelModDelPanel extends JPanel {
	private static final long serialVersionUID = -2822887779041244370L;
	private JTextField RankID;
	private JTextField RankName;
	private JTextField MateID;
	private JTextField MateConsume;
	private JButton modifyButton;
	private JButton delButton;
	@SuppressWarnings("rawtypes")
	private JComboBox prodLev;
	@SuppressWarnings("rawtypes")
	public ProdLevelModDelPanel() {
		super();
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);
		
		setupComponet(new JLabel("�����ţ�"), 0, 0, 1, 0, false);
		RankID = new JTextField();
		setupComponet(RankID, 1, 0, 1, 100, true);
		
		setupComponet(new JLabel("�������ƣ�"), 2, 0, 1, 0, false);
		RankName = new JTextField();
		setupComponet(RankName, 3, 0, 3, 0, true);
		
		setupComponet(new JLabel("������ϱ�ţ�"), 0, 1, 1, 0, false);
		MateID = new JTextField();
		setupComponet(MateID, 1, 1, 1, 100, true);
		
		setupComponet(new JLabel("��������������"), 2, 1, 1, 0, false);
		MateConsume = new JTextField();
		setupComponet(MateConsume, 3, 1, 1, 100, true);

		setupComponet(new JLabel("ѡ����������"), 0, 7, 1, 0, false);
		prodLev = new JComboBox();
		prodLev.setPreferredSize(new Dimension(230, 21));
		initComboBox();// ��ʼ������ѡ���
		prodLev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectItem();
			}
		});
		setupComponet(prodLev, 1, 7, 2, 0, true);
		modifyButton = new JButton("�޸�");
		delButton = new JButton("ɾ��");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(delButton);
		// ��λ��ť
		setupComponet(panel, 3, 7, 1, 0, false);
		// ����ɾ����ť�ĵ����¼�
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)prodLev.getSelectedItem();
				
				if (item == null)
					return;
				
				int confirm = JOptionPane.showConfirmDialog(null, "ȷ��ɾ��������Ϣ��");
				if (confirm == JOptionPane.YES_OPTION) {
					 int rs = EsmsDAO.delete("tb_rank", "Rank_ID", item.substring(0, item.indexOf(' ')));
					 if (rs > 0) {
						 JOptionPane.showMessageDialog(null,
						 "�����¼��" + item + "��ɾ���ɹ�");
						 prodLev.removeItem(item);
				 }
			   }
			}
		});
		// �����޸İ�ť�ĵ����¼�
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String item = (String) prodLev.getSelectedItem();
				 Rank prodLev = new Rank();
					prodLev.setRankID(item.substring(0, item.indexOf(' ')));
					prodLev.setRankName(RankName.getText().trim());
					prodLev.setMateID(MateID.getText().trim());
					prodLev.setMateConsume(MateConsume.getText().trim());
					if (EsmsDAO.update("tb_rank", "Rank", prodLev) == 1)
						JOptionPane.showMessageDialog(null, "�޸����");
					else
						JOptionPane.showMessageDialog(null, "�޸�ʧ��");
					initComboBox();
			}
		});
	}

	// ��ʼ���ͻ�����ѡ���
		@SuppressWarnings("unchecked")
		public void initComboBox() {
			List<String> list =null;
			try {
				list = EsmsDAO.getList("tb_rank", "Rank_ID","Rank_Name");
			} catch (SQLException e) { e.printStackTrace(); }
			prodLev.removeAllItems();
			for(String item:list)
				prodLev.addItem(item);
			selectItem();
		}
	
	
	// �������λ�ò���ӵ�������
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
		 selectedItem = (String) prodLev.getSelectedItem();
		 if(selectedItem == null)
				return;
		 String sql = "select * from tb_rank where Rank_ID='"
				+selectedItem.substring(0, selectedItem.indexOf(' '))+"'";
		 ResultSet rs = EsmsDAO.query(sql);
		 try {
			rs.next();
			RankID.setText(rs.getString(1));
			RankName.setText(rs.getString(2));
			MateID.setText(rs.getString(3));
			MateConsume.setText(rs.getString(4));
		
		 } catch (SQLException e) {
			e.printStackTrace();
		 }
	}
}

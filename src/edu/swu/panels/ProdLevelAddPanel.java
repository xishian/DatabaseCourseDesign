package edu.swu.panels;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.swu.dao.EsmsDAO;
import edu.swu.model.Rank;
public class ProdLevelAddPanel extends JPanel {
	private static final long serialVersionUID = -3718658892117470330L;
	private JTextField RankName;
	private JTextField MateID;
	private JTextField MateConsume;
	private JButton saveButton;
	private JButton resetButton;
	public ProdLevelAddPanel() {
		super();
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);
		
		setupComponet(new JLabel("�������ƣ�"), 0, 0, 1, 0, false);
		RankName = new JTextField();
		setupComponet(RankName, 1, 0, 3, 350, true);
		
		setupComponet(new JLabel("������ϱ�ţ�"), 0, 1, 1, 0, false);
		MateID = new JTextField();
		setupComponet(MateID, 1, 1, 1, 100, true);
		
		setupComponet(new JLabel("��������������"), 2, 1, 1, 0, false);
		MateConsume = new JTextField();
		setupComponet(MateConsume, 3, 1, 1, 100, true);
		
		saveButton = new JButton("����");
		// ��λ���水ť
		setupComponet(saveButton, 1, 7, 1, 0, false);
		saveButton.addActionListener(new SaveButtonActionListener());
		resetButton = new JButton("����");
		// ��λ���ð�ť
		setupComponet(resetButton, 3, 7, 1, 0, false);
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RankName.setText("");
				MateID.setText("");
				MateConsume.setText("");
			}
		});
	}
	// �������λ�ò���ӵ�������
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		add(component, gridBagConstrains);
	}
	// ���水ť���¼�������
	private final class SaveButtonActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			if (RankName.getText().equals("")
					|| MateID.getText().equals("")
					|| MateConsume.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "����дȫ����Ϣ");
				return;
			}
			
			ResultSet set = EsmsDAO.query("select max(Rank_ID) from tb_rank");
			String id = null;
			try {
				if (set != null && set.next()) {
					String sid = set.getString(1);
					if (sid == null)
						id = "10001";
					else {
						id = String.valueOf(Integer.parseInt(sid)+1);
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			Rank prodLev = new Rank();
			prodLev.setRankID(id);
			prodLev.setRankName(RankName.getText().trim());
			prodLev.setMateID(MateID.getText().trim());
			prodLev.setMateConsume(MateConsume.getText().trim());
			EsmsDAO.addRank(prodLev);
			JOptionPane.showMessageDialog(ProdLevelAddPanel.this, "�ѳɹ��������������Ϣ",
					"�����Ϣ", JOptionPane.INFORMATION_MESSAGE);
			resetButton.doClick();
		}
	}
	
}
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
import edu.swu.model.Expense;
public class BuyMatExpAddingPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7938289395047395853L;
	private JTextField MateID;
	private JTextField MateName;
	private JTextField MateUnitPrice;
	private JTextField MateAmount;
	private JTextField MateToPrice;
	private JTextField BuyTime;
	private JTextField StaffID;
	private JTextField Memo;
	
	private JButton saveButton; 
	private JButton resetButton;
	public BuyMatExpAddingPanel() {
		super();
		setBounds(10, 10, 460, 300);
		setLayout(new GridBagLayout());
		setVisible(true);
			
		setupComponet(new JLabel("���ϱ�ţ�"), 0, 0, 1, 0, false); 
		MateID = new JTextField();
		setupComponet(MateID, 1, 0, 1, 100, true);

		setupComponet(new JLabel("�������ƣ�"), 2, 0, 1, 0, false);
		MateUnitPrice = new JTextField();
		setupComponet(MateUnitPrice, 3, 0, 1,100, true);
//		MateUnitPrice.addKeyListener(new InputKeyListener());
		
		setupComponet(new JLabel("���ϵ��ۣ�"), 0, 1, 1, 0, false);
		MateUnitPrice = new JTextField();
		setupComponet(MateUnitPrice, 1, 1, 1, 100, true);

		setupComponet(new JLabel("����������"), 2, 1, 1, 0, false);
		MateAmount = new JTextField();
		setupComponet(MateAmount, 3, 1, 1, 100, true);
		
		setupComponet(new JLabel("�����ܼۣ�"), 0, 2, 1, 0, false);
		MateToPrice = new JTextField();
		setupComponet(MateToPrice, 1, 2, 1, 100, true);
		
		setupComponet(new JLabel("����ʱ�䣺"), 2, 2, 1, 0, false);
		BuyTime = new JTextField();
		setupComponet(BuyTime, 3, 2, 1, 100, true);
		
		setupComponet(new JLabel("����Ա����ţ�"), 0, 3, 1, 0, false);
		StaffID = new JTextField();
		setupComponet(StaffID, 1, 3, 1, 100, true);
		
		setupComponet(new JLabel("��ע��"), 2, 3, 1, 0, false);
		Memo = new JTextField();
		setupComponet(Memo, 3, 3, 3, 100, true);

		saveButton = new JButton("����");
		setupComponet(saveButton, 1, 6, 1, 0, false);
		saveButton.addActionListener(new SaveButtonActionListener());
		
		resetButton = new JButton("����");
		setupComponet(resetButton, 3, 6, 1, 0, false);
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MateID.setText("");
				MateName.setText("");
				MateUnitPrice.setText("");
				MateAmount.setText("");
				MateToPrice.setText("");
				BuyTime.setText("");
				StaffID.setText("");
				Memo.setText("");
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
			if (MateName.getText().trim().equals("")
					|| MateUnitPrice.getText().trim().equals("")
					|| MateAmount.getText().trim().equals("")
					|| MateToPrice.getText().trim().equals("")
					|| StaffID.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "������Ϣδ����(ʱ�䡢��ע��Ϊ��)��"
						,"Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (MateName.getText().trim().equals("")
					//|| MateID.getText().equals("")
					|| MateUnitPrice.getText().trim().equals("")
					|| MateAmount.getText().trim().equals("")
					|| MateToPrice.getText().trim().equals("")
					|| BuyTime.getText().trim().equals("")
					|| StaffID.getText().trim().equals("")
					|| Memo.getText().trim().equals("")) {
				int right = JOptionPane.showConfirmDialog(null, "��Ϣ��������ȷ���ύ��");
				if(right==JOptionPane.OK_OPTION){}
				else
					return;
			}
//			ResultSet haveUser = EsmsDAO
//					.query("select * from tb_expense where Mate_Name='"
//							+ MateName.getText().trim() + "'");
//			try {
//				if (haveUser.next()){
//					System.out.println("error");
//					JOptionPane.showMessageDialog(BuyMatExpAddingPanel.this,
//							"�������֧����Ϣ���ʧ��", "�����Ϣ",
//							JOptionPane.INFORMATION_MESSAGE);
//					return;
//				}
//			} catch (Exception er) {
//				er.printStackTrace();
//			}
			ResultSet set = EsmsDAO.query("select max(Expense_ID) from tb_expense");
			
			String id = "";
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
			Expense purMatExp = new Expense();
			purMatExp.setMateID(id);
			purMatExp.setMateName(MateName.getText().trim());
			purMatExp.setMateUnitPrice(MateUnitPrice.getText().trim());
			purMatExp.setMateAmount(MateAmount.getText().trim());
			purMatExp.setMateToPrice(MateToPrice.getText().trim());
			purMatExp.setBuyTime(BuyTime.getText().trim());
			purMatExp.setStaffID(StaffID.getText().trim());
			purMatExp.setMemo(Memo.getText().trim());
			
			EsmsDAO.addExpense(purMatExp);

			JOptionPane.showMessageDialog(BuyMatExpAddingPanel.this, "�ѳɹ������Ϣ",
					"���֧����Ϣ", JOptionPane.INFORMATION_MESSAGE);
			resetButton.doClick();
		}
	}

}
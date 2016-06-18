package edu.swu.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import edu.swu.dao.EsmsDAO;
import edu.swu.model.User;
public class LoginFrame extends JFrame {
	
	private static final long serialVersionUID = -7825770133226381913L;
	private JLabel userLabel;
	private JLabel passLabel;
	private JButton exit;
	private JButton login;
	private User user; //��ǰ��ͼ��¼���û�
	
	boolean right = false;
	
	public LoginFrame(MainFrame frame) {
		setTitle("��ӭ��½");
		final JPanel panel = new LoginPanel();
		panel.setLayout(null);
		getContentPane().add(panel);
		setBounds(300, 200, panel.getWidth(), panel.getHeight());
		userLabel = new JLabel();
		userLabel.setText("�û�����");
		userLabel.setBounds(100, 135, 200, 18);
		panel.add(userLabel);
		final JTextField userName = new JTextField();
		userName.setBounds(150, 135, 200, 18);
		panel.add(userName);
		passLabel = new JLabel();
		passLabel.setText("��  �룺");
		passLabel.setBounds(100, 165, 200, 18);
		panel.add(passLabel);
		final JPasswordField userPassword = new JPasswordField();
		userPassword.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == 10)
					login.doClick();
			}
		});
		userPassword.setBounds(150, 165, 200, 18);
		panel.add(userPassword);
		
		login = new JButton();
		login.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(final ActionEvent e) {
				//�����ݿ��в�������¼�û��������Ϣ���������ֺ�����ȥ�����û���id���ҵ��˾���Ϊ������֤
				try {
					user = EsmsDAO.getUser(userName.getText(), userPassword.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(user == null){
					JOptionPane.showMessageDialog(null, "����������������룡����");
					return;
				}
				else{
					JOptionPane.showMessageDialog(null, "��¼�ɹ���");
					setVisible(false);
					frame.login = true;
					frame.setVisible(true);
					
				}
					//JOptionPane.showMessageDialog(null, "��¼�ɹ���");
				
				setVisible(false);
			}
		});
		login.setText("��¼");
		login.setBounds(180, 195, 60, 18);
		panel.add(login);
		
		exit = new JButton();
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				System.exit(0);
			}
		});
		exit.setText("�˳�");
		exit.setBounds(260, 195, 60, 18);
		panel.add(exit);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}

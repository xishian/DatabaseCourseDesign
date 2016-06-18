package edu.swu.view;

//����������ڣ�������ϵͳ������������

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class MainFrame{
	private JDesktopPane desktopPane;
	private JFrame frame;
	private JLabel backLabel;
	private int frameWidth = 1000;
	private int frameHeight = 700;
	
	public boolean login = false;
	
	//����ΪdesktopPane��̬���inner frame
	private Map<String, JInternalFrame> innerframes = new HashMap<String, JInternalFrame>();
	
	static {
		try {
			//�޸ĳ����Ĭ�Ϲ۸У�����ѡ�����ϵͳĬ�ϵĹ۸�
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public MainFrame() {
		//��ʼ��frame�����ø��ֲ���
		frame = new JFrame("��ҵ��������ϵͳ");
		frame.getContentPane().setBackground(new Color(0,138,212));
		frame.addComponentListener(new FrameListener());
		frame.getContentPane().setLayout(new BorderLayout());
		//frame.setBounds(100, 100, 800, 600);
		frame.setSize(frameWidth,frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLocationRelativeTo(null);
		
		//����������ĵ�����Ļ���ĵ����
		Toolkit toolKit = Toolkit.getDefaultToolkit();
		Dimension dimen = toolKit.getScreenSize();
		double width =  dimen.getWidth();
		//double height = dimen.getHeight();
		frame.setLocation((int)(width-frameWidth)/2, 0);
		

		//Ϊframe�ڲ�������
		addMenuBar();
		addLeftPanel();
//		addCenterPanel();
		
		desktopPane = new JDesktopPane();
		backLabel = new JLabel();// ������ǩ
		backLabel.setVerticalAlignment(SwingConstants.TOP);
		backLabel.setHorizontalAlignment(SwingConstants.CENTER);
		updateBackImage(); // ���»��ʼ������ͼƬ
		desktopPane.add(backLabel, new Integer(Integer.MIN_VALUE));
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		addBottomPanel();
		
		frame.setVisible(true);
	}
	
	
	private void addBottomPanel() {
		
	}

	
	private void addMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("��ʼ");
//		JMenuItem linkItem = addItem(menu, "�������ݿ�");
//		linkItem.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
		JMenuItem loginItem = addItem(menu,"��¼");
		loginItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new LoginFrame(MainFrame.this);
			}
		});
		menuBar.add(menu);
		
		JMenu optionMenu = new JMenu("ѡ��");
		JMenuItem modBackImageItem = new JMenuItem("�޸ı���ͼƬ");
		modBackImageItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
//				backLabel.setText(null);
				JFileChooser file=new JFileChooser();
				  file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
			        file.showDialog(new JLabel(), "ѡ��");
			        if(file==null) {}
			    int backw = frame.getWidth();
			    int backh = frame.getHeight();
				backLabel.setSize(backw, backh);
			    ImageIcon backImg=new ImageIcon(file.getSelectedFile().getPath());
			    backImg.setImage(backImg.getImage().getScaledInstance(backw,backh,Image.SCALE_DEFAULT));
			    backLabel.setIcon(backImg);
			}

		}); 

		JMenuItem modLFItem = new JMenuItem("�޸Ĺ۸�");
		modLFItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int temp = JOptionPane.showConfirmDialog(null, "�޸Ĺ۸�ǰ��ر������Ӵ��ڡ�");
				if(temp == JOptionPane.OK_OPTION){
					String lf = UIManager.getLookAndFeel().getClass().getName();
					
					//System.out.println(lf.toString());
					if(lf.compareTo("javax.swing.plaf.metal.MetalLookAndFeel")==0){
						try {
							UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
								| UnsupportedLookAndFeelException e1) {
							e1.printStackTrace();
						}
					}
					else{
						try {
							UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
								| UnsupportedLookAndFeelException e1) {
							e1.printStackTrace();
						}
					}
				}
				else{
					
				}
			}
		});
		
		optionMenu.add(modLFItem);
		optionMenu.addSeparator();
		optionMenu.add(modBackImageItem);
		menuBar.add(optionMenu);
		
		JMenu menuHelp = new JMenu("����");
		JMenuItem contactItem = addItem(menuHelp,"��ϵ����");
		contactItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"�ﺣ��:\nTEL:18161167025\nMAIL:wwwsunhaixian@163.com\n"
						+ "����:\nTEL:13368060218\nMAIL:447811210@163.com",
						"��ϵ����", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		JMenuItem infoItem = addItem(menuHelp,"ʹ��˵��");
		infoItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"��ô������ʹ��˵����", "ʹ��˵��", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuBar.add(menuHelp);
		
		frame.add(menuBar, BorderLayout.NORTH);
	}
	
	public void addLeftPanel(){
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(12, 1));
	//	leftPanel.setSize((int)frame.getWidth()/5, frame.getHeight());
		
		//���Ҳ��panel��Ӱ�ť
		JButton clentManageBtn = createButton("�ͻ�����","ClientManageInFrame");
		leftPanel.add(clentManageBtn);
		JButton departManageBtn  = createButton("���Ź���","DepartManageInFrame");
		leftPanel.add(departManageBtn);
		JButton orderManageBtn = createButton("��������","OrderManageInFrame");
		leftPanel.add(orderManageBtn);
		JButton reimManageBtn =  createButton("���˹���","ReimManageInFrame");
		leftPanel.add(reimManageBtn);
		JButton salesManageBtn = createButton("���۹���","SalesManageInFrame");
		leftPanel.add(salesManageBtn);
		JButton staffManageBtn = createButton("Ա������","StaffManageInFrame");
		leftPanel.add(staffManageBtn);
		//�����޸�
		JButton clentManageBtn2 = createButton("���ϲֿ�","MateHouseInfoManageInFrame");
		leftPanel.add(clentManageBtn2);
		JButton departManageBtn2  = createButton("���Ϲ���","MateManageInFrame");
		leftPanel.add(departManageBtn2);
		JButton orderManageBtn2 = createButton("��Ʒ�ֿ�","ProdHouseManageInFrame");
		leftPanel.add(orderManageBtn2);
		JButton reimManageBtn2 =  createButton("��Ʒ����","ProdInfoManageInFrame");
		leftPanel.add(reimManageBtn2);
		JButton salesManageBtn2 = createButton("��������","ProdLevelManageInFrame");
		leftPanel.add(salesManageBtn2);
		JButton staffManageBtn2 = createButton("֧������","ExpenseManageInFrame");
		leftPanel.add(staffManageBtn2);
		
		leftPanel.setSize(200,frame.getHeight());
		frame.add(leftPanel,BorderLayout.WEST);
	}
	
	//֮����Ҫ����д����Ϊ�˸���ť����¼�
	private JButton createButton(String text,String clsName) {
		//ImageButton btn = new ImageButton(text);
		JButton btn = new JButton();
		btn.setToolTipText(text);
		btn.setAction(new OpenFrameAction(clsName));
		btn.setMargin(new Insets(0, 0, 0, 0));
		btn.setHideActionText(true);
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		btn.setIcon(new ImageIcon("img/"+text+".jpg"));
		btn.setText(null);
		btn.addActionListener(new LoginListener());
		return btn;
	}

   class LoginListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(login == false){
				JOptionPane.showMessageDialog(null, "��ʼ����ǰ����е�¼��");
				frame.setVisible(false);
				new LoginFrame(MainFrame.this);
			}
				
				
		}  
   }
	
	// ������˵���ĵ����¼�������
	protected final class OpenFrameAction extends AbstractAction {
		private static final long serialVersionUID = -7111984164804994431L;
		private String clsName = null;
		
		public OpenFrameAction(String clsName) {
			this.clsName = clsName;
			putValue(Action.NAME, clsName);
			putValue(Action.SHORT_DESCRIPTION, clsName);
			putValue(Action.SMALL_ICON, null);
		}
		
		public void actionPerformed(final ActionEvent e) {
			//��inner frames�в���ָ�����ֵ�innerFrame��û�оʹ���������һ���᷵��һ��frame����
			JInternalFrame innerframe = getInternalFrame(clsName);
			
			innerframe.addInternalFrameListener(new InternalFrameAdapter() {
				// ���ڲ�����չ�ʱ�����ڲ���������inner frames����������ô��塣
				public void internalFrameClosed(InternalFrameEvent e) {
					innerframes.remove(clsName);
				}
			});
			
			//��һ�δ���inner frameʱ�Ὣ����ӵ�desktopPane��
			if (innerframe.getDesktopPane() == null) {
				desktopPane.add(innerframe);
				innerframe.setVisible(true);
			}
			
			try {
				innerframe.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		}
		private JInternalFrame getInternalFrame(String clsName) {
			JInternalFrame inframe = null;
			if (!innerframes.containsKey(clsName)) {
				try {
					//��̬��������
					//��ô����Ϊ�˼ӿ����������ٶȣ�����ڳ�������ʱȥһһ�������󣬻Ῠ��
					inframe = (JInternalFrame)Class.forName("edu.swu.innerframe." + clsName).newInstance();
					innerframes.put(clsName, inframe);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else{
				inframe = innerframes.get(clsName);
			}

			return inframe;
		}
	}
	
	private JMenuItem addItem(JMenu menu,String text){
		JMenuItem item = new JMenuItem(text);
		menu.add(item);
		return item;
	}
	
	// ���±���ͼƬ�ķ���
	private void updateBackImage() {
		if (backLabel != null) {
			int backw = frame.getWidth();
			int backh = frame.getHeight();
			backLabel.setSize(backw, backh);
			backLabel.setText("<html><body><image width='" + backw
					+ "' height='" + backh + "' src="
					+ MainFrame.this.getClass().getResource("welcome.png")
					+ "'></img></body></html>");
		}
	}

	
	//���������
	class FrameListener extends ComponentAdapter {
			public void componentResized(final ComponentEvent e) {
				updateBackImage();
			}
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});
	}


	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}//end MainFrame


	class InputKeyListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
		String key="-0123456789"+(char)8;
		if(key.indexOf(e.getKeyChar())<0){
			e.consume();
		}
	}
	}

package edu.swu.view;

//程序的主窗口，是整个系统启动的主界面

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
	
	//用来为desktopPane动态添加inner frame
	private Map<String, JInternalFrame> innerframes = new HashMap<String, JInternalFrame>();
	
	static {
		try {
			//修改程序的默认观感，这里选择的是系统默认的观感
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public MainFrame() {
		//初始化frame，设置各种参数
		frame = new JFrame("企业供销管理系统");
		frame.getContentPane().setBackground(new Color(0,138,212));
		frame.addComponentListener(new FrameListener());
		frame.getContentPane().setLayout(new BorderLayout());
		//frame.setBounds(100, 100, 800, 600);
		frame.setSize(frameWidth,frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLocationRelativeTo(null);
		
		//将界面的中心点与屏幕中心点对齐
		Toolkit toolKit = Toolkit.getDefaultToolkit();
		Dimension dimen = toolKit.getScreenSize();
		double width =  dimen.getWidth();
		//double height = dimen.getHeight();
		frame.setLocation((int)(width-frameWidth)/2, 0);
		

		//为frame内部添加组件
		addMenuBar();
		addLeftPanel();
//		addCenterPanel();
		
		desktopPane = new JDesktopPane();
		backLabel = new JLabel();// 背景标签
		backLabel.setVerticalAlignment(SwingConstants.TOP);
		backLabel.setHorizontalAlignment(SwingConstants.CENTER);
		updateBackImage(); // 更新或初始化背景图片
		desktopPane.add(backLabel, new Integer(Integer.MIN_VALUE));
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		addBottomPanel();
		
		frame.setVisible(true);
	}
	
	
	private void addBottomPanel() {
		
	}

	
	private void addMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("开始");
//		JMenuItem linkItem = addItem(menu, "连接数据库");
//		linkItem.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
		JMenuItem loginItem = addItem(menu,"登录");
		loginItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new LoginFrame(MainFrame.this);
			}
		});
		menuBar.add(menu);
		
		JMenu optionMenu = new JMenu("选项");
		JMenuItem modBackImageItem = new JMenuItem("修改背景图片");
		modBackImageItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
//				backLabel.setText(null);
				JFileChooser file=new JFileChooser();
				  file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
			        file.showDialog(new JLabel(), "选择");
			        if(file==null) {}
			    int backw = frame.getWidth();
			    int backh = frame.getHeight();
				backLabel.setSize(backw, backh);
			    ImageIcon backImg=new ImageIcon(file.getSelectedFile().getPath());
			    backImg.setImage(backImg.getImage().getScaledInstance(backw,backh,Image.SCALE_DEFAULT));
			    backLabel.setIcon(backImg);
			}

		}); 

		JMenuItem modLFItem = new JMenuItem("修改观感");
		modLFItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int temp = JOptionPane.showConfirmDialog(null, "修改观感前请关闭所有子窗口。");
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
		
		JMenu menuHelp = new JMenu("帮助");
		JMenuItem contactItem = addItem(menuHelp,"联系我们");
		contactItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"孙海弦:\nTEL:18161167025\nMAIL:wwwsunhaixian@163.com\n"
						+ "王龙:\nTEL:13368060218\nMAIL:447811210@163.com",
						"联系我们", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		JMenuItem infoItem = addItem(menuHelp,"使用说明");
		infoItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"怎么可能有使用说明！", "使用说明", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuBar.add(menuHelp);
		
		frame.add(menuBar, BorderLayout.NORTH);
	}
	
	public void addLeftPanel(){
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(12, 1));
	//	leftPanel.setSize((int)frame.getWidth()/5, frame.getHeight());
		
		//向右侧的panel添加按钮
		JButton clentManageBtn = createButton("客户管理","ClientManageInFrame");
		leftPanel.add(clentManageBtn);
		JButton departManageBtn  = createButton("部门管理","DepartManageInFrame");
		leftPanel.add(departManageBtn);
		JButton orderManageBtn = createButton("订单管理","OrderManageInFrame");
		leftPanel.add(orderManageBtn);
		JButton reimManageBtn =  createButton("报账管理","ReimManageInFrame");
		leftPanel.add(reimManageBtn);
		JButton salesManageBtn = createButton("销售管理","SalesManageInFrame");
		leftPanel.add(salesManageBtn);
		JButton staffManageBtn = createButton("员工管理","StaffManageInFrame");
		leftPanel.add(staffManageBtn);
		//留待修改
		JButton clentManageBtn2 = createButton("材料仓库","MateHouseInfoManageInFrame");
		leftPanel.add(clentManageBtn2);
		JButton departManageBtn2  = createButton("材料管理","MateManageInFrame");
		leftPanel.add(departManageBtn2);
		JButton orderManageBtn2 = createButton("产品仓库","ProdHouseManageInFrame");
		leftPanel.add(orderManageBtn2);
		JButton reimManageBtn2 =  createButton("产品管理","ProdInfoManageInFrame");
		leftPanel.add(reimManageBtn2);
		JButton salesManageBtn2 = createButton("生产级别","ProdLevelManageInFrame");
		leftPanel.add(salesManageBtn2);
		JButton staffManageBtn2 = createButton("支出管理","ExpenseManageInFrame");
		leftPanel.add(staffManageBtn2);
		
		leftPanel.setSize(200,frame.getHeight());
		frame.add(leftPanel,BorderLayout.WEST);
	}
	
	//之所以要单独写，是为了给按钮添加事件
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
				JOptionPane.showMessageDialog(null, "开始操作前请进行登录！");
				frame.setVisible(false);
				new LoginFrame(MainFrame.this);
			}
				
				
		}  
   }
	
	// 主窗体菜单项的单击事件监听器
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
			//从inner frames中查找指定名字的innerFrame，没有就创建，所以一定会返回一个frame对象
			JInternalFrame innerframe = getInternalFrame(clsName);
			
			innerframe.addInternalFrameListener(new InternalFrameAdapter() {
				// 在内部窗体闭关时，从内部窗体容器inner frames对象中清除该窗体。
				public void internalFrameClosed(InternalFrameEvent e) {
					innerframes.remove(clsName);
				}
			});
			
			//第一次创建inner frame时会将其添加到desktopPane中
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
					//动态创建对象
					//这么做是为了加快程序的启动速度，如果在程序启动时去一一创建对象，会卡死
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
	
	// 更新背景图片的方法
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

	
	//窗体监听器
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

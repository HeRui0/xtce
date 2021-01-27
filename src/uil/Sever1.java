package uil;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import component.userManageComponent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.JSplitPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sever1 extends JFrame {

	private JPanel contentPane;

	public Sever1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//窗口大小
		setBounds(100, 100, 800, 500);
		setLocationRelativeTo(null);
		this.setResizable(false);

		//菜单栏设置
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("设置");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("切换账号");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logOn l1 = new logOn();
				l1.setVisible(true);
				dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("退出程序");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//分割面板
		JSplitPane splitPane = new JSplitPane();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(splitPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(splitPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
		);

		/**
		 * 创建左侧菜单目录
		 */
		//左侧树--功能设置
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("管理菜单");
		DefaultMutableTreeNode userManage = new DefaultMutableTreeNode("用户管理");
		DefaultMutableTreeNode severManage = new DefaultMutableTreeNode("服务管理");
		DefaultMutableTreeNode zhilingManage = new DefaultMutableTreeNode("指令库管理");
		DefaultMutableTreeNode yichangManage = new DefaultMutableTreeNode("异常管理");
		DefaultMutableTreeNode zidingyiManage = new DefaultMutableTreeNode("自定义指令");
		DefaultMutableTreeNode shiliManage = new DefaultMutableTreeNode("业务实例化");
		DefaultMutableTreeNode zhurubaoManage = new DefaultMutableTreeNode("注入包生成");
		
		//添加子节点
		root.add(userManage);
		root.add(severManage);
		root.add(zhilingManage);
		root.add(yichangManage);
		root.add(zidingyiManage);
		root.add(shiliManage);
		root.add(zhurubaoManage);
		//生成树目录
		JTree tree = new JTree(root);
		//设置树目录选择监听器
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				//得到当前选中的节点对象
				Object lastPathComponent = e.getNewLeadSelectionPath().getLastPathComponent();
				/**
				 * 创建右侧功能页面
				 */
				if(userManage.equals(lastPathComponent)) {
					userManageComponent user_panel = new userManageComponent();
					//JLabel lblNewLabel = new JLabel("这里进行用户管理。。");
					splitPane.setRightComponent(user_panel);
				}else if(severManage.equals(lastPathComponent)) {
					//severManageComponent sever_panel = new severManageComponent();
					JLabel lblNewLabel = new JLabel("这里进行服务管理。。");
					splitPane.setRightComponent(lblNewLabel);
				}else if(zhilingManage.equals(lastPathComponent)) {
					JLabel lblNewLabel = new JLabel("这里进行zhilingManage。。");
					splitPane.setRightComponent(lblNewLabel);
				}else if(yichangManage.equals(lastPathComponent)) {
					JLabel lblNewLabel = new JLabel("这里进行yichangManage。。");
					splitPane.setRightComponent(lblNewLabel);
				}else if(zidingyiManage.equals(lastPathComponent)) {
					JLabel lblNewLabel = new JLabel("这里进行zidingyiManage。。");
					splitPane.setRightComponent(lblNewLabel);
				}else if(shiliManage.equals(lastPathComponent)) {
					JLabel lblNewLabel = new JLabel("这里进行shiliManage。。");
					splitPane.setRightComponent(lblNewLabel);
				}else if(zhurubaoManage.equals(lastPathComponent)) {
					JLabel lblNewLabel = new JLabel("这里进行zhurubaoManage。。");
					splitPane.setRightComponent(lblNewLabel);
				}
			}
		});
		splitPane.setLeftComponent(tree);

		contentPane.setLayout(gl_contentPane);
	}
}

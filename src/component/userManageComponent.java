package component;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class userManageComponent extends Box {
	final int WIDTH=850;//功能区宽度
	final int HEIGHT=600;//功能区高度

	private JTable table;
	private Vector<String>  titles;
	private Vector<Vector> tableData;
	private TableModel tableModel;

	/**
	 * Creates a <code>Box</code> that displays its components
	 * along the specified axis.
	 *
	 * @param axis can be {@link BoxLayout#X_AXIS},
	 *             {@link BoxLayout#Y_AXIS},
	 *             {@link BoxLayout#LINE_AXIS} or
	 *             {@link BoxLayout#PAGE_AXIS}.
	 * @throws AWTError if the <code>axis</code> is invalid
	 * @see #createHorizontalBox
	 * @see #createVerticalBox
	 */
	public userManageComponent() {
		//垂直布局
		super(BoxLayout.Y_AXIS);
		//组装视图
		JPanel btnPanel = new JPanel();
		//设置面板宽高
		//btnPanel.setMaximumSize(new Dimension(WIDTH, 40));
		//设置流水右向布局
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JButton addBtn = new JButton("添加");
		JButton updateBtn = new JButton("修改");
		JButton deleteBtn = new JButton("删除");

		btnPanel.add(addBtn);
		btnPanel.add(updateBtn);
		btnPanel.add(deleteBtn);

		addBtn.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//弹出添加对话框
				new adduserDialog().setVisible(true);
			}
		});
		updateBtn.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//弹出添加对话框
				new updateDialog().setVisible(true);
			}
		});

		this.add(btnPanel);

		String[] columnNames = {"编号","权限", "研究室", "姓名", "联系方式", "邮箱", "密码"};    //定义表格列明数组
		String[][] tableValues = {{"1","管理", "系统室", "何睿", "15638147863", "1050637025@qq.com", "12345"},
				{"2","管理", "系统室", "吕良庆", "18675849032", "llq@nssc.com", "12345"},
				{"3","使用", "系统室", "周永吉", "15638893863", "1050637025@qq.com", "12345"}
		};

		JTable table = new JTable(tableValues, columnNames);
		//创建显示表格的滚动面板
		JScrollPane scrollPane = new JScrollPane(table);

/**
 //组装表格
 String[] ts = {"账号","密码"};
 titles = new Vector<>();
 for (String title : ts){
 titles.add(title);
 }

 tableData = new Vector<>();

 tableModel = new DefaultTableModel(tableData,titles);
 table = new JTable(tableModel){

@Override public boolean isCellEditable(int row, int column) {
return false;
}
};
 //设置只能选中一行
 table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 */

		this.add(scrollPane);
		//requestData();

		//请求数据
		//public void requestData(){}
	}
}

//添加用户界面
class adduserDialog extends JDialog{
	final  int WIDTH = 400;
	final  int HEIGHT = 400;

	public adduserDialog(){
		//super();
		setBounds(100, 100, 400, 400);
		setLocationRelativeTo(null);//设置对话框位于屏幕中间
		this.setResizable(false);
		Box vBox =Box.createVerticalBox();

		//{"编号","权限", "研究室", "姓名", "联系方式", "邮箱", "密码"}
		//id power lab relname tel mail psd
		Box idBox = Box.createHorizontalBox();
		JLabel id = new JLabel("   编  号：   ");
		JTextField idField = new JTextField(15);
		idBox.add(id);
		idBox.add(idField);

		Box powerBox = Box.createHorizontalBox();
		JLabel power = new JLabel("   权  限：   ");
		JTextField powerField = new JTextField(15);
		powerBox.add(power);
		powerBox.add(powerField);

		Box labBox = Box.createHorizontalBox();
		JLabel lab = new JLabel("  研究室：  ");
		JTextField labField = new JTextField(15);
		labBox.add(lab);
		labBox.add(labField);

		Box nameBox = Box.createHorizontalBox();
		JLabel name = new JLabel("   姓  名：   ");
		JTextField nameField = new JTextField(15);
		nameBox.add(name);
		nameBox.add(nameField);

		Box telBox = Box.createHorizontalBox();
		JLabel tel = new JLabel("联系方式：");
		JTextField telField = new JTextField(15);
		telBox.add(tel);
		telBox.add(telField);

		Box mailBox = Box.createHorizontalBox();
		JLabel mail = new JLabel("   邮  箱：   ");
		JTextField mailField = new JTextField(15);
		mailBox.add(mail);
		mailBox.add(mailField);

		Box psdBox = Box.createHorizontalBox();
		JLabel psd = new JLabel("   密  码：   ");
		JTextField psdField = new JTextField(15);
		psdBox.add(psd);
		psdBox.add(psdField);

		//“添加”按钮
		Box addBox = Box.createHorizontalBox();
		JButton addBtn =new JButton("添加");
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		addBox.add(addBtn);

		//组装
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(idBox);
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(powerBox);
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(labBox);
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(nameBox);
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(telBox);
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(mailBox);
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(psdBox);
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(addBox);
		vBox.add(Box.createVerticalStrut(15));

		Box hBox = Box.createHorizontalBox();
		hBox.add(Box.createHorizontalStrut(20));
		hBox.add(vBox);
		hBox.add(Box.createHorizontalStrut(20));

		this.add(hBox);
	}
}

class updateDialog extends JDialog {
	final int WIDTH = 400;
	final int HEIGHT = 400;

	public updateDialog() {
		//super();
		setBounds(100, 100, 400, 400);
		setLocationRelativeTo(null);//设置对话框位于屏幕中间
		this.setResizable(false);
		Box vBox = Box.createVerticalBox();

		//"1","管理", "系统室", "何睿", "15638147863", "1050637025@qq.com", "12345"
		//{"编号","权限", "研究室", "姓名", "联系方式", "邮箱", "密码"}
		//id power lab relname tel mail psd
		Box idBox = Box.createHorizontalBox();
		JLabel id = new JLabel("   编  号：   ");
		JTextField idField = new JTextField("1",15);
		idBox.add(id);
		idBox.add(idField);

		Box powerBox = Box.createHorizontalBox();
		JLabel power = new JLabel("   权  限：   ");
		JTextField powerField = new JTextField("管理",15);
		powerBox.add(power);
		powerBox.add(powerField);

		Box labBox = Box.createHorizontalBox();
		JLabel lab = new JLabel("  研究室：  ");
		JTextField labField = new JTextField("系统室",15);
		labBox.add(lab);
		labBox.add(labField);

		Box nameBox = Box.createHorizontalBox();
		JLabel name = new JLabel("   姓  名：   ");
		JTextField nameField = new JTextField("何睿",15);
		nameBox.add(name);
		nameBox.add(nameField);

		Box telBox = Box.createHorizontalBox();
		JLabel tel = new JLabel("联系方式：");
		JTextField telField = new JTextField("15638147863",15);
		telBox.add(tel);
		telBox.add(telField);

		Box mailBox = Box.createHorizontalBox();
		JLabel mail = new JLabel("   邮  箱：   ");
		JTextField mailField = new JTextField("1050637025@qq.com",15);
		mailBox.add(mail);
		mailBox.add(mailField);

		Box psdBox = Box.createHorizontalBox();
		JLabel psd = new JLabel("   密  码：   ");
		JTextField psdField = new JTextField("12345",15);
		psdBox.add(psd);
		psdBox.add(psdField);

		//“添加”按钮
		Box addBox = Box.createHorizontalBox();
		JButton addBtn = new JButton("修改");
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		addBox.add(addBtn);

		//组装
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(idBox);
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(powerBox);
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(labBox);
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(nameBox);
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(telBox);
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(mailBox);
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(psdBox);
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(addBox);
		vBox.add(Box.createVerticalStrut(15));

		Box hBox = Box.createHorizontalBox();
		hBox.add(Box.createHorizontalStrut(20));
		hBox.add(vBox);
		hBox.add(Box.createHorizontalStrut(20));

		this.add(hBox);
	}
}

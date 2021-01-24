package uil;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

class ImageScale {
    public static ImageIcon getImage(ImageIcon icon,int width,int height){
        Image image=icon.getImage().getScaledInstance(width, height,Image.SCALE_REPLICATE);
        icon.setImage(image);
        return icon;
    }
}
class WindowXY {
    public static Point getXY(int w,int h){
        Toolkit toolkit=Toolkit.getDefaultToolkit();
        int width=toolkit.getScreenSize().width;
        int height=toolkit.getScreenSize().height;
        return new Point((width-w)/2,(height-h)/2);
    }
    public static Point getXY(Dimension dimension) {
        return getXY(dimension.width, dimension.height);
    }
}

@SuppressWarnings("serial")
public class logOn extends JDialog {

    JDialog jd;

    private final JPanel contentPanel = new JPanel();
    private JTextField userName;
    private JTextField password;
    private JTextField user_mail;
    private JTextField textField_3;
    private JTextField user_psd;
    private JTextField textField_5;
    private JTextField user_relname;
    private JTextField user_lab;

    private static final int DIALOG_WIDTH=400;
    private static final int DIALOG_HEIGHT=288;
    private static final int DIALOG_HEIGHT_EXTEND=600;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
       /**加载界面顶部图片
    	try
        {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {
            //TODO exception
            System.out.println("加载炫彩皮肤失败！");
        }
       */
        try {
            logOn dialog = new logOn();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);

            //dialog.dispose();//销毁当前界面
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public logOn() {
    	setTitle("xtce工具");
        setAlwaysOnTop(true);
        //setResizable(false);
        setBounds(400, 100, 358,293);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        //设置居中
        //setLocation(WindowXY.getXY(LoginDialog.this.getSize()));

        JButton btnNewButton = new JButton("注 册");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(logOn.this.getHeight()==DIALOG_HEIGHT_EXTEND){
                    logOn.this.setSize(DIALOG_WIDTH,DIALOG_HEIGHT);
                }
                else{
                    logOn.this.setSize(DIALOG_WIDTH,DIALOG_HEIGHT_EXTEND);
                }
            }
        });
        btnNewButton.setBounds(53, 224, 93, 23);
        contentPanel.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("登 录");
        btnNewButton_1.addActionListener(new ActionListener() {
        	//登录按钮事件监听器
        	public void actionPerformed(ActionEvent e) {
                String name = userName.getText();
                String pass = password.getText();

                if("".equals(name)||"".equals(pass)) {
                    JOptionPane.showMessageDialog(null, "用户名或密码不能为空！");

                }else if ("123".equals(name)&&"123".equals(pass)) {
                    JOptionPane.showMessageDialog(null, "登录成功！欢迎使用！");
                    Sever1 ml=new Sever1();//为跳转的界面
                    ml.setVisible(true);
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(null, "账户名或密码错误，请检查！");
                }
        	}
        });

       
        btnNewButton_1.setBounds(190, 224, 93, 23);
        contentPanel.add(btnNewButton_1);

        userName = new JTextField();
        userName.setBounds(133, 147, 150, 25);
        contentPanel.add(userName);
        userName.setColumns(10);

        password = new JTextField();
        password.setBounds(133, 182, 150, 25);
        contentPanel.add(password);
        password.setColumns(10);

        JLabel lblNewLabel = new JLabel("用户名");
        lblNewLabel.setBounds(53, 151, 54, 15);
        contentPanel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("密 码");
        lblNewLabel_1.setBounds(53, 194, 54, 15);
        contentPanel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("中心照片。。。");
        lblNewLabel_2.setBounds(0, 0, 360, 136);
       // ImageIcon icon=new ImageIcon(logOn.class.getResource("/res/login.jpg"));
       // icon=ImageScale.getImage(icon, lblNewLabel_2.getWidth(), lblNewLabel_2.getHeight());
        //lblNewLabel_2.setIcon((icon));
        //contentPanel.add(lblNewLabel_2);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "\u6CE8\u518C\u7528\u6237", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(24, 262, 336, 280);
        contentPanel.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_3 = new JLabel("邮 箱");
        lblNewLabel_3.setBounds(41, 29, 55, 18);
        panel.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("验证码");
        lblNewLabel_4.setBounds(41, 85, 55, 18);
        panel.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("密 码");
        lblNewLabel_5.setBounds(41, 115, 55, 18);
        panel.add(lblNewLabel_5);

        JLabel label = new JLabel("确认密码");
        label.setBounds(41, 145, 55, 18);
        panel.add(label);
        

        user_mail = new JTextField();
        user_mail.setBounds(123, 22, 150, 25);
        panel.add(user_mail);
        user_mail.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setBounds(123, 80, 150, 25);
        panel.add(textField_3);
        textField_3.setColumns(10);

        user_psd = new JTextField();
        user_psd.setBounds(123, 113, 150, 25);
        panel.add(user_psd);
        user_psd.setColumns(10);

        textField_5 = new JTextField();
        textField_5.setBounds(123, 145, 150, 25);
        panel.add(textField_5);
        textField_5.setColumns(10);
        
       


        JButton btnNewButton_2 = new JButton("发送验证码");
        btnNewButton_2.setBounds(123, 52, 83, 23);
        panel.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("取 消");
        btnNewButton_3.setBounds(41, 240, 83, 27);
        panel.add(btnNewButton_3);

        JButton btnNewButton_4 = new JButton("确 认");
        btnNewButton_4.setBounds(182, 240, 83, 27);
        panel.add(btnNewButton_4);
        
        JLabel label_1 = new JLabel("真实姓名");
        label_1.setBounds(41, 175, 55, 18);
        panel.add(label_1);
        
        user_relname = new JTextField();
        user_relname.setColumns(10);
        user_relname.setBounds(123, 175, 150, 25);
        panel.add(user_relname);
        
        JLabel label_2 = new JLabel("研究室");
        label_2.setBounds(41, 205, 55, 18);
        panel.add(label_2);
        
        user_lab = new JTextField();
        user_lab.setColumns(10);
        user_lab.setBounds(123, 205, 150, 25);
        panel.add(user_lab);
    }

}
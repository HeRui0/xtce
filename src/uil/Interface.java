package uil;

import bll.Receiver;
import bll.Parser;
import bll.Builder;
import org.dom4j.Attribute;
import org.dom4j.Element;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Interface {
    static HashMap<String, String> map = new HashMap<>();


    List<Element> list = null;
    List<Element> listFirst = null;
    List<Element> listSecond = null;

    // 1. 创建一个顶层容器（窗口）
    JFrame jf = new JFrame("XTCE生成工具");          // 创建窗口
    // 2. 创建中间容器（面板容器）
    JPanel panel = new JPanel();                // 创建面板容器，使用默认的布局管理器


    ArrayList<String> listValue = new ArrayList<>();
    ArrayList<String> listFirstValue = new ArrayList<>();
    ArrayList<String> listSecondValue = new ArrayList<>();

    ArrayList<String> listName = new ArrayList<>();
    ArrayList<String> listLength = new ArrayList<>();

    public static void main(String[] args) {
        //主导头参数信息
        map.put("PVN", "版本号 3bits");
        map.put("PT", "包类型 1bit");
        map.put("SHF", "副导头标志 1bit");
        map.put("APID", "应用过程标识符 11bits");
        map.put("SF", "分组标志 2bits");
        map.put("PSC", "序列计数 14bits（填写数值）");
        map.put("PDL", "包长度 16bits（填写数值）");

        //副导头参数信息
        map.put("TC", "时间码 16bits（填写数值）");
        map.put("IMT", "执行方式类型 8bits（填写16进制数）");
        map.put("ICN", "指令编码数 8bits（填写数值）");

        //指令中参数信息
        map.put("AllowOrNot", "是否需要判断事件项是否允许操作 1bit");
        map.put("InstructionEncodingOrNot", "是否需要根据指令编码选择 1bit");
        map.put("InstructionIdentifierOrNot", "是否需要根据指令标识符选择 1bit");
        map.put("TimeAllow", "选择事件表中的事件项 2bits");
        map.put("TimeOne", "时间码1（可选） 32bits（填写数值）");
        map.put("TimeTwo", "时间码2（可选） 32bits（填写数值）");
        map.put("InstructionEncoding", "指令标识符（可选） 16bits（填写16进制数）");
        map.put("InstructionIdentifier", "指令编码（可选） 16bits（填写16进制数）");

        map.put("TimeShift", "时间平移量 32bits（填写数值）");

        map.put("ParameterMonitoringItems", "参数监控项 29Bytes（填写数值）");

        map.put("N", "监控项数目 8bits（填写数值）");

        map.put("MonitorItemID", "监控项ID 8bits（填写数值）");
        map.put("ParameterID", "参数ID 8bits（填写数值）");
        map.put("ParameterPosition", "参数位置 8bits（填写数值）");
        map.put("ParameterFormat", "参数格式 8bits（填写数值）");
        map.put("MonitoringCondition1", "监视条件1 8bits（填写数值）");
        map.put("MonitoringCondition2", "监视条件2 8bits（填写数值）");
        map.put("ConversionFormulaID", "转换公式ID 8bits（填写数值）");
        map.put("MonitoringPeriod", "监视周期 8bits（填写数值）");
        map.put("CriterionPeriod1", "判据周期1 8bits（填写数值）");
        map.put("CriterionPeriod2", "判据周期2 8bits（填写数值）");
        map.put("EventAction1ID", "事件动作1ID 8bits（填写数值）");
        map.put("EventAction2ID", "事件动作2ID 8bits（填写数值）");
        map.put("StateAfterAction1ID", "动作后状态1ID 8bits（填写数值）");
        map.put("StateAfterAction2ID", "动作后状态2ID 8bits（填写数值）");
        map.put("MonitoringConditionType", "监控条件类型 8bits（填写数值）");
        map.put("ParameterType", "参数类型 8bits（填写数值）");

        map.put("MacroWhat", "范围类型 2bits");
        map.put("MacroCommandID", "宏指令ID 8bits（填写数值）");

        map.put("ConditionOrNot", "是否根据后续条件选择删除 1bit");
        map.put("StaticOrNot", "静态部分能否删除 1bit");
        map.put("JudgeOrNot", "是否需要判断宏指令是否允许删除 1bit");
        map.put("N1", "宏指令数目（可选） 8bits（填写数值）");

        Interface in = new Interface();
        in.Panel();
    }

    public void Panel () {
        panel.removeAll();
        panel.repaint();

        // 1. 创建一个顶层容器（窗口）
        //JFrame jf = new JFrame("XTCE");          // 创建窗口
        jf.setSize(800, 500);                       // 设置窗口大小
        jf.setLocationRelativeTo(null);             // 把窗口位置设置到屏幕中心
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 当点击窗口的关闭按钮时退出程序（没有这一句，程序不会退出）

        // 2. 创建中间容器（面板容器）
        //JPanel panel = new JPanel();                // 创建面板容器，使用默认的布局管理器

        panel.setLayout(null);
        // 4. 把 面板容器 作为窗口的内容面板 设置到 窗口
        jf.setContentPane(panel);

        // 5. 显示窗口，前面创建的信息都在内存中，通过 jf.setVisible(true) 把内存中的窗口显示在屏幕上。
        jf.setVisible(true);

        JButton btn = new JButton("选择已有的xsd文件");
        btn.setBounds(100,100,150,30);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelFirst();
            }
        });
        panel.add(btn);

        JButton btnn = new JButton("创建新的xsd文件");
        btnn.setBounds(100,200,150,30);
        btnn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelNew1();
            }
        });
        panel.add(btnn);
    }

    public void PanelFirst () {
        panel.removeAll();
        panel.repaint();

        // 添加一个标签
        JLabel label1 = new JLabel("请填写主导头信息（全部必选）");
        label1.setBounds(10,10,300,30);
        panel.add(label1);

        String path = "xsd/first.xsd";
        listFirst = Parser.parse(path);
        for (int i = 0; i < listFirst.size(); i++) {
            Attribute attribute = listFirst.get(i).attribute("argumentName");
            System.out.println(attribute.getText());
        }

        ArrayList<JTextField> listText = new ArrayList<>();
        for (int i = 0; i < listFirst.size(); i++) {
            Attribute attribute = listFirst.get(i).attribute("argumentName");
            System.out.println(attribute.getText());
            JLabel label = new JLabel(map.get(attribute.getText()));
            label.setBounds(10,50+50*i,200,30);
            panel.add(label);
            JTextField textField = new JTextField(8);
            textField.setBounds(210,50+50*i,150,30);
            listText.add(textField);
            panel.add(textField);
        }

        // 5. 显示窗口，前面创建的信息都在内存中，通过 jf.setVisible(true) 把内存中的窗口显示在屏幕上。
        jf.setVisible(true);

        JButton btn = new JButton("下一步");
        btn.setBounds(650,400,100,30);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listText.size(); i++) {
                    listFirstValue.add(listText.get(i).getText());
                    System.out.println("提交: " + listText.get(i).getText());
                }
                PanelSecond();
            }
        });
        panel.add(btn);
    }

    public void PanelSecond () {
        panel.removeAll();
        panel.repaint();

        // 添加一个标签
        JLabel label1 = new JLabel("请填写副导头信息（全部可选）");
        label1.setBounds(10,10,300,30);
        panel.add(label1);

        String path = "xsd/second.xsd";
        listSecond = Parser.parse(path);
        for (int i = 0; i < listSecond.size(); i++) {
            Attribute attribute = listSecond.get(i).attribute("argumentName");
            System.out.println(attribute.getText());
        }
        ArrayList<JTextField> listText = new ArrayList<>();
        for (int i = 0; i < listSecond.size(); i++) {
            Attribute attribute = listSecond.get(i).attribute("argumentName");
            System.out.println(attribute.getText());
            JLabel label = new JLabel(map.get(attribute.getText()));
            label.setBounds(10,50+50*i,220,30);
            panel.add(label);
            JTextField textField = new JTextField(8);
            textField.setBounds(230,50+50*i,150,30);
            listText.add(textField);
            panel.add(textField);
        }

        // 5. 显示窗口，前面创建的信息都在内存中，通过 jf.setVisible(true) 把内存中的窗口显示在屏幕上。
        jf.setVisible(true);

        JButton btn = new JButton("下一步");
        btn.setBounds(650,400,100,30);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listText.size(); i++) {
                    listSecondValue.add(listText.get(i).getText());
                    System.out.println("提交: " + listText.get(i).getText());
                }
                Panel1();
            }
        });
        panel.add(btn);
    }

    public void Panel1 () {
        panel.removeAll();
        panel.repaint();

        // 添加一个标签
        JLabel label = new JLabel("请选择指令名称：");
        label.setBounds(10,100,150,30);
        panel.add(label);

        // 需要选择的条目
        String[] listData = new String[]{"F1", "F2", "F3", "F4", "F5", "F6", "F9", "FA", "FB", "TC[11,1]", "TC[11,2]",
                "TC[11,5]", "TC[11,7]", "TC[11,30]", "TC[11,31]", "TC[11,32]", "TC[11,33]", "TC[11,34]", "TC[12,5]",
                "TC[12,6]", "TC[12,15]", "TC[12,16]", "TC[12,30]", "TC[12,31]", "TC[11,32]", "TC[12,33]", "TC[12,34]",
                "TC[12,35]", "TC[12,36]", "TC[11,37]", "TC[12,38]", "TC[12,39]", "TC[11,40]", "TC[12,41]", "TC[12,42]",
                "TC[12,43]", "TC[12,44]", "TC[19,8]", "TC[19,9]", "TC[30,1]", "TC[30,2]", "TC[30,3]", "TC[30,4]",
                "TC[30,5]", "TC[30,6]"};

        // 创建一个下拉列表框
        final JComboBox<String> comboBox = new JComboBox<>(listData);
        comboBox.setBounds(120,100,100,30);

        // 添加到内容面板
        panel.add(comboBox);

        // 5. 显示窗口，前面创建的信息都在内存中，通过 jf.setVisible(true) 把内存中的窗口显示在屏幕上。
        jf.setVisible(true);

        // 添加条目选中状态改变的监听器
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // 只处理选中的状态
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("选中: " + comboBox.getSelectedIndex() + " = " + comboBox.getSelectedItem());
                    String path = Receiver.read(comboBox.getSelectedItem().toString());
                    list = Parser.parse(path);
                    for (int i = 0; i < list.size(); i++) {
                        Attribute attribute = list.get(i).attribute("argumentName");
                        System.out.println(attribute.getText());
                    }
                    Panel2(path);
                }
            }
        });
    }

    public void Panel2 (String path) {
        panel.removeAll();
        panel.repaint();
        ArrayList<JTextField> listText = new ArrayList<>();
        int flag = 0;
        for (int i = 1; i < list.size(); i++) {
            Attribute attribute = list.get(i).attribute("argumentName");
            System.out.println(attribute.getText());
            if(attribute.getText().equals("N") || attribute.getText().equals("N1")) {
                flag = 1;
            }
            System.out.println(flag);
            JLabel label = new JLabel(map.get(attribute.getText()));
            label.setBounds(10,10+50*(i-1),300,30);
            panel.add(label);
            JTextField textField = new JTextField(8);
            textField.setBounds(300,10+50*(i-1),150,30);
            listText.add(textField);
            panel.add(textField);
        }
        // 5. 显示窗口，前面创建的信息都在内存中，通过 jf.setVisible(true) 把内存中的窗口显示在屏幕上。
        jf.setVisible(true);
        // 创建一个按钮，点击后获取文本框中的文本
        if(flag == 0) {
            JButton btn = new JButton("提交");
            btn.setBounds(650,400,100,30);
            System.out.println("flag0");
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < listText.size(); i++) {
                        listValue.add(listText.get(i).getText());
                        System.out.println("提交: " + listText.get(i).getText());
                    }
                    String filePath = Builder.build(path, listValue, listFirstValue, listSecondValue);
                    Panel3(filePath);
                }
            });
            panel.add(btn);
        }
        else if(flag == 1) {
            JButton btn = new JButton("下一步");
            btn.setBounds(650,400,100,30);
            System.out.println("flag1");
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < listText.size(); i++) {
                        listValue.add(listText.get(i).getText());
                        System.out.println("提交: " + listText.get(i).getText());
                    }
                    Panel4(listText.get(listText.size()-1).getText(), path);
                }
            });
            panel.add(btn);
        }
        jf.setContentPane(panel);
    }

    public void Panel3 (String path) {
        System.out.println("panel4");
        panel.removeAll();
        panel.repaint();
        JLabel label1 = new JLabel("已成功生成文件！");
        label1.setBounds(50,100,150,30);
        panel.add(label1);
        JLabel label2 = new JLabel("文件路径：" + path);
        label2.setBounds(50,200,400,30);
        panel.add(label2);
        JButton btn = new JButton("继续");
        btn.setBounds(650,400,100,30);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Panel();
            }
        });
        panel.add(btn);
    }

    public void Panel4 (String s, String path) {
        panel.removeAll();
        panel.repaint();
        ArrayList<JTextField> listText = new ArrayList<>();
        int n = Integer.valueOf(s);
        System.out.println(n);
        for (int i = 1; i <= n; i++) {
            JLabel label = new JLabel("ID" + i + " 8bits（填写数值）");
            label.setBounds(10,10+50*(i-1),150,30);
            panel.add(label);
            JTextField textField = new JTextField(8);
            textField.setBounds(200,10+50*(i-1),150,30);
            listText.add(textField);
            panel.add(textField);
        }
        // 创建一个按钮，点击后获取文本框中的文本
        JButton btn = new JButton("提交");
        btn.setBounds(650,400,100,30);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listText.size(); i++) {
                    listValue.add(listText.get(i).getText());
                    System.out.println("提交: " + listText.get(i).getText());
                }
                String filePath = Builder.build(path, listValue, listFirstValue, listSecondValue, n);
                Panel3(filePath);
            }
        });
        panel.add(btn);
        jf.setContentPane(panel);
    }

    public void PanelNew1 () {
        panel.removeAll();
        panel.repaint();

        ArrayList<JTextField> listText = new ArrayList<>();
        JLabel label1 = new JLabel("指令名称");
        label1.setBounds(10,50,200,30);
        panel.add(label1);
        JTextField textField1 = new JTextField(8);
        textField1.setBounds(100,50,150,30);
        listText.add(textField1);
        panel.add(textField1);

        JLabel label2 = new JLabel("指令参数数目");
        label2.setBounds(10,100,200,30);
        panel.add(label2);
        JTextField textField2 = new JTextField(8);
        textField2.setBounds(100,100,150,30);
        listText.add(textField2);
        panel.add(textField2);

        // 5. 显示窗口，前面创建的信息都在内存中，通过 jf.setVisible(true) 把内存中的窗口显示在屏幕上。
        jf.setVisible(true);

        JButton btn = new JButton("下一步");
        btn.setBounds(650,400,100,30);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelNew2(listText.get(0).getText(), listText.get(1).getText());
            }
        });
        panel.add(btn);
    }

    public void PanelNew2 (String name, String s) {
        panel.removeAll();
        panel.repaint();

        ArrayList<JTextField> listText = new ArrayList<>();
        int n = Integer.valueOf(s);
        System.out.println(n);
        for (int i = 1; i <= n; i++) {
            JLabel label1 = new JLabel("参数" + i + "名称");
            label1.setBounds(10,10+50*(i-1),150,30);
            panel.add(label1);
            JTextField textField1 = new JTextField(8);
            textField1.setBounds(100,10+50*(i-1),150,30);
            listText.add(textField1);
            panel.add(textField1);

            JLabel label2 = new JLabel("参数" + i + "长度位数");
            label2.setBounds(300,10+50*(i-1),150,30);
            panel.add(label2);
            JTextField textField2 = new JTextField(8);
            textField2.setBounds(390,10+50*(i-1),150,30);
            listText.add(textField2);
            panel.add(textField2);
        }
        // 创建一个按钮，点击后获取文本框中的文本
        JButton btn = new JButton("提交");
        btn.setBounds(650,400,100,30);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listText.size(); i++) {
                    if(i % 2 == 0) {
                        listName.add(listText.get(i).getText());
                    }
                    else {
                        listLength.add(listText.get(i).getText());
                    }
                    System.out.println("提交: " + listText.get(i).getText());
                }
                String filePath = Builder.build(name, listName, listLength, n);
                PanelNew3(filePath);
            }
        });
        panel.add(btn);
        jf.setContentPane(panel);
    }

    public void PanelNew3 (String path) {
        panel.removeAll();
        panel.repaint();
        JLabel label1 = new JLabel("已成功生成xsd文件！");
        label1.setBounds(50,100,150,30);
        panel.add(label1);
        JLabel label2 = new JLabel("文件路径：" + path);
        label2.setBounds(50,200,400,30);
        panel.add(label2);
        JButton btn = new JButton("继续");
        btn.setBounds(650,400,100,30);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Panel();
            }
        });
        panel.add(btn);
    }
}

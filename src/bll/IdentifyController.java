package bll;

import dal.ReadTest;
import dal.Write;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Attribute;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;


public class IdentifyController {

    public static void load() {
        /*String filepath = null;
        try {
            filepath = ReadTest.search();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        String filepath="xsd/TC[11,7].xsd";
        File file = new File(filepath);
        Document document = null; //建立document对象
        try {
            SAXReader saxReader = new SAXReader(); //建立读文件对象实例
            document = saxReader.read(file); //读取当前路径下的XML文件,获得document对象
        } catch (Exception ex) {
            ex.printStackTrace(); //异常处理
        }
        Element root = document.getRootElement(); //获取根节点
        String name = root.attribute("name").getText();
        //long startTime = System.nanoTime();   //获取开始时间
        Element cmdElm = root.element("CommandMetaData"); //获取cmd节点
        Element mcsElm = cmdElm.element("MetaCommandSet"); //获取mcs节点
        List<Element> listmc = mcsElm.elements("MetaCommand"); //listmc存放mcs节点下所有mc节点
        Element mcElm = listmc.get(1); //取第二个mc节点
        Element bmcElm = mcElm.element("BaseMetaCommand"); //获取bmc节点
        Element aalElm = bmcElm.element("ArgumentAssignmentList"); //获取aal节点
        List<Element> list = aalElm.elements("ArgumentAssignment"); //list存放所有aa节点，为xsd文件的所有参数
        long endTime = System.nanoTime(); //获取结束时间
        //System.out.println("程序运行时间：" + (endTime - startTime) + "ns");    //输出程序运行时间

        //Scanner sc = new Scanner(System.in);
        for(int i = 0; i < list.size(); i++) {
            //String value = sc.nextLine();
            Attribute attribute = list.get(i).attribute("argumentValue");
            //attribute.setValue(value);
            String s = attribute.getText();
            if(s.equals(" ")) {
                System.out.println("0");
            }
            else {
                System.out.println("1");
            }
            System.out.println(s);
        }

        /*for (int i = 0; i < list.size(); i++) {
            Attribute attribute = list.get(i).attribute("argumentValue");
            attribute.setValue("123");
            String s = attribute.getText();
            System.out.println(s);
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//设置日期格式
        String time = df.format(new Date());
        System.out.println(time);// new Date()为获取当前系统时间

        OutputFormat format = OutputFormat.createPrettyPrint(); //格式化为缩进格式
        format.setEncoding("UTF-8"); //设置编码格式
        File fileOut = new File("xml/" + name + ' ' + time + ".xml");
        try {
            XMLWriter writer = new XMLWriter(new FileWriter(fileOut), format);
            //写入数据
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Write.insert(name, time);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }
    public static void main(String[] args) {
        /*System.gc();
        long total = Runtime.getRuntime().totalMemory(); // byte
        long m1 = Runtime.getRuntime().freeMemory();
        System.out.println("before:" + (total - m1));*/

        //long startTime=System.nanoTime();   //获取开始时间
        IdentifyController ic = new IdentifyController();
        ic.load();
        //long endTime=System.nanoTime(); //获取结束时间
       // System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间

        /*long total1 = Runtime.getRuntime().totalMemory();
        long m2 = Runtime.getRuntime().freeMemory();
        System.out.println("after:" + (total1 - m2));
        System.exit(0);*/

        //合并两个xml文件
        String filepath1 = "xsd/TC[11,7].xsd";
        File file1 = new File(filepath1);
        Document document1 = null; //建立document对象
        try {
            SAXReader saxReader = new SAXReader(); //建立读文件对象实例
            document1 = saxReader.read(file1); //读取当前路径下的XML文件,获得document对象
        } catch (Exception ex) {
            ex.printStackTrace(); //异常处理
        }
        String filepath2 = "xsd/TC[11,5].xsd";
        File file2 = new File(filepath2);
        Document document2 = null; //建立document对象
        try {
            SAXReader saxReader = new SAXReader(); //建立读文件对象实例
            document2 = saxReader.read(file2); //读取当前路径下的XML文件,获得document对象
        } catch (Exception ex) {
            ex.printStackTrace(); //异常处理
        }
        List<Element> list = document2.getRootElement().elements();
        Element parent = document1.getRootElement();
        for(Element element : list) {
            parent.add(element.detach());
        }
        OutputFormat format = OutputFormat.createPrettyPrint(); //格式化为缩进格式
        format.setEncoding("UTF-8"); //设置编码格式
        File fileOut = new File("xsd/final.xsd");
        try {
            XMLWriter writer = new XMLWriter(new FileWriter(fileOut), format);
            //写入数据
            writer.write(document1);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

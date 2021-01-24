package bll;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Attribute;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Test {
    public void load() {
        String filepath="xsd/TC[11,5].xsd";
        File file = new File(filepath);
        Document document = null; //建立document对象
        try {
            SAXReader saxReader = new SAXReader(); //建立读文件对象实例
            document = saxReader.read(file); //读取当前路径下的XML文件,获得document对象
        } catch (Exception ex) {
            ex.printStackTrace(); //异常处理
        }

        long startTime=System.nanoTime();   //获取开始时间
        List<Element> list = new ArrayList<Element>(){};
        Element root = document.getRootElement(); //获取根节点
        String name = root.attribute("name").getText();
        getList(root, list);
        long endTime=System.nanoTime(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ns");    //输出程序运行时间


        for(int i = 0; i < list.size(); i++) {
            Attribute attribute = list.get(i).attribute("argumentValue");
            attribute.setValue("testtest");
            String s = attribute.getText();
            System.out.println(s);
        }

        OutputFormat format = OutputFormat.createPrettyPrint(); //格式化为缩进格式
        format.setEncoding("UTF-8"); //设置编码格式
        File fileOut = new File("xml/"+name+"test1.xml");
        try {
            XMLWriter writer = new XMLWriter(new FileWriter(fileOut),format);
            //写入数据
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void getList(Element node, List<Element> list){
        if(node.getName() == "ArgumentAssignment") {
            list.add(node);
        }
        if(node.getName() != "ArgumentAssignment") {
            //递归遍历当前节点所有的子节点
            List<Element> listElement = node.elements();//所有一级子节点的list
            for(int i = 0; i < listElement.size(); i++) {//遍历所有一级子节点
                getList(listElement.get(i), list);//递归

            }
        }
    }
    public static void main(String[] args) {
        /*System.gc();
        long total = Runtime.getRuntime().totalMemory(); // byte
        long m1 = Runtime.getRuntime().freeMemory();
        System.out.println("before:" + (total - m1));*/

        long startTime=System.nanoTime();   //获取开始时间
        Test test = new Test();
        test.load();
        long endTime=System.nanoTime(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ns");    //输出程序运行时间

        /*long total1 = Runtime.getRuntime().totalMemory();
        long m2 = Runtime.getRuntime().freeMemory();
        System.out.println("after:" + (total1 - m2));
        System.exit(0);*/
    }
}

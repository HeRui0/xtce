package bll;

import dal.Write;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static bll.Parser.doc;

public class Builder {
    public static String build (String path, ArrayList<String> listValue, ArrayList<String> listFirstValue, ArrayList<String> listSecondValue) {
        //写入指令文件
        File file = new File(path);
        Document document = doc(file);
        Element root = document.getRootElement(); //获取根节点
        String name = root.attribute("name").getText();
        Element cmdElm = root.element("CommandMetaData"); //获取cmd节点
        Element mcsElm = cmdElm.element("MetaCommandSet"); //获取mcs节点
        List<Element> listmc = mcsElm.elements("MetaCommand"); //listmc存放mcs节点下所有mc节点
        Element mcElm = listmc.get(1); //取第二个mc节点
        Element bmcElm = mcElm.element("BaseMetaCommand"); //获取bmc节点
        Element aalElm = bmcElm.element("ArgumentAssignmentList"); //获取aal节点
        List<Element> list = aalElm.elements("ArgumentAssignment"); //list存放所有aa节点，为xsd文件的所有参数

        for (int i = 1; i < list.size(); i++) {
            Attribute attribute = list.get(i).attribute("argumentValue");
            System.out.println(attribute.getText());
            attribute.setValue(listValue.get(i-1));
            System.out.println(attribute.getText());
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
        }

        //写入主导头文件
        File fileF = new File("xsd/first.xsd");
        Document documentF = doc(fileF);
        Element rootF = documentF.getRootElement(); //获取根节点
        Element cmdElmF = rootF.element("CommandMetaData"); //获取cmd节点
        Element mcsElmF = cmdElmF.element("MetaCommandSet"); //获取mcs节点
        List<Element> listmcF = mcsElmF.elements("MetaCommand"); //listmc存放mcs节点下所有mc节点
        Element mcElmF = listmcF.get(1); //取第二个mc节点
        Element bmcElmF = mcElmF.element("BaseMetaCommand"); //获取bmc节点
        Element aalElmF = bmcElmF.element("ArgumentAssignmentList"); //获取aal节点
        List<Element> listF = aalElmF.elements("ArgumentAssignment"); //list存放所有aa节点，为xsd文件的所有参数

        for (int i = 0; i < listF.size(); i++) {
            Attribute attribute = listF.get(i).attribute("argumentValue");
            System.out.println(attribute.getText());
            attribute.setValue(listFirstValue.get(i));
            System.out.println(attribute.getText());
        }

        File fileOutF = new File("xml/" + name + "first" + ' ' + time + ".xml");
        try {
            XMLWriter writer = new XMLWriter(new FileWriter(fileOutF), format);
            //写入数据
            writer.write(documentF);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Write.insert(name+"first", time);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //写入副导头文件
        File fileS = new File("xsd/second.xsd");
        Document documentS = doc(fileS);
        Element rootS = documentS.getRootElement(); //获取根节点
        Element cmdElmS = rootS.element("CommandMetaData"); //获取cmd节点
        Element mcsElmS = cmdElmS.element("MetaCommandSet"); //获取mcs节点
        List<Element> listmcS = mcsElmS.elements("MetaCommand"); //listmc存放mcs节点下所有mc节点
        Element mcElmS = listmcS.get(1); //取第二个mc节点
        Element bmcElmS = mcElmS.element("BaseMetaCommand"); //获取bmc节点
        Element aalElmS = bmcElmS.element("ArgumentAssignmentList"); //获取aal节点
        List<Element> listS = aalElmS.elements("ArgumentAssignment"); //list存放所有aa节点，为xsd文件的所有参数

        for (int i = 0; i < listS.size(); i++) {
            Attribute attribute = listS.get(i).attribute("argumentValue");
            System.out.println(attribute.getText());
            attribute.setValue(listSecondValue.get(i));
            System.out.println(attribute.getText());
        }

        File fileOutS = new File("xml/" + name + "second" + ' ' + time + ".xml");
        try {
            XMLWriter writer = new XMLWriter(new FileWriter(fileOutS), format);
            //写入数据
            writer.write(documentS);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Write.insert(name+"second", time);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //将三个文件合并为一个文件
        String filepath1 = fileOutF.toString();
        File file1 = new File(filepath1);
        Document document1 = null; //建立document对象
        try {
            SAXReader saxReader = new SAXReader(); //建立读文件对象实例
            document1 = saxReader.read(file1); //读取当前路径下的XML文件,获得document对象
        } catch (Exception ex) {
            ex.printStackTrace(); //异常处理
        }
        String filepath2 = fileOutS.toString();
        File file2 = new File(filepath2);
        Document document2 = null; //建立document对象
        try {
            SAXReader saxReader = new SAXReader(); //建立读文件对象实例
            document2 = saxReader.read(file2); //读取当前路径下的XML文件,获得document对象
        } catch (Exception ex) {
            ex.printStackTrace(); //异常处理
        }
        String filepath3 = fileOut.toString();
        File file3 = new File(filepath3);
        Document document3 = null; //建立document对象
        try {
            SAXReader saxReader = new SAXReader(); //建立读文件对象实例
            document3 = saxReader.read(file3); //读取当前路径下的XML文件,获得document对象
        } catch (Exception ex) {
            ex.printStackTrace(); //异常处理
        }
        Element root1 = document1.getRootElement();
        root1.attribute("name").setValue(name);
        List<Element> list2 = document2.getRootElement().elements();
        List<Element> list3 = document3.getRootElement().elements();
        Element parent = document1.getRootElement();
        for(Element element : list2) {
            parent.add(element.detach());
        }
        for(Element element : list3) {
            parent.add(element.detach());
        }

        File fileOutFinal = new File("xml/" + name + "final" + ' ' + time + ".xml");
        try {
            XMLWriter writer = new XMLWriter(new FileWriter(fileOutFinal), format);
            //写入数据
            writer.write(document1);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Write.insert(name+"final", time);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String filePath = fileOutFinal.toString();
        return filePath;
    }

    public static String build (String path, ArrayList<String> listValue, ArrayList<String> listFirstValue, ArrayList<String> listSecondValue, int n) {
        //写入指令文件
        File file = new File(path);
        Document document = doc(file);
        Element root = document.getRootElement(); //获取根节点
        String name = root.attribute("name").getText();
        Element cmdElm = root.element("CommandMetaData"); //获取cmd节点
        Element mcsElm = cmdElm.element("MetaCommandSet"); //获取mcs节点
        List<Element> listmc = mcsElm.elements("MetaCommand"); //listmc存放mcs节点下所有mc节点

        Element mcE = listmc.get(0); //取第一个mc节点
        Element alElm = mcE.element("ArgumentList"); //获取al节点
        for(int i = 1; i <= n; i++) { //al节点下增加节点
            alElm.addElement("xtce:Argument").addAttribute("name", "ID"+String.valueOf(i)).addAttribute("argumentTypeRef", "UnsignedType8");
        }

        Element ccElm = mcE.element("CommandContainer"); //获取cc节点
        Element elElm = ccElm.element("EntryList"); //获取el节点
        for(int i = 1; i <= n; i++) { //el节点下增加节点
            elElm.addElement("xtce:ArgumentRefEntry").addAttribute("argumentRef", "UnsignedType8");
        }

        Element mcElm = listmc.get(1); //取第二个mc节点
        Element bmcElm = mcElm.element("BaseMetaCommand"); //获取bmc节点
        Element aalElm = bmcElm.element("ArgumentAssignmentList"); //获取aal节点
        for(int i = 1; i <= n; i++) { //aal节点下增加节点
            aalElm.addElement("xtce:ArgumentAssignment").addAttribute("argumentName", "ID"+String.valueOf(i)).addAttribute("argumentValue", " ");
        }

        List<Element> list = aalElm.elements("ArgumentAssignment"); //list存放所有aa节点，为xsd文件的所有参数

        for (int i = 1; i < list.size(); i++) {
            Attribute attribute = list.get(i).attribute("argumentValue");
            System.out.println(attribute.getText());
            attribute.setValue(listValue.get(i-1));
            System.out.println(attribute.getText());
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
        }

        //写入主导头文件
        File fileF = new File("xsd/first.xsd");
        Document documentF = doc(fileF);
        Element rootF = documentF.getRootElement(); //获取根节点
        Element cmdElmF = rootF.element("CommandMetaData"); //获取cmd节点
        Element mcsElmF = cmdElmF.element("MetaCommandSet"); //获取mcs节点
        List<Element> listmcF = mcsElmF.elements("MetaCommand"); //listmc存放mcs节点下所有mc节点
        Element mcElmF = listmcF.get(1); //取第二个mc节点
        Element bmcElmF = mcElmF.element("BaseMetaCommand"); //获取bmc节点
        Element aalElmF = bmcElmF.element("ArgumentAssignmentList"); //获取aal节点
        List<Element> listF = aalElmF.elements("ArgumentAssignment"); //list存放所有aa节点，为xsd文件的所有参数

        for (int i = 0; i < listF.size(); i++) {
            Attribute attribute = listF.get(i).attribute("argumentValue");
            System.out.println(attribute.getText());
            attribute.setValue(listFirstValue.get(i));
            System.out.println(attribute.getText());
        }

        File fileOutF = new File("xml/" + name + "first" + ' ' + time + ".xml");
        try {
            XMLWriter writer = new XMLWriter(new FileWriter(fileOutF), format);
            //写入数据
            writer.write(documentF);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Write.insert(name+"first", time);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //写入副导头文件
        File fileS = new File("xsd/second.xsd");
        Document documentS = doc(fileS);
        Element rootS = documentS.getRootElement(); //获取根节点
        Element cmdElmS = rootS.element("CommandMetaData"); //获取cmd节点
        Element mcsElmS = cmdElmS.element("MetaCommandSet"); //获取mcs节点
        List<Element> listmcS = mcsElmS.elements("MetaCommand"); //listmc存放mcs节点下所有mc节点
        Element mcElmS = listmcS.get(1); //取第二个mc节点
        Element bmcElmS = mcElmS.element("BaseMetaCommand"); //获取bmc节点
        Element aalElmS = bmcElmS.element("ArgumentAssignmentList"); //获取aal节点
        List<Element> listS = aalElmS.elements("ArgumentAssignment"); //list存放所有aa节点，为xsd文件的所有参数

        for (int i = 0; i < listS.size(); i++) {
            Attribute attribute = listS.get(i).attribute("argumentValue");
            System.out.println(attribute.getText());
            attribute.setValue(listSecondValue.get(i));
            System.out.println(attribute.getText());
        }

        File fileOutS = new File("xml/" + name + "second" + ' ' + time + ".xml");
        try {
            XMLWriter writer = new XMLWriter(new FileWriter(fileOutS), format);
            //写入数据
            writer.write(documentS);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Write.insert(name+"second", time);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //将三个文件合并为一个文件
        String filepath1 = fileOutF.toString();
        File file1 = new File(filepath1);
        Document document1 = null; //建立document对象
        try {
            SAXReader saxReader = new SAXReader(); //建立读文件对象实例
            document1 = saxReader.read(file1); //读取当前路径下的XML文件,获得document对象
        } catch (Exception ex) {
            ex.printStackTrace(); //异常处理
        }
        String filepath2 = fileOutS.toString();
        File file2 = new File(filepath2);
        Document document2 = null; //建立document对象
        try {
            SAXReader saxReader = new SAXReader(); //建立读文件对象实例
            document2 = saxReader.read(file2); //读取当前路径下的XML文件,获得document对象
        } catch (Exception ex) {
            ex.printStackTrace(); //异常处理
        }
        String filepath3 = fileOut.toString();
        File file3 = new File(filepath3);
        Document document3 = null; //建立document对象
        try {
            SAXReader saxReader = new SAXReader(); //建立读文件对象实例
            document3 = saxReader.read(file3); //读取当前路径下的XML文件,获得document对象
        } catch (Exception ex) {
            ex.printStackTrace(); //异常处理
        }
        List<Element> list2 = document2.getRootElement().elements();
        List<Element> list3 = document3.getRootElement().elements();
        Element parent = document1.getRootElement();
        for(Element element : list2) {
            parent.add(element.detach());
        }
        for(Element element : list3) {
            parent.add(element.detach());
        }

        File fileOutFinal = new File("xml/" + name + "final" + ' ' + time + ".xml");
        try {
            XMLWriter writer = new XMLWriter(new FileWriter(fileOutFinal), format);
            //写入数据
            writer.write(document1);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Write.insert(name+"final", time);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String filePath = fileOutFinal.toString();
        return filePath;
    }

    public static String build (String name, ArrayList<String> listName, ArrayList<String> listLength, int n) {
        File file = new File("xsd/create.xsd");
        Document document = doc(file);
        Element root = document.getRootElement(); //获取根节点
        root.attribute("name").setValue(name);
        Element cmdElm = root.element("CommandMetaData"); //获取cmd节点
        Element atsElm = cmdElm.element("ArgumentTypeSet"); //获取ats节点
        HashSet<String> set = new HashSet<>();
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if(!set.contains(listLength.get(i))) {
                set.add(listLength.get(i));
                atsElm.addElement("xtce:BinaryParameterType").addAttribute("name", listLength.get(i)+"Boolean");
                list.add(listLength.get(i));
            }
        }
        List<Element> listpt = atsElm.elements("BinaryParameterType");
        for(int i = 0; i < listpt.size(); i++) {
            listpt.get(i).addElement("xtce:UnitSet");
            listpt.get(i).addElement("BinaryDataEncoding").addAttribute("sizeInBits", list.get(i));
        }
        Element mcsElm = cmdElm.element("MetaCommandSet"); //获取mcs节点
        List<Element> listmc = mcsElm.elements("MetaCommand"); //listmc存放mcs节点下所有mc节点

        Element mcE = listmc.get(0); //取第一个mc节点
        mcE.attribute("name").setValue(name + "X");
        Element alElm = mcE.element("ArgumentList"); //获取al节点
        for(int i = 0; i < n; i++) { //al节点下增加节点
            alElm.addElement("xtce:Argument").addAttribute("name", listName.get(i)).addAttribute("argumentTypeRef", listLength.get(i)+"Boolean");
        }

        Element ccElm = mcE.element("CommandContainer"); //获取cc节点
        ccElm.attribute("name").setValue(name + "XPacket");
        Element elElm = ccElm.element("EntryList"); //获取el节点
        for(int i = 0; i < n; i++) { //el节点下增加节点
            elElm.addElement("xtce:ArgumentRefEntry").addAttribute("argumentRef", listLength.get(i)+"Boolean");
        }

        Element mcElm = listmc.get(1); //取第二个mc节点
        mcElm.attribute("name").setValue(name);
        Element bmcElm = mcElm.element("BaseMetaCommand"); //获取bmc节点
        bmcElm.attribute("metaCommandRef").setValue(name + "X");
        Element aalElm = bmcElm.element("ArgumentAssignmentList"); //获取aal节点
        for(int i = 0; i < n; i++) { //aal节点下增加节点
            aalElm.addElement("xtce:ArgumentAssignment").addAttribute("argumentName", listName.get(i)).addAttribute("argumentValue", " ");
        }

        Element ccElm1 = mcElm.element("CommandContainer");
        ccElm1.attribute("name").setValue(name + "Packet");
        Element bcElm = ccElm1.element("BaseContainer");
        bcElm.attribute("containerRef").setValue(name + "XPacket");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//设置日期格式
        String time = df.format(new Date());
        System.out.println(time);// new Date()为获取当前系统时间

        OutputFormat format = OutputFormat.createPrettyPrint(); //格式化为缩进格式
        format.setEncoding("UTF-8"); //设置编码格式
        File fileOut = new File("xsd/" + name + ' ' + time + ".xsd");
        try {
            XMLWriter writer = new XMLWriter(new FileWriter(fileOut), format);
            //写入数据
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileOut.toString();
    }
}

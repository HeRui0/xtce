package bll;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class Parser {
    public static List parse (String path) {
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
        return list;
    }
    public static Document doc (File file) {
        Document document = null; //建立document对象
        try {
            SAXReader saxReader = new SAXReader(); //建立读文件对象实例
            document = saxReader.read(file); //读取当前路径下的XML文件,获得document对象
        } catch (Exception ex) {
            ex.printStackTrace(); //异常处理
        }
        return document;
    }
}

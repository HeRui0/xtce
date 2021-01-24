package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Write {
    public static void insert (String name, String time) throws Exception {
        Connection conn = null;
        // 加载驱动类
        Class.forName("com.mysql.jdbc.Driver");
        //long start =System.currentTimeMillis();

        // 建立连接
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/xtce",
                "root", "root");
        //long end = System.currentTimeMillis();
        System.out.println(conn);
        //System.out.println("建立连接耗时： " + (end - start) + "ms 毫秒");
        System.out.println("增操作");
        // 创建Statement对象
        Statement stmt = conn.createStatement();

        String address = "xml/"+name+" "+time+".xml";
        String sql = "insert into xml values ('"+name+"', '"+time+"', '"+address+"')";
        // 执行SQL语句,查
        stmt.executeUpdate(sql);
        //System.out.println("成功插入新数据"+"kitty");
        stmt.close();
        conn.close();
    }
}

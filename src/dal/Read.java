package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Read {
    public static String search (String name) throws Exception {
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
        System.out.println("查操作");
        // 创建Statement对象
        Statement stmt = conn.createStatement();

        // 执行SQL语句,查
        ResultSet rs = stmt.executeQuery("select address from xsd where name = '"+name+"'");
        System.out.println("姓名");
        //String str = rs.getString("address");
        String str = null;
        while (rs.next()) {
            str = rs.getString("address");
            //System.out.println(rs.getString("address"));
        }
        rs.close();
        stmt.close();
        conn.close();
        System.out.println(str);
        return str;
    }
}

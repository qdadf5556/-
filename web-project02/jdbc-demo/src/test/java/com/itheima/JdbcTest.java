package com.itheima;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class JdbcTest {
    @Test
    public void testUpdate() throws Exception {
        // 加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/db02";
        Connection connection = DriverManager.getConnection(url, "root", "1234");
        Statement statement = connection.createStatement();

        statement.executeUpdate("update user set age=25 where id=1");
        statement.close();
        connection.close();
    }

    @Test
    public void TestSelect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/db02";
        Connection connection = DriverManager.getConnection(url, "root", "1234");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
        preparedStatement.setString(1, "daqiao"); // 第一个问号对应的参数
        preparedStatement.setString(2, "123456"); // 第二个问号对应的参数
        // 执行查询
        ResultSet rs = preparedStatement.executeQuery();
        // 处理结果集
        while (rs.next()) {
            int id = rs.getInt("id");
            String uName = rs.getString("username");
            String pwd = rs.getString("password");
            String name = rs.getString("name");
            int age = rs.getInt("age");

            User user = new User(id, uName, pwd, name, age);
            System.out.println(user);
        }
        // 关闭资源
        rs.close();
        preparedStatement.close();
        connection.close();
    }


    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        // 加载驱动，修正类名
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 建立连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db02", "root", "1234");
        // 准备语句，明确列名
        PreparedStatement pStatement = connection.prepareStatement("insert into user (username, password, name, age) values(?,?,?,?)");
        // 创建用户对象
        User user = new User(null, "zhangsan2", "12345611", "Zhang San2", 18); // 去掉前导空格，假设为无意
        // 设置参数，修正顺序

        pStatement.setString(1, user.getUsername());
        pStatement.setString(2, user.getPassword());
        pStatement.setString(3, user.getName());
        pStatement.setInt(4, user.getAge());
        // 执行插入
        pStatement.execute();
        // 关闭资源
        pStatement.close();
        connection.close();
    }

    @Test
    public void testDelete() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db02", "root", "1234");

        PreparedStatement pStatement = connection.prepareStatement("delete from user where id = ?");

        pStatement.setInt(1, 4);

        pStatement.execute();

        pStatement.close();
        connection.close();
    }
    @Test
    public void testSelect2() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db01", "root", "1234");

        PreparedStatement pStatement = connection.prepareStatement("select id,username,password from emp where salary<=?;");
        pStatement.setInt(1, 5000);

        ResultSet rs = pStatement.executeQuery();
        while (rs.next()){
            Emp emp = new Emp(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            System.out.println(emp);
        }
    }

}

package jdbcEx01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest {

  public static void main(String[] args) {
    String DriverName = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/bookmarketdb?serverTimezone=Asia/Seoul";
    String username = "root";
    String password = "!wodbs4906";

    try {
      Class.forName(DriverName);
      System.out.println("Driver loaded Successfully");
    }catch (Exception e) {
      System.out.println("Driver loaded Failed!");
    }

    try{
      Connection con = DriverManager.getConnection(url, username, password);
      System.out.println("Connection Established" + con.getAutoCommit());
      con.setAutoCommit(true);
      Statement stmt = con.createStatement();
      int result = stmt.executeUpdate("insert into person(id, name) values(100,'홍길동')");


      if (result == 1) {
        System.out.println("Insert Successfully");
      } else {
        System.out.println("Insert Failed");
      }
    } catch (Exception e) {
      System.out.println("Connection Established" + e);
    }

  }
}


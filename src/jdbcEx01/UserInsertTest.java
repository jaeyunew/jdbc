package jdbcEx01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UserInsertTest {

  public static void main(String[] args) {
    String DriverName = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/bookmarketdb?serverTimezone=Asia/Seoul";
    String username = "root";
    String password = "!wodbs4906";

    try {
      Class.forName(DriverName);
      System.out.println("Driver loaded Successfully");
    } catch (Exception e) {
      System.out.println("Driver could not be loaded");
    }

    try (Connection con = DriverManager.getConnection(url, username, password)) {
      System.out.println("AutoCommit 상태" + con.getAutoCommit());
      con.setAutoCommit(true);

      String sql = "insert into users(userid, username, userpassword, userage,useremail) values(?,?,?,?,?)";

      PreparedStatement pstmt = con.prepareStatement(sql);

      pstmt.setString(1, "200");
      pstmt.setString(2, "김재윤");
      pstmt.setString(3, "123456");
      pstmt.setInt(4, 27);
      pstmt.setString(5,"qkenr@gmail.com");

      int result = pstmt.executeUpdate();
      System.out.println("저장된 행의 수" + result);

      if (result == 1) {
        System.out.println("Insert Success");
      }

    } catch (Exception e) {
      System.out.println("connection established" + e);
    }
  }

}

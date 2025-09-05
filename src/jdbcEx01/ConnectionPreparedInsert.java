package jdbcEx01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class ConnectionPreparedInsert {

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

    try ( Connection con = DriverManager.getConnection(url, username, password)){  //도로연결
      System.out.println("AutoCommit 상태" + con.getAutoCommit());
      con.setAutoCommit(true);

      // 매개변수화된 SQL문
      String sql= "insert into person(id, name) values(?,?)";

      // PrepareStatement 얻기
      PreparedStatement pstmt = con.prepareStatement(sql);

      // 값 지정
      pstmt.setInt(1,100);
      pstmt.setString(2,"신길동");

      // SQL문 실행
      int result = pstmt.executeUpdate();
      System.out.println("저장된 행의 수"+ result);

      if (result == 1) {
        System.out.println("Insert Successfully");
      }
    } catch (Exception e) {
      System.out.println("Connection Established" + e);
    }

  }
}


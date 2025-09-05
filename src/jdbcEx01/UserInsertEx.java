package jdbcEx01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserInsertEx {

  public static void main(String[] args) {
    Connection con = null;
    try {
      // 1. DB의 드라이버를 찾아서 로드해야 한다. MYSQL JDBC 드라이버 등록
      Class.forName("com.mysql.cj.jdbc.Driver");
      System.out.println("Driver loaded Successfully!");

      // 2. 드라이버로드가 OK라면, 연결 Connection 객체 생성
      con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/bookmarketdb?serverTimezone=Asia/Seoul", "root",
          "!wodbs4906");
      System.out.println("Connection established!" + con);

      // 3. Connection 객체가 생성되었다면, 쿼리문을 Statements 객체에 담아 DB에게 전송한다.
      String sql = "insert into users(userid, username, userpassword, userage, useremail)"+
           "values('10','신세계','1234',20,'kim@gmail.com')";

      // 4. 전송한 결과를 받아서 처리한다.
      int result = con.createStatement().executeUpdate(sql);
      if(result==1){
        System.out.println("Insert Successful!");
      }else {
        System.out.println("Insert Failed!");
      }
    } catch (Exception e) {
      System.out.println("Driver loaded Failed!");
    } finally {

    }

    // 5. 다 사용한 Statements와 Connection 객체를 닫는다.
    if (con != null) {
      try {
        con.close();
        System.out.println("Connection Closed!");
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }
}

package jdbcEx01;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BoardInsertTest {

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

      String sql = "insert into boards(btitle, bcontent, bwriter,bdate,bfilename,bfiledata) values(?,?,?,now(),?,?)";

      PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      pstmt.setString(1, "봄이 온다2");
      pstmt.setString(2, "꽃이 동산에 가득하니 행복하다2");
      pstmt.setString(3, "신세계2");
      pstmt.setString(4,"spring.jpg");
      pstmt.setBlob(5,new FileInputStream("C:/Temp/spring.jpg"));

      int result = pstmt.executeUpdate();
      System.out.println("저장된 행의 수" + result);

      // bno 값 얻기
      if(result == 1){
        ResultSet rs = pstmt.getGeneratedKeys();
        if(rs.next()){
          int bno = rs.getInt(1);
          System.out.println("bno = " + bno);
        }
        rs.close();
      }

      if (result == 1) {
        System.out.println("Insert Success");
      }

    } catch (Exception e) {
      System.out.println("connection established" + e);
    }
  }

}

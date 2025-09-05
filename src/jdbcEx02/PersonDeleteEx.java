package jdbcEx02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jdbcEx01.vo.Person;
import util.DButil;

//  JDBC를 이용해서 데이터삭제를 하는 DELETE 문을 실행하는 방법
// DELETE FROM 테이블명; 해당 테이블의 모든 행을 지운다.
// DELETE FROM 테이블명 WHERE id = 식별값;  //해당 식별값의 행만 지운다.
// DELETE FROM person WHERE num =1;  // person 테이블의 pk num = 1 행을 삭제한다.
// String sql = "DELETE from person WHERE num = ?";
public class PersonDeleteEx {

  public static void main(String[] args) {
    Connection connection = DButil.getConnection();

    //SQL 문 작성
    String sql = "DELETE from person WHERE num = ?";

    //PreparedStatement
    try (PreparedStatement pstmt = connection.prepareStatement(sql)){
      pstmt.setInt(1, 1);
      int rows = pstmt.executeUpdate();
      System.out.println(rows + " rows deleted");

      String selectSql = "select id,name from person";
      ResultSet rs = pstmt.executeQuery(selectSql);
      while (rs.next()) {
        Person person = new Person();
        person.setId(rs.getInt(1));
        person.setName(rs.getString(2));
        System.out.println(person.getId() + " " + person.getName());


    }
    }catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}


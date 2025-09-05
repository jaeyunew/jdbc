package jdbcEx01;

import java.sql.Connection;git
import java.sql.ResultSet;
import java.sql.Statement;
import jdbcEx01.vo.Person;
import util.DButil;

public class ConnectionDBUtilTest {
  public static void main(String[] args) {
    try{
      Connection con = DButil.getConnection(); //도로연결

      Statement stmt = con.createStatement();
      int result = stmt.executeUpdate("insert into  person(id, name) values (1000000,'홍길동11')");
      if(result == 1){
        System.out.println("Insert Success");
      }

      String selectsql = "select id,name from person";
      ResultSet rs = stmt.executeQuery(selectsql);
      while (rs.next()){
        Person person = new Person();
        person.setId(rs.getInt(1));
        person.setName(rs.getString(2));
        System.out.println(person.toString());
      }

    }catch(Exception e){
      System.out.println("Connection established" + e);
    }
  }

}

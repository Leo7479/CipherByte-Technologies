import java.sql.*;
public class DatabaseTools {
    Connection conn = null;
    Statement st = null;
    String url = "jdbc:mysql://localhost/",userName = "root", password = "root";
    DatabaseTools(){
        try{
            conn = DriverManager.getConnection(url, userName, password);
            st = conn.createStatement();
            st.execute("CREATE DATABASE IF NOT EXISTS onlineexamination;");
            st.execute("USE onlineexamination;");
            st.execute("CREATE TABLE IF NOT EXISTS students (id int primary key, name varchar(50), password varchar(30));");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public boolean studentExists(String id){
        try{
            ResultSet rs = st.executeQuery("select * from students where id="+id+";");
            if(rs.next()){
                return true;
            }
        }catch(Exception e){
            System.out.println("Student doesn't exist: "+e);
        }
        return false;
    }
    public boolean passwordMatches(String p, String i){
        try{
            ResultSet rs = st.executeQuery("SELECT * FROM students WHERE id = "+i+";");
            rs.next();
            if(p.equals(rs.getString("password"))) {
                return true;
            }
        }catch(Exception e){
            System.out.println("Password Doesn't Match: "+e);
        }
        return false;
    }
    public String getName(String id){
        try{
            ResultSet rs = st.executeQuery("SELECT * FROM students WHERE id = "+id);
            rs.next();
            return rs.getString("name");
        }catch(Exception e){
            System.out.println(e);
        }
        return "";
    }
    public boolean updatePassword(String id, String newpass){
        try{
            st.execute("update students set password = \""+newpass+"\" where id="+id+";");
            return true;
        }catch(Exception e){}
        return false;
    }
}

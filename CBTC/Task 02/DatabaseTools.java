import java.sql.*;
import java.util.Random;
import java.time.LocalDate;

public class DatabaseTools {
    String userName = "root",password = "root";
    int port = 3306;
    String dataBaseUrl = "jdbc:mysql://localhost:"+port+"";
    Connection conn = null;
    Statement st = null;
    DatabaseTools(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dataBaseUrl, userName, password);
            st = conn.createStatement();
            st.execute("create database if not exists onebanking;");
            st.execute("use onebanking;");
        } catch (Exception e) {
            System.out.println(e);
            closeConnection();
        }
    }

    public int getLastID(){
        try{
            ResultSet rs = st.executeQuery("select * from users;");
            while(!rs.isLast())
                rs.next();
            return rs.getInt("user_id");
        }catch(Exception e){
            System.out.println(e);
        }
        return -1;
    }

    public String getLastAccount(){
        try{
            ResultSet rs = st.executeQuery("select * from users;");
            while(!rs.isLast())
                rs.next();
            return rs.getString("account_no");
        }catch(Exception e){
            System.out.println(e);
        }
        return "000000000000000";
    }

    public String insertData(String[] personal, String[] account, Boolean atmNeeded) throws SQLException {
        Random r = new Random();
        String lastAccount = getLastAccount();
        String newAccountNumber = lastAccount.substring(0,10) + String.valueOf(Integer.parseInt(lastAccount.substring(10)) + 1);
        String newATMNumber="NULL", newATMExpiry="NULL";
        int currentYear = Integer.parseInt(String.valueOf(LocalDate.now()).split("-")[0]);
        int age = currentYear - Integer.parseInt(personal[3].split("/")[2]);
        System.out.println(age);
        try {
            if (atmNeeded) {
                newATMNumber = String.valueOf(Long.parseLong(getATMNumber(lastAccount)) + 1L);
                newATMExpiry = "28/4/2030";
            }
            String usersQuery = "insert into users values ("+ (getLastID() + 1) +", \""+ (newAccountNumber) +"\", \""+ personal[0] +"\", \"hello@john\", \""+ personal[2] +"\", \""+ account[2] +"\");";
            String accountQuery = "insert into account_details values ( \""+ newAccountNumber +"\", \"0\", \""+account[1]+"\", \""+ personal[4] +"\", \""+ personal[5] +"\", \""+ personal[1] +"\", "+ age +", \""+ account[0] +"\", \""+ newATMNumber +"\", \""+newATMExpiry+"\", \""+account[3]+"\");";
            st.execute(accountQuery);
            st.execute(usersQuery);
            System.out.println(usersQuery);
            System.out.println(accountQuery);
            return newAccountNumber;
        }catch(Exception e){
            throw e;
        }
    }

    public boolean checkCredentials(String email, String password) throws UserNotFoundException, SQLException{
        try{
            String query = "select * from users where email = \""+ email +"\";";
            ResultSet rs = st.executeQuery(query);
            if (!rs.next() ) {
                System.out.println("no data found");
                throw new UserNotFoundException();
            } else {
                do {
                    if(password.equals(rs.getString("password"))){
                        return true;
                    }
                } while (rs.next());
            }
        }catch(Exception e){
            throw e;
        }
        return false;
    }

    public String getName(String email) throws UserNotFoundException{
        try{
            String str="select * from users where email =\""+email+"\";";
            ResultSet rs = st.executeQuery(str);
            rs.next();
            return rs.getString("name");
        }catch(Exception e){
            System.out.println("From getName: "+e);
            throw new UserNotFoundException();
        }
    }

    public String getName(Long account) throws UserNotFoundException{
        try{
            String str="select * from users where account_no =\""+account+"\";";
            ResultSet rs = st.executeQuery(str);
            rs.next();
            return rs.getString("name");
        }catch(Exception e){
            System.out.println("From getName: "+e);
            throw new UserNotFoundException();
        }
    }

    public String getAccountNumber(String email) throws UserNotFoundException{
        try{
            String str="select * from users where email =\""+email+"\";";
            ResultSet rs = st.executeQuery(str);
            rs.next();
            return rs.getString("account_no");
        }catch(Exception e){
            System.out.println("From getAccountNumber: "+e);
            throw new UserNotFoundException();
        }
    }

    public String getBalance(String account) throws UserNotFoundException{
        try{
            String str="select * from account_details where account_no =\""+account+"\";";
            ResultSet rs = st.executeQuery(str);
            rs.next();
            return rs.getString("balance");
        }catch(Exception e){
            System.out.println("From getAccountNumber: "+e);
            throw new UserNotFoundException();
        }
    }

    public boolean updateBalance(String account, String amount) throws UserNotFoundException{
        try{
            String str=" update account_details set balance =\""+amount+"\" where account_no=\""+account+"\";";
            st.execute(str);
            return true;
        }catch(Exception e){
            System.out.println();
        }
        return false;
    }

    public String getATMNumber(String account){
        String str = "select atm_no from account_details where account_no = \""+account+"\";";
        try {
            ResultSet rs = st.executeQuery(str);
            rs.next();
            return rs.getString("atm_no");
        } catch (Exception e) {
            System.out.println(e);
        }
        return "Error Retrieving Data.";
    }

    public String getATMExpiry(String account){
        String str = "select atm_expiry from account_details where account_no = \""+account+"\";";
        try {
            ResultSet rs = st.executeQuery(str);
            rs.next();
            return rs.getString("atm_expiry");
        } catch (Exception e) {}
        return "Error Retrieving Data.";
    }

    public void closeConnection(){
        try{
            st.close();
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}

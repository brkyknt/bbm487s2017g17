/**
 * Created by gismat on 14.05.2017.
 */
import java.sql.*;
import java.util.ArrayList;

import java.time.*;
import java.util.Calendar;

public class DatabaseHandler {

    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306";

    //  Database credentials
    static final String USER = "root"; // may be different on your mysql server
    static final String PASS = ""; // may be different on your mysql server
    private static Connection conn = null;

    private static void getConnection(){


        try{
            //STEP 2: Register JDBC driver
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);


            //System.out.println("Creating statement...");

            //STEP 6: Clean-up environment


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }


    }

    public static User checkLogin(String email, String password){

        User user=null;
        Statement stmt = null;


        if(conn==null){
            getConnection();
        }else{

            // connection already established
        }

        try {
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT _id,fullname, email, password, type,totalfine FROM librarybookloan.Users WHERE email='" + email +"'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.first()) {
                user = new User(
                        rs.getInt("_id"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("type"),
                        rs.getFloat("totalfine"));

                System.out.println("on db handler: "+user);
            }else{
                user=null;
                return user;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(password.equals(user.getPassword())){

            return user;

        }else{

            user=null;
            return user;

        }

    }

    public static ArrayList<Book> searchBooks(String title){

        ArrayList<Book> books=new ArrayList<>();
        Statement stmt = null;


        if(conn==null){
            getConnection();
        }else{

            // connection already established
        }

        try {
            stmt = conn.createStatement();
            int istaken=0;
            String sql;
            sql = "SELECT _id,title,author,publication,librarylocation FROM librarybookloan.Books WHERE title='" + title +"' AND istaken='"+istaken+"' ";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publication"),
                        rs.getString("librarylocation")));


            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return books;

    }



    public static ArrayList<Book> getAllBooks(){

        ArrayList<Book> books=new ArrayList<>();
        Statement stmt = null;

        if(conn==null){
            getConnection();
        }else{

            // connection already established
        }

        try {
            stmt = conn.createStatement();
            int istaken=0;
            String sql;
            sql = "SELECT _id,title,author,publication,librarylocation FROM librarybookloan.Books ";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publication"),
                        rs.getString("librarylocation")));


            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return books;

    }

    public static ArrayList<User> getAllUsers(){

        ArrayList<User> users=new ArrayList<>();
        Statement stmt = null;

        if(conn==null){
            getConnection();
        }

        try {
            stmt = conn.createStatement();
            int istaken=0;
            String sql;
            sql = "SELECT _id,fullname,email, password, type, totalfine FROM librarybookloan.Users ";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("_id"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("type"),
                        rs.getFloat("totalfine")));


            }


            for (int i = 0; i < users.size(); i++) {
                System.out.println(users.get(i).toString());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return users;

    }


    public static Book insertBook(Book input){

        PreparedStatement statement=null;


        if(conn==null){
            getConnection();
        }else{

            // connection already established
        }

        try {

            String sql;
            sql = "INSERT INTO LIBRARYBOOKLOAN.Books(title,author,publication,librarylocation) VALUES (?,?,?,?)";

            statement = conn.prepareStatement(sql);
            statement.setString(1,input.getTitle());
            statement.setString(2,input.getAuthor());
            statement.setString(3, input.getPublication());
            statement.setString(4, input.getLocation());
            statement.executeUpdate();


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            input=null;
            e.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            input=null;

            e.printStackTrace();
        }

        return input;




    }


    public static boolean insertCheckout(int userId,int bookId){


        // bu fonksiyon checkout yapacak 2 dakikalık yetiştiremedim tamamlıcam akşam.


        PreparedStatement statement=null;


        if(conn==null){
            getConnection();
        }else{

            // connection already established
        }

        try {

            String sql;
            sql = "INSERT INTO librarybookloan.UserBooks(startdate, enddate, userid, bookid) VALUES (?,?,?,?)";

            Calendar calendar = Calendar.getInstance();


            java.util.Date currentDate = calendar.getTime();

            // now, create a java.sql.Date from the java.util.Date

            LocalDate startDate=LocalDate.now();


            LocalDate endDate=LocalDate.now().plusMonths(1);


            statement = conn.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(startDate));
            statement.setDate(2,Date.valueOf(endDate));
            statement.setInt(3, userId);
            statement.setInt(4, bookId);
            statement.executeUpdate();


        } catch (SQLException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
            return false;
        }catch(Exception e){
            //Handle errors for Class.forName


            e.printStackTrace();
            return false;
        }
        return true;


    }
    public  static User insertUser(User input){

        PreparedStatement statement=null;


        if(conn==null){
            getConnection();
        }else{

            // connection already established
        }

        try {

            String sql;
            sql = "INSERT INTO LIBRARYBOOKLOAN.Users(fullname,email,password,type) VALUES (?,?,?,?)";

            statement = conn.prepareStatement(sql);
            statement.setString(1,input.getFullname());
            statement.setString(2,input.getEmail());
            statement.setString(3, input.getPassword());
            statement.setString(4, input.getType());
            statement.executeUpdate();


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            input=null;
            e.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            input=null;

            e.printStackTrace();
        }
        return input;




    }

    public static ArrayList<Book> getUserBooks(int id) {
        ArrayList<Book> books=new ArrayList<>();
        Statement stmt = null;


        if(conn==null){
            getConnection();
        }else{

            // connection already established
        }

        try {
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT Books._id,Books.title,author,publication,librarylocation FROM librarybookloan.UserBooks INNER JOIN librarybookloan.Books ON UserBooks.bookid=Books._id WHERE userid= '" + id +"'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publication"),
                        rs.getString("librarylocation")));


            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return books;
    }



}

package com.example.ecommerce;
import java.sql.*;
public class DBConnection {
    private final String dbUrl="jdbc:mysql://localhost:3306/ecommerce";
    private final String dbUN="root";
    private final String dbPWD="Snd@1142000";
    private Statement getStatement(){
        try{
            Connection con=DriverManager.getConnection(dbUrl,dbUN,dbPWD);
            return con.createStatement();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet getQueryTable(String query){
        try{
            Statement statement=getStatement();
            return statement.executeQuery(query);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public int updateDatabase(String query){
        try{
            Statement statement=getStatement();
            return statement.executeUpdate(query);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        DBConnection dbcon=new DBConnection();
        ResultSet rs=dbcon.getQueryTable("select * from customer");
        if(rs!=null){
            System.out.println("connection successful");
        }
        else System.out.println("connection failed");

    }
}

package com.example.ecommerce;

import java.sql.ResultSet;

public class Login {
    public Customer customerLogin(String userName,String pwd){
        String query="select * from customer where email='"+userName+"'and password='"+pwd+"'";
        DBConnection con=new DBConnection();
        try{
            ResultSet rs=con.getQueryTable(query);
            if(rs.next())
                return new Customer(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getString("mobile"));

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Login lg=new Login();
        Customer customer=lg.customerLogin("abcd@gmail.com","123456");
        System.out.println("Welcome: "+customer.getName());
        //System.out.println(lg.customerLogin("abcd@gmail.com","123456"));
    }
}

package com.example.ecommerce;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;

public class Product {
    private SimpleIntegerProperty id;
    private SimpleStringProperty pname;
    private SimpleDoubleProperty price;

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getPname() {
        return pname.get();
    }
    public SimpleStringProperty pnameProperty() {
        return pname;
    }
    public double getPrice() {
        return price.get();
    }
    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public Product(int id, String pname, Double price) {
        this.id = new SimpleIntegerProperty(id);
        this.pname = new SimpleStringProperty(pname);
        this.price = new SimpleDoubleProperty(price);
    }
    public static ObservableList<Product> getAllProducts(){
        String selectAllProducts="select id,pname,price from product";
        return fetchProductData(selectAllProducts);
    }
    public  static ObservableList<Product> fetchProductData(String query){
        ObservableList<Product> data= FXCollections.observableArrayList();
        DBConnection dbcon=new DBConnection();
        try{
            ResultSet rs=dbcon.getQueryTable(query);
            while(rs.next()){
                Product product=new Product(rs.getInt("id"),rs.getString("pname"), rs.getDouble("price"));
                data.add(product);
            }
            return data;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

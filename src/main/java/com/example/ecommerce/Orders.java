package com.example.ecommerce;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Date;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Orders {
    private SimpleIntegerProperty id;
    private SimpleStringProperty pname;
    private SimpleIntegerProperty quantity;
   private SimpleStringProperty order_date;
    private SimpleStringProperty order_status;
    //private SimpleDoubleProperty price;


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

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public String getOrder_date() {
        return order_date.get();
    }

    public SimpleStringProperty order_dateProperty() {
        return order_date;
    }

    public String getOrder_status() {
        return order_status.get();
    }

    public SimpleStringProperty order_statusProperty() {
        return order_status;
    }

    public Orders(int id, String pname, int quantity, String  order_date, String order_status) {
        //DateFormat df=new DateFormat();
        this.id = new SimpleIntegerProperty(id);
        this.pname = new SimpleStringProperty(pname);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.order_date = new SimpleStringProperty(order_date);
        this.order_status = new SimpleStringProperty(order_status);
    }
    public static ObservableList<Orders> getAllOrders(){
        String selectOrders="select orders.id,product.pname,orders.quantity,orders.order_date,orders.order_status from orders join product on orders.product_id=product_id;";
        return fetchOrderData(selectOrders);
    }
    public  static ObservableList<Orders> fetchOrderData(String query){
        ObservableList<Orders> data= FXCollections.observableArrayList();
        DBConnection dbcon=new DBConnection();
        try{
            ResultSet rs=dbcon.getQueryTable(query);
            while(rs.next()){
                java.sql.Date dateSql=rs.getDate("order_date");

                java.util.Date dateutil=new java.util.Date(dateSql.getTime());
                DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
                String odate=df.format(dateutil);
                Orders order=new Orders(rs.getInt("id"),rs.getString("pname"),rs.getInt("quantity"),odate,rs.getString("order_status"));
                data.add(order);
            }
            return data;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

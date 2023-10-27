package com.example.ecommerce;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class OrderList

{
    private TableView<Orders> orderTable;

    public VBox createOrderTable(ObservableList<Orders> data){
        //columns
        TableColumn id=new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn pname=new TableColumn("PNAME");
        pname.setCellValueFactory(new PropertyValueFactory<>("pname"));

        TableColumn quantity=new TableColumn("QUANTITY");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn order_date=new TableColumn("ORDER DATE");
        order_date.setCellValueFactory(new PropertyValueFactory<>("order_date"));

        TableColumn order_status=new TableColumn("ORDER STATUS");
        order_status.setCellValueFactory(new PropertyValueFactory<>("order_status"));
        //Data - dummy data

        orderTable=new TableView<>();
        orderTable.getColumns().addAll(id,pname,quantity,order_date,order_status);
        orderTable.setItems(data);
        orderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vbox=new VBox();
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(orderTable);
        return vbox;
    }
    public VBox getAllOrders(){
        ObservableList<Orders> data=Orders.getAllOrders();
        return createOrderTable(data);
    }
}

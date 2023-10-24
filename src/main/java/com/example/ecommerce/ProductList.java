package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class ProductList {
    private TableView<Product> productTable;

    public VBox createTable(ObservableList<Product> data){
        //columns
        TableColumn id=new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn pname=new TableColumn("PNAME");
        pname.setCellValueFactory(new PropertyValueFactory<>("pname"));

        TableColumn price=new TableColumn("PRICE");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        //Data - dummy data

        productTable=new TableView<>();
        productTable.getColumns().addAll(id,pname,price);
        productTable.setItems(data);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vbox=new VBox();
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(productTable);
        return vbox;
    }
//    public VBox getDummyTable(){
//        ObservableList<Product> data =FXCollections.observableArrayList();
//        data.add(new Product(2,"Iphone",44500.00));
//        data.add(new Product(5,"laptop",44500.00));
//        data.add(new Product(7,"printer",30000.90));
//        return createTable(data);
//    }

    public VBox getAllProducts(){
        ObservableList<Product> data=Product.getAllProducts();
        return createTable(data);
    }
    public Product getSelectedProduct(){
      return productTable.getSelectionModel().getSelectedItem();
    }
    public VBox getProductsInCart(ObservableList<Product> data){
        return createTable(data);
    }
}

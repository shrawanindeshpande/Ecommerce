package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class UserInterface {
    GridPane loginPage;
    HBox headerBar;
    HBox footerBar;
    Button signInButton;
    Label welcomeLabel;
    VBox body;

    Customer loggedInCustomer;

    ProductList productlist=new ProductList();

    VBox productPage;

    Button placeOrderButton=new Button("Place Order");


    ObservableList<Product> itemsInCart= FXCollections.observableArrayList();

    BorderPane createContent(){
        BorderPane root=new BorderPane();
        root.setPrefSize(800,600);
       // root.getChildren().add(loginPage);
       // root.setCenter(loginPage);
        root.setTop(headerBar);
        body=new VBox();
        body.setPadding(new Insets(10));
        body.setAlignment(Pos.CENTER);
        productPage=productlist.getAllProducts();
        root.setCenter(body);
        body.getChildren().add(productPage);
        root.setBottom(footerBar);
        return root;

    }
    public UserInterface(){
        createLoginPage();
        createHeaderBar();
        createFooterBar();
    }
    private void createLoginPage(){
        //text
        Text userNameText=new Text("User Name");
        Text pwdText=new Text("Password");
        //text feilds for input
        TextField userName=new TextField();
        userName.setPromptText("Enter User Name");
        PasswordField pwd=new PasswordField();
        pwd.setPromptText("Enter Password");
        Label mlabel=new Label("Hi");
        Button loginB=new Button("Login");
        loginB.setStyle("-fx-background-color: #71a3f5;");
        loginPage=new GridPane();
        loginPage.setStyle("-fx-background-color: #C0C0C0;");
        loginPage.setAlignment(Pos.CENTER);
        loginPage.setVgap(20.0);
        loginPage.setHgap(20.0);
        loginPage.addColumn(0,userNameText,pwdText,mlabel);
        loginPage.addColumn(1,userName,pwd,loginB);
        loginB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name=userName.getText();
                String pass=pwd.getText();
                Login login=new Login();
                loggedInCustomer=login.customerLogin(name,pass);
                if(loggedInCustomer!=null){
                    mlabel.setText("Welcome: "+loggedInCustomer.getName());
                    welcomeLabel.setText("Welcome "+loggedInCustomer.getName());
                    headerBar.getChildren().add(welcomeLabel);
                    body.getChildren().clear();
                    body.getChildren().add(productPage);
                }
                else{
                    mlabel.setText("Login Failed ! please provide correct credential");
                }
            }
        });
    }
    private void createHeaderBar(){
        TextField searchBar=new TextField();
        searchBar.setPromptText("Search Product");
        searchBar.setPrefWidth(220);
        Button searchButton=new Button("Search");

        signInButton=new Button("Sign in");
        welcomeLabel=new Label();

        Button cartButton=new Button("Cart");

        headerBar =new HBox();
        headerBar.setStyle("-fx-background-color: #71a3f5;");
        headerBar.setPadding(new Insets(10));
        headerBar.setSpacing(10);
        headerBar.setAlignment(Pos.CENTER);
        headerBar.getChildren().addAll(searchBar,searchButton,signInButton,cartButton);

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();//clear the page
                body.getChildren().add(loginPage);//put login page on click of sign in button
                headerBar.getChildren().remove(signInButton);
            }
        });
        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                VBox prodPage=productlist.getProductsInCart(itemsInCart);
                prodPage.setAlignment(Pos.CENTER);
                prodPage.setSpacing(10);
                prodPage.getChildren().add(placeOrderButton);
                body.getChildren().add(prodPage);
                footerBar.setVisible(false);//all cases needs to be handled
            }
        });

        //placeOrderButton.setAlignment(Pos.CENTER);
        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (itemsInCart==null){
                    //please select a product to place order
                    showDialog("please add some products in cart");
                }
                if(loggedInCustomer==null){
                    showDialog("Please login before placing order");
                }
                int count=Order.placeMultipleOrder(loggedInCustomer,itemsInCart);
                if(count!=0){
                    showDialog("order for "+count+" products placed successfully!!");
                }
                else{
                    showDialog("order failed");
                }
            }
        });

    }
    private void createFooterBar(){

        Button buyNowButton=new Button("Buy Now");
        Button addToCartButton=new Button("Add to cart");
        signInButton=new Button("Sign in");
        welcomeLabel=new Label();
        footerBar =new HBox();
        footerBar.setStyle("-fx-background-color: #71a3f5;");
        footerBar.setPadding(new Insets(10));
        footerBar.setSpacing(10);
        footerBar.setAlignment(Pos.CENTER);
        footerBar.getChildren().addAll(buyNowButton,addToCartButton);

        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product=productlist.getSelectedProduct();
                if (product==null){
                    //please select a product to place order
                    showDialog("please select a product before placing order");
                }
                if(loggedInCustomer==null){
                    showDialog("Please login before placing order");
                }
                boolean status=Order.placeOrder(loggedInCustomer,product);
                if(status==true){
                    showDialog("order placed successfully!!");
                }
                else{
                    showDialog("order failed");
                }
            }
        });
        addToCartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product=productlist.getSelectedProduct();
                if (product==null){
                    //please select a product to place order
                    showDialog("please select a product to add it to cart!");
                }
                itemsInCart.add(product);
                showDialog("Selected item has been added to the cart successfully!");
            }
        });

    }
    private void showDialog(String msg){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.setTitle("Message");
        alert.showAndWait();
    }
}

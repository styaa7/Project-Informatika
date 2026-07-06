package craftflow.controller.admin;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import craftflow.model.Order;
import craftflow.model.Product;
import craftflow.service.XMLService;
import craftflow.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DashboardController implements Initializable {

    @FXML
    private Label lblTotalOrders;
    @FXML
    private Label lblTotalProducts;
    @FXML
    private Label lblTotalRevenue;
    @FXML
    private TableView<Order> tableRecentOrders;
    @FXML
    private TableColumn<Order, String> tcOrderIdDashboard;
    @FXML
    private TableColumn<Order, String> tcCustomerDashboard;
    @FXML
    private TableColumn<Order, String> tcStatusDashboard;

    ObservableList<Order> dataOrderDashboard = FXCollections.observableArrayList();
    ObservableList<Product> dataProductDashboard = FXCollections.observableArrayList();
    XMLService xmlService = new XMLService();


    @FXML private void goDashboard()  { SceneManager.switchScene("/resources/view/admin-dashboard/admin-dashboard.fxml"); }
    @FXML private void goOrder()      { SceneManager.switchScene("/resources/view/admin-dashboard/order-management.fxml"); }
    @FXML private void goProduct()    { SceneManager.switchScene("/resources/view/admin-dashboard/product-management.fxml"); }
    @FXML private void goCustomer()   { /* TODO: arahkan ke scene customer-management.fxml */ }
    @FXML private void goEmployee()   { /* TODO: arahkan ke scene employee-management.fxml */ }
    @FXML private void goBilling()    { /* TODO: arahkan ke scene billing.fxml */ }
    @FXML private void goAnalytics()  { /* TODO: arahkan ke scene analytics.fxml */ }
    @FXML private void goSetting()    { /* TODO: arahkan ke scene setting.fxml */ }
    @FXML private void goHelp()       { /* TODO: arahkan ke scene help.fxml */ }
    @FXML private void handleLogout() { /* TODO: kembali ke scene login.fxml */ }

    private int menghitungTotalOrder() {
        List<Order> dataOrderList = dataOrderDashboard;
        
        int totalOrder = 0;
        for(Order o : dataOrderList) {
            totalOrder += 1;
        }

        return totalOrder;
    }

    private int menghitungTotalProduct() {
        List<Product> dataProductList = dataProductDashboard;

        int totalProduct = 0;
        for(Product p : dataProductList) {
            totalProduct += 1;
        }

        return totalProduct;
    }

    private double menghitungTotalRevenue() {
        List<Order> dataOrderList = dataOrderDashboard;

        double totalRevenue = 0;
        for (Order o : dataOrderList) {
            totalRevenue += o.getTotal();
        }

        return totalRevenue;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcOrderIdDashboard.setCellValueFactory(new PropertyValueFactory<Order, String>("orderId"));
        tcCustomerDashboard.setCellValueFactory(new PropertyValueFactory<Order, String>("customerName"));
        tcStatusDashboard.setCellValueFactory(new PropertyValueFactory<Order, String>("Status"));

        dataOrderDashboard.addAll(xmlService.loadOrders());
        dataProductDashboard.addAll(xmlService.loadProducts());
        tableRecentOrders.setItems(dataOrderDashboard);

        lblTotalOrders.setText(String.valueOf(menghitungTotalOrder()));
        lblTotalProducts.setText(String.valueOf(menghitungTotalProduct()));
        lblTotalRevenue.setText("Rp. "+String.valueOf(menghitungTotalRevenue()));
    }
}

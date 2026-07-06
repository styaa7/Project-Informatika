package craftflow.controller.admin;

import java.net.URL;
import java.util.ResourceBundle;

import craftflow.model.Order;
import craftflow.service.XMLService;
import craftflow.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OrderManagementController implements Initializable {

    @FXML
    private ComboBox<String> statusBox;
    @FXML
    private TableView<Order> tableOrder;
    @FXML
    private TableColumn<Order, String> tcOrderId;
    @FXML
    private TableColumn<Order, String> tcCustomerName;
    @FXML
    private TableColumn<Order, String> tcProduct;
    @FXML
    private TableColumn<Order, String> tcDate;
    @FXML
    private TableColumn<Order, Double> tcTotal;
    @FXML
    private TableColumn<Order, String> tcStatus;

    ObservableList<Order> dataOrder = FXCollections.observableArrayList();
    XMLService xmlService = new XMLService();

    // ===== Navigasi Sidebar =====
    @FXML private void goDashboard()  { SceneManager.switchScene("/resources/view/admin-dashboard/admin-dashboard.fxml"); }
    @FXML private void goOrder()      { SceneManager.switchScene("/resources/view/admin-dashboard/order-management.fxml"); }
    @FXML private void goProduct()    { SceneManager.switchScene("/resources/view/admin-dashboard/product-management.fxml"); }
    @FXML private void goCustomer()   { /* TODO */ }
    @FXML private void goEmployee()   { /* TODO */ }
    @FXML private void goBilling()    { /* TODO */ }
    @FXML private void goAnalytics()  { /* TODO */ }
    @FXML private void goSetting()    { /* TODO */ }
    @FXML private void goHelp()       { /* TODO */ }
    @FXML private void handleLogout() { /* TODO */ }

    // ===== Aksi halaman Order =====
    @FXML 
    private void handleAddOrder()  {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/admin-dashboard/form-tambah-order.fxml"));
            Parent root = loader.load();

            FormTambahOrderController formTambahanController = loader.getController();

            Stage formStage = new Stage();
            formStage.setTitle("Tambah Data Baru");

            formStage.initModality(Modality.APPLICATION_MODAL);

            formStage.setScene(new Scene(root));

            formStage.setResizable(false);

            formStage.showAndWait();

            Order hasilInput = formTambahanController.getOrderBaru();

            if (hasilInput != null) {
                dataOrder.add(hasilInput);
                xmlService.saveOrders(dataOrder);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Gagal Memuat Form Tambah Data");
        }
    }

    @FXML
    public void handleDeleteOrder() {
        Order selectetOrder = tableOrder.getSelectionModel().getSelectedItem();

        if (selectetOrder == null) {
            System.out.println("Pilih produk terlebih dahulu");
            return;
        }

        dataOrder.remove(selectetOrder);

        tableOrder.refresh();
        xmlService.saveOrders(dataOrder);
    }

    @FXML
    public void handleUpdateOrder() {
        Order selectedOrder = tableOrder.getSelectionModel().getSelectedItem();

        try {
            if (selectedOrder != null) {
                selectedOrder.setStatus(statusBox.getValue());
            }
    
            tableOrder.refresh();
            xmlService.saveOrders(dataOrder);
    
            // clearFields();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML private void handleGoToPage()  {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcOrderId.setCellValueFactory(new PropertyValueFactory<Order, String>("orderId"));
        tcCustomerName.setCellValueFactory(new PropertyValueFactory<Order, String>("customerName"));
        tcProduct.setCellValueFactory(new PropertyValueFactory<Order, String>("productId"));
        tcDate.setCellValueFactory(new PropertyValueFactory<Order, String>("tanggalPesanan"));
        tcTotal.setCellValueFactory(new PropertyValueFactory<Order, Double>("Total"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<Order, String>("Status"));

        dataOrder.addAll(xmlService.loadOrders());
        tableOrder.setItems(dataOrder);

        tableOrder.setOnMouseClicked(event -> {
            Order selected = tableOrder.getSelectionModel().getSelectedItem();

            if (selected != null) {
                statusBox.setValue(selected.getStatus());     
            }
        });

        statusBox.getItems().addAll("Waiting for Payment", "Processing", "Shipped", "Completed", "Cancelled");

    }
}

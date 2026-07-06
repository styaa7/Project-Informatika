package craftflow.controller.admin;

import craftflow.service.XMLService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import craftflow.model.Product;
import craftflow.util.SceneManager;

public class ProductManagementController implements Initializable {

    @FXML
    private TextField stockField;
    @FXML
    private TextField priceField;
    @FXML 
    private TableView<Product> tableProducts;
    @FXML
    private TableColumn<Product, String> tcIdProduct;
    @FXML
    private TableColumn<Product, String> tcName;
    @FXML
    private TableColumn<Product, Double> tcPrice;
    @FXML
    private TableColumn<Product, Integer> tcStock;
    @FXML
    private TableColumn<Product, String> tcKategori;
    @FXML 
    private TableColumn<Product, String> tcStatus;

    public void clearFields() {
        priceField.clear();
        stockField.clear();
    }

    ObservableList<Product> dataProduct = FXCollections.observableArrayList();
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

    // ===== Aksi halaman Product =====
    @FXML 
    private void handleAddProduct()  {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/admin-dashboard/form-tambah-product.fxml"));
            Parent root = loader.load();

            FormTambahProductController formTambahanController = loader.getController();

            Stage formStage = new Stage();
            formStage.setTitle("Tambah Data Baru");

            formStage.initModality(Modality.APPLICATION_MODAL);

            formStage.setScene(new Scene(root));

            formStage.setResizable(false);

            formStage.showAndWait();

            Product hasilInput = formTambahanController.getProductBaru();

            if (hasilInput != null) {
                dataProduct.add(hasilInput);
                xmlService.saveProducts(dataProduct);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Gagal Memuat Form Tambah Data");
        }
    }

    @FXML
    private void handleDeleteProduct() {
        Product selected = tableProducts.getSelectionModel().getSelectedItem();

        if (selected == null) {
            System.out.println("pilih product dulu");
            return;
        }

        dataProduct.remove(selected);

        tableProducts.refresh();
        xmlService.saveProducts(dataProduct);
    }

    @FXML
    private void handleUpdateProduct() {
        Product selectedProduct = tableProducts.getSelectionModel().getSelectedItem();

        try {
            if (selectedProduct != null) {
                // selectedProduct.setProductId(productIdField.getText());
                // selectedProduct.setNama(nameField.getText());
                // selectedProduct.setKategori(kategoriBox.getValue());
                selectedProduct.setPrice(Double.parseDouble(priceField.getText()));
                selectedProduct.setStock(Integer.parseInt(stockField.getText()));

                tableProducts.refresh();
                xmlService.saveProducts(dataProduct);

                clearFields();
            }
        }catch (Exception e) {
            System.out.println("Error");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcIdProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("productId"));
        tcName.setCellValueFactory(new PropertyValueFactory<Product, String>("Name"));
        tcKategori.setCellValueFactory(new PropertyValueFactory<Product, String>("Kategori"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("Price"));
        tcStock.setCellValueFactory(new PropertyValueFactory<Product, Integer>("Stock"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<Product, String>("Status"));
        
        dataProduct.addAll(xmlService.loadProducts());
        tableProducts.setItems(dataProduct);

        tableProducts.setOnMouseClicked(event -> {
            Product selected = tableProducts.getSelectionModel().getSelectedItem();

            if (selected != null) {
                // productIdField.setText(selected.getProductId());
                // nameField.setText(selected.getName());
                // kategoriBox.setValue(selected.getKategori());
                stockField.setText(String.valueOf(selected.getStock()));
                priceField.setText(String.valueOf(selected.getPrice()));
            }
        });
    }
}

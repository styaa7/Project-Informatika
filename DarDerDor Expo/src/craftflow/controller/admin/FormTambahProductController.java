package craftflow.controller.admin;

import java.net.URL;
import java.util.ResourceBundle;

import craftflow.model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormTambahProductController implements Initializable {


    @FXML
    private TextField productIdField;
    @FXML
    private TextField namaField;
    @FXML
    private TextField stockField;
    @FXML
    private TextField statusField;
    @FXML 
    private TextField priceField;
    @FXML 
    private ComboBox<String> kategoriBox;

    private Product productBaru;
    
    @FXML
    public void simpanButton(ActionEvent event) {
        try {
            String tempId = productIdField.getText();
            String tempNama = namaField.getText();
            String  tempKategori = kategoriBox.getValue();
            int tempStock = Integer.parseInt(stockField.getText());
            double tempHarga = Double.parseDouble(priceField.getText());

            productBaru = new Product(tempId, tempNama, tempKategori, tempHarga, tempStock);

            Node source = (Node) event.getSource();

            Stage stage = (Stage) source.getScene().getWindow();

            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Tidak Boleh Kosong");
        }
    }

    public Product getProductBaru() {
        return productBaru;
    }

    @FXML 
    public void batalButton(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        kategoriBox.getItems().addAll("Jacket", "Shoe", "Belt", "Bag", "Glove", "Wallet");
    }
    
}

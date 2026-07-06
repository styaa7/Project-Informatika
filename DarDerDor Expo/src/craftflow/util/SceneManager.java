package craftflow.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Utility untuk memuat & memindahkan scene pada Stage yang sama,
 * sehingga tiap modul (Dashboard, Product, Order, dll) berjalan
 * sebagai Scene terpisah tanpa membuka window baru.
 */
public class SceneManager {

    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    /**
     * Pindah ke scene lain berdasarkan path FXML.
     * Contoh: SceneManager.switchScene("/com/example/view/dashboard.fxml");
     */
    public static void switchScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Parent root = loader.load();

            Scene currentScene = primaryStage.getScene();
            if (currentScene == null) {
                primaryStage.setScene(new Scene(root));
            } else {
                currentScene.setRoot(root);
            }

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

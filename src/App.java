import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
            Scene scene = new Scene(loader.load());

            primaryStage.setScene(scene);
            primaryStage.setTitle("Login - Aplikasi Gudang");
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Gagal memuat file FXML atau CSS. Periksa kembali path file Anda.");
            e.printStackTrace();
        }
    }

   public static void main(String[] args) {
        launch(args);
    }
}

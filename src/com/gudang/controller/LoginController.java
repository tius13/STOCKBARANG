package com.gudang.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label lblStatus;
    @FXML
    private PasswordField txtPass;
    @FXML
    private TextField txtUser;

    @FXML
    private void loginAction(ActionEvent event) {
        String user = txtUser.getText();
        String pass = txtPass.getText();

        if (user.equals("admin") && pass.equals("123")) {
            try {
                // Ganti window saat login berhasil
                Stage stage = (Stage) txtUser.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.setTitle("Menu Utama");
            } catch (IOException e) {
                e.printStackTrace();
                lblStatus.setText("Error: Gagal memuat menu!");
            }
        } else {
            lblStatus.setText("Login gagal!");
        }
    }
}

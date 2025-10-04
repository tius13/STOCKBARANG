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

public class MenuController {


@FXML
private void openBarang() throws Exception {
    Stage stage = new Stage();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Barang.fxml"));
    Scene scene = new Scene(loader.load());
    stage.setScene(scene);
    stage.setTitle("Manajemen Barang");
    stage.show();
}

@FXML
private void openSupplier() throws Exception {
    Stage stage = new Stage();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Supplier.fxml"));
    Scene scene = new Scene(loader.load());
    stage.setScene(scene);
    stage.setTitle("Manajemen Supplier");
    stage.show();
}}


package com.gudang.controller;

import com.gudang.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplierController {

    @FXML
    private TableView<Supplier> tableSupplier;
    @FXML
    private TableColumn<Supplier, String> colKode;
    @FXML
    private TableColumn<Supplier, String> colNama;
    @FXML
    private TableColumn<Supplier, String> colAlamat;
    @FXML
    private TableColumn<Supplier, String> colTelepon;
    private ObservableList<Supplier> supplierData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colKode.setCellValueFactory(cellData -> cellData.getValue().kodeProperty());
        colNama.setCellValueFactory(cellData -> cellData.getValue().namaProperty());
        colAlamat.setCellValueFactory(cellData -> cellData.getValue().alamatProperty());
        colTelepon.setCellValueFactory(cellData -> cellData.getValue().teleponProperty());
        tableSupplier.setItems(supplierData);
    }

    @FXML
    private void handleTambah() {
        showFormSupplier(null);
    }

    @FXML
    private void handleEdit() {
        Supplier selectedSupplier = tableSupplier.getSelectionModel().getSelectedItem();
        if (selectedSupplier != null) {
            showFormSupplier(selectedSupplier);
        } else {
            showAlert(Alert.AlertType.WARNING, "peringatan", "pilih salah satu supplier dari tabel untuk diubah");
        }
    }

    @FXML
    private void handleHapus() {
        Supplier selectedSupplier = tableSupplier.getSelectionModel().getSelectedItem();
        if (selectedSupplier != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("konfirmasi hapus");
            alert.setHeaderText("hapus " + selectedSupplier.getNama() + "?");
            alert.setContentText("apakah anda yakin ingin menghapus data ini?");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    supplierData.remove(selectedSupplier);
                }
            });
        } else {
            showAlert(Alert.AlertType.WARNING, "peringatan", "pilih salah satu supplier dari tabel untuk dihapus");
        }
    }

    private void showFormSupplier(Supplier supplier) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FormSupplier.fxml"));
            Stage formStage = new Stage();
            formStage.setTitle(supplier == null ? "tambah supplier" : "edit supplier");
            formStage.initModality(Modality.WINDOW_MODAL);
            formStage.initOwner(tableSupplier.getScene().getWindow());
            Scene scene = new Scene(loader.load());
            formStage.setScene(scene);

            FormSupplierController controller = loader.getController();
            controller.setData(supplierData, supplier);
            formStage.showAndWait();
            
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "error", "gagal membuka form supplier");
        }
    }
    
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


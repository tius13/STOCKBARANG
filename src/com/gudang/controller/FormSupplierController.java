package com.gudang.controller;

import com.gudang.model.Supplier;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormSupplierController {

    @FXML
    private TextField txtKode;
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtAlamat;
    @FXML
    private TextField txtTelepon;

    private ObservableList<Supplier> supplierData;
    private Supplier supplierToEdit;
    private boolean isEditMode = false;

    public void setData(ObservableList<Supplier> supplierData, Supplier supplier) {
        this.supplierData = supplierData;
        this.supplierToEdit = supplier;

        if (supplier != null) {
            isEditMode = true;
            txtKode.setText(supplier.getKode());
            txtNama.setText(supplier.getNama());
            txtAlamat.setText(supplier.getAlamat());
            txtTelepon.setText(supplier.getTelepon());
            
            txtKode.setDisable(true);
        }
    }

    @FXML
    private void handleSimpan() {
        if (!isInputValid()) {
            return; 
        }

        String kode = txtKode.getText().trim();
        String nama = txtNama.getText().trim();
        String alamat = txtAlamat.getText().trim();
        String telepon = txtTelepon.getText().trim();

        if (isEditMode) {
            supplierToEdit.setNama(nama);
            supplierToEdit.setAlamat(alamat);
            supplierToEdit.setTelepon(telepon);
        } else {
            Supplier newSupplier = new Supplier(kode, nama, alamat, telepon);
            supplierData.add(newSupplier);
        }

        closeWindow();
    }

    private boolean isInputValid() {
        String errorMessage = "";
        String kode = txtKode.getText().trim();
        String nama = txtNama.getText().trim();
        String telepon = txtTelepon.getText().trim();
        String alamat = txtAlamat.getText().trim();


        if (kode.isEmpty()) {
            errorMessage += "field Kode wajib diisi\n";
        }

        if (nama.isEmpty()) {
            errorMessage += "field nama wajib diisi\n";
        }

         if (alamat.isEmpty()) {
            errorMessage += "field alamat wajib diisi\n";
        }

        if (telepon.isEmpty()) {
            errorMessage += "field telepon wajib diisi\n";
        }

        if (!isEditMode) {
            for (Supplier s : supplierData) {
                if (s.getKode().equalsIgnoreCase(kode)) {
                    errorMessage += "kode supplier sudah ada\n";
                    break;
                }
            }
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            showAlert(Alert.AlertType.ERROR, "input tidak valid", errorMessage);
            return false;
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) txtKode.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


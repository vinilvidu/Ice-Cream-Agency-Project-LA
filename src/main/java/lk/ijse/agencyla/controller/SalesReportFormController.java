package lk.ijse.agencyla.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SalesReportFormController {

    @FXML
    private ComboBox<?> cmbItemCode;

    @FXML
    private ComboBox<?> cmbVanId;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private Label lblAmount;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPrice;

    @FXML
    private TableView<?> tblSale;

    @FXML
    private TextField txtBillCode;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtFQty;

    @FXML
    private TextField txtQty;

    @FXML
    void btnAddItemOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceReportOnAction(ActionEvent event) {

    }

    @FXML
    void cmbItemCodeOnAction(ActionEvent event) {

    }

    @FXML
    void txtAddDateOnAction(ActionEvent event) {

    }

    @FXML
    void txtAddOrderIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtFQtyOnAction(ActionEvent event) {

    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }

}

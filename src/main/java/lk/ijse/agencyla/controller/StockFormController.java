package lk.ijse.agencyla.controller;

import com.sun.javafx.menu.MenuItemBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.agencyla.bo.BOFactory;
import lk.ijse.agencyla.bo.custom.StockBO;
import lk.ijse.agencyla.controller.util.Validate;
import lk.ijse.agencyla.dto.StockDTO;
import lk.ijse.agencyla.entity.Stock;
import lk.ijse.agencyla.view.tdm.StockTM;

import java.sql.SQLException;
import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class StockFormController {

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<StockTM> tblStock;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitPrice;

    StockBO stockBO = (StockBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STOCK);
    private MenuItemBase btnDeleteOnAction;
    private CollationElementIterator btnSaveOnAction;

    public void initialize(){
        tblStock.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblStock.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStock.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblStock.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));


        //initUI();

        /*tblStock.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDeleteOnAction.setDisable(newValue == null);
            btnSaveOnAction.setText(newValue != null ? "Update" : "Save");
            btnSaveOnAction.equals(newValue == null);

            if (newValue != null) {
                txtId.setText(String.valueOf(newValue.getClass()));
                txtName.setText(String.valueOf(newValue.getClass()));
                txtUnitPrice.setText(String.valueOf(newValue.toString().equals(2)));
                txtQty.setText(newValue.getClass() + "");

                txtId.equals(false);
                txtName.equals(false);
                txtUnitPrice.setDisable(false);
                txtQty.equals(false);
            }
        });

        txtQty.setOnAction(event -> btnSaveOnAction.reset());*/
        loadAllStock();

    }

    private void loadAllStock() {
        tblStock.getItems().clear();
        try {
            //*Get all items*//
            ArrayList<StockDTO> allStocks = stockBO.getAllStocks();
            for (StockDTO s : allStocks) {
                tblStock.getItems().add(new StockTM (s.getItemCode(), s.getName(), s.getUnitPrice(), s.getQty()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        txtId.clear();
        txtName.clear();
        txtUnitPrice.clear();
        txtQty.clear();
        txtId.setDisable(true);
        txtName.setDisable(true);
        txtUnitPrice.setDisable(true);
        txtQty.setDisable(true);
        txtId.setEditable(false);


    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();

    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtUnitPrice.setText("");
        txtQty.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String itemCode = txtId.getText();

        boolean isDeleted = stockBO.deleteStock(itemCode);
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "item deleted!").show();
            initialize();
        }
    }

    @FXML
    void btnPrintOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isValidate = validateStock();

        if (isValidate) {
            String item_code = txtId.getText();
            String name = txtName.getText();
            Double unit_price = Double.valueOf(txtUnitPrice.getText());
            Integer qty = Integer.valueOf(txtQty.getText());

            StockDTO dto = new StockDTO(item_code, name, unit_price, qty);

            try {
                boolean isSaved = stockBO.saveStock(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "stock saved!").show();
                    initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean validateStock() {

        int num=0;
        String code = txtId.getText();
        boolean isCodeValidate= Pattern.matches("(it0)[0-9]{3,7}",code);
        if (!isCodeValidate){
            num=1;
            Validate.vibrateTextField(txtId);
        }

        String name=txtName.getText();
        boolean isNameValidate= Pattern.matches("[A-z 0-9]{3,}",name);
        if (!isNameValidate){
            num=1;
            Validate.vibrateTextField(txtName);
        }

        String uPrice=txtUnitPrice.getText();
        boolean isPriceValidate= Pattern.matches("[0-9 .]{2,}",uPrice);
        if (!isPriceValidate){
            num=1;
            Validate.vibrateTextField(txtUnitPrice);
        }

        String qty=txtQty.getText();
        boolean isQtyValidate= Pattern.matches("[0-9 ]{1,}",qty);
        if (!isQtyValidate){
            num=1;
            Validate.vibrateTextField(txtQty);
        }


        if(num==1){
            num=0;
            return false;
        }else {
            num=0;
            return true;

        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String item_code=txtId.getText();
        String name=txtName.getText();
        Double unit_price= Double.valueOf(txtUnitPrice.getText());
        Integer qty= Integer.valueOf(txtQty.getText());

        StockDTO dto = new StockDTO(item_code, name, unit_price, qty);

        try {
            boolean isUpdated = stockBO.updateStock(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "stock updated!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String item_code = txtId.getText();

        try {
            Stock dto = stockBO.searchById(item_code);

            if (dto != null) {
                txtId.setText(dto.getItemCode());
                txtName.setText(dto.getName());
                txtUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
                txtQty.setText(String.valueOf(dto.getQty()));

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}

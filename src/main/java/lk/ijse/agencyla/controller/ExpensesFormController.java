package lk.ijse.agencyla.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.agencyla.bo.BOFactory;
import lk.ijse.agencyla.bo.custom.CustomerBO;
import lk.ijse.agencyla.bo.custom.ExpensesBO;
import lk.ijse.agencyla.dto.CustomerDTO;
import lk.ijse.agencyla.dto.ExpensesDTO;
import lk.ijse.agencyla.dto.StockDTO;
import lk.ijse.agencyla.dto.VanDTO;
import lk.ijse.agencyla.entity.Expenses;
import lk.ijse.agencyla.entity.Stock;
import lk.ijse.agencyla.view.tdm.CustomerTM;
import lk.ijse.agencyla.view.tdm.ExpensesTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExpensesFormController {

    @FXML
    private ComboBox<String > cmbVanId;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colVanId;

    @FXML
    private TableView<ExpensesTM> tblExpences;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtDescription;

    ExpensesBO expensesBO  = (ExpensesBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EXPENSES);

    public void initialize(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colVanId.setCellValueFactory(new PropertyValueFactory<>("vanId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        loadAllExpenses();
        loadAllVanId();
    }

    private void loadAllVanId() {
        try {
            ArrayList<VanDTO> allVans = expensesBO.getAllVanID();
            for (VanDTO v : allVans) {
                cmbVanId.getItems().add(v.getVanId());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load van ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllExpenses() {
        tblExpences.getItems().clear();
        try {
            //*Get all customers*//*
            ArrayList<ExpensesDTO> allExpenses = expensesBO.getAllExpenses();

            for (ExpensesDTO ex : allExpenses) {
                tblExpences.getItems().add(new ExpensesTM(ex.getCode(), ex.getVanId(), ex.getAmount(), ex.getDescription(), ex.getDate()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtCode.setText("");
        cmbVanId.setValue("");
        txtAmount.setText("");
        txtDescription.setText("");
        txtDate.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String code = txtCode.getText();

        boolean isDeleted = expensesBO.deleteExpenses(code);
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "expenses deleted!").show();
            initialize();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String code = txtCode.getText();
        String vanId = cmbVanId.getValue();
        double amount = Double.parseDouble(txtAmount.getText());
        String description = txtDescription.getText();
        String date = txtDate.getText();

        ExpensesDTO dto = new ExpensesDTO(code, vanId, amount, description, date);

        try {
            boolean isSaved = expensesBO.saveExpenses(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "expenses saved!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String code = txtCode.getText();
        String vanId = cmbVanId.getValue();
        double amount = Double.parseDouble(txtAmount.getText());
        String description = txtDescription.getText();
        String date = txtDate.getText();

        ExpensesDTO dto = new ExpensesDTO(code, vanId, amount, description,date);

        try {
            boolean isUpdated = expensesBO.updateExpenses(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "expenses updated!").show();
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
        String code = txtCode.getText();

        try {
            Expenses dto = expensesBO.searchById(code);

            if (dto != null) {
                txtCode.setText(dto.getCode());
                cmbVanId.setValue(dto.getVanId());
                txtAmount.setText(String.valueOf(dto.getAmount()));
                txtDescription.setText(dto.getDescription());
                txtDate.setText(dto.getDate());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

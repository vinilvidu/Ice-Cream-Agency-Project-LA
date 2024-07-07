package lk.ijse.agencyla.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.agencyla.bo.BOFactory;
import lk.ijse.agencyla.bo.custom.DailyTransactionBO;
import lk.ijse.agencyla.bo.custom.ExpensesBO;
import lk.ijse.agencyla.controller.util.Validate;
import lk.ijse.agencyla.dto.DailyTransactionDTO;
import lk.ijse.agencyla.dto.ExpensesDTO;
import lk.ijse.agencyla.dto.StockDTO;
import lk.ijse.agencyla.dto.VanDTO;
import lk.ijse.agencyla.entity.DailyTransaction;
import lk.ijse.agencyla.entity.Stock;
import lk.ijse.agencyla.view.tdm.DailyTransactionTM;
import lk.ijse.agencyla.view.tdm.ExpensesTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class DailyTransactionFormController {

    @FXML
    private ComboBox<String > cmbVanId;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colVanId;

    @FXML
    private TableView<DailyTransactionTM> tblDailyTransaction;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtId;

    DailyTransactionBO dailyTransactionBO  = (DailyTransactionBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DAILY_TRANSACTION);

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("billId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colVanId.setCellValueFactory(new PropertyValueFactory<>("vanId"));

        loadAllTransactions();
        loadAllVanId();
    }

    private void loadAllVanId() {
        try {
            ArrayList<VanDTO> allVans = dailyTransactionBO.getAllVanID();
            for (VanDTO v : allVans) {
                cmbVanId.getItems().add(v.getVanId());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load van ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllTransactions() {
        try {
            //*Get all customers*//*
            ArrayList<DailyTransactionDTO> allTransactions = dailyTransactionBO.getAllTransactions();

            for (DailyTransactionDTO dt : allTransactions) {
                tblDailyTransaction.getItems().add(new DailyTransactionTM(dt.getBillId(), dt.getAmount(), dt.getDate(),dt.getVanId()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtId.setText("");
        txtAmount.setText("");
        txtDate.setText("");
        cmbVanId.setValue("");

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String billId = txtId.getText();

        boolean isDeleted = dailyTransactionBO.deleteDailyTransaction(billId);
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "transaction deleted!").show();
            //initialize();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isValidate = validateTransaction();

        if (isValidate) {
            String billId = txtId.getText();
            double amount = Double.parseDouble(txtAmount.getText());
            String date = txtDate.getText();
            String vanId = cmbVanId.getValue();


            DailyTransactionDTO dto = new DailyTransactionDTO(billId, amount, date, vanId);

            try {
                boolean isSaved = dailyTransactionBO.saveDailyTransaction(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "transaction saved!").show();
                    //initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean validateTransaction() {
        int num=0;
        String id = txtId.getText();
        boolean isIdValidate= Pattern.matches("(B0)[0-9]{3,7}",id);
        if (!isIdValidate){
            num=1;
            Validate.vibrateTextField(txtId);
        }

        String amount=txtAmount.getText();
        boolean isAmountValidate= Pattern.matches("[0-9 .]{3,}",amount);
        if (!isAmountValidate){
            num=1;
            Validate.vibrateTextField(txtAmount);
        }

        String date=txtDate.getText();
        boolean isDateValidate= Pattern.matches("[0-9 -]{10,12}",date);
        if (!isDateValidate){
            num=1;
            Validate.vibrateTextField(txtDate);
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
        String billId = txtId.getText();
        double amount = Double.parseDouble(txtAmount.getText());
        String date = txtDate.getText();
        String vanId = cmbVanId.getValue();


        DailyTransactionDTO dto = new DailyTransactionDTO(billId, amount, date, vanId);

        try {
            boolean isUpdated = dailyTransactionBO.updateDailyTransaction(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "daily transaction updated!").show();
                //initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String billId = txtId.getText();

        try {
            DailyTransaction dto = dailyTransactionBO.searchById(billId);

            if (dto != null) {
                txtId.setText(dto.getBillId());
                txtAmount.setText(String.valueOf(dto.getAmount()));
                txtDate.setText(dto.getDate());
                cmbVanId.setValue(dto.getVanId());

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

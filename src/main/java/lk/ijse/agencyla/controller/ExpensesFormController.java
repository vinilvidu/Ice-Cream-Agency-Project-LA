package lk.ijse.agencyla.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.agencyla.bo.BOFactory;
import lk.ijse.agencyla.bo.custom.CustomerBO;
import lk.ijse.agencyla.bo.custom.ExpensesBO;
import lk.ijse.agencyla.controller.util.Validate;
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
import java.util.regex.Pattern;

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
        boolean isValidate = validateExpenses();

        if (isValidate) {
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
    }

    private boolean validateExpenses() {
        int num=0;
        String code = txtCode.getText();
        boolean isCodeValidate= Pattern.matches("(EX)[0-9]{3,7}",code);
        if (!isCodeValidate){
            num=1;
            Validate.vibrateTextField(txtCode);
        }

        String amount=txtAmount.getText();
        boolean isAmountValidate= Pattern.matches("[0-9 .]{3,}",amount);
        if (!isAmountValidate){
            num=1;
            Validate.vibrateTextField(txtAmount);
        }

        String description=txtDescription.getText();
        boolean isDescriptonValidate= Pattern.matches("[A-z]{3,}",description);
        if (!isDescriptonValidate){
            num=1;
            Validate.vibrateTextField(txtDescription);
        }

        String date=txtDate.getText();
        boolean isDateValidate= Pattern.matches("[0-9 -]{10}",date);
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

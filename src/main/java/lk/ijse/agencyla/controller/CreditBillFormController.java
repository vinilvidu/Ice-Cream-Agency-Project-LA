package lk.ijse.agencyla.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.agencyla.bo.BOFactory;
import lk.ijse.agencyla.bo.custom.CreditBillBO;
import lk.ijse.agencyla.bo.custom.ExpensesBO;
import lk.ijse.agencyla.controller.util.Validate;
import lk.ijse.agencyla.dto.CreditBillDTO;
import lk.ijse.agencyla.dto.CustomerDTO;
import lk.ijse.agencyla.dto.ExpensesDTO;
import lk.ijse.agencyla.dto.RouteDTO;
import lk.ijse.agencyla.entity.CreditBill;
import lk.ijse.agencyla.entity.Customer;
import lk.ijse.agencyla.entity.Expenses;
import lk.ijse.agencyla.view.tdm.CreditBillTM;
import lk.ijse.agencyla.view.tdm.ExpensesTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class CreditBillFormController {

    @FXML
    private ComboBox<String > cmbCusId;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colCusId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colRouteId;

    @FXML
    private TableView<CreditBillTM> tblCreditBill;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtId1;

    @FXML
    private TextField txtId11;

    @FXML
    private TextField txtId21;


    CreditBillBO creditBillBO  = (CreditBillBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CREDIT_BILL);

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("billId"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colRouteId.setCellValueFactory(new PropertyValueFactory<>("routeId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        loadAllCreditBills();
        loadAllCustomerId();
    }

    private void loadAllCustomerId() {
        try {
            ArrayList<CustomerDTO> allCustomerIds = creditBillBO.getAllCustomerId();
            for (CustomerDTO c : allCustomerIds) {
                cmbCusId.getItems().add(c.getId());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load customer ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllCreditBills() {
        tblCreditBill.getItems().clear();
        try {
            //*Get all customers*//*
            ArrayList<CreditBillDTO> allCreditBills = creditBillBO.getAllCreditBills();

            for (CreditBillDTO cb : allCreditBills) {
                tblCreditBill.getItems().add(new CreditBillTM(cb.getBillId(), cb.getCusId(), cb.getRouteId(), cb.getAmount(), cb.getDate()));
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
        cmbCusId.setValue("");
        txtId11.setText("");
        txtId1.setText("");
        txtId21.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String billId = txtId.getText();

        boolean isDeleted = creditBillBO.deleteCreditBill(billId);
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "credit bill deleted!").show();
            initialize();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isValidate = validateCreditBill();

        if (isValidate) {
            String billId = txtId.getText();
            String cusId = cmbCusId.getValue();
            String routeId = txtId11.getText();
            double amount = Double.valueOf(txtId1.getText());
            String date = txtId21.getText();

            CreditBillDTO dto = new CreditBillDTO(billId, cusId, routeId, amount, date);

            try {
                boolean isSaved = creditBillBO.saveCreditBill(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "credit bill saved!").show();
                    initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private boolean validateCreditBill() {
        int num=0;
        String id = txtId.getText();
        boolean isIdValidate= Pattern.matches("(CB)[0-9]{3,7}",id);
        if (!isIdValidate){
            num=1;
            Validate.vibrateTextField(txtId);
        }


        String amount=txtId1.getText();
        boolean isAmountValidate= Pattern.matches("[0-9 .]{3,}",amount);
        if (!isAmountValidate){
            num=1;
            Validate.vibrateTextField(txtId1);
        }

        String date=txtId21.getText();
        boolean isDateValidate= Pattern.matches("[0-9 -]{10}",date);
        if (!isDateValidate){
            num=1;
            Validate.vibrateTextField(txtId21);
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
        String cusId = cmbCusId.getValue();
        String routeId = txtId11.getText();
        double amount = Double.valueOf(txtId1.getText());
        String date = txtId21.getText();

        CreditBillDTO dto = new CreditBillDTO(billId, cusId, routeId, amount,date);

        try {
            boolean isUpdated = creditBillBO.updateCreditBill(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "credit bill updated!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbCusIdOnAction(ActionEvent event) {
        String cusId = cmbCusId.getValue();
        try {
            Customer dto = creditBillBO.searchByCusId(cusId);
            if (dto != null) {
                txtId11.setText(dto.getRouteId());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String billId = txtId.getText();

        try {
            CreditBill dto = creditBillBO.searchById(billId);

            if (dto != null) {
                txtId.setText(dto.getBillId());
                cmbCusId.setValue(dto.getCusId());
                txtId11.setText(dto.getRouteId());
                txtId1.setText(String.valueOf(dto.getAmount()));
                txtId21.setText(dto.getDate());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

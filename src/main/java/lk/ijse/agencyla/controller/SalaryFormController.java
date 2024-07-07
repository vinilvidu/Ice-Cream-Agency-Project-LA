package lk.ijse.agencyla.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.agencyla.bo.BOFactory;
import lk.ijse.agencyla.bo.custom.CustomerBO;
import lk.ijse.agencyla.bo.custom.EmployeeBO;
import lk.ijse.agencyla.bo.custom.SalaryBO;
import lk.ijse.agencyla.controller.util.Validate;
import lk.ijse.agencyla.dto.CustomerDTO;
import lk.ijse.agencyla.dto.EmployeeDTO;
import lk.ijse.agencyla.dto.RouteDTO;
import lk.ijse.agencyla.dto.SalaryDTO;
import lk.ijse.agencyla.entity.Customer;
import lk.ijse.agencyla.entity.Employee;
import lk.ijse.agencyla.entity.Salary;
import lk.ijse.agencyla.view.tdm.CustomerTM;
import lk.ijse.agencyla.view.tdm.SalaryTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class SalaryFormController {

    @FXML
    private ComboBox<String> cmbEmpId;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colMonth;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<SalaryTM> tblSalary;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtMonth;

    @FXML
    private TextField txtName;

    SalaryBO salaryBO  = (SalaryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SALARY);

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        loadAllSalary();
        loadAllEmpId();
    }

    private void loadAllEmpId() {
        try {
            ArrayList<EmployeeDTO> allEmployees = salaryBO.getAllEmpID();
            for (EmployeeDTO e : allEmployees) {
                cmbEmpId.getItems().add(e.getId()
                );
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load salary ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllSalary() {
        tblSalary.getItems().clear();
        try {
            //*Get all customers*//*
            ArrayList<SalaryDTO> allSalary = salaryBO.getAllSalary();

            for (SalaryDTO  s : allSalary) {
                tblSalary.getItems().add(new SalaryTM(s.getSalaryId(), s.getEmpId(), s.getName(), s.getMonth(), s.getAmount(), s.getDate()));
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
        cmbEmpId.setValue("");
        txtName.setText("");
        txtMonth.setText("");
        txtAmount.setText("");
        txtDate.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String salaryId = txtId.getText();

        boolean isDeleted = salaryBO.deleteSalary(salaryId);
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "salary deleted!").show();
            initialize();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isValidate = validateSalary();

        if (isValidate) {
            String salary_id = txtId.getText();
            String employee_id = cmbEmpId.getValue();
            String name = txtName.getText();
            String month = txtMonth.getText();
            double amount = Double.parseDouble(txtAmount.getText());
            String date = txtDate.getText();


            SalaryDTO dto = new SalaryDTO(salary_id, employee_id, name, month, amount, date);

            try {
                boolean isSaved = salaryBO.saveSalary(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "salary saved!").show();
                    initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean validateSalary() {
        int num=0;
        String id = txtId.getText();
        boolean isIDValidate= Pattern.matches("(S0)[0-9]{3,7}",id);
        if (!isIDValidate){
            num=1;
            Validate.vibrateTextField(txtId);
        }

        String name=txtName.getText();
        boolean isNameValidate= Pattern.matches("[A-z]{3,}",name);
        if (!isNameValidate){
            num=1;
            Validate.vibrateTextField(txtName);
        }

        String month=txtMonth.getText();
        boolean isMonthValidate= Pattern.matches("[A-z]{3,}",month);
        if (!isMonthValidate){
            num=1;
            Validate.vibrateTextField(txtMonth);
        }

        String amount=txtAmount.getText();
        boolean isAmountValidate= Pattern.matches("[0-9 .]{3,}",amount);
        if (!isAmountValidate){
            num=1;
            Validate.vibrateTextField(txtAmount);
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
        String salary_id = txtId.getText();
        String employee_id = cmbEmpId.getValue();
        String name = txtName.getText();
        String month = txtMonth.getText();
        double amount = Double.parseDouble(txtAmount.getText());
        String date = txtDate.getText();

        SalaryDTO dto = new SalaryDTO(salary_id, employee_id, name, month,amount,date);

        try {
            boolean isUpdated = salaryBO.updateSalary(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "salary updated!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbEmpIdOnAction(ActionEvent event) {
        String salaryId = cmbEmpId.getValue();
        try {
            Employee dto = salaryBO.searchByEmpId(salaryId);
            if (dto != null) {
                txtName.setText(dto.getName());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String code = txtId.getText();

        try {
            Salary dto = salaryBO.searchById(code);

            if (dto != null) {
                txtId.setText(dto.getSalaryId());
                cmbEmpId.setValue(dto.getEmpId());
                txtName.setText(dto.getName());
                txtMonth.setText(dto.getMonth());
                txtAmount.setText(String.valueOf(dto.getAmount()));
                txtDate.setText(dto.getDate());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

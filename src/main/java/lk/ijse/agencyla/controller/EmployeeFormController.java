package lk.ijse.agencyla.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.agencyla.bo.BOFactory;
import lk.ijse.agencyla.bo.custom.EmployeeBO;
import lk.ijse.agencyla.bo.custom.StockBO;
import lk.ijse.agencyla.dto.*;
import lk.ijse.agencyla.entity.Employee;
import lk.ijse.agencyla.entity.Expenses;
import lk.ijse.agencyla.view.tdm.EmployeeTM;
import lk.ijse.agencyla.view.tdm.StockTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeFormController {

    @FXML
    private ComboBox<String> cmbVanId;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colVanId;

    @FXML
    private TableView<EmployeeTM> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtName;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    public void initialize(){
        tblEmployee.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblEmployee.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblEmployee.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblEmployee.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblEmployee.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblEmployee.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("vanId"));

        loadAllEmployee();
        loadAllVanId();
    }

    private void loadAllVanId() {
        try {
            ArrayList<VanDTO> allVans = employeeBO.getAllVanID();
            for (VanDTO v : allVans) {
                cmbVanId.getItems().add(v.getVanId());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load van ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllEmployee() {
        tblEmployee.getItems().clear();
        try {
            //*Get all items*//
            ArrayList<EmployeeDTO> allEmployees = employeeBO.getAllEmployee();
            for (EmployeeDTO e : allEmployees) {
                tblEmployee.getItems().add(new EmployeeTM(e.getId(), e.getName(), e.getNic(), e.getAddress(), e.getContact(), e.getVanId()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtId.setText("");
        txtName.setText("");
        txtNIC.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        cmbVanId.setValue("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();

        boolean isDeleted = employeeBO.deleteEmployee(id);
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "employee deleted!").show();
            initialize();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String nic = txtNIC.getText();
        String address = txtAddress.getText();
        int contact = Integer.parseInt(txtContact.getText());
        String vanId = cmbVanId.getValue();

        EmployeeDTO dto = new EmployeeDTO(id, name, nic, address, contact, vanId);

        try {
            boolean isSaved = employeeBO.saveEmployee(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee saved!").show();
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
        String id = txtId.getText();
        String name = txtName.getText();
        String nic = txtNIC.getText();
        String address = txtAddress.getText();
        int contact = Integer.parseInt(txtContact.getText());
        String vanId = cmbVanId.getValue();

        EmployeeDTO dto = new EmployeeDTO(id, name, nic, address, contact, vanId);

        try {
            boolean isUpdated = employeeBO.updateEmployee(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee updated!").show();
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
        String id = txtId.getText();

        try {
            Employee dto = employeeBO.searchById(id);

            if (dto != null) {
                txtId.setText(dto.getId());
                txtName.setText(dto.getName());
                txtNIC.setText(dto.getNic());
                txtAddress.setText(dto.getAddress());
                txtContact.setText(String.valueOf(dto.getContact()));
                cmbVanId.setValue(dto.getVanId());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

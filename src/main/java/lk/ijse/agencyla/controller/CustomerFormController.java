package lk.ijse.agencyla.controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.agencyla.bo.BOFactory;
import lk.ijse.agencyla.bo.custom.CustomerBO;
import lk.ijse.agencyla.bo.custom.RouteBO;
import lk.ijse.agencyla.controller.util.Validate;
import lk.ijse.agencyla.dao.DAOFactory;
import lk.ijse.agencyla.dao.custom.RouteDAO;
import lk.ijse.agencyla.dto.CustomerDTO;
import lk.ijse.agencyla.dto.RouteDTO;
import lk.ijse.agencyla.dto.StockDTO;
import lk.ijse.agencyla.entity.Customer;
import lk.ijse.agencyla.view.tdm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerFormController {

    @FXML
    private ComboBox<String> cmbRouteId;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colRouteID;

    @FXML
    private TableColumn<?, ?> colShopName;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtShopName;

    CustomerBO customerBO  = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);


    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colShopName.setCellValueFactory(new PropertyValueFactory<>("shopName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colRouteID.setCellValueFactory(new PropertyValueFactory<>("routeId"));

        loadAllCustomers();
        loadAllRouteID();



    }

    private void loadAllRouteID() {
        try {
            ArrayList<RouteDTO> allRoutes = customerBO.getAllRouteID();
            for (RouteDTO c : allRoutes) {
                cmbRouteId.getItems().add(c.getRouteId());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load route ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void loadAllCustomers() {
        tblCustomer.getItems().clear();
        try {

            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();

            for (CustomerDTO c : allCustomers) {
                tblCustomer.getItems().add(new CustomerTM(c.getId(), c.getName(), c.getShopName(), c.getContact(), c.getAddress(), c.getRouteId()));
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
        txtName.setText("");
        txtShopName.setText("");
        txtContact.setText("");
        txtAddress.setText("");
        cmbRouteId.setValue("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();

        boolean isDeleted = customerBO.deleteCustomer(id);
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
            initialize();
        }
    }

    @FXML
    void btnSaveOnAction() throws SQLException, ClassNotFoundException {
        boolean isValidate = validateCustomer();

        if (isValidate) {
            String id = txtId.getText();
            String name = txtName.getText();
            String shopName = txtShopName.getText();
            String contact = txtContact.getText();
            String address = txtAddress.getText();
            String routeId = cmbRouteId.getValue();

            CustomerDTO dto = new CustomerDTO(id, name, shopName, contact, address, routeId);

            try {
                boolean isSaved = customerBO.saveCustomer(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                    initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean validateCustomer() {
        int num=0;
        String id = txtId.getText();
        boolean isIDValidate= Pattern.matches("(C)[0-9]{3,7}",id);
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

        String shopName=txtShopName.getText();
        boolean isShopNameValidate= Pattern.matches("[A-z ]{3,}",shopName);
        if (!isShopNameValidate){
            num=1;
            Validate.vibrateTextField(txtShopName);
        }

        String contact=txtContact.getText();
        boolean isContactValidate= Pattern.matches("[0-9]{10}",contact);
        if (!isContactValidate){
            num=1;
            Validate.vibrateTextField(txtContact);
        }

        String address=txtAddress.getText();
        boolean isAddressValidate= Pattern.matches("[A-z]{3,}",address);
        if (!isAddressValidate){
            num=1;
            Validate.vibrateTextField(txtAddress);
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
        String id = txtId.getText();
        String name = txtName.getText();
        String shopName = txtAddress.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String routeId = cmbRouteId.getValue();

        CustomerDTO dto = new CustomerDTO(id, name, shopName, contact,address,routeId);

        try {
            boolean isUpdated = customerBO.updateCustomer(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
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
            Customer dto = customerBO.searchById(id);

            if (dto != null) {
                txtId.setText(dto.getId());
                txtName.setText(dto.getName());
                txtShopName.setText(dto.getShopName());
                txtContact.setText(dto.getContact());
                txtAddress.setText(dto.getAddress());
                cmbRouteId.setValue(dto.getRouteId());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}

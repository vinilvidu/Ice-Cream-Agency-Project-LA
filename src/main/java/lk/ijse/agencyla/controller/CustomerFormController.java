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


        /*initUI();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                txtId.setText(String.valueOf(newValue.getClass()));
                txtName.setText(String.valueOf(newValue.getClass()));
                txtShopName.setText(String.valueOf(newValue.getClass()));
                txtContact.setText(String.valueOf(newValue.getClass()));
                txtAddress.setText(String.valueOf(newValue.getClass()));
                cmbRouteId.setValue(newValue.getClass());

                txtCustomerId.setDisable(false);
                txtCustomerName.setDisable(false);
                txtCustomerAddress.setDisable(false);
            }
        });

        txtCustomerAddress.setOnAction(event -> btnSave.fire());*/
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
            //*Get all customers*//*
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

    /*private void initUI() {
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerId.setDisable(true);
        txtCustomerName.setDisable(true);
        txtCustomerAddress.setDisable(true);
        txtCustomerId.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }*/
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
        String id = txtId.getText();
        String name = txtName.getText();
        String shopName = txtShopName.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String routeId = cmbRouteId.getValue();

        CustomerDTO dto = new CustomerDTO(id, name, shopName, contact,address,routeId);

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

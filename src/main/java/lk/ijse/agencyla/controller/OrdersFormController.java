package lk.ijse.agencyla.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.agencyla.bo.BOFactory;
import lk.ijse.agencyla.bo.custom.PurchaseOrderBO;
import lk.ijse.agencyla.dto.*;
import lk.ijse.agencyla.entity.Employee;
import lk.ijse.agencyla.entity.Order;
import lk.ijse.agencyla.entity.OrderDetail;
import lk.ijse.agencyla.entity.Stock;
import lk.ijse.agencyla.view.tdm.OrderTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersFormController {

    @FXML
    private ComboBox<String > cmbItemCode;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private Label lblName;

    @FXML
    private TableView<OrderTM> tblOrders;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtQty;

    private ObservableList<OrderTM> cartList = FXCollections.observableArrayList();

    PurchaseOrderBO purchaseOrderBO  = (PurchaseOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PO);

    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        loadAllItemCode();
    }

    private void loadAllItemCode() {
        try {
            ArrayList<StockDTO> allStocks = purchaseOrderBO.getAllItemCode();
            for (StockDTO st : allStocks) {
                cmbItemCode.getItems().add(st.getItemCode());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load item code").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAddItemOnAction(ActionEvent event) {
        String orderId = txtId.getId();
        String itemCode = cmbItemCode.getValue();
        String name = lblName.getText();
        int qty = Integer.parseInt(txtQty.getText());
        String date = txtDate.getText();

        for (int i = 0; i < tblOrders.getItems().size(); i++) {
            if (itemCode.equals(colItemCode.getCellData(i))) {
                qty += cartList.get(i).getQty();

                cartList.get(i).setQty(qty);

                tblOrders.refresh();

                return;
            }
        }

        OrderTM orderTM = new OrderTM(orderId,itemCode, name, qty,  date);

        cartList.add(orderTM);

        tblOrders.setItems(cartList);
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String orderId = txtId.getText();
        String itemCode = cmbItemCode.getValue();
        String itemName = lblName.getText();
        int qty = Integer.parseInt(txtQty.getText());
        String date = txtDate.getText();

        var order = new Order(orderId, itemCode, itemName,qty,date);

        List<OrderDetailDTO> odList = new ArrayList<>();
        List<OrderDetail> stlist = new ArrayList<>();
        for (int i = 0; i < tblOrders.getItems().size(); i++) {
            OrderTM tm = (OrderTM) cartList.get(i);

            OrderDetailDTO od = new OrderDetailDTO(
                    orderId,
                    //tm.getOrderId(),
                    tm.getItemCode(),
                    tm.getQty()
            );
            odList.add(od);
        }

        PlaceOrderDTO po = new PlaceOrderDTO(order, odList,stlist);
        System.out.println(order.toString());
        try {
            boolean isPlaced = purchaseOrderBO.placeOrder(po);
            System.out.println(po.toString());
            if(isPlaced) {
                new Alert(Alert.AlertType.CONFIRMATION, "order placed!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "order not placed!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbItemCodeOnAction(ActionEvent event) {
        String itemCode = cmbItemCode.getValue();
        try {
            Stock stock = purchaseOrderBO.searchByItemCode(itemCode);
            if (stock != null) {
                lblName.setText(stock.getName());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        txtQty.requestFocus();
    }

    @FXML
    void txtAddDateOnAction(ActionEvent event) {

    }

    @FXML
    void txtAddOrderIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }

}

package lk.ijse.agencyla.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.agencyla.bo.BOFactory;
import lk.ijse.agencyla.bo.custom.CustomerBO;
import lk.ijse.agencyla.bo.custom.PlaseDailyLoadingBO;
import lk.ijse.agencyla.controller.util.Validate;
import lk.ijse.agencyla.dto.*;
import lk.ijse.agencyla.entity.DailyLoading;
import lk.ijse.agencyla.entity.DailyLoadingDetail;
import lk.ijse.agencyla.entity.Stock;
import lk.ijse.agencyla.view.tdm.DailyLoadingTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DailyLoadingFormController {

    @FXML
    private ComboBox<String> cmbItemCode;

    @FXML
    private ComboBox<String> cmbVanId;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colVanId;

    @FXML
    private Label lblName;

    @FXML
    private TableView<DailyLoadingTM> tblLoadingReport;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtQty;

    private ObservableList<DailyLoadingTM> loadList = FXCollections.observableArrayList();

    PlaseDailyLoadingBO plaseDailyLoadingBO  = (PlaseDailyLoadingBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACE_DL);

    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colVanId.setCellValueFactory(new PropertyValueFactory<>("vanId"));

        loadAllVanId();
        loadAllItemCode();
    }

    private void loadAllItemCode() {
        try {
            ArrayList<StockDTO> allStocks = plaseDailyLoadingBO.getAllItemCode();
            for (StockDTO st : allStocks) {
                cmbItemCode.getItems().add(st.getItemCode());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load item code").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllVanId() {
        try {
            ArrayList<VanDTO> allVans = plaseDailyLoadingBO.getAllVanID();
            for (VanDTO v : allVans) {
                cmbVanId.getItems().add(v.getVanId());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load van ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAddItemOnAction(ActionEvent event) {
        boolean isValidate = validateLoading();

        if (isValidate) {
            String repoId = txtId.getId();
            String itemCode = cmbItemCode.getValue();
            String itemName = lblName.getText();
            int qty = Integer.parseInt(txtQty.getText());
            String vanId = cmbVanId.getValue();
            String date = txtDate.getId();

            for (int i = 0; i < tblLoadingReport.getItems().size(); i++) {
                if (itemCode.equals(colItemCode.getCellData(i))) {

                    qty = loadList.get(i).getQty();
                    loadList.get(i).setQty(qty);

                    tblLoadingReport.refresh();

                }
            }

            DailyLoadingTM dailyLoadingTM = new DailyLoadingTM(repoId, itemCode, itemName, qty, vanId, date);

            loadList.add(dailyLoadingTM);

            tblLoadingReport.setItems(loadList);
        }
    }

    private boolean validateLoading() {
        int num=0;
        String id = txtId.getText();
        boolean isIdValidate= Pattern.matches("(R0)[0-9]{3,7}",id);
        if (!isIdValidate){
            num=1;
            Validate.vibrateTextField(txtId);
        }

        String date=txtDate.getText();
        boolean isDateValidate= Pattern.matches("[0-9 -]{10}",date);
        if (!isDateValidate){
            num=1;
            Validate.vibrateTextField(txtDate);
        }

        String qty=txtQty.getText();
        boolean isQtyValidate= Pattern.matches("[0-9]{1,}",qty);
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
    void btnPlaceReportOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String repoId = txtId.getId();
        String itemCode = cmbItemCode.getValue();
        String itemName = lblName.getText();
        int qty = Integer.parseInt(txtQty.getText());
        String vanId = cmbVanId.getValue();
        String date = txtDate.getId();

        var dailyLoading = new DailyLoading(repoId,itemCode, itemName, qty, vanId,  date);
        System.out.println(dailyLoading);

        List<DailyLoadingDetailDTO> ldList = new ArrayList<>();

        for (int i = 0; i < tblLoadingReport.getItems().size(); i++) {
            DailyLoadingTM tm = (DailyLoadingTM) loadList.get(i);

            DailyLoadingDetailDTO ld = new DailyLoadingDetailDTO(
                    tm.getVanId(),
                    repoId,
                    tm.getItemCode(),
                    tm.getQty()
            );
            ldList.add(ld);

            PlaceLoadingDTO pl = new PlaceLoadingDTO(dailyLoading,ldList);
            System.out.println(pl.toString());
            boolean isPlaced = plaseDailyLoadingBO.placeLoading(pl);
            if (isPlaced) {
                new Alert(Alert.AlertType.CONFIRMATION, "report placed!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "report not placed!").show();
            }
        }
    }

    @FXML
    void cmbItemCodeOnAction(ActionEvent event) {
        String itemCode = cmbItemCode.getValue();
        try {
            Stock stock = plaseDailyLoadingBO.searchByItemCode(itemCode);
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
    void cmbVanIdOnAction(ActionEvent event) {

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

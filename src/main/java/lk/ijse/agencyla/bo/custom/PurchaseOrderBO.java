package lk.ijse.agencyla.bo.custom;

import lk.ijse.agencyla.bo.SuperBO;
import lk.ijse.agencyla.dto.PlaceOrderDTO;
import lk.ijse.agencyla.dto.StockDTO;
import lk.ijse.agencyla.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PurchaseOrderBO extends SuperBO {
    ArrayList<StockDTO> getAllItemCode()throws SQLException, ClassNotFoundException;

    Stock searchByItemCode(String itemCode)throws SQLException, ClassNotFoundException;

    boolean placeOrder(PlaceOrderDTO po)throws SQLException, ClassNotFoundException;
}

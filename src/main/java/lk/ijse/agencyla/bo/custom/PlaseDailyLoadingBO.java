package lk.ijse.agencyla.bo.custom;

import lk.ijse.agencyla.bo.SuperBO;
import lk.ijse.agencyla.dto.PlaceLoadingDTO;
import lk.ijse.agencyla.dto.StockDTO;
import lk.ijse.agencyla.dto.VanDTO;
import lk.ijse.agencyla.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaseDailyLoadingBO extends SuperBO {
    ArrayList<VanDTO> getAllVanID()throws SQLException, ClassNotFoundException;

    ArrayList<StockDTO> getAllItemCode()throws SQLException, ClassNotFoundException;

    Stock searchByItemCode(String itemCode)throws SQLException, ClassNotFoundException;

    boolean placeLoading(PlaceLoadingDTO pl)throws SQLException, ClassNotFoundException;
}

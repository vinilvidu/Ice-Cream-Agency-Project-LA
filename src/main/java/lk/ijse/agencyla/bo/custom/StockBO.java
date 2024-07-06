package lk.ijse.agencyla.bo.custom;

import lk.ijse.agencyla.bo.SuperBO;
import lk.ijse.agencyla.dto.StockDTO;
import lk.ijse.agencyla.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockBO extends SuperBO {
    public  boolean saveStock(StockDTO dto) throws SQLException, ClassNotFoundException;

    ArrayList<StockDTO> getAllStocks()throws SQLException, ClassNotFoundException;

    boolean updateStock(StockDTO dto)throws SQLException, ClassNotFoundException;

    boolean deleteStock(String itemCode)throws SQLException, ClassNotFoundException;

    Stock searchById(String itemCode)throws SQLException, ClassNotFoundException;
}

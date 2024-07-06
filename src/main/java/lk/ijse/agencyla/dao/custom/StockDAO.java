package lk.ijse.agencyla.dao.custom;

import lk.ijse.agencyla.dao.CrudDAO;
import lk.ijse.agencyla.dto.DailyLoadingDetailDTO;
import lk.ijse.agencyla.dto.OrderDetailDTO;
import lk.ijse.agencyla.entity.DailyLoadingDetail;
import lk.ijse.agencyla.entity.OrderDetail;
import lk.ijse.agencyla.entity.Stock;

import java.sql.SQLException;
import java.util.List;

public interface StockDAO extends CrudDAO<Stock> {
    boolean updateQty(List<OrderDetailDTO> odList) throws SQLException, ClassNotFoundException;

    boolean updateQty(OrderDetail entity) throws SQLException, ClassNotFoundException;

    boolean updateLoadingQty(List<DailyLoadingDetailDTO> ldList)throws SQLException, ClassNotFoundException;


    boolean updateLoadingQty(DailyLoadingDetail entity) throws SQLException, ClassNotFoundException;
}

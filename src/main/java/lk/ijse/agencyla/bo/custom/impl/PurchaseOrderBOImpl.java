package lk.ijse.agencyla.bo.custom.impl;

import lk.ijse.agencyla.bo.custom.PurchaseOrderBO;
import lk.ijse.agencyla.dao.DAOFactory;
import lk.ijse.agencyla.dao.custom.CustomerDAO;
import lk.ijse.agencyla.dao.custom.OrderDAO;
import lk.ijse.agencyla.dao.custom.OrderDetailDAO;
import lk.ijse.agencyla.dao.custom.StockDAO;
import lk.ijse.agencyla.db.DBConnection;
import lk.ijse.agencyla.dto.PlaceOrderDTO;
import lk.ijse.agencyla.dto.RouteDTO;
import lk.ijse.agencyla.dto.StockDTO;
import lk.ijse.agencyla.entity.OrderDetail;
import lk.ijse.agencyla.entity.Route;
import lk.ijse.agencyla.entity.Stock;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {
    StockDAO stockDAO = (StockDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCK);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);
    @Override
    public ArrayList<StockDTO> getAllItemCode() throws SQLException, ClassNotFoundException {
        ArrayList<Stock> stockEntityData = stockDAO.getAll();
        ArrayList<StockDTO> convertToDto= new ArrayList<>();
        for (Stock s : stockEntityData) {
            convertToDto.add(new StockDTO(s.getItemCode(),s.getName(),s.getUnitPrice(),s.getQty()));
        }
        return convertToDto;
    }

    @Override
    public Stock searchByItemCode(String itemCode) throws SQLException, ClassNotFoundException {
        return stockDAO.search(itemCode);
    }

    @Override
    public boolean placeOrder(PlaceOrderDTO po) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = orderDAO.save(po.getOrder());
            if (isOrderSaved) {
                System.out.println(isOrderSaved);
                System.out.println();
                boolean isOrderDetailSaved = orderDetailDAO.save((OrderDetail) po.getOdList());
                System.out.println("1"+isOrderDetailSaved);
                if (isOrderDetailSaved) {
                    boolean isItemQtyUpdate = stockDAO.updateQty(po.getOdList());
                    if (isItemQtyUpdate) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

}

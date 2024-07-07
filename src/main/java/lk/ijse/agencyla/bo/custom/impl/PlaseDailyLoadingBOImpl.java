package lk.ijse.agencyla.bo.custom.impl;

import lk.ijse.agencyla.bo.custom.PlaseDailyLoadingBO;
import lk.ijse.agencyla.dao.DAOFactory;
import lk.ijse.agencyla.dao.custom.*;
import lk.ijse.agencyla.db.DBConnection;
import lk.ijse.agencyla.dto.PlaceLoadingDTO;
import lk.ijse.agencyla.dto.StockDTO;
import lk.ijse.agencyla.dto.VanDTO;
import lk.ijse.agencyla.entity.DailyLoading;
import lk.ijse.agencyla.entity.DailyLoadingDetail;
import lk.ijse.agencyla.entity.Stock;
import lk.ijse.agencyla.entity.Van;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaseDailyLoadingBOImpl implements PlaseDailyLoadingBO {

    DailyLoadingDAO dailyLoadingDAO = (DailyLoadingDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DAILY_LOADING);
    DailyLoadingDetailDAO dailyLoadingDetailDAO = (DailyLoadingDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DAILY_LOADING_DETAIL);
    VanDAO vanDAO = (VanDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VAN);
    StockDAO stockDAO = (StockDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCK);
    @Override
    public ArrayList<VanDTO> getAllVanID() throws SQLException, ClassNotFoundException {
        ArrayList<Van> vanEntityData = vanDAO.getAll();
        ArrayList<VanDTO> convertToDto= new ArrayList<>();
        for (Van v : vanEntityData) {
            convertToDto.add(new VanDTO(v.getVanId(),v.getNumber()));
        }
        return convertToDto;
    }

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
    public boolean placeLoading(PlaceLoadingDTO pl) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isLoadSaved = dailyLoadingDAO.save(pl.getLoading());
            System.out.println(isLoadSaved);
            if (isLoadSaved) {
                System.out.println(isLoadSaved);
                System.out.println("methnt awa");
                boolean isLoadingDetailSaved = dailyLoadingDetailDAO.save((DailyLoadingDetail) pl.getLdList());
                System.out.println(isLoadingDetailSaved);
                if (isLoadingDetailSaved) {
                    boolean isStockQtyUpdate = stockDAO.updateLoadingQty(pl.getLdList());
                    System.out.println(isStockQtyUpdate);
                    if (isStockQtyUpdate) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}

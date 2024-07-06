package lk.ijse.agencyla.dao.custom.impl;

import lk.ijse.agencyla.dao.SQLUtil;
import lk.ijse.agencyla.dao.custom.DailyLoadingDAO;
import lk.ijse.agencyla.entity.DailyLoading;

import java.sql.SQLException;
import java.util.ArrayList;

public class DailyLoadingDAOImpl implements DailyLoadingDAO {
    @Override
    public ArrayList<DailyLoading> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(DailyLoading entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO daily_item_loading_report(report_id,item_code,qty,date,vanId,name) VALUES(?,?,?,?,?.?)",entity.getRepoId(),entity.getItemCode(),entity.getQty(),entity.getDate(),entity.getVanId(),entity.getItemName());
    }

    @Override
    public boolean update(DailyLoading entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public DailyLoading search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean getId(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}

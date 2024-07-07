package lk.ijse.agencyla.dao.custom.impl;

import lk.ijse.agencyla.dao.SQLUtil;
import lk.ijse.agencyla.dao.custom.DailyLoadingDAO;
import lk.ijse.agencyla.dao.custom.DailyLoadingDetailDAO;
import lk.ijse.agencyla.entity.DailyLoading;
import lk.ijse.agencyla.entity.DailyLoadingDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class DailyLoadingDetailDAOImpl implements DailyLoadingDetailDAO {

    @Override
    public ArrayList<DailyLoadingDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public boolean save(DailyLoadingDetail entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO van_item_loading(van_id,report_id,item_code,qty) VALUES(?,?,?,?)",entity.getVanId(),entity.getRepoId(),entity.getItemCode(),entity.getQty());
    }

    @Override
    public boolean update(DailyLoadingDetail entity) throws SQLException, ClassNotFoundException {
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
    public DailyLoadingDetail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean getId(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
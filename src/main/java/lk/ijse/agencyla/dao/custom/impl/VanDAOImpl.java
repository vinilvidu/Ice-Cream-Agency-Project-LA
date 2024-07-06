package lk.ijse.agencyla.dao.custom.impl;

import lk.ijse.agencyla.dao.SQLUtil;
import lk.ijse.agencyla.dao.custom.VanDAO;
import lk.ijse.agencyla.entity.Van;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VanDAOImpl implements VanDAO {
    @Override
    public ArrayList<Van> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Van> allVans = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM van");
        while (rst.next()) {
            Van van = new Van(rst.getString("van_id"), rst.getString("number"));
            allVans.add(van);
        }
        return allVans;
    }

    @Override
    public boolean save(Van entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Van entity) throws SQLException, ClassNotFoundException {
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
    public Van search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean getId(String van_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT van_id FROM van", van_id);
    }
}

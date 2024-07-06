package lk.ijse.agencyla.dao.custom.impl;

import lk.ijse.agencyla.dao.SQLUtil;
import lk.ijse.agencyla.dao.custom.RouteDAO;
import lk.ijse.agencyla.entity.Route;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RouteDAOImpl implements RouteDAO {
    public  ArrayList<Route> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Route> allRoutes = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM route");
        while (rst.next()) {
            Route route = new Route(rst.getString("route_id"), rst.getString("Name"), rst.getString("van_id"),rst.getString("day"));
            allRoutes.add(route);
        }
        return allRoutes;
    }

    @Override
    public boolean save(Route entity) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }

    @Override
    public boolean update(Route entity) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }

    @Override
    public Route search(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }

    @Override
    public boolean getId(String route_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT route_id FROM route", route_id);
    }


}

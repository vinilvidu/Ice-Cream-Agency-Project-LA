package lk.ijse.agencyla.bo.custom.impl;

import lk.ijse.agencyla.bo.custom.RouteBO;
import lk.ijse.agencyla.dao.DAOFactory;
import lk.ijse.agencyla.dao.custom.CustomerDAO;
import lk.ijse.agencyla.dao.custom.RouteDAO;
import lk.ijse.agencyla.dto.CustomerDTO;
import lk.ijse.agencyla.dto.RouteDTO;
import lk.ijse.agencyla.entity.Customer;
import lk.ijse.agencyla.entity.Route;

import java.sql.SQLException;
import java.util.ArrayList;

public class RouteBOImpl implements RouteBO {
    RouteDAO routeDAO = (RouteDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROUTE);
    public ArrayList<RouteDTO> getAllRoutes() throws SQLException, ClassNotFoundException {
        ArrayList<RouteDTO> allRoutes= new ArrayList<>();
        ArrayList<Route> all = routeDAO.getAll();
        for (Route r : all) {
            allRoutes.add(new RouteDTO(r.getRouteId(),r.getName(),r.getVanId(), r.getDay()));
        }
        return allRoutes;
    }

}

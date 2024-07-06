package lk.ijse.agencyla.bo.custom;

import lk.ijse.agencyla.bo.SuperBO;
import lk.ijse.agencyla.dto.RouteDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RouteBO extends SuperBO {


    ArrayList<RouteDTO> getAllRoutes()throws SQLException, ClassNotFoundException;
}

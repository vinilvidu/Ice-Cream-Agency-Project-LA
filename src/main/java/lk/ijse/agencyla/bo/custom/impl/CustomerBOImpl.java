package lk.ijse.agencyla.bo.custom.impl;

import lk.ijse.agencyla.bo.custom.CustomerBO;
import lk.ijse.agencyla.dao.DAOFactory;
import lk.ijse.agencyla.dao.custom.CustomerDAO;
import lk.ijse.agencyla.dao.custom.RouteDAO;
import lk.ijse.agencyla.dto.CustomerDTO;
import lk.ijse.agencyla.dto.RouteDTO;
import lk.ijse.agencyla.entity.Customer;
import lk.ijse.agencyla.entity.Route;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    RouteDAO routeDAO = (RouteDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROUTE);
    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers= new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer c : all) {
            allCustomers.add(new CustomerDTO(c.getId(),c.getName(),c.getShopName(), c.getContact(), c.getAddress(), c.getRouteId()));
        }
        return allCustomers;
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getId(), dto.getName(), dto.getShopName(), dto.getContact(), dto.getAddress(), dto.getRouteId()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getId(),dto.getName(), dto.getShopName(), dto.getContact(), dto.getAddress(), dto.getRouteId()));
    }


    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public RouteDTO searchRoute(String routeId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean existRoute(String routeId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<RouteDTO> getAllRouteID() throws SQLException, ClassNotFoundException {
        ArrayList<Route> routeEntityData = routeDAO.getAll();
        ArrayList<RouteDTO> convertToDto= new ArrayList<>();
        for (Route r : routeEntityData) {
            convertToDto.add(new RouteDTO(r.getRouteId(),r.getName(),r.getVanId(),r.getDay()));
        }
        return convertToDto;
    }

    @Override
    public Customer searchById(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }

}

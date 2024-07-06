package lk.ijse.agencyla.dao.custom.impl;

import lk.ijse.agencyla.dao.DAOFactory;
import lk.ijse.agencyla.dao.SQLUtil;
import lk.ijse.agencyla.dao.custom.CustomerDAO;
import lk.ijse.agencyla.dao.custom.StockDAO;
import lk.ijse.agencyla.entity.Customer;
import lk.ijse.agencyla.entity.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {


    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        while (rst.next()) {
            Customer customer = new Customer(rst.getString("cus_id"), rst.getString("name"), rst.getString("shop_name"),rst.getString("contact"),rst.getString("address"),rst.getString("route_id"));
            allCustomers.add(customer);
        }
        return allCustomers;
    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Customer (cus_id,name,shop_name,contact, address,route_id) VALUES (?,?,?,?,?,?)", entity.getId(), entity.getName(),entity.getShopName(),entity.getContact(), entity.getAddress(),entity.getRouteId());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET name=?,shop_name=?,contact=?, address=?,route_id=? WHERE cus_id=?", entity.getName(),entity.getShopName(),entity.getContact(), entity.getAddress(),entity.getRouteId(), entity.getId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String cus_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE cus_id=?",cus_id);
    }

    @Override
    public Customer search(String cus_id) throws SQLException, ClassNotFoundException {
        ResultSet rst  = SQLUtil.execute("SELECT * FROM customer WHERE cus_id=?",cus_id+"");
        rst.next();
        return new Customer(cus_id+"",rst.getString("name"),rst.getString("shop_name"),rst.getString("contact"),rst.getString("address"),rst.getString("route_id"));
    }

    @Override
    public boolean getId(String id) throws SQLException, ClassNotFoundException {
        return false;
    }



}

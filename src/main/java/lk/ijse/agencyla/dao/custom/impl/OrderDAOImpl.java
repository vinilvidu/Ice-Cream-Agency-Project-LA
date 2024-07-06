package lk.ijse.agencyla.dao.custom.impl;

import lk.ijse.agencyla.dao.SQLUtil;
import lk.ijse.agencyla.dao.custom.OrderDAO;
import lk.ijse.agencyla.dto.OrderDTO;
import lk.ijse.agencyla.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        System.out.println("methnt awd");
        return SQLUtil.execute("INSERT INTO orders(order_id,item_code,item_name,qty,date) VALUES(?,?,?,?,?)",entity.getOrderId(),entity.getItemCode(),entity.getItemName(),entity.getQty(),entity.getDate());
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
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
    public Order search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean getId(String id) throws SQLException, ClassNotFoundException {
        return false;
    }


}

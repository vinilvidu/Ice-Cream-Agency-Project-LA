package lk.ijse.agencyla.dao.custom;

import lk.ijse.agencyla.dao.CrudDAO;
import lk.ijse.agencyla.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer> {

    int count() throws SQLException, ClassNotFoundException;
}

package lk.ijse.agencyla.dao.custom;

import lk.ijse.agencyla.dao.CrudDAO;
import lk.ijse.agencyla.entity.Employee;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<Employee> {
    int count()throws SQLException, ClassNotFoundException;
}

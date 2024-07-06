package lk.ijse.agencyla.dao.custom.impl;

import lk.ijse.agencyla.dao.SQLUtil;
import lk.ijse.agencyla.dao.custom.EmployeeDAO;
import lk.ijse.agencyla.entity.Employee;
import lk.ijse.agencyla.entity.Expenses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployees = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");
        while (rst.next()) {
            Employee employee = new Employee(rst.getString("employee_id"), rst.getString("name"), rst.getString("NIC"),rst.getString("address"),rst.getInt("contact"),rst.getString("van_id"));
            allEmployees.add(employee);
        }
        return allEmployees;
    }

    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee(employee_id,name,NIC,address,contact,van_id) VALUES(?,?,?,?,?,?)",entity.getId(),entity.getName(),entity.getNic(),entity.getAddress(),entity.getContact(),entity.getVanId());
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee SET name=?,NIC=?,address=?,contact=?,van_id=? WHERE employee_id=?",entity.getName(),entity.getNic(),entity.getAddress(),entity.getContact(),entity.getVanId(),entity.getId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String employee_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM employee WHERE employee_id=?",employee_id);
    }

    @Override
    public Employee search(String employee_id) throws SQLException, ClassNotFoundException {
        ResultSet rst  = SQLUtil.execute("SELECT * FROM employee WHERE employee_id=?",employee_id+"");
        rst.next();
        return new Employee(employee_id+"",rst.getString("name"),rst.getString("NIC"),rst.getString("address"),rst.getInt("contact"),rst.getString("van_id"));
    }

    @Override
    public boolean getId(String employee_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT employee_id FROM employee", employee_id);
    }
}

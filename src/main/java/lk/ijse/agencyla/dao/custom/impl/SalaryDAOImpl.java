package lk.ijse.agencyla.dao.custom.impl;

import lk.ijse.agencyla.dao.SQLUtil;
import lk.ijse.agencyla.dao.custom.SalaryDAO;
import lk.ijse.agencyla.entity.Customer;
import lk.ijse.agencyla.entity.Salary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public ArrayList<Salary> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Salary> allSalary = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM salary");
        while (rst.next()) {
            Salary salary = new Salary(rst.getString("salary_id"), rst.getString("employee_id"), rst.getString("name"),rst.getString("month"),rst.getDouble("amount"),rst.getString("date"));
            allSalary.add(salary);
        }
        return allSalary;
    }

    @Override
    public boolean save(Salary entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO salary (salary_id,employee_id,name,month, amount,Date) VALUES (?,?,?,?,?,?)", entity.getSalaryId(), entity.getEmpId(),entity.getName(),entity.getMonth(), entity.getAmount(),entity.getDate());
    }

    @Override
    public boolean update(Salary entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE salary SET employee_id=?,name=?,month=?, amount=?,date=? WHERE salary_id=?", entity.getEmpId(),entity.getName(),entity.getMonth(), entity.getAmount(),entity.getDate(), entity.getSalaryId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String salary_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM salary WHERE salary_id=?",salary_id);
    }

    @Override
    public Salary search(String salary_id) throws SQLException, ClassNotFoundException {
        ResultSet rst  = SQLUtil.execute("SELECT * FROM salary WHERE salary_id=?",salary_id+"");
        rst.next();
        return new Salary(salary_id+"",rst.getString("employee_id"),rst.getString("name"),rst.getString("month"),rst.getDouble("amount"),rst.getString("date"));
    }

    @Override
    public boolean getId(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}

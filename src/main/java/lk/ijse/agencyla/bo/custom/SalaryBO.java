package lk.ijse.agencyla.bo.custom;

import lk.ijse.agencyla.bo.SuperBO;
import lk.ijse.agencyla.dto.EmployeeDTO;
import lk.ijse.agencyla.dto.SalaryDTO;
import lk.ijse.agencyla.entity.Employee;
import lk.ijse.agencyla.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryBO extends SuperBO {
    ArrayList<SalaryDTO> getAllSalary()throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDTO> getAllEmpID()throws SQLException, ClassNotFoundException;

    Employee searchByEmpId(String salaryId)throws SQLException, ClassNotFoundException;

    boolean saveSalary(SalaryDTO dto)throws SQLException, ClassNotFoundException;

    Salary searchById(String code)throws SQLException, ClassNotFoundException;

    boolean updateSalary(SalaryDTO dto)throws SQLException, ClassNotFoundException;

    boolean deleteSalary(String salaryId)throws SQLException, ClassNotFoundException;
}

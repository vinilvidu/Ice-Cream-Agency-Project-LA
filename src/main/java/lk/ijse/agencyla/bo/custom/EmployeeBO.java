package lk.ijse.agencyla.bo.custom;

import lk.ijse.agencyla.bo.SuperBO;
import lk.ijse.agencyla.dto.EmployeeDTO;
import lk.ijse.agencyla.dto.VanDTO;
import lk.ijse.agencyla.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    static EmployeeDTO searchByEmpId(String id) {
        return null;
    }

    ArrayList<EmployeeDTO> getAllEmployee()throws SQLException, ClassNotFoundException;

    ArrayList<VanDTO> getAllVanID()throws SQLException, ClassNotFoundException;

    boolean saveEmployee(EmployeeDTO dto)throws SQLException, ClassNotFoundException;

    boolean updateEmployee(EmployeeDTO dto)throws SQLException, ClassNotFoundException;

    Employee searchById(String id)throws SQLException, ClassNotFoundException;

    boolean deleteEmployee(String id)throws SQLException, ClassNotFoundException;

    int getEmployeeCount()throws SQLException, ClassNotFoundException;
}

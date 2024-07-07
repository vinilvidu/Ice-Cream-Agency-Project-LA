package lk.ijse.agencyla.bo.custom.impl;

import lk.ijse.agencyla.bo.custom.EmployeeBO;
import lk.ijse.agencyla.dao.DAOFactory;
import lk.ijse.agencyla.dao.custom.EmployeeDAO;
import lk.ijse.agencyla.dao.custom.StockDAO;
import lk.ijse.agencyla.dao.custom.VanDAO;
import lk.ijse.agencyla.dto.EmployeeDTO;
import lk.ijse.agencyla.dto.RouteDTO;
import lk.ijse.agencyla.dto.StockDTO;
import lk.ijse.agencyla.dto.VanDTO;
import lk.ijse.agencyla.entity.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    VanDAO vanDAO = (VanDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VAN);
    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> allEmployees= new ArrayList<>();
        ArrayList<Employee> all = employeeDAO.getAll();
        for (Employee e : all) {
            allEmployees.add(new EmployeeDTO(e.getId(),e.getName(),e.getNic(),e.getAddress(),e.getContact(),e.getVanId()));
        }
        return allEmployees;
    }

    @Override
    public ArrayList<VanDTO> getAllVanID() throws SQLException, ClassNotFoundException {
        ArrayList<Van> vanEntityData = vanDAO.getAll();
        ArrayList<VanDTO> convertToDto= new ArrayList<>();
        for (Van v : vanEntityData) {
            convertToDto.add(new VanDTO(v.getVanId(),v.getNumber()));
        }
        return convertToDto;
    }

    @Override
    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(dto.getId(),dto.getName(),dto.getNic(),dto.getAddress(),dto.getContact(),dto.getVanId()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getId(),dto.getName(),dto.getNic(),dto.getAddress(),dto.getContact(),dto.getVanId()));
    }

    @Override
    public Employee searchById(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(id);
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public int getEmployeeCount() throws SQLException, ClassNotFoundException {
        return employeeDAO.count();
    }
}

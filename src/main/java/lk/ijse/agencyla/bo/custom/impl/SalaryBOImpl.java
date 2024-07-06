package lk.ijse.agencyla.bo.custom.impl;

import lk.ijse.agencyla.bo.custom.SalaryBO;
import lk.ijse.agencyla.dao.DAOFactory;
import lk.ijse.agencyla.dao.custom.EmployeeDAO;
import lk.ijse.agencyla.dao.custom.SalaryDAO;
import lk.ijse.agencyla.dto.EmployeeDTO;
import lk.ijse.agencyla.dto.SalaryDTO;
import lk.ijse.agencyla.entity.Customer;
import lk.ijse.agencyla.entity.Employee;
import lk.ijse.agencyla.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryBOImpl implements SalaryBO {

    SalaryDAO salaryDAO = (SalaryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SALARY);
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public ArrayList<SalaryDTO> getAllSalary() throws SQLException, ClassNotFoundException {
        ArrayList<SalaryDTO> allSalary= new ArrayList<>();
        ArrayList<Salary> all = salaryDAO.getAll();
        for (Salary s : all) {
            allSalary.add(new SalaryDTO(s.getSalaryId(),s.getEmpId(),s.getName(), s.getMonth(), s.getAmount(), s.getDate()));
        }
        return allSalary;
    }

    @Override
    public ArrayList<EmployeeDTO> getAllEmpID() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employeeEntityData = employeeDAO.getAll();
        ArrayList<EmployeeDTO> convertToDto= new ArrayList<>();
        for (Employee e : employeeEntityData) {
            convertToDto.add(new EmployeeDTO(e.getId(), e.getName(), e.getNic(),e.getAddress(),e.getContact(),e.getVanId()));
        }
        return convertToDto;
    }

    @Override
    public Employee searchByEmpId(String salaryId) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(salaryId);
    }

    @Override
    public boolean saveSalary(SalaryDTO dto) throws SQLException, ClassNotFoundException {
        return salaryDAO.save(new Salary(dto.getSalaryId(), dto.getEmpId(), dto.getName(), dto.getMonth(), dto.getAmount(), dto.getDate()));
    }

    @Override
    public Salary searchById(String code) throws SQLException, ClassNotFoundException {
        return salaryDAO.search(code);

    }

    @Override
    public boolean updateSalary(SalaryDTO dto) throws SQLException, ClassNotFoundException {
        return salaryDAO.update(new Salary(dto.getSalaryId(),dto.getEmpId(), dto.getName(), dto.getMonth(), dto.getAmount(), dto.getDate()));
    }

    @Override
    public boolean deleteSalary(String salaryId) throws SQLException, ClassNotFoundException {
        return salaryDAO.delete(salaryId);
    }
}

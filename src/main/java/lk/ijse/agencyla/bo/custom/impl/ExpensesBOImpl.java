package lk.ijse.agencyla.bo.custom.impl;

import lk.ijse.agencyla.bo.custom.ExpensesBO;
import lk.ijse.agencyla.dao.DAOFactory;
import lk.ijse.agencyla.dao.custom.CustomerDAO;
import lk.ijse.agencyla.dao.custom.ExpensesDAO;
import lk.ijse.agencyla.dao.custom.VanDAO;
import lk.ijse.agencyla.dto.CustomerDTO;
import lk.ijse.agencyla.dto.ExpensesDTO;
import lk.ijse.agencyla.dto.VanDTO;
import lk.ijse.agencyla.entity.Customer;
import lk.ijse.agencyla.entity.Expenses;
import lk.ijse.agencyla.entity.Stock;
import lk.ijse.agencyla.entity.Van;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExpensesBOImpl implements ExpensesBO {

    ExpensesDAO expensesDAO = (ExpensesDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EXPENSES);
    VanDAO vanDAO = (VanDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VAN);
    @Override
    public ArrayList<ExpensesDTO> getAllExpenses() throws SQLException, ClassNotFoundException {
        ArrayList<ExpensesDTO> allExpenses= new ArrayList<>();
        ArrayList<Expenses> all = expensesDAO.getAll();
        for (Expenses ex : all) {
            allExpenses.add(new ExpensesDTO(ex.getCode(),ex.getVanId(),ex.getAmount(), ex.getDescription(), ex.getDate()));
        }
        return allExpenses;
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
    public boolean saveExpenses(ExpensesDTO dto) throws SQLException, ClassNotFoundException {
        return expensesDAO.save(new Expenses(dto.getCode(),dto.getVanId(),dto.getDescription(),dto.getAmount(),dto.getDate()));
    }

    @Override
    public boolean updateExpenses(ExpensesDTO dto) throws SQLException, ClassNotFoundException {
        return expensesDAO.update(new Expenses(dto.getCode(), dto.getVanId(), dto.getDescription(),dto.getAmount(),dto.getDate()));
    }

    @Override
    public Expenses searchById(String code) throws SQLException, ClassNotFoundException {
        return expensesDAO.search(code);
    }

    @Override
    public boolean deleteExpenses(String code) throws SQLException, ClassNotFoundException {
        return expensesDAO.delete(code);
    }
}

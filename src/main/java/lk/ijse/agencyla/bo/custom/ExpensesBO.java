package lk.ijse.agencyla.bo.custom;

import lk.ijse.agencyla.bo.SuperBO;
import lk.ijse.agencyla.dto.ExpensesDTO;
import lk.ijse.agencyla.dto.VanDTO;
import lk.ijse.agencyla.entity.Expenses;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExpensesBO extends SuperBO {
    ArrayList<ExpensesDTO> getAllExpenses()throws SQLException, ClassNotFoundException;

    ArrayList<VanDTO> getAllVanID()throws SQLException, ClassNotFoundException;

    boolean saveExpenses(ExpensesDTO dto)throws SQLException, ClassNotFoundException;

    boolean updateExpenses(ExpensesDTO dto)throws SQLException, ClassNotFoundException;

    Expenses searchById(String code)throws SQLException, ClassNotFoundException;

    boolean deleteExpenses(String code)throws SQLException, ClassNotFoundException;
}

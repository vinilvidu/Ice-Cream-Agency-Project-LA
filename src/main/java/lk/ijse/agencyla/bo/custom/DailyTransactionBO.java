package lk.ijse.agencyla.bo.custom;

import lk.ijse.agencyla.bo.SuperBO;
import lk.ijse.agencyla.dto.DailyTransactionDTO;
import lk.ijse.agencyla.dto.VanDTO;
import lk.ijse.agencyla.entity.DailyTransaction;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DailyTransactionBO extends SuperBO {
    ArrayList<DailyTransactionDTO> getAllTransactions()throws SQLException, ClassNotFoundException;

    ArrayList<VanDTO> getAllVanID()throws SQLException, ClassNotFoundException;

    boolean saveDailyTransaction(DailyTransactionDTO dto)throws SQLException, ClassNotFoundException;

    boolean updateDailyTransaction(DailyTransactionDTO dto)throws SQLException, ClassNotFoundException;

    DailyTransaction searchById(String billId)throws SQLException, ClassNotFoundException;

    boolean deleteDailyTransaction(String billId)throws SQLException, ClassNotFoundException;
}

package lk.ijse.agencyla.bo.custom.impl;

import lk.ijse.agencyla.bo.custom.DailyTransactionBO;
import lk.ijse.agencyla.dao.DAOFactory;
import lk.ijse.agencyla.dao.custom.DailyTransactionDAO;
import lk.ijse.agencyla.dao.custom.ExpensesDAO;
import lk.ijse.agencyla.dao.custom.VanDAO;
import lk.ijse.agencyla.dto.DailyTransactionDTO;
import lk.ijse.agencyla.dto.ExpensesDTO;
import lk.ijse.agencyla.dto.VanDTO;
import lk.ijse.agencyla.entity.DailyTransaction;
import lk.ijse.agencyla.entity.Expenses;
import lk.ijse.agencyla.entity.Stock;
import lk.ijse.agencyla.entity.Van;

import java.sql.SQLException;
import java.util.ArrayList;

public class DailyTransactionBOImpl implements DailyTransactionBO {

    DailyTransactionDAO dailyTransactionDAO = (DailyTransactionDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DAILY_TRANSACTION);
    VanDAO vanDAO = (VanDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VAN);
    @Override
    public ArrayList<DailyTransactionDTO> getAllTransactions() throws SQLException, ClassNotFoundException {
        ArrayList<DailyTransactionDTO> allTransactions= new ArrayList<>();
        ArrayList<DailyTransaction> all = dailyTransactionDAO.getAll();
        for (DailyTransaction dt : all) {
            allTransactions.add(new DailyTransactionDTO(dt.getBillId(),dt.getAmount(),dt.getDate(), dt.getVanId()));
        }
        return allTransactions;
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
    public boolean saveDailyTransaction(DailyTransactionDTO dto) throws SQLException, ClassNotFoundException {
        return dailyTransactionDAO.save(new DailyTransaction(dto.getBillId(),dto.getAmount(),dto.getDate(),dto.getVanId()));
    }

    @Override
    public boolean updateDailyTransaction(DailyTransactionDTO dto) throws SQLException, ClassNotFoundException {
        return dailyTransactionDAO.update(new DailyTransaction(dto.getBillId(), dto.getAmount(), dto.getDate(),dto.getVanId()));
    }

    @Override
    public DailyTransaction searchById(String billId) throws SQLException, ClassNotFoundException {
        return dailyTransactionDAO.search(billId);

    }

    @Override
    public boolean deleteDailyTransaction(String billId) throws SQLException, ClassNotFoundException {
        return dailyTransactionDAO.delete(billId);
    }
}

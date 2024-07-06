package lk.ijse.agencyla.dao.custom.impl;

import lk.ijse.agencyla.dao.SQLUtil;
import lk.ijse.agencyla.dao.custom.DailyTransactionDAO;
import lk.ijse.agencyla.entity.DailyTransaction;
import lk.ijse.agencyla.entity.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DailyTransactionDAOImpl implements DailyTransactionDAO {
    @Override
    public ArrayList<DailyTransaction> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<DailyTransaction> allTransactions = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM daily_transaction");
        while (rst.next()) {
            DailyTransaction dailyTransaction = new DailyTransaction(rst.getString("bill_id"), rst.getDouble("amount"), rst.getString("date"),rst.getString("van_id"));
            allTransactions.add(dailyTransaction);
        }
        return allTransactions;
    }

    @Override
    public boolean save(DailyTransaction entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO daily_transaction(bill_id,amount,date,van_id) VALUES(?,?,?,?)",entity.getBillId(),entity.getAmount(),entity.getDate(),entity.getVanId());
    }

    @Override
    public boolean update(DailyTransaction entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE daily_transaction SET amount=?,date=?,van_id=? WHERE bill_id=?",entity.getAmount(),entity.getDate(),entity.getVanId(),entity.getBillId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String bill_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM daily_transaction WHERE bill_id=?",bill_id);
    }

    @Override
    public DailyTransaction search(String bill_id) throws SQLException, ClassNotFoundException {
        ResultSet rst  = SQLUtil.execute("SELECT * FROM daily_transaction WHERE bill_id=?",bill_id+"");
        rst.next();
        return new DailyTransaction(bill_id+"",rst.getDouble("amount"),rst.getString("date"),rst.getString("van_id"));
    }

    @Override
    public boolean getId(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}

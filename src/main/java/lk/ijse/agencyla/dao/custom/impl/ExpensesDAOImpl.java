package lk.ijse.agencyla.dao.custom.impl;

import lk.ijse.agencyla.dao.SQLUtil;
import lk.ijse.agencyla.dao.custom.ExpensesDAO;
import lk.ijse.agencyla.entity.Customer;
import lk.ijse.agencyla.entity.Expenses;
import lk.ijse.agencyla.entity.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExpensesDAOImpl implements ExpensesDAO {
    @Override
    public ArrayList<Expenses> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Expenses> allExpenses = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM expenses");
        while (rst.next()) {
            Expenses expenses = new Expenses(rst.getString("code"), rst.getString("van_id"), rst.getString("description"),rst.getDouble("amount"),rst.getString("date"));
            allExpenses.add(expenses);
        }
        return allExpenses;
    }

    @Override
    public boolean save(Expenses entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO expenses(code,van_id,description,amount,date) VALUES(?,?,?,?,?)",entity.getCode(),entity.getVanId(),entity.getDescription(),entity.getAmount(),entity.getDate());
    }

    @Override
    public boolean update(Expenses entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE expenses SET van_id=?,description=?,amount=?,date=? WHERE code=?",entity.getVanId(),entity.getDescription(),entity.getAmount(),entity.getDate(),entity.getCode());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM expenses WHERE code=?",code);
    }

    @Override
    public Expenses search(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst  = SQLUtil.execute("SELECT * FROM expenses WHERE code=?",code+"");
        rst.next();
        return new Expenses(code+"",rst.getString("van_id"),rst.getString("description"),rst.getDouble("amount"),rst.getString("date"));
    }

    @Override
    public boolean getId(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}

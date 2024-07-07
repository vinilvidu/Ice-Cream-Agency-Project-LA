package lk.ijse.agencyla.dao.custom.impl;

import lk.ijse.agencyla.dao.CrudDAO;
import lk.ijse.agencyla.dao.SQLUtil;
import lk.ijse.agencyla.dao.custom.CreditBillDAO;
import lk.ijse.agencyla.entity.CreditBill;
import lk.ijse.agencyla.entity.Expenses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreditBillDAOImpl implements CreditBillDAO {

    @Override
    public ArrayList<CreditBill> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CreditBill> allCreditBills = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM credit_bill");
        while (rst.next()) {
            CreditBill creditBill = new CreditBill(rst.getString("bill_id"), rst.getString("cus_id"), rst.getString("route_id"),rst.getDouble("amount"),rst.getString("date"));
            allCreditBills.add(creditBill);
        }
        return allCreditBills;
    }

    @Override
    public boolean save(CreditBill entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO credit_bill(bill_id,cus_id,route_id,amount,date) VALUES(?,?,?,?,?)",entity.getBillId(),entity.getCusId(),entity.getRouteId(),entity.getAmount(),entity.getDate());
    }

    @Override
    public boolean update(CreditBill entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE credit_bill SET cus_id=?,route_id=?amount=?,date=? WHERE bill_id=?",entity.getCusId(),entity.getRouteId(),entity.getAmount(),entity.getDate(),entity.getBillId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String bill_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM credit_bill WHERE bill_id=?",bill_id);
    }

    @Override
    public CreditBill search(String bill_id) throws SQLException, ClassNotFoundException {
        ResultSet rst  = SQLUtil.execute("SELECT * FROM credit_bill WHERE bill_id=?",bill_id+"");
        rst.next();
        return new CreditBill(bill_id+"",rst.getString("cus_id"),rst.getString("route_id"),rst.getDouble("amount"),rst.getString("date"));
    }

    @Override
    public boolean getId(String id) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public double amount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=SQLUtil.execute("SELECT SUM(amount) AS credit_amount FROM credit_bill");

        double creditAmount = 0.00;
        if(resultSet.next()) {
            creditAmount =  resultSet.getDouble("credit_amount");
        }
        return creditAmount;
    }
}

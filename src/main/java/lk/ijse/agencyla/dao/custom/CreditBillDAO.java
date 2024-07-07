package lk.ijse.agencyla.dao.custom;

import lk.ijse.agencyla.dao.CrudDAO;
import lk.ijse.agencyla.entity.CreditBill;

import java.sql.SQLException;

public interface CreditBillDAO extends CrudDAO<CreditBill> {
    double amount()throws SQLException, ClassNotFoundException;
}

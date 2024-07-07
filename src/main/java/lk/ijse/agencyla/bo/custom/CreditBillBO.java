package lk.ijse.agencyla.bo.custom;

import lk.ijse.agencyla.bo.SuperBO;
import lk.ijse.agencyla.dto.CreditBillDTO;
import lk.ijse.agencyla.dto.CustomerDTO;
import lk.ijse.agencyla.entity.CreditBill;
import lk.ijse.agencyla.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CreditBillBO extends SuperBO {

    ArrayList<CreditBillDTO> getAllCreditBills()throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getAllCustomerId()throws SQLException, ClassNotFoundException;

    boolean saveCreditBill(CreditBillDTO dto)throws SQLException, ClassNotFoundException;

    Customer searchByCusId(String cusId)throws SQLException, ClassNotFoundException;

    CreditBill searchById(String billId)throws SQLException, ClassNotFoundException;

    boolean updateCreditBill(CreditBillDTO dto)throws SQLException, ClassNotFoundException;

    boolean deleteCreditBill(String billId)throws SQLException, ClassNotFoundException;

    double getCreditAmount()throws SQLException, ClassNotFoundException;
}

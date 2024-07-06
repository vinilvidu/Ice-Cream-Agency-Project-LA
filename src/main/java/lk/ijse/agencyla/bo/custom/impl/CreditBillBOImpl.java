package lk.ijse.agencyla.bo.custom.impl;

import lk.ijse.agencyla.bo.custom.CreditBillBO;
import lk.ijse.agencyla.dao.DAOFactory;
import lk.ijse.agencyla.dao.custom.CreditBillDAO;
import lk.ijse.agencyla.dao.custom.CustomerDAO;
import lk.ijse.agencyla.dao.custom.ExpensesDAO;
import lk.ijse.agencyla.dto.CreditBillDTO;
import lk.ijse.agencyla.dto.CustomerDTO;
import lk.ijse.agencyla.dto.ExpensesDTO;
import lk.ijse.agencyla.dto.RouteDTO;
import lk.ijse.agencyla.entity.CreditBill;
import lk.ijse.agencyla.entity.Customer;
import lk.ijse.agencyla.entity.Expenses;
import lk.ijse.agencyla.entity.Route;

import java.sql.SQLException;
import java.util.ArrayList;

public class CreditBillBOImpl implements CreditBillBO {

    CreditBillDAO creditBillDAO = (CreditBillDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CREDIT_BILL);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CreditBillDTO> getAllCreditBills() throws SQLException, ClassNotFoundException {
        ArrayList<CreditBillDTO> allCreditBills= new ArrayList<>();
        ArrayList<CreditBill> all = creditBillDAO.getAll();
        for (CreditBill cb : all) {
            allCreditBills.add(new CreditBillDTO(cb.getBillId(),cb.getCusId(),cb.getRouteId(), cb.getAmount(), cb.getDate()));
        }
        return allCreditBills;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomerId() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customerEntityData = customerDAO.getAll();
        ArrayList<CustomerDTO> convertToDto= new ArrayList<>();
        for (Customer c : customerEntityData) {
            convertToDto.add(new CustomerDTO(c.getId(),c.getName(),c.getShopName(),c.getContact(),c.getAddress(),c.getRouteId()));
        }
        return convertToDto;
    }

    @Override
    public boolean saveCreditBill(CreditBillDTO dto) throws SQLException, ClassNotFoundException {
        return creditBillDAO.save(new CreditBill(dto.getBillId(),dto.getCusId(),dto.getRouteId(),dto.getAmount(),dto.getDate()));
    }

    @Override
    public Customer searchByCusId(String cusId) throws SQLException, ClassNotFoundException {
        return customerDAO.search(cusId);
    }

    @Override
    public CreditBill searchById(String billId) throws SQLException, ClassNotFoundException {
        return creditBillDAO.search(billId);

    }

    @Override
    public boolean updateCreditBill(CreditBillDTO dto) throws SQLException, ClassNotFoundException {
        return creditBillDAO.update(new CreditBill(dto.getBillId(), dto.getCusId(), dto.getRouteId(),dto.getAmount(),dto.getDate()));
    }

    @Override
    public boolean deleteCreditBill(String billId) throws SQLException, ClassNotFoundException {
        return creditBillDAO.delete(billId);
    }
}

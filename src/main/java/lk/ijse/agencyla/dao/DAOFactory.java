package lk.ijse.agencyla.dao;

import lk.ijse.agencyla.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,ROUTE,STOCK,EMPLOYEE,VAN,SALARY,EXPENSES,CREDIT_BILL,DAILY_TRANSACTION,ORDER,ORDER_DETAIL,DAILY_LOADING,DAILY_LOADING_DETAIL

    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ROUTE:
                return new RouteDAOImpl();
            case STOCK:
                return new StockDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case VAN:
                return new VanDAOImpl();
            case SALARY:
                return new SalaryDAOImpl();
            case EXPENSES:
                return new ExpensesDAOImpl();
            case CREDIT_BILL:
                return new CreditBillDAOImpl();
            case DAILY_TRANSACTION:
                return new DailyTransactionDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();
            case DAILY_LOADING:
                return new DailyLoadingDAOImpl();
            case DAILY_LOADING_DETAIL:
                return new DailyLoadingDetailDAOImpl();
            default:
                return null;
        }
    }
}

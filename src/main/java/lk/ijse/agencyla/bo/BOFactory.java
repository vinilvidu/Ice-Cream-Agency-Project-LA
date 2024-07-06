package lk.ijse.agencyla.bo;

import lk.ijse.agencyla.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,ROUTE,STOCK,EMPLOYEE,VAN,SALARY,EXPENSES,CREDIT_BILL,DAILY_TRANSACTION,PO,PLACE_DL
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case ROUTE:
                return new RouteBOImpl();
            case STOCK:
                return new StockBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case VAN:
                return new VanBOImpl();
            case SALARY:
                return new SalaryBOImpl();
            case EXPENSES:
                return new ExpensesBOImpl();
            case CREDIT_BILL:
                return new CreditBillBOImpl();
            case DAILY_TRANSACTION:
                return new DailyTransactionBOImpl();
            case PO:
                return new PurchaseOrderBOImpl();
            case PLACE_DL:
                return new PlaseDailyLoadingBOImpl();
            default:
                return null;
        }
    }
}

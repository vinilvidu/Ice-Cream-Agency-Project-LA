package lk.ijse.agencyla.bo.custom.impl;

import lk.ijse.agencyla.bo.custom.VanBO;
import lk.ijse.agencyla.dao.DAOFactory;
import lk.ijse.agencyla.dao.custom.StockDAO;
import lk.ijse.agencyla.dao.custom.VanDAO;

public class VanBOImpl implements VanBO {

    VanDAO vanDAO = (VanDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VAN);
}

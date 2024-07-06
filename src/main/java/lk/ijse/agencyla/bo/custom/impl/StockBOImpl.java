package lk.ijse.agencyla.bo.custom.impl;

import lk.ijse.agencyla.bo.custom.StockBO;
import lk.ijse.agencyla.dao.DAOFactory;
import lk.ijse.agencyla.dao.custom.StockDAO;
import lk.ijse.agencyla.dto.StockDTO;
import lk.ijse.agencyla.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockBOImpl implements StockBO {

    StockDAO stockDAO = (StockDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCK);

    @Override
    public boolean saveStock(StockDTO dto) throws SQLException, ClassNotFoundException {
        return stockDAO.save(new Stock(dto.getItemCode(),dto.getName(),dto.getUnitPrice(),dto.getQty()));
    }

    @Override
    public ArrayList<StockDTO> getAllStocks() throws SQLException, ClassNotFoundException {
        ArrayList<StockDTO> allStocks= new ArrayList<>();
        ArrayList<Stock> all = stockDAO.getAll();
        for (Stock s : all) {
            allStocks.add(new StockDTO(s.getItemCode(),s.getName(),s.getUnitPrice(),s.getQty()));
        }
        return allStocks;
    }

    @Override
    public boolean updateStock(StockDTO dto) throws SQLException, ClassNotFoundException {
        return stockDAO.update(new Stock(dto.getItemCode(), dto.getName(), dto.getUnitPrice(),dto.getQty()));
    }

    @Override
    public boolean deleteStock(String itemCode) throws SQLException, ClassNotFoundException {
        return stockDAO.delete(itemCode);
    }

    @Override
    public Stock searchById(String itemCode) throws SQLException, ClassNotFoundException {
        return stockDAO.search(itemCode);
    }
}

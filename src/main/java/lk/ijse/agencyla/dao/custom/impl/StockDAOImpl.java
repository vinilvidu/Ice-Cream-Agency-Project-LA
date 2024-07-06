package lk.ijse.agencyla.dao.custom.impl;

import lk.ijse.agencyla.dao.SQLUtil;
import lk.ijse.agencyla.dao.custom.StockDAO;
import lk.ijse.agencyla.dto.DailyLoadingDetailDTO;
import lk.ijse.agencyla.dto.OrderDetailDTO;
import lk.ijse.agencyla.entity.DailyLoadingDetail;
import lk.ijse.agencyla.entity.OrderDetail;
import lk.ijse.agencyla.entity.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAOImpl implements StockDAO {
    @Override
    public ArrayList<Stock> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Stock> allStocks = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM stock");
        while (rst.next()) {
            Stock stock = new Stock(rst.getString("code"), rst.getString("name"), rst.getDouble("price"),rst.getInt("qty"));
            allStocks.add(stock);
        }
        return allStocks;
    }

    @Override
    public boolean save(Stock entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO stock(code,name,qty,price) VALUES(?,?,?,?)",entity.getItemCode(),entity.getName(),entity.getQty(),entity.getUnitPrice());
    }

    @Override
    public boolean update(Stock entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE stock SET name=?,qty=?,price=? WHERE code=?",entity.getName(),entity.getQty(),entity.getUnitPrice(),entity.getItemCode());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM stock WHERE code=?",code);
    }

    @Override
    public Stock search(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst  = SQLUtil.execute("SELECT * FROM stock WHERE code=?",code+"");
        rst.next();
        return new Stock(code+"",rst.getString("name"),rst.getDouble("price"),rst.getInt("qty"));
    }

    @Override
    public boolean getId(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
    public boolean updateQty(List<OrderDetailDTO> odList) throws SQLException, ClassNotFoundException {
        for (OrderDetailDTO od : odList) {
            if(!updateQty((List<OrderDetailDTO>) od))  {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateQty(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE stock SET qty = qty + ? WHERE code = ?",entity.getQty(),entity.getItemCode());
    }

    public boolean updateLoadingQty(List<DailyLoadingDetailDTO> ldList) throws SQLException {
        for (DailyLoadingDetailDTO ld : ldList) {
            if(!updateLoadingQty((List<DailyLoadingDetailDTO>) ld))  {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateLoadingQty(DailyLoadingDetail entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE stock SET qty = qty - ? WHERE code = ?",entity.getQty(),entity.getItemCode());
    }
}

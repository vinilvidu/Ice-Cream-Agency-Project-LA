package lk.ijse.agencyla.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;



public class StockDTO implements Serializable {
    private String itemCode;
    private String name;
    private double unitPrice;
    private int qty;

    @Override
    public String toString() {
        return "StockDTO{" +
                "itemCode='" + itemCode + '\'' +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                '}';
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public StockDTO(String itemCode, String name, double unitPrice, int qty) {
        this.itemCode = itemCode;
        this.name = name;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }
}

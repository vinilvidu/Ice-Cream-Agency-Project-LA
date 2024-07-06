package lk.ijse.agencyla.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class StockTM  {
    private String itemCode;
    private String name;
    private double unitPrice;
    private int qty;

    @Override
    public String toString() {
        return "StockTM{" +
                "itemCode='" + itemCode + '\'' +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", Qty=" + qty +
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
        qty = qty;
    }

    public StockTM(){}

    public StockTM(String itemCode, String name, double unitPrice, int qty) {
        this.itemCode = itemCode;
        this.name = name;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }
}

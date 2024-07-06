package lk.ijse.agencyla.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Stock {
    private String itemCode;
    private String name;
    private double unitPrice;
    private int qty;

}

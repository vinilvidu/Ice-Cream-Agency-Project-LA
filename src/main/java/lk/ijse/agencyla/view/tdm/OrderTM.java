package lk.ijse.agencyla.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class OrderTM {
    private String orderId;
    private String itemCode;
    private String itemName;
    private int qty;
    private String date;
}

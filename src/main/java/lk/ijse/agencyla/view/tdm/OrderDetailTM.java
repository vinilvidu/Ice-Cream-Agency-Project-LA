package lk.ijse.agencyla.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class OrderDetailTM {
    private String orderId;
    private String itemCode;
    private int qty;
}

package lk.ijse.agencyla.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditBillTM {
    private String billId;
    private String cusId;
    private String routeId;
    private double amount;
    private String date;

}

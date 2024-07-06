package lk.ijse.agencyla.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditBillDTO {
    private String billId;
    private String cusId;
    private String routeId;
    private double amount;
    private String date;

}

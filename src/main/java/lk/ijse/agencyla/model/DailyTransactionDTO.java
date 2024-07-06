package lk.ijse.agencyla.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DailyTransactionDTO {
    private String billId;
    private double amount;
    private String date;
    private String vanId;
}

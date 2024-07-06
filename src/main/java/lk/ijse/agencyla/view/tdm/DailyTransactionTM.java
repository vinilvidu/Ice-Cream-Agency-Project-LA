package lk.ijse.agencyla.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DailyTransactionTM {
    private String billId;
    private double amount;
    private String date;
    private String vanId;
}

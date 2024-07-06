package lk.ijse.agencyla.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DailyTransaction {
    private String billId;
    private double amount;
    private String date;
    private String vanId;
}

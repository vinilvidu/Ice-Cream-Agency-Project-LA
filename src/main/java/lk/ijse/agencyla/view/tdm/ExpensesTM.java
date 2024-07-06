package lk.ijse.agencyla.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExpensesTM {
    private String code;
    private String vanId;
    private double amount;
    private String description;
    private String date;
}

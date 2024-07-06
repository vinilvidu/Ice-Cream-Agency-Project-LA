package lk.ijse.agencyla.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExpensesDTO {
    private String code;
    private String vanId;
    private double amount;
    private String description;
    private String date;
}

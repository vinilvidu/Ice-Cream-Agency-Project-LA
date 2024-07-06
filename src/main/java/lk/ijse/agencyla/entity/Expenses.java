package lk.ijse.agencyla.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Expenses {
    private String code;
    private String vanId;
    private String description;
    private double amount;
    private String date;
}

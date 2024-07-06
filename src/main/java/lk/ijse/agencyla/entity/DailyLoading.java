package lk.ijse.agencyla.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DailyLoading {
    private String repoId;
    private String itemCode;
    private String itemName;
    private int qty;
    private String vanId;
    private String date;
}

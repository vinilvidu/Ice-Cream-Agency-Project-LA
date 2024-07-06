package lk.ijse.agencyla.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DailyLoadingDTO {
    private String repoId;
    private String itemCode;
    private String itemName;
    private int qty;
    private String vanId;
    private String date;
}

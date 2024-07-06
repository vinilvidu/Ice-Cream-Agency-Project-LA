package lk.ijse.agencyla.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DailyLoadingDetail {
    private String repoId;
    private String vanId;
    private String itemCode;
    private int qty;
}

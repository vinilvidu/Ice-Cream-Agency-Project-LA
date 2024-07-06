package lk.ijse.agencyla.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DailyLoadingDetailDTO {
    private String repoId;
    private String vanId;
    private String itemCode;
    private int qty;
}

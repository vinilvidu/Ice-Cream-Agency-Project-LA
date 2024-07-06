package lk.ijse.agencyla.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DailyLoadingDetailTM {
    private String repoId;
    private String vanId;
    private String itemCode;
    private int qty;
}

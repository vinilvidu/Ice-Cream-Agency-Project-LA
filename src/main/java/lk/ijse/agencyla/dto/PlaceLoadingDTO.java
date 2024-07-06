package lk.ijse.agencyla.dto;

import lk.ijse.agencyla.entity.DailyLoading;
import lk.ijse.agencyla.entity.DailyLoadingDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class PlaceLoadingDTO {
    private DailyLoading loading;
    private List<DailyLoadingDetailDTO> ldList;
}

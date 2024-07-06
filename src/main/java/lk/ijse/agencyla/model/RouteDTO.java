package lk.ijse.agencyla.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RouteDTO {
    private String routeId;
    private String name;
    private String vanId;
    private String day;
}

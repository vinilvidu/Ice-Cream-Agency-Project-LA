package lk.ijse.agencyla.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Route {
    private String routeId;
    private String name;
    private String vanId;
    private String day;
}

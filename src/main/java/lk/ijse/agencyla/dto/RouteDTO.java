package lk.ijse.agencyla.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class RouteDTO {
    private String routeId;
    private String name;
    private String vanId;
    private String day;

    @Override
    public String toString() {
        return "RouteDTO{" +
                "routeId='" + routeId + '\'' +
                ", name='" + name + '\'' +
                ", vanId='" + vanId + '\'' +
                ", day='" + day + '\'' +
                '}';
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVanId() {
        return vanId;
    }

    public void setVanId(String vanId) {
        this.vanId = vanId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public RouteDTO(String routeId, String name, String vanId, String day) {
        this.routeId = routeId;
        this.name = name;
        this.vanId = vanId;
        this.day = day;
    }
}

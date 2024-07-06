package lk.ijse.agencyla.dto;

public class VanDTO {


    private String vanId;
    private String number;

    public String getVanId() {
        return vanId;
    }

    public void setVanId(String vanId) {
        this.vanId = vanId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public VanDTO(){}

    public VanDTO(String vanId, String number) {
        this.vanId = vanId;
        this.number = number;
    }
    @Override
    public String toString() {
        return "VanDTO{" +
                "vanId='" + vanId + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}

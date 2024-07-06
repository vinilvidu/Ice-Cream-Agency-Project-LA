package lk.ijse.agencyla.entity;

public class Van {
    private String vanId;
    private String number;

    @Override
    public String toString() {
        return "Van{" +
                "vanId='" + vanId + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

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

    public Van(String vanId, String number) {
        this.vanId = vanId;
        this.number = number;
    }
}

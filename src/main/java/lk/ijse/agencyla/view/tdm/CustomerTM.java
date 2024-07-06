package lk.ijse.agencyla.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



public class CustomerTM implements Comparable<CustomerTM>{
    private String id;
    private String name;
    private String shopName;
    private String contact;
    private String address;
    private String routeId;

    @Override
    public String toString() {
        return "CustomerTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", shopName='" + shopName + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", routeId='" + routeId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public CustomerTM(){}

    public CustomerTM(String id, String name, String shopName, String contact, String address, String routeId) {
        this.id = id;
        this.name = name;
        this.shopName = shopName;
        this.contact = contact;
        this.address = address;
        this.routeId = routeId;
    }

    @Override
    public int compareTo(CustomerTM o) {
        return id.compareTo(o.getId());
    }
}

package lk.ijse.agencyla.dto;

import lk.ijse.agencyla.entity.Order;
import lk.ijse.agencyla.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceOrderDTO implements Serializable {
    private Order order;

    private List<OrderDetailDTO> odList;
    private List<OrderDetail> stlist;
}

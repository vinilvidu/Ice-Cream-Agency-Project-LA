package lk.ijse.agencyla.dao.custom;

import lk.ijse.agencyla.dao.CrudDAO;
import lk.ijse.agencyla.dto.OrderDetailDTO;
import lk.ijse.agencyla.entity.OrderDetail;

import java.util.List;

public interface OrderDetailDAO extends CrudDAO<OrderDetail> {
    boolean save(List<OrderDetailDTO> odList);
}

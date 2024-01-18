package lk.ijse.mini.api.dao.custom;

import lk.ijse.mini.api.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO {
    boolean save(String orderId, List<OrderDetail> orderDetailsList, Connection connection) throws SQLException;
}

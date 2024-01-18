package lk.ijse.mini.api.dao.custom;

import lk.ijse.mini.api.entity.Order;

import java.sql.Connection;

public interface OrderDAO {
    boolean save(Order order, Connection connection, String customerId);

    String getOrderId(Connection connection);
}

package lk.ijse.mini.api.bo.custom;

import lk.ijse.mini.api.dto.OrderDTO;
import lk.ijse.mini.api.dto.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderBO {
    boolean perchesOrder(OrderDTO orderDTO, List<OrderDetailDTO> orderDetailDTOList, String customerId, Connection connection) throws SQLException;

    String getOrderId(Connection connection);

}

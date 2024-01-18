package lk.ijse.mini.api.dao.custom.impl;

import lk.ijse.mini.api.entity.OrderDetail;
import lk.ijse.mini.api.dao.custom.OrderDetailDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    private static final Logger logger = LoggerFactory.getLogger(ItemDAOImpl.class);

    private static final String SAVE_ORDER_DETAIL = "INSERT INTO order_detail (orderId,itemCode,description,unitPrice,qty,total)VALUES(?,?,?,?,?,?)";
    @Override
    public boolean save(String orderId, List<OrderDetail> orderDtoList, Connection connection) throws SQLException {
        for (OrderDetail dto : orderDtoList){
            if (!save(orderId,dto,connection)){
                return false;
            }
        }
        return true;
    }

    private static boolean save(String orderId,OrderDetail dto,Connection connection) throws SQLException {        try {
        var ps = connection.prepareStatement(SAVE_ORDER_DETAIL);
        ps.setString(1, orderId);
        ps.setString(2, dto.getItemCode());
        ps.setString(3, dto.getDescription());
        ps.setDouble(4, dto.getUnitPrice());
        ps.setInt(5, dto.getQty());
        ps.setDouble(6, dto.getTotal());

            if (ps.executeUpdate() != 0) {
                logger.info("Order details information saved successfully!!!");
                return true;
            } else {
                logger.error("Order details information saved unsuccessfully!!!");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

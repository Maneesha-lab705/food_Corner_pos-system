package lk.ijse.mini.api.dao.custom.impl;

import lk.ijse.mini.api.entity.Order;
import lk.ijse.mini.api.dao.custom.OrderDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class OrderDAOImpl implements OrderDAO {

    private static final Logger logger = LoggerFactory.getLogger(ItemDAOImpl.class);

    private static final String SAVE_ITEM_DATA ="INSERT INTO orders (orderId,customerId,price,date) VALUES(?,?,?,?)" ;
    @Override
    public boolean save(Order order, Connection connection, String customerId) {
        try {
            var ps = connection.prepareStatement(SAVE_ITEM_DATA);
            ps.setString(1, order.getOrderId());
            ps.setString(2, order.getCustomerId());
            ps.setDouble(3, order.getPrice());
            ps.setDate(4, Date.valueOf(order.getDate()));

            if (ps.executeUpdate() != 0) {
                logger.info("Order information saved successfully!!!");
                return true;
            } else {
                logger.error("Order information saved unsuccessfully!!!");
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getOrderId(Connection connection) {
        String lastOrderId = null;

            String query = "SELECT MAX(orderId) FROM orders";

            try{
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    lastOrderId = resultSet.getString(1);
                    return generateNextOrderId(lastOrderId);
                }
            }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return lastOrderId;
    }

    private static String generateNextOrderId(String lastOrderId) {
        // Extract the numerical part
        String numericPart = lastOrderId.substring(1);

        // Convert it to an integer and increment by 1
        int nextNumericValue = Integer.parseInt(numericPart) + 1;

        // Format it back to the original format with leading zeros
        String nextNumericPart = String.format("%03d", nextNumericValue);

        // Combine it with the prefix "O"
        return "O" + nextNumericPart;
    }
}

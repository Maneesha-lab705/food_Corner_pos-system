package lk.ijse.mini.api.dao.custom;

import lk.ijse.mini.api.dto.ItemDTO;
import lk.ijse.mini.api.entity.Item;
import lk.ijse.mini.api.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemDAO {
    boolean save(Item item);

    boolean update(Item item);

    boolean delete(String code);

    ArrayList<ItemDTO> getAllItem(Connection connection);

    boolean updateQtyOrder(List<OrderDetail> orderDetailsList, Connection connection) throws SQLException;
}

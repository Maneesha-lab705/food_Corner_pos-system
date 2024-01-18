package lk.ijse.mini.api.dao.custom.impl;

import lk.ijse.mini.api.dao.CrudUtil;
import lk.ijse.mini.api.dao.custom.ItemDAO;
import lk.ijse.mini.api.dto.CustomerDTO;
import lk.ijse.mini.api.dto.ItemDTO;
import lk.ijse.mini.api.entity.Item;
import lk.ijse.mini.api.entity.OrderDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    private static final Logger logger = LoggerFactory.getLogger(ItemDAOImpl.class);

    private static final String UPDATE_ORDER_ITEM_DATA = "UPDATE item SET qty = (qty - ?) WHERE code = ?";
    @Override
    public boolean save(Item item) {
        String sql="INSERT INTO item (code,description,unitPrice,qty) VALUES (?,?,?,?)";
        try {
            return CrudUtil.execute(sql,item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQty());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean update(Item item) {
        String sql="UPDATE item SET description=?,unitPrice=?,qty=? WHERE code=?;";

        try {
            return CrudUtil.execute(sql,item.getDescription(),item.getUnitPrice(),item.getQty(),item.getCode());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean delete(String code) {
        String sql="DELETE FROM item WHERE code=? ";
        try {
            CrudUtil.execute(sql,code);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public ArrayList<ItemDTO> getAllItem(Connection connection) {
        String sql="SELECT * FROM item";
        try {
            var preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
            while(resultSet.next()){
                ItemDTO itemDTO = new ItemDTO(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4)
                );
                itemDTOS.add(itemDTO);

            }
            return itemDTOS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateQtyOrder(List<OrderDetail> orderDtoList, Connection connection) throws SQLException {
        for (OrderDetail dto : orderDtoList) {
            if (!updateQtyOrder(dto,connection)){
                return false;
            }
        }
        return true;
    }

    public boolean updateQtyOrder(OrderDetail dto,Connection connection) throws SQLException {
        try {
            var ps = connection.prepareStatement(UPDATE_ORDER_ITEM_DATA);
            ps.setInt(1, dto.getQty());
            ps.setString(2, dto.getItemCode());

            if (ps.executeUpdate() != 0) {
                logger.info("Item Qty updated successfully!!!");
                return true;
            } else {
                logger.error("Item Qty updated unsuccessfully!!!");
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

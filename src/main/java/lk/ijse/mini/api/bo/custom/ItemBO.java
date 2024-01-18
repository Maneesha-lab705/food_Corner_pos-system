package lk.ijse.mini.api.bo.custom;

import lk.ijse.mini.api.dto.ItemDTO;

import java.sql.Connection;
import java.util.ArrayList;

public interface ItemBO {
    boolean saveItem(ItemDTO itemDTO);

    boolean updateItem(ItemDTO itemDTO);

    boolean deleteItem(String code);

    ArrayList<ItemDTO> getAllItem(Connection connection);
}

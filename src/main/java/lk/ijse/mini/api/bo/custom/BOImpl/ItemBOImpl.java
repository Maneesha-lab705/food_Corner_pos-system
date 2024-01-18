package lk.ijse.mini.api.bo.custom.BOImpl;

import lk.ijse.mini.api.bo.custom.ItemBO;
import lk.ijse.mini.api.dao.custom.ItemDAO;
import lk.ijse.mini.api.dao.custom.impl.ItemDAOImpl;
import lk.ijse.mini.api.dto.CustomerDTO;
import lk.ijse.mini.api.dto.ItemDTO;
import lk.ijse.mini.api.entity.Item;

import java.sql.Connection;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO=new ItemDAOImpl();
    @Override
    public boolean saveItem(ItemDTO itemDTO) {
        return itemDAO.save(new Item(itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQty()));
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) {
        return itemDAO.update(new Item(itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQty()));
    }

    @Override
    public boolean deleteItem(String code) {
       return itemDAO.delete(code);
    }

    @Override
    public ArrayList<ItemDTO> getAllItem(Connection connection) {
        ArrayList<ItemDTO> allItem = itemDAO.getAllItem(connection);
        return allItem;
    }
}

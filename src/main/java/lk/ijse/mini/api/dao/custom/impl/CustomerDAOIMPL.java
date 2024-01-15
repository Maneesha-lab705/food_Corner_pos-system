package lk.ijse.mini.api.dao.custom.impl;

import lk.ijse.mini.api.dao.CrudUtil;
import lk.ijse.mini.api.dao.custom.CustomerDAO;
import lk.ijse.mini.api.dto.CustomerDTO;
import lk.ijse.mini.api.entity.Customer;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOIMPL implements CustomerDAO {
    @Override
    public boolean save(Customer customer) {
        String sql="INSERT INTO customer (id,name,address,email,contact) VALUES(?,?,?,?,?)";
        try {
            return CrudUtil.execute(sql,customer.getId(),customer.getName(),customer.getAddress(),customer.getEmail(),customer.getContact());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Customer sharch(String id) {
        System.out.println(id);
        String sql="SELECT * FROM customer WHERE id =? ";
        try {
            ResultSet resultSet =CrudUtil.execute(sql,id);
            if (resultSet.next()){
              String ids=resultSet.getString("id");
              String name=resultSet.getString("name");
              String address=resultSet.getString("address");
              String email=resultSet.getString("email");
              int contact=resultSet.getInt("contact");

                System.out.println(ids+" "+name+" "+address+" "+email+" "+contact);
              return new Customer(ids,name,address,email,contact);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    @SneakyThrows
    @Override
    public boolean update(Customer customer) {
        String sql="UPDATE customer SET name=? ,address=?,email=?,contact=? WHERE id=?;";

        return CrudUtil.execute(sql,customer.getName(),customer.getAddress(),customer.getEmail(),customer.getContact(),customer.getId());

    }


    @SneakyThrows
    public boolean delete(String customerID) {
        String sql ="DELETE  FROM customer WHERE id =?";

        return CrudUtil.execute(sql,customerID);
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer(Connection connection) {
        String sql="SELECT * FROM customer";
        try {
            var preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
            while(resultSet.next()){
                CustomerDTO customerDTO = new CustomerDTO(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                );
                customerDTOS.add(customerDTO);

            }
            return customerDTOS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

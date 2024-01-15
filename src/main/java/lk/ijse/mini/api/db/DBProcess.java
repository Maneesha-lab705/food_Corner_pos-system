//package lk.ijse.mini.api.db;
//
//import lk.ijse.mini.api.dto.CustomerDTO;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class DBProcess {
//
//    private static final String SAVE_DATA = "INSERT INTO customer (id,name,address,email,contact) VALUES(?,?,?,?,?)";
//    private static final String GET_DATA = "SELECT * FROM Customer WHERE id = ?";
//    private static final String GET_CDATA = "SELECT id,name FROM Customer WHERE id = ?";
//
//    private static final String DELETE_CUSTOMER = "DELETE  FROM customer WHERE id =?";
//
//    private  static final String UPDATE_CUSTOMER="UPDATE customer SET name=? ,address=?,email=?,contact=? WHERE id=?;";
//
//
//    public boolean saveCustomer(CustomerDTO customerDTO, Connection connection) {
//        try {
//            PreparedStatement ps = connection.prepareStatement(SAVE_DATA);
//            ps.setString(1, customerDTO.getId());
//            ps.setString(2, customerDTO.getName());
//            ps.setString(3, customerDTO.getAddress());
//            ps.setString(4, customerDTO.getEmail());
//            ps.setInt(5, customerDTO.getContact());
//
//            if (ps.executeUpdate() > 0) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
////    public CustomerDTO getCustomer(Connection connection, String id) {
////
////        try {
////            PreparedStatement preparedStatement = connection.prepareStatement(GET_CDATA);
////            preparedStatement.setString(1, id);
////            ResultSet resultSet = preparedStatement.executeQuery();
////            if (resultSet.next()) {
////                return new CustomerDTO(
////                        resultSet.getString("id"),
////                        resultSet.getString("name")
////
////                );
////            }
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        }
////
////        return null;
////    }
//
//    public boolean deleteStudent(String customerID, Connection connection) throws SQLException {
////        System.out.println("mn awa");
//        try {
//
//            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER);
//            preparedStatement.setString(1,customerID);
//          //  System.out.println("mn giya");
//            return preparedStatement.executeUpdate() != 0;
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public boolean updateCustomer(CustomerDTO customerDTO, Connection connection) {
//        System.out.println("awa");
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER);
//            preparedStatement.setString(1,customerDTO.getName());
//            preparedStatement.setString(2,customerDTO.getAddress());
//            preparedStatement.setString(3,customerDTO.getEmail());
//            preparedStatement.setInt(4,customerDTO.getContact());
//            preparedStatement.setString(5,customerDTO.getId());
//
//            if (preparedStatement.executeUpdate() > 0) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//}
//
//

package lk.ijse.mini.api;


import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.mini.api.db.DBProcess;
import lk.ijse.mini.api.dto.CustomerDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name ="customer",urlPatterns = "/customer",
        initParams = {
                @WebInitParam(name = "db-user",value = "root"),
                @WebInitParam(name = "db-pw",value = "1234"),
                @WebInitParam(name = "db-url",value = "jdbc:mysql://localhost:3306/javaee?createDatabaseIfNotExist=true"),
                @WebInitParam(name = "db-class",value = "com.mysql.cj.jdbc.Driver")
        }
        ,loadOnStartup = 1
)

public class Customer extends HttpServlet {
    Connection connection;
//    String SAVE_DATA ="INSERT INTO customer (id,name,address,email,contact) VALUES(?,?,?,?,?)" ;
    String GET_DATA ="SELECT * FROM customer WHERE id = ?" ;

    String UPDATE_DATA = "UPDATE customer SET name=?,city=?, email=? WHERE id=?";

    @Override
    public void init() throws ServletException {

        try {
            String user =getServletConfig().getInitParameter("db-user");
            String password =getServletConfig().getInitParameter("db-pw");
            String url =getServletConfig().getInitParameter("db-url");
            Class.forName(getServletConfig().getInitParameter("db-class"));
            this.connection = DriverManager.getConnection(url,user,password);

            System.out.println(user);
            System.out.println(password);
            System.out.println(url);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: 12/2/2023 catch

        if(req.getContentType() != null && req.getContentType().toLowerCase().startsWith("application/json")){
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(),CustomerDTO.class);
            var dbProcess = new DBProcess();
            boolean result = dbProcess.saveCustomer(customerDTO, connection);
            if (result) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("Student information saved successfully.");
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to save student information.");
            }
        }else{
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            System.out.println("hi");
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");

        var dbProcess = new DBProcess();
        CustomerDTO customerDTO = dbProcess.getCustomer(connection , id);
        System.out.println(customerDTO.getName()+"---"+customerDTO.getId());
        Jsonb jsonb = JsonbBuilder.create();
        try {
            var json = jsonb.toJson(customerDTO);
            resp.setContentType("application/json");
            resp.getWriter().write(json);
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
//
//        try {
//            PreparedStatement preparedStatement =connection.prepareStatement(GET_DATA);
//            preparedStatement.setString(1,id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()){
//                String name = resultSet.getString(1);
//                String city = resultSet.getString(2);
//                String email = resultSet.getString(3);
//
//                writer.println(name+" "+city+" "+email);
//            }else {
//                writer.println("not found");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    }
}
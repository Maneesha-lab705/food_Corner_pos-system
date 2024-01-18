package lk.ijse.mini.api.api;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.mini.api.bo.custom.BOImpl.OrderBOImpl;
import lk.ijse.mini.api.bo.custom.OrderBO;
import lk.ijse.mini.api.dto.CustomerDTO;
import lk.ijse.mini.api.dto.OrderDTO;
import lk.ijse.mini.api.dto.OrderDetailDTO;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name ="order",urlPatterns = "/order")

public class PurchaseOrderAPI extends HttpServlet {

    Connection connection;

    OrderBO orderBO = new OrderBOImpl();

//    Logger logger = LoggerFactory.getLogger(CustomerDAOImpl.class);

    @Override
    public void init() throws ServletException {

        try {
            InitialContext ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/posSystem");
            this.connection = pool.getConnection();

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Jsonb jsonb = JsonbBuilder.create();

        JsonObject jsonObject = jsonb.fromJson(req.getReader(), JsonObject.class);

        JsonObject orderDTOJson = jsonObject.getJsonObject("orderDTO");
        OrderDTO orderDTO = jsonb.fromJson(orderDTOJson.toString(), OrderDTO.class);

        JsonArray orderDetailDTOListJson = jsonObject.getJsonArray("orderDetailDTOList");
        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
        for (JsonValue value : orderDetailDTOListJson) {
            OrderDetailDTO orderDetailDTO = jsonb.fromJson(value.toString(), OrderDetailDTO.class);
            orderDetailDTOList.add(orderDetailDTO);
        }

        JsonObject customerDTOJson = jsonObject.getJsonObject("customerDTO");
        CustomerDTO customerDTO = jsonb.fromJson(customerDTOJson.toString(), CustomerDTO.class);
        try {
            boolean isPurchase = orderBO.perchesOrder(orderDTO, orderDetailDTOList, customerDTO.getId(),connection);
            if (isPurchase) {
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to process the order");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing the order");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nextOrderId = orderBO.getOrderId(connection);
        Jsonb jsonb = JsonbBuilder.create();
        try {
            String json = jsonb.toJson(nextOrderId);
            resp.setContentType("application/json");
            resp.getWriter().write(json);
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
    }
}

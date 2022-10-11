package dao.custom;

import dao.CrudDAO;
import entity.OrderDetail;

import javax.json.JsonArray;
import java.sql.Connection;
import java.sql.SQLException;

public interface OrderDetailDAO extends CrudDAO<OrderDetail, String> {
    JsonArray searchOrderDetails(Connection connection,String id) throws SQLException, ClassNotFoundException;
}

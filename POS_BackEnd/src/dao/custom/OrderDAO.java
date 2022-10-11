package dao.custom;

import dao.CrudDAO;
import entity.Order;

import javax.json.JsonObject;
import java.sql.Connection;
import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order, String> {
    JsonObject generateOid(Connection connection) throws SQLException, ClassNotFoundException;
}

package dao.custom;

import dao.CrudDAO;
import entity.Customer;

import javax.json.JsonObject;
import java.sql.Connection;
import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer, String> {
    JsonObject generateId(Connection connection) throws SQLException, ClassNotFoundException;
}

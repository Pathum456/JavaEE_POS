package dao;

import javax.json.JsonArray;
import java.sql.Connection;
import java.sql.SQLException;

public interface CrudDAO<T, ID> extends SuperDAO {
    JsonArray getAll(Connection connection) throws SQLException, ClassNotFoundException;

    boolean add(Connection connection, T t) throws SQLException, ClassNotFoundException;

    boolean update(Connection connection, T t) throws SQLException, ClassNotFoundException;

    boolean delete(Connection connection,ID id) throws SQLException, ClassNotFoundException;

    T search(Connection connection, ID id) throws SQLException, ClassNotFoundException;
}

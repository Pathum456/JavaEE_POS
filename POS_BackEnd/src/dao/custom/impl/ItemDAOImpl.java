package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import entity.Item;

import javax.json.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public JsonArray getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery(connection, "SELECT * FROM Item");
        JsonArrayBuilder itemArray = Json.createArrayBuilder();

        while (rst.next()) {
            Item item = new Item(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getInt(4));
            JsonObjectBuilder itemObj = Json.createObjectBuilder();
            itemObj.add("code", item.getCode());
            itemObj.add("name", item.getName());
            itemObj.add("unitPrice", item.getUnitPrice());
            itemObj.add("qty", item.getQty());
            itemArray.add(itemObj.build());
        }
        return itemArray.build();
    }

    @Override
    public boolean add(Connection connection, Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection, "INSERT INTO Item VALUES (?,?,?,?)", item.getCode(), item.getName(), item.getUnitPrice(), item.getQty());
    }

    @Override
    public boolean update(Connection connection, Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection, "UPDATE Item SET name=?,unitPrice=?,qtyOnHand=? WHERE code=?", item.getName(), item.getUnitPrice(), item.getQty(), item.getCode());
    }

    @Override
    public boolean delete(Connection connection, String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection,"DELETE FROM Item WHERE code=?", code);
    }

    @Override
    public Item search(Connection connection, String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery(connection, "SELECT * FROM Item WHERE code=?", code);
        Item item = null;
        while (rst.next()) {
            item = new Item(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getInt(4));
        }
        return item;
    }

    @Override
    public JsonObject generateCode(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet codeSet = CrudUtil.executeQuery(connection, "SELECT code FROM Item ORDER BY code DESC LIMIT 1");
        JsonObjectBuilder obj = Json.createObjectBuilder();
        if (codeSet.next()) {
            int tempCode = Integer.parseInt(codeSet.getString(1).split("-")[1]);
            tempCode = tempCode + 1;
            if (tempCode <= 9) {
                String code = "I00-000" + tempCode;
                obj.add("code", code);
            } else if (tempCode <= 99) {
                String code = "I00-00" + tempCode;
                obj.add("code", code);
            } else if (tempCode <= 999) {
                String code = "I00-0" + tempCode;
                obj.add("code", code);
            } else if (tempCode <= 9999) {
                String code = "I00-" + tempCode;
                obj.add("code", code);
            }
        } else {
            String code = "I00-0001";
            obj.add("code", code);
        }
        return obj.build();
    }

    @Override
    public boolean updateQty(Connection connection, int qty, String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection, "UPDATE Item SET qtyOnHand=(qtyOnHand-" + qty + ") WHERE code='" + code + "'");
    }
}

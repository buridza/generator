package com.it_academy.by.database.DAO.impl;
import com.it_academy.by.database.DAO.CarDao;
import com.it_academy.by.database.entities.Car;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CarDaoImpl extends AbstractDao implements CarDao {

    private static final CarDaoImpl INSTANCE = new CarDaoImpl();

    private static final String saveProductQuery = "INSERT INTO CARS (SUPPLIER, MODEL, PRICE) VALUES (?, ?, ?)";
    private static final String updateProductQuery = "UPDATE CARS SET SUPPLIER=?, MODEL=?, PRICE=? WHERE ID=?";
    private static final String getProductQuery = "SELECT * FROM CARS WHERE ID=?";
    private static final String getBySupplierQuery = "SELECT * FROM CARS WHERE SUPPLIER=?";
    private static final String deleteProductQuery = "DELETE FROM CARS WHERE ID=?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetBySupplier;
    private PreparedStatement psDelete;

    @Override
    public Car save(Car car) throws SQLException {
        psSave = prepareStatement(saveProductQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, car.getSupplier());
        psSave.setString(2, car.getModel());
        psSave.setDouble(3, car.getPrice());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            car.setId(rs.getLong(1));
        }
        close(rs);
        return car;
    }

    @Override
    public Car get(Serializable id) throws SQLException {
        psGet = prepareStatement(getProductQuery);
        psGet.setLong(1, (long)id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateProduct(rs);
        }
        close(rs);

        return null;
    }

    @Override
    public void update(Car car) throws SQLException {
        psUpdate = prepareStatement(updateProductQuery);
        psUpdate.setLong(4, car.getId());
        psUpdate.setString(1, car.getSupplier());
        psUpdate.setString(2, car.getModel());
        psUpdate.setDouble(3, car.getPrice());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = prepareStatement(deleteProductQuery);
        psDelete.setLong(1, (long)id);
        return psDelete.executeUpdate();
    }

    @Override
    public Car getByModel(String model) throws SQLException {
        psGetBySupplier = prepareStatement(getBySupplierQuery);
        psGetBySupplier.setString(1, model);
        psGetBySupplier.execute();
        ResultSet rs = psGetBySupplier.getResultSet();
        Car car = new Car();
        while (rs.next()) {
            car.setId(rs.getLong(1));
            car.setSupplier(rs.getString(2));
            car.setModel(rs.getString(3));
            car.setPrice(rs.getDouble(4));
        }
        close(rs);
        return car;
    }

    private Car populateProduct(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setId(rs.getLong(1));
        car.setSupplier(rs.getString(2));
        car.setModel(rs.getString(3));
        car.setPrice(rs.getDouble(4));
        return car;
    }

    public static CarDaoImpl getInstance() {
        return INSTANCE;
    }
}

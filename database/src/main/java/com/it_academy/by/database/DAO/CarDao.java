package com.it_academy.by.database.DAO;

import com.it_academy.by.database.entities.Car;

import java.sql.SQLException;

public interface CarDao extends DAO<Car>{
    Car getByModel(String model) throws SQLException;
}

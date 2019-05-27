package com.itacademy.service;

import com.it_academy.by.database.DAO.impl.CarDaoImpl;
import com.it_academy.by.database.entities.Car;

import java.sql.SQLException;

public class UserService {

    private static final UserService INSTANCE = new UserService();

    public final CarDaoImpl carDao = CarDaoImpl.getInstance();

     public Car getByModel(String model){
        try {
            return carDao.getByModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        } return null;
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}

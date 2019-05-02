package com.itacademy.service;

import com.it_academy.by.database.DAO.impl.CarDaoImpl;
import com.it_academy.by.database.entities.Car;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

public class UserService {

    private static final UserService INSTANCE = new UserService();

    public final CarDaoImpl carDao = CarDaoImpl.getInstance();

    public static void save(String model) {
        try(FileOutputStream fos=new FileOutputStream("D://notes.txt"))
        {
            byte[] buffer = model.getBytes();

            fos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

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

    public static void main(String[] args) {
        UserService userService = new UserService();
        try {
            save(userService.carDao.getByModel("Merc").getModel());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

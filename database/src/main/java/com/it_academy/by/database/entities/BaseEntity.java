package com.it_academy.by.database.entities;

import java.io.Serializable;

public interface BaseEntity<T extends Serializable> {

    T getId();
}

SET SEARCH_PATH = rental_company;

DROP TABLE  rental_company.car;
CREATE TABLE car
(
    id BIGSERIAL PRIMARY KEY,
    supplier VARCHAR(30) NOT NULL,
    model VARCHAR(30) NOT NULL,
    max_speed INT DEFAULT 0,
    dollars_per_hour INT NOT NULL DEFAULT 0
);


DROP TABLE rental_company.address;
CREATE TABLE address (
                         id BIGSERIAL PRIMARY KEY,
                         city VARCHAR(64) NOT NULL,
                         street VARCHAR(64) NOT NULL
);

DROP TABLE rental_company.user_;
CREATE TABLE user_ (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(64) NOT NULL,
                       login VARCHAR(64) NOT NULL UNIQUE,
                       password VARCHAR(64) NOT NULL,
                       birth_date date NOT NULL,
                       email VARCHAR(64),
                       phone_number VARCHAR(64),
                       status CHAR(10) DEFAULT 'ACTIVE' NOT NULL,
                       role CHAR(8) DEFAULT 'USER' NOT NULL ,
                       address_id INT REFERENCES address (id)
);

DROP TABLE rental_company.client_order;
CREATE TABLE client_order (
                              id BIGSERIAL PRIMARY KEY,
                              lease_period TIME NOT NULL,
                              passport_details VARCHAR(45) NOT NULL ,
                              status BOOLEAN DEFAULT FALSE,
                              reasons VARCHAR(45) NOT NULL,
                              car_id INT,
                              client_id INT,
                              FOREIGN KEY (car_id) REFERENCES car(id) ON DELETE CASCADE,
                              FOREIGN KEY (client_id) REFERENCES user_(id) ON DELETE CASCADE
);

DROP TABLE passport;
CREATE TABLE passport (
                          id BIGSERIAL PRIMARY KEY,
                          number VARCHAR(20) NOT NULL UNIQUE,
                          user_id INT NOT NULL UNIQUE,
                          FOREIGN KEY (user_id) REFERENCES user_(id) ON DELETE CASCADE
);


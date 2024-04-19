CREATE TABLE Users
(
    id            INT PRIMARY KEY NOT NULL,
    username      VARCHAR(255),
    email_address VARCHAR(255),
    password      VARCHAR(255)
);

CREATE TABLE Merchant
(
    id                INT PRIMARY KEY NOT NULL,
    merchant_name     VARCHAR(255),
    merchant_location VARCHAR(255),
    open              BOOLEAN
);

CREATE TABLE Product
(
    id           INT PRIMARY KEY NOT NULL,
    product_name VARCHAR(255),
    price        DECIMAL(10, 2),
    merchant_id  INT,
    FOREIGN KEY (merchant_id) REFERENCES Merchant (id)
);

CREATE TABLE Order
(
    id                  INT PRIMARY KEY NOT NULL,
    order_time          TIMESTAMP,
    destination_address VARCHAR(255),
    user_id             INT,
    completed           BOOLEAN,
    FOREIGN KEY (user_id) REFERENCES Users (id)
);

CREATE TABLE OrderDetail
(
    id          INT PRIMARY KEY NOT NULL,
    order_id    INT,
    product_id  INT,
    quantity    INT,
    total_price DECIMAL(10, 2),
    FOREIGN KEY (order_id) REFERENCES Order (id),
    FOREIGN KEY (product_id) REFERENCES Product (id)
);
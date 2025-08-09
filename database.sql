CREATE SCHEMA `mini_online_shopping_DB`;

USE `mini_online_shopping_DB`;

CREATE TABLE `users`(
	user_id 	TINYINT PRIMARY KEY AUTO_INCREMENT,
    username 	VARCHAR(50) UNIQUE NOT NULL,
	`password` 	VARCHAR(800) NOT NULL,
    email 		VARCHAR(100) UNIQUE NOT NULL,
    `role` 		ENUM('ADMIN', 'USER') NOT NULL
);

CREATE TABLE `products`(
	product_id 		TINYINT PRIMARY KEY AUTO_INCREMENT,
    product_name 	VARCHAR(500) NOT NULL,
    `description` 	VARCHAR(500),
    price 			INT NOT NULL,
    image_url 		VARCHAR(500) NOT NULL,
    stock 			INT NOT NULL
);

CREATE TABLE `orders`(
	order_id 		TINYINT PRIMARY KEY AUTO_INCREMENT,
    user_id			TINYINT NOT NULL,
	`status`		VARCHAR(50) NOT NULL,
    created_at		DATETIME DEFAULT(CURRENT_TIMESTAMP()),
    constraint FK_USER_ORDER FOREIGN KEY (user_id) REFERENCES `users`(user_id)
);

CREATE TABLE `order_items`(
	order_items_id 	TINYINT PRIMARY KEY AUTO_INCREMENT,
    order_id 		TINYINT NOT NULL,
	product_id 		TINYINT NOT NULL,
    quantity		TINYINT NOT NULL,
    price			INT NOT NULL,
    UNIQUE (order_id, product_id)
)
CREATE DATABASE IF NOT EXISTS fashion_store;
USE fashion_store;

-- 1. Category Master
CREATE TABLE IF NOT EXISTS category_master (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL,
    parent_category_id INT DEFAULT NULL
);

-- 2. Product Master
CREATE TABLE IF NOT EXISTS product_master (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(200) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    unit VARCHAR(50), -- e.g., 'pcs', 'kg'
    discount DECIMAL(5,2) DEFAULT 0.00,
    image VARCHAR(255), -- Path or URL to image
    category_id INT,
    stock INT DEFAULT 0,
    FOREIGN KEY (category_id) REFERENCES category_master(category_id)
);

-- 3. User Master
CREATE TABLE IF NOT EXISTS user_master (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    login_id VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, -- In real app, hash this!
    password_question VARCHAR(255),
    password_answer VARCHAR(255),
    email VARCHAR(100),
    phone VARCHAR(20),
    address TEXT,
    city VARCHAR(100),
    state VARCHAR(100),
    country VARCHAR(100),
    pin VARCHAR(20),
    role VARCHAR(20) DEFAULT 'CUSTOMER' -- 'ADMIN' or 'CUSTOMER'
);

-- 4. Order Master
CREATE TABLE IF NOT EXISTS order_master (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    order_datetime DATETIME DEFAULT CURRENT_TIMESTAMP,
    session_id VARCHAR(100), -- To link with session/user
    user_id INT,             -- Link to registered user if logged in
    payment_mode VARCHAR(50),
    tax DECIMAL(10,2) DEFAULT 0.00,
    total_amount DECIMAL(10,2) NOT NULL,
    order_status VARCHAR(50) DEFAULT 'PENDING',
    FOREIGN KEY (user_id) REFERENCES user_master(user_id)
);

-- 5. Order Details
CREATE TABLE IF NOT EXISTS order_details (
    order_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_id INT,
    product_price DECIMAL(10,2), -- Price at time of purchase
    discount DECIMAL(5,2),       -- Discount at time of purchase
    quantity INT DEFAULT 1,
    FOREIGN KEY (order_id) REFERENCES order_master(order_id),
    FOREIGN KEY (product_id) REFERENCES product_master(product_id)
);

-- 6. Session Tracking (for "Keep a track of every body who visits")
CREATE TABLE IF NOT EXISTS session_tracking (
    id INT AUTO_INCREMENT PRIMARY KEY,
    session_id VARCHAR(100) NOT NULL,
    visit_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    user_agent TEXT,
    ip_address VARCHAR(50)
);

-- DUMMY DATA --

-- Categories
INSERT INTO category_master (category_name, parent_category_id) VALUES 
('Men', NULL),
('Women', NULL),
('Kids', NULL),
('Shirts', 1),
('Dresses', 2);

-- Products
INSERT INTO product_master (product_name, price, unit, discount, image, category_id, stock) VALUES
('Cool T-Shirt', 25.00, 'pcs', 10.00, 'tshirt.jpg', 4, 100),
('Summer Dress', 45.00, 'pcs', 5.00, 'dress.jpg', 5, 50),
('Jeans', 40.00, 'pcs', 0.00, 'jeans.jpg', 1, 80);

-- Admin User (password: admin123)
-- In a real app, use a hashed password.
INSERT INTO user_master (username, login_id, password, password_question, password_answer, role) VALUES
('Administrator', 'admin', 'admin123', 'Favorite Color', 'Red', 'ADMIN');

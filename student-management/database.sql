CREATE DATABASE IF NOT EXISTS student_db;

USE student_db;
CREATE TABLE IF NOT EXISTS departments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    department_code VARCHAR(255) NOT NULL UNIQUE,
    department_name VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS students (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    register_no VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    department_id BIGINT NOT NULL,
    year INT NOT NULL,
    semester INT NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (department_id) REFERENCES departments(id)
);

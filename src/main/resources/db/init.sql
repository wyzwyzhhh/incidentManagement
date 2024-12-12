-- Create database if not exists
CREATE DATABASE IF NOT EXISTS incident_management;
USE incident_management;

-- Create incidents table
CREATE TABLE IF NOT EXISTS incidents (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(20) NOT NULL,
    priority VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create index for common queries
CREATE INDEX idx_status ON incidents(status);
CREATE INDEX idx_priority ON incidents(priority);
CREATE INDEX idx_created_at ON incidents(created_at); 
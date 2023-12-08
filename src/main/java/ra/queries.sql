drop database if exists md05_db;
create database md05_db;
use md05_db;
create table products
(
    id   bigint primary key auto_increment,
    name varchar(255),
    price decimal(10,0),
    description text,
    status bit(1)
);
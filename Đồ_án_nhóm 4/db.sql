drop database bookstore;
create database bookstore;
use bookstore;

CREATE TABLE IF NOT EXISTS roles (
   id INT(11) NOT NULL AUTO_INCREMENT,
   name VARCHAR(30) NOT NULL,
   description VARCHAR(255),
   CONSTRAINT pk_role PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS category (
   id INT(11) NOT NULL AUTO_INCREMENT,
   name VARCHAR(30) NOT NULL,
   description VARCHAR(255),
   CONSTRAINT pk_role PRIMARY KEY(id)
);
create table IF NOT EXISTS users(
	id INT(11) NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL UNIQUE,
    fullname VARCHAR(100),
    password VARCHAR(100),
    phone VARCHAR(20),
    address VARCHAR(255),
    birthday varchar(25),
    role_id INT(11)  NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY(id),
    CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);
create table IF NOT EXISTS author(
	id INT(11) NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    CONSTRAINT pk_author PRIMARY KEY(id)
);


create table IF NOT EXISTS books(
	id INT(11) NOT NULL AUTO_INCREMENT,
    name varchar(255) DEFAULT NULL,
    description varchar(255) DEFAULT NULL,
    price varchar(255) DEFAULT NULL,
    author_id INT(11) NOT NULL,
    category_id INT(11) not null,
    image varchar(255) DEFAULT NULL,
    CONSTRAINT pk_books PRIMARY KEY(id),
	CONSTRAINT fk_books_author FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE CASCADE,
    CONSTRAINT fk_books_category FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
);


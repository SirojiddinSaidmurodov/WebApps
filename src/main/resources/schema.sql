DROP
    DATABASE IF EXISTS webApp;
CREATE
    DATABASE webApp;
USE
    webApp;
CREATE TABLE `user`
(
    id       SERIAL PRIMARY KEY,
    login    varchar(40),
    password varchar(100)
);
INSERT INTO `user`(login, password) value ('admin', 'test');
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
    password varchar(100),
    session  varchar(32) default null
);
CREATE TABLE photos
(
    id      SERIAL PRIMARY KEY,
    user_id BIGINT UNSIGNED,
    `name`  varchar(50),
    photo   LONGBLOB,
    FOREIGN KEY (user_id) REFERENCES user (id)
);
INSERT INTO `user`(login, password) value ('admin', 'test');
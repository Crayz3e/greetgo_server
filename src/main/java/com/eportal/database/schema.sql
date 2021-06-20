DROP TABLE IF EXISTS user_students;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS student;

CREATE TABLE user (
    login VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    PRIMARY KEY (login)
);

CREATE TABLE student (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(20) NOT NULL,
    age FLOAT NOT NULL,
    address VARCHAR(20) NOT NULL,
    phoneNumber VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_students (
    login VARCHAR(20) NOT NULL,
    id INT NOT NULL,
    FOREIGN KEY (id) REFERENCES student(id)
);

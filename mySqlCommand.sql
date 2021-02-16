CREATE SCHEMA `ewatch_data` DEFAULT CHARACTER SET utf8 ;

--create table Users
--=========================
CREATE TABLE `ewatch_data`.`users` (
  `UserName` VARCHAR(50) NOT NULL,
  `UserPassword` VARCHAR(45) NULL,
  `Email` VARCHAR(45) NULL,
  `PathImage` VARCHAR(45) NULL,
  `PhoneNo` VARCHAR(45) NULL,
  `UserRole` VARCHAR(10) NULL,
  PRIMARY KEY (`UserName`));

--create table categories
--=========================
CREATE TABLE `ewatch_data`.`categories` (
  `categoryId` INT NOT NULL,
  `categoryName` VARCHAR(100) NULL,
  PRIMARY KEY (`categoryId`));


ALTER TABLE `ewatch_data`.`categories` 
CHANGE COLUMN `categoryId` `CategoryId` INT(11) NOT NULL ,
CHANGE COLUMN `categoryName` `CategoryName` VARCHAR(100) NULL DEFAULT NULL ;

--create table products
--=========================
CREATE TABLE `ewatch_data`.`products` (
  `ProductId` INT NOT NULL,
  `CategoryId` INT NOT NULL,
  `ProductName` VARCHAR(45) NULL,
  `ProductImage` VARCHAR(45) NULL,
  `ReleaseDate` DATE NULL,
  `UnitPrice` FLOAT NULL,
  `Quantity` INT NULL,
  ` Note` VARCHAR(255) NULL,
  PRIMARY KEY (`ProductId`),
  INDEX `CategoryId_idx` (`CategoryId` ASC),
  CONSTRAINT `CategoryId`
    FOREIGN KEY (`CategoryId`)
    REFERENCES `ewatch_data`.`categories` (`CategoryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

--create table customers
--=========================
CREATE TABLE `ewatch_data`.`customers` (
  `CustomerId` VARCHAR(10) NOT NULL,
  `CustomerPassword` VARCHAR(100) NOT NULL,
  `CustomerName` VARCHAR(45) NOT NULL,
  `CustomerAddress` VARCHAR(45) NULL,
  `PhoneNo` VARCHAR(18) NULL,
  `CustomerStatus` VARCHAR(10) NULL DEFAULT 'Enable',
  PRIMARY KEY (`CustomerId`));

--create table orders
--=========================
CREATE TABLE `ewatch_data`.`orders` (
  `OrderId` INT NOT NULL AUTO_INCREMENT,
  `CustomerId` VARCHAR(45) NULL,
  `OrderDate` DATE NULL,
  `OrderStatus` VARCHAR(45) NULL,
  `DeliveryDate` DATE NULL,
  PRIMARY KEY (`OrderId`),
  INDEX `CustomerId_idx` (`CustomerId` ASC),
  CONSTRAINT `CustomerId`
    FOREIGN KEY (`CustomerId`)
    REFERENCES `ewatch_data`.`customers` (`CustomerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

--create table orderdetails
--=========================
CREATE TABLE `ewatch_data`.`orderdetails` (
  `OrderId` INT NOT NULL,
  `ProductId` INT NOT NULL,
  `Quantity` INT NULL,
  `UnitPrice` FLOAT NULL,
  INDEX `OrderId_idx` (`OrderId` ASC),
  INDEX `ProductId_idx` (`ProductId` ASC),
  CONSTRAINT `OrderId`
    FOREIGN KEY (`OrderId`)
    REFERENCES `ewatch_data`.`orders` (`OrderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ProductId`
    FOREIGN KEY (`ProductId`)
    REFERENCES `ewatch_data`.`products` (`ProductId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



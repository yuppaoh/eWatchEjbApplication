
ALTER TABLE `ewatch_data`.`users` 
RENAME TO  `ewatch_data`.`employees` ;

CREATE TABLE `ewatch_data`.`employees` (
  `UserName` VARCHAR(50) NOT NULL,
  `UserPassword` VARCHAR(45) NULL,  
  `UserRole` VARCHAR(10) NULL,
  PRIMARY KEY (`UserName`));
  
INSERT INTO `ewatch_data`.`employees` (`UserName`, `UserPassword`, `UserRole`) VALUES ('user', '123', 'on');


CREATE TABLE `ewatch_data`.`brands` (
  `BrandId` INT NOT NULL AUTO_INCREMENT,
  `BrandName` VARCHAR(100) NULL,
  PRIMARY KEY (`BrandId`));                            -- sua dong nay
  
INSERT INTO `ewatch_data`.`brands` (`BrandName`) VALUES ('Watchs for Men');
INSERT INTO `ewatch_data`.`brands` (`BrandName`) VALUES ('Watches for Women');
INSERT INTO `ewatch_data`.`brands` (`BrandName`) VALUES ('Watches for Kid');

  
  CREATE TABLE `ewatch_data`.`products` (
  `ProductId` INT NOT NULL AUTO_INCREMENT,
  `BrandId` INT NOT NULL,
  `ProductName` VARCHAR(45) NULL,
  `ProductImage` VARCHAR(45) NULL,
  `ProductType` VARCHAR(45) NULL,
  `UnitPrice` FLOAT NULL,
  `Quantity` INT NULL,
  `Description` Text NULL,
  PRIMARY KEY (`ProductId`),
  INDEX `Brand_idx` (`BrandId` ASC),
  CONSTRAINT `BrandId`
    FOREIGN KEY (`BrandId`)
    REFERENCES `ewatch_data`.`brands` (`BrandId`)      -- sua dong nay
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
INSERT INTO `ewatch_data`.`products` (`BrandId`, `ProductName`, `ProductImage`, `ProductType`, `UnitPrice`, `Quantity`, `Description`) VALUES ('1', 'Smart Watch 1', 'p-1.png', 'Men watch', '329', '5', 'New');
INSERT INTO `ewatch_data`.`products` (`BrandId`, `ProductName`, `ProductImage`, `ProductType`, `UnitPrice`, `Quantity`, `Description`) VALUES ('1', 'Sport Watch 1', 'p-2.png', 'Women watch', '311', '6', 'New');
INSERT INTO `ewatch_data`.`products` (`BrandId`, `ProductName`, `ProductImage`, `ProductType`, `UnitPrice`, `Quantity`, `Description`) VALUES ('1', 'Luxury Watch 1', 'p-3.png', 'Kids watch', '311', '6', 'New');
INSERT INTO `ewatch_data`.`products` (`BrandId`, `ProductName`, `ProductImage`, `ProductType`, `UnitPrice`, `Quantity`, `Description`) VALUES ('1', 'Luxury Watch', 'p-4.png', 'Men watch', '325', '8', 'New');
    
CREATE TABLE `ewatch_data`.`customers` (
  `CustomerId` VARCHAR(20) NOT NULL,
  `CustomerPassword` VARCHAR(100) NOT NULL,
  `CustomerName` VARCHAR(45) NOT NULL,
  `CustomerAddress` VARCHAR(45) NULL,
  `PhoneNo` VARCHAR(18) NULL,
  `CustomerStatus` VARCHAR(10) NULL DEFAULT 'Enable',
  PRIMARY KEY (`CustomerId`));
    
CREATE TABLE `ewatch_data`.`orders` (
  `OrderId` INT NOT NULL AUTO_INCREMENT,
  `CustomerId` VARCHAR(45) NULL,
  `OrderDate` DATE NULL,
  `OrderStatus` VARCHAR(45) NULL,  -- waitting, pending, diliveried, cancel
  `DeliveryDate` DATE NULL,
  `Description` Text NULL,
  PRIMARY KEY (`OrderId`),
  INDEX `CustomerId_idx` (`CustomerId` ASC),
  CONSTRAINT `CustomerId`
    FOREIGN KEY (`CustomerId`)
    REFERENCES `ewatch_data`.`customers` (`CustomerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
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
    
 
CREATE TABLE `ewatch_data`.`comments` (
  `CommentId` INT NOT NULL AUTO_INCREMENT,
  `ProductId` INT NOT NULL,
  `CustomerId` VARCHAR(20) NOT NULL unique,
  `Comment` TEXT NULL,
  PRIMARY KEY (`CommentId`),
  CONSTRAINT `ProductId_Comments`                      -- sua dong nay
    FOREIGN KEY (`ProductId`)
    REFERENCES `ewatch_data`.`products` (`ProductId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CustomerId_Comments`                     -- sua dong nay
    FOREIGN KEY (`CustomerId`)
    REFERENCES `ewatch_data`.`customers` (`CustomerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    

    
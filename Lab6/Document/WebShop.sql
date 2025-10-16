-- Create the WebShop database
CREATE DATABASE WebShop;
GO

-- Use the database
USE WebShop;
GO

-- Drop tables if they exist (to reset)
IF OBJECT_ID('OrderDetails', 'U') IS NOT NULL DROP TABLE OrderDetails;
IF OBJECT_ID('Orders', 'U') IS NOT NULL DROP TABLE Orders;
IF OBJECT_ID('Products', 'U') IS NOT NULL DROP TABLE Products;
IF OBJECT_ID('Categories', 'U') IS NOT NULL DROP TABLE Categories;
IF OBJECT_ID('Accounts', 'U') IS NOT NULL DROP TABLE Accounts;
GO

-- Create Categories table
CREATE TABLE Categories (
    Id NVARCHAR(50) PRIMARY KEY,
    Name NVARCHAR(255) NOT NULL
);
GO

-- Create Accounts table
CREATE TABLE Accounts (
    Username NVARCHAR(50) PRIMARY KEY,
    Password NVARCHAR(255) NOT NULL,
    Fullname NVARCHAR(255),
    Email NVARCHAR(255),
    Photo NVARCHAR(255),
    Activated BIT,
    Admin BIT
);
GO

-- Create Products table
CREATE TABLE Products (
    Id INT IDENTITY PRIMARY KEY,
    Name NVARCHAR(255) NOT NULL,
    Image NVARCHAR(255),
    Price FLOAT,
    Createdate DATE,
    Available BIT,
    Categoryid NVARCHAR(50) FOREIGN KEY REFERENCES Categories(Id)
);
GO

-- Create Orders table
CREATE TABLE Orders (
    Id BIGINT IDENTITY PRIMARY KEY,
    Address NVARCHAR(255),
    Createdate DATE,
    Username NVARCHAR(50) FOREIGN KEY REFERENCES Accounts(Username)
);
GO

-- Create OrderDetails table
CREATE TABLE OrderDetails (
    Id BIGINT IDENTITY PRIMARY KEY,
    Price FLOAT,
    Quantity INT,
    Productid INT FOREIGN KEY REFERENCES Products(Id),
    Orderid BIGINT FOREIGN KEY REFERENCES Orders(Id)
);
GO







-- Sample Categories
INSERT INTO Categories (Id, Name) VALUES
('C01', 'Electronics'),
('C02', 'Books'),
('C03', 'Clothing');
GO

-- Sample Accounts
INSERT INTO Accounts (Username, Password, Fullname, Email, Photo, Activated, Admin) VALUES
('admin', 'adminpass', 'Administrator', 'admin@example.com', 'admin.jpg', 1, 1),
('user1', 'userpass', 'John Doe', 'john@example.com', 'john.jpg', 1, 0);
GO

-- Sample Products
INSERT INTO Products (Name, Image, Price, Createdate, Available, Categoryid) VALUES
('Smartphone', 'smartphone.jpg', 699.99, GETDATE(), 1, 'C01'),
('Laptop', 'laptop.jpg', 1299.99, GETDATE(), 1, 'C01'),
('Novel', 'novel.jpg', 19.99, GETDATE(), 1, 'C02'),
('T-Shirt', 'tshirt.jpg', 9.99, GETDATE(), 1, 'C03'),
('Jeans', 'jeans.jpg', 39.99, GETDATE(), 1, 'C03');
GO

-- Sample Orders
INSERT INTO Orders (Address, Createdate, Username) VALUES
('123 Main St', GETDATE(), 'user1'),
('456 Another Ave', GETDATE(), 'user1');
GO

-- Sample OrderDetails
INSERT INTO OrderDetails (Price, Quantity, Productid, Orderid) VALUES
(699.99, 1, 1, 1),
(19.99, 2, 3, 1),
(9.99, 3, 4, 2);
GO

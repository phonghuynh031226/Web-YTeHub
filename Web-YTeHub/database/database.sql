CREATE DATABASE YTeHub;
GO

USE YTeHub;
GO

CREATE TABLE Users (
    UserID INT IDENTITY(1,1) PRIMARY KEY,
    FullName NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) NOT NULL UNIQUE,
    Phone NVARCHAR(20) NOT NULL UNIQUE,
    Password NVARCHAR(255) NOT NULL,
    Role NVARCHAR(20) NOT NULL DEFAULT 'user',
    AccountStatus NVARCHAR(20) NOT NULL DEFAULT 'active'
        CHECK (AccountStatus IN ('active', 'locked'))
);
GO

CREATE TABLE Addresses (
    AddressID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    ReceiverName NVARCHAR(100) NOT NULL,
    ReceiverPhone NVARCHAR(20) NOT NULL,
    Province NVARCHAR(100) NOT NULL,
    District NVARCHAR(100) NOT NULL,
    Ward NVARCHAR(100) NOT NULL,
    AddressLine NVARCHAR(255) NOT NULL,
    IsDefault BIT NOT NULL DEFAULT 0,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);
GO

CREATE TABLE Categories (
    CategoryID INT IDENTITY(1,1) PRIMARY KEY,
    CategoryName NVARCHAR(100) NOT NULL
);
GO
ALTER TABLE Categories
ADD IsActive BIT NOT NULL DEFAULT 1;

CREATE TABLE Products (
    ProductID INT IDENTITY(1,1) PRIMARY KEY,
    CategoryID INT NOT NULL,
    ProductName NVARCHAR(150) NOT NULL,
    Price DECIMAL(18,2) NOT NULL CHECK (Price >= 0),
    Stock INT NOT NULL DEFAULT 0 CHECK (Stock >= 0),
    ImageURL NVARCHAR(255),
    Description NVARCHAR(500),
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);

  ALTER TABLE Products
ADD CreatedDate DATETIME NOT NULL DEFAULT GETDATE();
GO

CREATE TABLE Cart (
    CartID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    ProductID INT NOT NULL,
    Quantity INT NOT NULL DEFAULT 1 CHECK (Quantity > 0),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
    CONSTRAINT UQ_Cart_User_Product UNIQUE (UserID, ProductID)
);
GO

CREATE TABLE Vouchers (
    VoucherID INT IDENTITY(1,1) PRIMARY KEY,
    Code NVARCHAR(50) NOT NULL UNIQUE,
    DiscountValue DECIMAL(18,2) NOT NULL CHECK (DiscountValue >= 0),
    MinOrderValue DECIMAL(18,2) NOT NULL CHECK (MinOrderValue >= 0),
    ExpiredDate DATE NOT NULL
);
GO

CREATE TABLE Orders (
    OrderID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    AddressID INT NOT NULL,
    VoucherID INT NULL,
    OrderDate DATETIME NOT NULL DEFAULT GETDATE(),
    TotalAmount DECIMAL(18,2) NOT NULL CHECK (TotalAmount >= 0),
    DiscountAmount DECIMAL(18,2) NOT NULL DEFAULT 0 CHECK (DiscountAmount >= 0),
    FinalAmount DECIMAL(18,2) NOT NULL CHECK (FinalAmount >= 0),
    PaymentMethod NVARCHAR(50) NOT NULL,
    OrderStatus NVARCHAR(50) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (AddressID) REFERENCES Addresses(AddressID),
    FOREIGN KEY (VoucherID) REFERENCES Vouchers(VoucherID),
    CONSTRAINT CK_Orders_FinalAmount
        CHECK (FinalAmount = TotalAmount - DiscountAmount),
    CONSTRAINT CK_Orders_Discount_Not_Greater_Than_Total
        CHECK (DiscountAmount <= TotalAmount)
);
GO

CREATE TABLE OrderDetails (
    OrderDetailID INT IDENTITY(1,1) PRIMARY KEY,
    OrderID INT NOT NULL,
    ProductID INT NOT NULL,
    Quantity INT NOT NULL CHECK (Quantity > 0),
    UnitPrice DECIMAL(18,2) NOT NULL CHECK (UnitPrice >= 0),
    TotalPrice DECIMAL(18,2) NOT NULL CHECK (TotalPrice >= 0),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
    CONSTRAINT CK_OrderDetails_TotalPrice
        CHECK (TotalPrice = Quantity * UnitPrice)
);
GO

CREATE TABLE Reviews (
    ReviewID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    ProductID INT NOT NULL,
    Rating INT NOT NULL CHECK (Rating BETWEEN 1 AND 5),
    Comment NVARCHAR(500),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);
GO

INSERT INTO Users (FullName, Email, Phone, Password, Role, AccountStatus)
VALUES
(N'Nguyễn Văn An', 'an@gmail.com', '0901111111', '123456', 'user', 'active'),
(N'Trần Thị Bình', 'binh@gmail.com', '0902222222', '123456', 'user', 'active'),
(N'Lê Quản Trị', 'admin@gmail.com', '0903333333', 'admin123', 'admin', 'active');
GO

INSERT INTO Addresses (UserID, ReceiverName, ReceiverPhone, Province, District, Ward, AddressLine, IsDefault)
VALUES
(1, N'Nguyễn Văn An', '0901111111', N'TP.HCM', N'Quận 1', N'Bến Nghé', N'12 Nguyễn Huệ', 1),
(2, N'Trần Thị Bình', '0902222222', N'Hà Nội', N'Cầu Giấy', N'Dịch Vọng', N'45 Xuân Thủy', 1),
(3, N'Lê Quản Trị', '0903333333', N'Đà Nẵng', N'Hải Châu', N'Thạch Thang', N'99 Bạch Đằng', 1);
GO

INSERT INTO Categories (CategoryName)
VALUES
(N'Máy đo huyết áp'),
(N'Nhiệt kế điện tử'),
(N'Dụng cụ sơ cứu');
GO

INSERT INTO Products (CategoryID, ProductName, Price, Stock, ImageURL, Description)
VALUES
(1, N'Máy đo huyết áp Omron HEM-7120', 850000, 20, 'omron.jpg', N'Máy đo huyết áp điện tử tự động'),
(2, N'Nhiệt kế điện tử Microlife', 120000, 35, 'microlife.jpg', N'Nhiệt kế điện tử cho gia đình'),
(3, N'Băng gạc y tế', 50000, 100, 'banggac.jpg', N'Dụng cụ sơ cứu cơ bản');
GO

INSERT INTO Cart (UserID, ProductID, Quantity)
VALUES
(1, 1, 1),
(1, 3, 2),
(2, 2, 1);
GO

INSERT INTO Vouchers (Code, DiscountValue, MinOrderValue, ExpiredDate)
VALUES
('SALE10', 10000, 100000, '2026-12-31'),
('SALE50', 50000, 500000, '2026-12-31'),
('MEDICAL20', 20000, 200000, '2026-12-31');
GO

INSERT INTO Orders (UserID, AddressID, VoucherID, OrderDate, TotalAmount, DiscountAmount, FinalAmount, PaymentMethod, OrderStatus)
VALUES
(1, 1, 1, GETDATE(), 950000, 10000, 940000, N'COD', N'Pending'),
(2, 2, 3, GETDATE(), 120000, 0, 120000, N'Momo', N'Confirmed'),
(1, 1, NULL, GETDATE(), 100000, 0, 100000, N'Banking', N'Shipping');
GO

INSERT INTO OrderDetails (OrderID, ProductID, Quantity, UnitPrice, TotalPrice)
VALUES
(1, 1, 1, 850000, 850000),
(1, 3, 2, 50000, 100000),
(2, 2, 1, 120000, 120000),
(3, 3, 2, 50000, 100000);
GO

INSERT INTO Reviews (UserID, ProductID, Rating, Comment)
VALUES
(1, 1, 5, N'Sản phẩm dùng rất tốt'),
(2, 2, 4, N'Đo nhanh và chính xác'),
(1, 3, 5, N'Giá rẻ, chất lượng ổn');
GO
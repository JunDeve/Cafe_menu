DROP TABLE cafe_menu

CREATE TABLE cafe_menu (
    menu_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
	 selectedImage VARCHAR(50) NOT NULL,
	 iceOrHot ENUM('ice', 'hot') NOT NULL,
    product_price DECIMAL(10, 2) NOT NULL,
    image_name VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO cafe_menu (product_name, selectedImage, iceOrHot, product_price, image_name)
VALUES ('카페라떼', 'Espresso&Latte', 'ice', 4500, 'latte_image.jpg');

SELECT * FROM cafe_menu

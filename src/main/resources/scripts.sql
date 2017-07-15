INSERT INTO category (category_id, name, parent_category_id) 
	VALUES (0, '', 0);

INSERT INTO image (product_id, name, height, width, type, location) 
	VALUES (0, '', 0, 0, '', '');

INSERT INTO product (product_id, category_id, name, summary, description, price, qty) 
	VALUES (0, 0, '', '', '', '', 0);

INSERT INTO vendor (vendor_id, name, address1, address2, city, state, zip, country, phone, website) 
	VALUES (0, '', '', '', '', '', 0, '', 0, '');

INSERT INTO product_vendor (product_id, vendor_id) 
	VALUES (0, 0);

UPDATE category 
	SET category_id = 0, 
		name = '', 
		parent_category_id = 0 
	WHERE category_id = <condition>;

UPDATE image 
	SET product_id = 0, 
		name = '', 
		height = 0, 
		width = 0, 
		type = '', 
		location = '' 
	WHERE product_id = <condition>;
	
UPDATE product 
	SET product_id = 0, 
		category_id = 0, 
		name = '', 
		summary = '', 
		description = '', 
		price = '', 
		qty = 0 
	WHERE product_id = <condition>;

UPDATE product_vendor 
	SET product_id = 0, 
		vendor_id = 0 
	WHERE product_id = <condition> 
		and vendor_id = <condition>;

UPDATE vendor 
	SET vendor_id = 0, 
		name = '', 
		address1 = '', 
		address2 = '', 
		city = '', 
		state = '', 
		zip = 0, 
		country = '', 
		phone = 0, 
		website = '' 
	WHERE vendor_id = <condition>;

DELETE FROM category 
	WHERE category_id = <condition>;

DELETE FROM image 
	WHERE product_id = <condition>;

DELETE FROM product 
	WHERE product_id = <condition>;

DELETE FROM product_vendor 
	WHERE product_id = <condition> 
		and vendor_id = <condition>;

DELETE FROM vendor 
	WHERE vendor_id = <condition>;

SELECT category_id, name, parent_category_id 
	FROM category
	WHERE category_id = <condition>;
	
SELECT product_id, name, height, width, type, location 
	FROM image
	WHERE image_id = <condition>;

SELECT product_id, category_id, name, summary, description, price, qty 
	FROM product
	WHERE product_id = <condition>;
	
SELECT product_id, vendor_id 
	FROM product_vendor
	WHERE product_id = <condition> 
		and vendor_id = <condition>;
		
SELECT product.*, vendor.*
	FROM product_vendor
	JOIN product
		ON product.product_id = product_vendor.product_id
	JOIN vendor
		ON vendor.vendor_id = product_vendor.vendor_id;
	WHERE product_id = <condition> 
		and vendor_id = <condition>;

SELECT vendor_id, name, address1, address2, city, state, zip, country, phone, website 
	FROM vendor
	WHERE vendor_id = <condition>;
-- Tables creation

CREATE DATABASE IF NOT EXISTS tahiti
CHARACTER SET utf8 COLLATE utf8_bin;


use tahiti;


CREATE TABLE coordinates (
	id_coordinates INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	
	latitude FLOAT NOT NULL,
	longitude FLOAT NOT NULL
)ENGINE=InnoDB,
CHARACTER SET utf8 COLLATE utf8_bin;


CREATE TABLE transport (
	id_transport INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	
	type ENUM('bus', 'boat') NOT NULL,
	price INT NOT NULL,
	is_per_km BOOLEAN 
)ENGINE=InnoDB,
CHARACTER SET utf8 COLLATE utf8_bin;


CREATE TABLE site (
	id_site INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	
	name VARCHAR(100) NOT NULL,
	type ENUM('historic', 'activity') NOT NULL,
	price INT NOT NULL,
	lien_image VARCHAR(100),
	id_coordinates INT NOT NULL,
	FOREIGN KEY (id_coordinates) REFERENCES coordinates(id_coordinates) ON DELETE CASCADE
)ENGINE=InnoDB,
CHARACTER SET utf8 COLLATE utf8_bin;


CREATE TABLE hotel (
	id_hotel INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	
	name VARCHAR(50) NOT NULL,
	price INT NOT NULL,
	beach_name VARCHAR(50),
	lien_image VARCHAR(150),
	id_coordinates INT NOT NULL,
	FOREIGN KEY (id_coordinates) REFERENCES coordinates(id_coordinates) ON DELETE CASCADE
)ENGINE=InnoDB,
CHARACTER SET utf8 COLLATE utf8_bin;


CREATE TABLE ride (
	id_ride INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	
	departure_site INT NOT NULL,
	arrival_site INT NOT NULL,
	id_transport INT NOT NULL,
	
	FOREIGN KEY (departure_site) REFERENCES site(id_site) ON DELETE CASCADE,
	FOREIGN KEY (arrival_site) REFERENCES site(id_site) ON DELETE CASCADE,
	FOREIGN KEY (id_transport) REFERENCES transport(id_transport) ON DELETE CASCADE
)ENGINE=InnoDB,
CHARACTER SET utf8 COLLATE utf8_bin;
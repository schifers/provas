CREATE TABLE person (
	id INT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL
);

CREATE TABLE role (
	id INT AUTO_INCREMENT PRIMARY KEY,
	rolename VARCHAR(50) NOT NULL
);

CREATE TABLE person_role (
	id INT AUTO_INCREMENT PRIMARY KEY,
	person_id INTEGER NOT NULL,
	role_id INTEGER NOT NULL
);

ALTER TABLE person_role ADD CONSTRAINT person_role_person_fk FOREIGN KEY (person_id) REFERENCES person(id);
ALTER TABLE person_role ADD CONSTRAINT person_role_role_fk FOREIGN KEY (role_id) REFERENCES role(id);

CREATE TABLE menu (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL
);

CREATE TABLE menu_item (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	item_type VARCHAR(2),
	action VARCHAR(100),
	url VARCHAR(100),
	icon VARCHAR(100),
	menu_item_id INTEGER
);

ALTER TABLE menu_item ADD CONSTRAINT menu_item_menu_item_fk FOREIGN KEY (menu_item_id) REFERENCES menu_item(id);
ALTER TABLE menu_item ADD CONSTRAINT menu_item_item_type_ck CHECK ('IT','LS');

CREATE TABLE menu_menu_item (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	ordering INT(2) NOT NULL,
	menu_id INTEGER NOT NULL,
	menu_item_id INTEGER NOT NULL
);

ALTER TABLE menu_menu_item ADD CONSTRAINT menu_menu_item_menu_fk FOREIGN KEY (menu_id) REFERENCES menu(id);
ALTER TABLE menu_menu_item ADD CONSTRAINT menu_menu_item_menu_item_fk FOREIGN KEY (menu_item_id) REFERENCES menu_item(id);

CREATE TABLE role_menu_item (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	role_id INTEGER NOT NULL,
	menu_item_id INTEGER NOT NULL
);

ALTER TABLE role_menu_item ADD CONSTRAINT role_menu_item_role_fk FOREIGN KEY (role_id) REFERENCES role(id);
ALTER TABLE role_menu_item ADD CONSTRAINT role_menu_item_menu_item_fk FOREIGN KEY (menu_item_id) REFERENCES menu_item(id);

CREATE TABLE exam (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	exam_date DATETIME
);

CREATE TABLE test (

);
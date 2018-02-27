INSERT INTO provas.menu (name) VALUES ('principal');

INSERT INTO provas.person (username, password) VALUES ('guest', 'guest');

INSERT INTO provas.menu_type (name) VALUES ('ITEM');
INSERT INTO provas.menu_type (name) VALUES ('LIST');

INSERT INTO provas.menu_item (name, action, url, menu_type_id, menu_item_id) VALUES ('Início', NULL, 'index.html', 1, NULL);

INSERT INTO provas.menu_menu_item (ordering, menu_id, menu_item_id) VALUES (1, 1, 1);

INSERT INTO provas.role (rolename) VALUES ('ROLE_GUEST');
INSERT INTO provas.role (rolename) VALUES ('ROLE_USER');
INSERT INTO provas.role (rolename) VALUES ('ROLE_ADMIN');

INSERT INTO provas.role_menu_item (role_id, menu_item_id) VALUES (1, 1);

INSERT INTO provas.role_menu_item (role_id, menu_item_id) VALUES (2, 1);

INSERT INTO provas.role_menu_item (role_id, menu_item_id) VALUES (3, 1);

INSERT INTO provas.person_role (person_id, role_id) VALUES (1, 1);


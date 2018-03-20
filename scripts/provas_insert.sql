INSERT INTO provas.menu (name) VALUES ('principal');

INSERT INTO provas.person (username, password) VALUES ('guest', 'guest');
INSERT INTO provas.person (username, password) VALUES ('user', 'BPiZbadjt6lpsQKO4wB1aerzpjVIbdqyEdUSyFud+Ps=');
INSERT INTO provas.person (username, password) VALUES ('admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=');

INSERT INTO provas.menu_item (name, action, url, icon, item_type, menu_item_id) VALUES ('Início', NULL, '/', 'fa fa-home', 'IT', NULL);
INSERT INTO provas.menu_item (name, action, url, icon, item_type, menu_item_id) VALUES ('Perfil', NULL, '/user/dashboard', 'fa fa-user', 'IT', NULL);
INSERT INTO provas.menu_item (name, action, url, icon, item_type, menu_item_id) VALUES ('Administração', NULL, '/admin/dashboard', 'fa fa-wrench', 'LS', NULL);
INSERT INTO provas.menu_item (name, action, url, icon, item_type, menu_item_id) VALUES ('Menu', NULL, '/admin/menu', 'fa fa-list', 'IT', 3);

INSERT INTO provas.menu_menu_item (ordering, menu_id, menu_item_id) VALUES (1, 1, 1);
INSERT INTO provas.menu_menu_item (ordering, menu_id, menu_item_id) VALUES (2, 1, 2);
INSERT INTO provas.menu_menu_item (ordering, menu_id, menu_item_id) VALUES (3, 1, 3);
INSERT INTO provas.menu_menu_item (ordering, menu_id, menu_item_id) VALUES (1, 1, 4);

INSERT INTO provas.role (rolename) VALUES ('ROLE_GUEST');
INSERT INTO provas.role (rolename) VALUES ('ROLE_USER');
INSERT INTO provas.role (rolename) VALUES ('ROLE_ADMIN');

INSERT INTO provas.role_menu_item (role_id, menu_item_id) VALUES (1, 1);

INSERT INTO provas.role_menu_item (role_id, menu_item_id) VALUES (2, 1);
INSERT INTO provas.role_menu_item (role_id, menu_item_id) VALUES (2, 2);

INSERT INTO provas.role_menu_item (role_id, menu_item_id) VALUES (3, 1);
INSERT INTO provas.role_menu_item (role_id, menu_item_id) VALUES (3, 2);
INSERT INTO provas.role_menu_item (role_id, menu_item_id) VALUES (3, 3);
INSERT INTO provas.role_menu_item (role_id, menu_item_id) VALUES (3, 4);

INSERT INTO provas.person_role (person_id, role_id) VALUES (1, 1);
INSERT INTO provas.person_role (person_id, role_id) VALUES (2, 2);
INSERT INTO provas.person_role (person_id, role_id) VALUES (3, 2);
INSERT INTO provas.person_role (person_id, role_id) VALUES (3, 3);


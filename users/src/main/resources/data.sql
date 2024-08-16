INSERT IGNORE INTO permissions (permission_name) values ("READ"), ("CREATE"), ("UPDATE"), ("DELETE");
INSERT IGNORE INTO roles (role_name) values ("ADMIN"), ("USER");
INSERT IGNORE INTO roles_permissions (permission_id, role_id) values (1,1), (2,1), (3,1), (4,1), (1,2);
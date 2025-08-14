DELETE FROM users;
DELETE FROM inventories;
DELETE FROM user_inventory;

ALTER SEQUENCE users_id_seq RESTART WITH 1;
ALTER SEQUENCE inventories_id_seq RESTART WITH 1;

INSERT INTO users (username, email, password, role)
VALUES ('test', 'test@mail.ru', '$2a$12$d/kWeVWU8GhpWl7RkBIfaeQoBGJeyCxzshqoDU2frhtY2JWvZHjlG', 'USER');

INSERT INTO inventories (name)
VALUES ('item 1'),
       ('item 2'),
       ('item 3'),
       ('item 4');

INSERT INTO user_inventory (user_id, inventory_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4);
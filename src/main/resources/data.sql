DELETE
FROM users;
DELETE
FROM inventories;
DELETE
FROM collections;
DELETE
FROM user_inventory;
DELETE
FROM user_collection;
DELETE
FROM rewards;
DELETE
FROM daily_tasks;

ALTER SEQUENCE users_id_seq RESTART WITH 1;
ALTER SEQUENCE collections_id_seq RESTART WITH 1;
ALTER SEQUENCE inventories_id_seq RESTART WITH 1;
ALTER SEQUENCE user_collection_id_seq RESTART WITH 1;
ALTER SEQUENCE rewards_id_seq RESTART WITH 1;
ALTER SEQUENCE daily_tasks_id_seq RESTART WITH 1;

INSERT INTO users (username, email, password, role, gold, mana)
VALUES ('test',
        'test@mail.ru',
        '$2a$12$d/kWeVWU8GhpWl7RkBIfaeQoBGJeyCxzshqoDU2frhtY2JWvZHjlG',
        'USER',
        '200',
        '300');

INSERT INTO inventories (name, image)
VALUES ('item 1', 'item1.png'),
       ('item 2', 'item2.png'),
       ('item 3', 'item1.png'),
       ('item 4', 'item2.png'),
       ('item 5', 'item1.png'),
       ('item 6', 'item2.png'),
       ('item 7', 'item1.png'),
       ('item 8', 'item2.png');

INSERT INTO collections (name, image)
VALUES ('collection 1', 'collection_1.png'),
       ('collection 2', 'collection_2.png'),
       ('collection 3', 'collection_3.png'),
       ('collection 4', 'collection_4.png'),
       ('collection 5', 'collection_5.png'),
       ('collection 6', 'collection_6.png'),
       ('collection 7', 'collection_7.png'),
       ('collection 8', 'collection_8.png'),
       ('collection 9', 'collection_9.png'),
       ('collection 10', 'collection_10.png'),
       ('collection 11', 'collection_11.png'),
       ('collection 12', 'collection_12.png');

INSERT INTO user_inventory (user_id, inventory_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8);

INSERT INTO user_collection (user_id, collection_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8),
       (1, 9),
       (1, 10),
       (1, 11),
       (1, 12);


INSERT INTO rewards (gold, mana, inventory_id, amount_inventory_items)
VALUES (200, 100, 1, 1);

INSERT INTO daily_tasks(name, description, type_task, target, reward_id)
VALUES ('daily login', 'daily login desc', 'LOGIN', 1, 1);
--
-- INSERT INTO user_task_daily (user_id, daily_task_id, progress, completed)
-- VALUES (1, 1, 0, false);

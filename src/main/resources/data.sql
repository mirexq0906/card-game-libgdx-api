DELETE
FROM app_user;
DELETE
FROM inventory;
DELETE
FROM equipment;
DELETE
FROM crystal;
DELETE
FROM character_template;
DELETE
FROM character_instance;
DELETE
FROM user_inventory;
DELETE
FROM reward;
DELETE
FROM daily_task;

ALTER SEQUENCE app_user_id_seq RESTART WITH 1;
ALTER SEQUENCE inventory_id_seq RESTART WITH 1;
-- ALTER SEQUENCE equipment_id_seq RESTART WITH 1;
-- ALTER SEQUENCE crystal_id_seq RESTART WITH 1;
ALTER SEQUENCE character_template_id_seq RESTART WITH 1;
ALTER SEQUENCE character_instance_id_seq RESTART WITH 1;
ALTER SEQUENCE reward_id_seq RESTART WITH 1;
ALTER SEQUENCE daily_task_id_seq RESTART WITH 1;

INSERT INTO app_user (username, email, password, role, gold, mana)
VALUES ('test',
        'test@mail.ru',
        '$2a$12$d/kWeVWU8GhpWl7RkBIfaeQoBGJeyCxzshqoDU2frhtY2JWvZHjlG',
        'USER',
        '200',
        '300');

INSERT INTO inventory (name, image, type, description)
VALUES ('item 1', 'item1.png', 'EQUIPMENT', 'description'),
       ('item 2', 'item2.png', 'EQUIPMENT', 'description'),
       ('item 3', 'item3.png', 'CRYSTAL', 'description');

INSERT INTO equipment (id, slot_type, power, hp)
VALUES (1, 'WEAPON', 20, 20),
       (2, 'ARMOR', 10, 20);

INSERT INTO crystal (id)
VALUES (3);

INSERT INTO character_template (name, image, base_hp, base_auto_attack)
VALUES ('collection 1', 'collection_1.png', 100, 10),
       ('collection 2', 'collection_2.png', 150, 15);

INSERT INTO character_instance (level, template_id, user_id)
VALUES (1, 1, 1),
       (1, 2, 1);

INSERT INTO user_inventory (user_id, inventory_id)
VALUES (1, 1),
       (1, 2),
       (1, 3);

INSERT INTO reward (gold, mana, inventory_id, amount_inventory_items)
VALUES (200, 100, 1, 1);

INSERT INTO daily_task(name, description, type_task, target, reward_id)
VALUES ('daily login', 'daily login desc', 'LOGIN', 1, 1);

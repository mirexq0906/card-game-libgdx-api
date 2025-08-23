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
       ('item 3', 'item3.png', 'EQUIPMENT', 'description'),
       ('item 4', 'item3.png', 'CRYSTAL', 'description'),
       ('item 5', 'item3.png', 'CRYSTAL', 'description');

INSERT INTO equipment (id, slot_type, power, hp)
VALUES (1, 'WEAPON', 20, 20),
       (2, 'ARMOR', 10, 30),
       (3, 'ARMOR', 20, 30),
       (4, 'ARMOR', 50, 50);

INSERT INTO crystal (id)
VALUES (5);

INSERT INTO user_inventory (user_id, inventory_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5);

INSERT INTO character_template (name, image, base_hp, base_auto_attack, base_speed)
VALUES ('collection 1', 'collection_1.png', 100, 10, 1),
       ('collection 2', 'collection_2.png', 150, 15, 1),
       ('collection 3', 'collection_3.png', 100, 10, 1);

INSERT INTO character_instance (level, template_id, user_id, fraction_type, rarity_type)
VALUES (1, 1, 1, 'WATER', 'UNCOMMON'),
       (1, 3, 1, 'WATER', 'UNCOMMON'),
       (1, 2, 1, 'FIRE', 'LEGEND');

INSERT INTO reward (gold, mana, inventory_id, amount_inventory_items)
VALUES (200, 100, 1, 1);

INSERT INTO daily_task(name, description, type_task, target, reward_id)
VALUES ('daily login', 'daily login desc', 'LOGIN', 1, 1);

INSERT INTO set_bonus(set_name, pieces_required, stat_type, bonus_value)
VALUES ('dragon set easy', 1, 'ATTACK', 20),
       ('dragon set hard', 4, 'HP', 30);

INSERT INTO equipment_set_bonus(equipment_id, set_bonus_id)
VALUES (1, 1),
       (1, 2);

INSERT INTO battle_mode(name, mode, image)
VALUES ('Campaign', 'CAMPAIGN', 'campaign.jpg'),
       ('Danger', 'DANGER', 'danger.jpg');

INSERT INTO battle_task(name, battle_mode_id, reward_id)
VALUES ('task 1', 1, 1);


INSERT INTO enemy(name, image, hp, power, fraction_type, rarity_type, speed)
VALUES ('enemy 1', 'collection_1.png', 100, 50, 'WATER', 'UNCOMMON', 1),
       ('enemy 2', 'collection_2.png', 110, 40, 'WATER', 'UNCOMMON', 1),
       ('enemy 3', 'collection_3.png', 120, 30, 'WATER', 'UNCOMMON', 1);

INSERT INTO battle_task_enemy(battle_task_id, enemy_id)
VALUES (1, 1),
       (1, 2),
       (1, 3);

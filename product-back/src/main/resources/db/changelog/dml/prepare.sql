-- liquibase formatted sql

-- changeset grabovsky.alexey:add_category_in_db
insert into categories (name)
values ('Food');

-- changeset grabovsky.alexey:add_product_in_db
insert into products (title, description, price, rating, category_id)
values ('Milk', 'Fresh Milk', 98.02, 0, 1),
('Bread', 'Fresh Bread', 23.07, 0, 1);

-- changeset grabovsky.alexey:add_user_in_db
INSERT INTO users (email, password, username, enabled, activated, activation_code)
VALUES ('user@test.ru', '$2a$12$0lCh0ZBnMJzs0gnluRi1q.00DLal0ILpBWg7xUPlfYv7aKdMQUvPW', 'user', true, true,
        'dsfafdas9fdsa9f-sadfsa8f9asdf-asdf');


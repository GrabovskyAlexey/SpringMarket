DROP TABLE IF EXISTS categories cascade ;
DROP TABLE IF EXISTS products cascade;

CREATE TABLE IF NOT EXISTS categories
(
    id         bigserial PRIMARY KEY NOT NULL,
    name       varchar(250)          NOT NULL,
    created_at timestamp DEFAULT (current_timestamp),
    updated_at timestamp DEFAULT (current_timestamp)
);

CREATE TABLE IF NOT EXISTS products
(
    id          bigserial PRIMARY KEY NOT NULL,
    title       varchar(250)          NOT NULL,
    description text,
    price       numeric(10,2),
    rating      numeric(3,2),
    category_id bigint                NOT NULL,
    created_at  timestamp DEFAULT (current_timestamp),
    updated_at  timestamp DEFAULT (current_timestamp)
);

ALTER TABLE products ADD CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE;

insert into categories (name)
values ('Food');

insert into products (title, description, price, rating, category_id)
values ('Milk', 'Fresh Milk', 98.02, 0, 1),
       ('Bread', 'Fresh Bread', 23.07, 0, 1);
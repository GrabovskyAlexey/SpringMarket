-- liquibase formatted sql

-- changeset grabovsky.alexey:add_product_table
CREATE TABLE "users"
(
    "id"              bigserial PRIMARY KEY NOT NULL,
    "username"        varchar(250) UNIQUE   NOT NULL,
    "email"           varchar(250) UNIQUE   NOT NULL,
    "password"        varchar(250)          NOT NULL,
    "enabled"         boolean               NOT NULL,
    "activated"       boolean               NOT NULL,
    "activation_code" varchar(250)          NOT NULL,
    "created_at"      timestamp DEFAULT (current_timestamp),
    "updated_at"      timestamp DEFAULT (current_timestamp)
);
COMMENT ON TABLE "users" IS 'Таблица пользователей';

CREATE TABLE "profiles"
(
    "user_id"    bigint       NOT NULL,
    "name"       varchar(250) NOT NULL,
    "surname"    varchar(250) NOT NULL,
    "phone"      varchar(250),
    "city"       varchar(250),
    "birthday"   date,
    "reg_date"   date,
    "created_at" timestamp DEFAULT (current_timestamp),
    "updated_at" timestamp DEFAULT (current_timestamp),
    CONSTRAINT fk_profile_user FOREIGN KEY ("user_id") REFERENCES "users" ("id")
);
COMMENT ON TABLE "profiles" IS 'Данные пользователя';

CREATE TABLE "delivery_addresses"
(
    "id"         bigserial PRIMARY KEY NOT NULL,
    "user_id"    bigint                NOT NULL,
    "country"    varchar(250)          NOT NULL,
    "city"       varchar(250)          NOT NULL,
    "region"     varchar(250),
    "street"     varchar(250),
    "house"      varchar(50),
    "flat"       int,
    "created_at" timestamp DEFAULT (current_timestamp),
    "updated_at" timestamp DEFAULT (current_timestamp),
    CONSTRAINT fk_user_delivery_address FOREIGN KEY ("user_id") REFERENCES "users" ("id")
);
COMMENT ON TABLE "delivery_addresses" IS 'Таблица для адресов доставки';

CREATE TABLE "roles"
(
    "id"          bigserial PRIMARY KEY NOT NULL,
    "role"        varchar(250)          NOT NULL,
    "description" varchar(250)          NOT NULL
);
COMMENT ON TABLE "roles" IS 'Таблица для ролей позльзователей';

CREATE TABLE "authorities"
(
    "id"          bigserial PRIMARY KEY NOT NULL,
    "authority"   varchar(250)          NOT NULL,
    "description" varchar(250)          NOT NULL
);
COMMENT ON TABLE "authorities" IS 'Таблица прав для пользователей и ролей';

CREATE TABLE "users_roles"
(
    "user_id" bigint NOT NULL,
    "role_id" bigint NOT NULL,
    PRIMARY KEY ("user_id", "role_id"),
    CONSTRAINT fk_user_role FOREIGN KEY ("user_id") REFERENCES "users" ("id"),
    CONSTRAINT fk_role_user FOREIGN KEY ("role_id") REFERENCES "roles" ("id")
);
COMMENT ON TABLE "users_roles" IS 'ManyToMany для связи пользователей и ролей';

CREATE TABLE "roles_authorities"
(
    "authority_id" bigint NOT NULL,
    "role_id"      bigint NOT NULL,
    PRIMARY KEY ("authority_id", "role_id"),
    CONSTRAINT fk_authority_role FOREIGN KEY ("authority_id") REFERENCES "authorities" ("id"),
    CONSTRAINT fk_role_authority FOREIGN KEY ("role_id") REFERENCES "roles" ("id")
);
COMMENT ON TABLE "roles_authorities" IS 'ManyToMany для связи ролей и прав';

CREATE TABLE "categories"
(
    "id"         bigserial PRIMARY KEY NOT NULL,
    "name"       varchar(250)          NOT NULL,
    "created_at" timestamp DEFAULT (current_timestamp),
    "updated_at" timestamp DEFAULT (current_timestamp)
);
COMMENT ON TABLE "categories" IS 'Таблица для категорий';

CREATE TABLE "products"
(
    "id"          bigserial PRIMARY KEY NOT NULL,
    "title"       varchar(250)          NOT NULL,
    "description" text,
    "price"       numeric(10, 2),
    "rating"      numeric(3, 2),
    "category_id" bigint                NOT NULL,
    "created_at"  timestamp DEFAULT (current_timestamp),
    "updated_at"  timestamp DEFAULT (current_timestamp),
    CONSTRAINT fk_product_category FOREIGN KEY ("category_id") REFERENCES "categories" ("id")
);
COMMENT ON TABLE "products" IS 'Таблица для продуктов';

CREATE TABLE "product_images"
(
    "id"         bigserial PRIMARY KEY NOT NULL,
    "product_id" bigint                NOT NULL,
    "image_url"  varchar(250)          NOT NULL,
    "created_at" timestamp DEFAULT (current_timestamp),
    "updated_at" timestamp DEFAULT (current_timestamp),
    CONSTRAINT fk_product_images FOREIGN KEY ("product_id") REFERENCES "products" ("id")
);
COMMENT ON TABLE "product_images" IS 'Таблица для изображений продуктов';

CREATE TABLE "reviews"
(
    "id"          bigserial PRIMARY KEY NOT NULL,
    "author_id"   bigint                NOT NULL,
    "product_id"  bigint                NOT NULL,
    "rating"      int,
    "review_text" text,
    "created_at"  timestamp DEFAULT (current_timestamp),
    "updated_at"  timestamp DEFAULT (current_timestamp),
    CONSTRAINT fk_review_author FOREIGN KEY ("author_id") REFERENCES "users" ("id"),
    CONSTRAINT fk_review_product FOREIGN KEY ("product_id") REFERENCES "products" ("id")
);
COMMENT ON TABLE "reviews" IS 'Таблица для отзывов';


CREATE TABLE "orders"
(
    "id"           bigserial PRIMARY KEY NOT NULL,
    "user_id"      bigint                NOT NULL,
    "address_id"   bigint                NOT NULL,
    "order_status" text                  NOT NULL,
    "created_at"   timestamp DEFAULT (current_timestamp),
    "updated_at"   timestamp DEFAULT (current_timestamp),
    CONSTRAINT fk_user_order FOREIGN KEY ("user_id") REFERENCES "users" ("id"),
    CONSTRAINT fk_order_address FOREIGN KEY ("address_id") REFERENCES "delivery_addresses" ("id")
);
COMMENT ON TABLE "orders" IS 'Таблица для заказов';

CREATE TABLE "items"
(
    "id"         bigserial PRIMARY KEY NOT NULL,
    "product_id" bigint                NOT NULL,
    "order_id"   bigint                NOT NULL,
    "count"      int                   NOT NULL,
    "price"      numeric(10, 2)        NOT NULL,
    "created_at" timestamp DEFAULT (current_timestamp),
    "updated_at" timestamp DEFAULT (current_timestamp),
    CONSTRAINT fk_product_item FOREIGN KEY ("product_id") REFERENCES "products" ("id"),
    CONSTRAINT fk_item_order FOREIGN KEY ("order_id") REFERENCES "orders" ("id")
);
COMMENT ON TABLE "items" IS 'Таблица для элементов заказа';
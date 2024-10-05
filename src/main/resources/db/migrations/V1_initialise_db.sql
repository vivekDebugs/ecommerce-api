CREATE TABLE IF NOT EXISTS category (
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(100),
    description VARCHAR(100),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    is_deleted BOOLEAN,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS  product (
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(100),
    description VARCHAR(100),
    price DOUBLE,
    image_url VARCHAR(100),
    category_id BIGINT NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    is_deleted BOOLEAN,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE IF NOT EXISTS category_featured_products (
    category_id BIGINT,
    product_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT NOT NULL,
    email VARCHAR(100),
    password VARCHAR(500),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    is_deleted BOOLEAN
);

CREATE TABLE IF NOT EXISTS fakestore_order (
    id BIGINT AUTO_INCREMENT NOT NULL,
    user_id BIGINT NOT NULL,
    fakestore_product_id BIGINT NOT NULL,
    quantity INTEGER,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    is_deleted BOOLEAN,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id)
)
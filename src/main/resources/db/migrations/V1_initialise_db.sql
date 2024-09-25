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
)
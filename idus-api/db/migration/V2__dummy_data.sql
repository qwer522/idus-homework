INSERT INTO idus_member(id, password, gender, name, alias, email, phone, role, last_login_date)
VALUES (1, '1234', 'MAN', 'user1', 'aliasuser1', 'email1@gmail.com',
        '01011112222', 'USER', '2021-08-07 13:45:57.406384');
INSERT INTO idus_member(id, password, gender, name, alias, email, phone, role, last_login_date)
VALUES (2, '1234', 'MAN', 'user2', 'aliasuser2', 'email2@gmail.com',
        '01011113333', 'USER', '2021-08-07 13:45:57.406384');
INSERT INTO idus_member(id, password, gender, name, alias, email, phone, role, last_login_date)
VALUES (3, '1234', 'WOMAN', 'user3', 'aliasuser3', 'email3@gmail.com',
        '01011114444', 'USER', '2021-08-07 13:45:57.406384');
INSERT INTO idus_member(id, password, gender, name, alias, email, phone, role, last_login_date)
VALUES (4, '1234', 'MAN', 'user4', 'aliasuser4', 'email4@gmail.com',
        '01011115555', 'USER', '2021-08-07 13:45:57.406384');
INSERT INTO idus_member(id, password, gender, name, alias, email, phone, role, last_login_date)
VALUES (5, '1234', 'UNKNOWN', 'user5', 'aliasuser5', 'email5@gmail.com',
        '01011116666', 'USER', '2021-08-07 13:45:57.406384');

INSERT INTO idus_order(id, order_key, orderer_id, status, created_by, created_date,
                       last_modified_by, last_modified_date)
VALUES (1, 'ORDER1', 1, 'ORDERED', 'user1', '2021-08-07 13:45:57.406384', 'user1',
        '2021-08-07 13:45:57.406384');
INSERT INTO idus_order(id, order_key, orderer_id, status, created_by, created_date,
                       last_modified_by, last_modified_date)
VALUES (2, 'ORDER2', 1, 'PAYMENTED', 'user1', '2021-07-07 13:45:57.406384', 'user1',
        '2021-07-07 13:45:57.406384');
INSERT INTO idus_order(id, order_key, orderer_id, status, created_by, created_date,
                       last_modified_by, last_modified_date)
VALUES (3, 'ORDER3', 2, 'ORDERED', 'user2', '2021-08-07 13:45:57.406384', 'user2',
        '2021-08-07 13:45:57.406384');

INSERT INTO idus_order_detail(id, order_id, amount, created_by, created_date,
                              last_modified_by, last_modified_date)
VALUES (1, 1, 20000, 'user1', '2021-08-07 13:45:57.406384', 'user1',
        '2021-08-07 13:45:57.406384');
INSERT INTO idus_order_detail(id, order_id, amount, created_by, created_date,
                              last_modified_by, last_modified_date)
VALUES (2, 2, 90000, 'user1', '2021-07-07 13:45:57.406384', 'user1',
        '2021-07-07 13:45:57.406384');
INSERT INTO idus_order_detail(id, order_id, amount, created_by, created_date,
                              last_modified_by, last_modified_date)
VALUES (3, 3, 30000, 'user1', '2021-08-07 13:45:57.406384', 'user1',
        '2021-08-07 13:45:57.406384');

INSERT INTO idus_ordered_product(id, order_id, product_id, product_name, sale_price, quantity)
VALUES (1, 1, 1, '1번 상품', 20000, 1);
INSERT INTO idus_ordered_product(id, order_id, product_id, product_name, sale_price, quantity)
VALUES (2, 2, 2, '2번 상품', 90000, 1);
INSERT INTO idus_ordered_product(id, order_id, product_id, product_name, sale_price, quantity)
VALUES (3, 3, 3, '3번 상품', 30000, 1);
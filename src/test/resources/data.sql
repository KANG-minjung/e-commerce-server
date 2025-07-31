-- 1. 상품 등록 (ITEM)
INSERT INTO tbl_item (id, item_nm, price, update_date)
VALUES (1, '셔츠', 15000, CURRENT_TIMESTAMP),
       (2, '바지', 20000, CURRENT_TIMESTAMP);

-- 2. 상품 옵션 등록 (ITEM_OPTION)
INSERT INTO tbl_item_option (id, item_id, size, color)
VALUES (1, 1, 'M', 'White'),
       (2, 1, 'L', 'Black'),
       (3, 2, 'M', 'Blue');

-- 3. 상품 재고 등록 (ITEM_STOCK)
INSERT INTO tbl_item_stock (id, item_option_id, quantity)
VALUES (1, 1, 30),
       (2, 2, 10),
       (3, 3, 50);

-- USER
INSERT INTO tbl_user (id, user_nm, balance, update_date)
VALUES (1, '테스트유저', 100000, CURRENT_TIMESTAMP);

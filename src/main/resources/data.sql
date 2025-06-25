insert into products (code, description, product_type, supplier_price, stock_quantity, is_available) values ( 'axp-123', 'Smartphone', 0, 30.1, 100, true);
insert into products (code, description, product_type, supplier_price, stock_quantity, is_available) values ( 'asdf', 'GPU Nvidia', 0, 42, 3, true);
insert into products (code, description, product_type, supplier_price, stock_quantity, is_available) values ( 'abcd33', 'Wash machine', 1, 44, 20, true);
insert into products (code, description, product_type, supplier_price, stock_quantity, is_available) values ( 'asert-x03', 'Couch', 2, 405, 20, true);

insert into stock_history (product_id, movement, sell_value, sell_date, sell_quantity, total_value, is_canceled) values (1, 0, 40.5, '2025-06-25 14:21:54.617341', 10, 405, false);
insert into stock_history (product_id, movement, sell_value, sell_date, sell_quantity, total_value, is_canceled) values (1, 1, 40.5, '2025-06-25 14:21:54.617341', 100, 4050, false);
insert into stock_history (product_id, movement, sell_value, sell_date, sell_quantity, total_value, is_canceled) values (2, 0, 40.5, '2025-06-25 14:21:54.617341', 10, 405, false);
insert into stock_history (product_id, movement, sell_value, sell_date, sell_quantity, total_value, is_canceled) values (3, 0, 40.5, '2025-06-25 14:21:54.617341', 10, 405, false);
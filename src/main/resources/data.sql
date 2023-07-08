INSERT INTO dbcow.user
(id, createDate, deleteFlag, updateDate, password, roles, username)
VALUES
(1, '2023-01-01', 0, '2023-01-01', '{noop}password', 'ROLE_USER', 'user1')
,(2, '2023-01-01', 0, '2023-01-01', '{noop}password', 'ROLE_ADMIN', 'user2')
,(3, '2023-01-01', 1, '2023-01-01', '{noop}password', 'ROLE_USER', 'user3')
,(4, '2023-01-01', 1, '2023-01-01', '{noop}password', 'ROLE_ADMIN', 'user4');

INSERT INTO dbcow.setting
(id, candidate, createDate, deleteFlag, name, `type`, updateDate, username, value)
VALUES
(1, 'あ,い,う,え,お', '2023-01-01', 0, 'リストテストリストテストリストテストリストテストリストテストリストテスト', 'list', '2023-01-01', 'user1', 'い')
,(2, 'あ,い,う,え,お', '2023-01-01', 0, 'リストテストリストテストリストテストリストテストリストテストリストテスト', 'list', '2023-01-01', 'user2', 'い')
,(3, 'あ,い,う,え,お', '2023-01-01', 1, 'リストテストリストテストリストテストリストテストリストテストリストテスト', 'list', '2023-01-01', 'user3', 'い')
,(4, 'あ,い,う,え,お', '2023-01-01', 1, 'リストテストリストテストリストテストリストテストリストテストリストテスト', 'list', '2023-01-01', 'user4', 'い')
,(5, NULL, '2023-01-01', 0, '文字列テスト文字列テスト文字列テスト文字列テスト文字列テスト文字列テスト文字列テスト', 'str', '2023-01-01', 'user1', NULL)
,(6, NULL, '2023-01-01', 0, '文字列テスト文字列テスト文字列テスト文字列テスト文字列テスト文字列テスト文字列テスト', 'str', '2023-01-01', 'user2', NULL)
,(7, NULL, '2023-01-01', 0, '数字テスト数字テスト数字テスト数字テスト数字テスト数字テスト数字テスト数字テスト', 'int', '2023-01-01', 'user1', '1')
,(8, NULL, '2023-01-01', 0, '数字テスト数字テスト数字テスト数字テスト数字テスト数字テスト数字テスト数字テスト', 'int', '2023-01-01', 'user2', '1')
,(9, NULL, '2023-01-01', 0, '日時テスト日時テスト日時テスト日時テスト日時テスト日時テスト日時テスト日時テスト', 'datetime', '2023-01-01', 'user1', '2023-01-01T12:30')
,(10, NULL, '2023-01-01', 0, '日時テスト日時テスト日時テスト日時テスト日時テスト日時テスト日時テスト日時テスト', 'datetime', '2023-01-01', 'user2', '2023-01-01T12:30')
,(11, NULL, '2023-01-01', 0, '日付テスト日付テスト日付テスト日付テスト日付テスト日付テスト日付テスト日付テスト', 'date', '2023-01-01', 'user1', '2023-01-01')
,(12, NULL, '2023-01-01', 0, '日付テスト日付テスト日付テスト日付テスト日付テスト日付テスト日付テスト日付テスト', 'date', '2023-01-01', 'user2', '2023-01-01')
,(13, NULL, '2023-01-01', 0, 'トグルテストトグルテストトグルテストトグルテストトグルテストトグルテストトグルテスト', 'toggle', '2023-01-01', 'user1', 'true')
,(14, NULL, '2023-01-01', 0, 'トグルテストトグルテストトグルテストトグルテストトグルテストトグルテストトグルテスト', 'toggle', '2023-01-01', 'user2', 'true')
,(15, 'あ,い,う,え,お', '2023-01-01', 0, 'ラジオテストラジオテストラジオテストラジオテストラジオテストラジオテストラジオテスト', 'radio', '2023-01-01', 'user1', 'い')
,(16, 'あ,い,う,え,お', '2023-01-01', 0, 'ラジオテストラジオテストラジオテストラジオテストラジオテストラジオテストラジオテスト', 'radio', '2023-01-01', 'user2', 'い')
;

INSERT INTO dbcow.`connect`
(id, conname, createDate, dbtype, deleteFlag, host, password, status, updateDate, user, username,dataregistflag,dataupdateflag,datadeleteflag, connectstring)
VALUES
(1, 'con1', '2023-01-01', 1, 0, 'localhost', 'pass1', 1, '2023-01-01', 'user1', 'user1', 1, 1, 1, "aaa")
,(2, 'con2', '2023-01-01', 1, 0, 'localhost', 'pass2', 1, '2023-01-01', 'user2', 'user1', 1, 1, 1, "aaa")
,(3, 'con3', '2023-01-01', 1, 0, 'localhost', 'pass3', 1, '2023-01-01', 'user3', 'user1', 1, 1, 1, "aaa")
,(4, 'con4', '2023-01-01', 1, 0, 'localhost', 'pass4', 1, '2023-01-01', 'user4', 'user1', 1, 1, 1, "aaa")
,(5, 'con5', '2023-01-01', 1, 0, 'localhost', 'pass1', 1, '2023-01-01', 'user1', 'user2', 1, 1, 1, "aaa")
,(6, 'con6', '2023-01-01', 1, 0, 'localhost', 'pass2', 1, '2023-01-01', 'user2', 'user2', 1, 1, 1, "aaa")
,(7, 'con7', '2023-01-01', 1, 0, 'localhost', 'pass3', 1, '2023-01-01', 'user3', 'user2', 1, 1, 1, "aaa")
,(8, 'con8', '2023-01-01', 1, 1, 'localhost', 'pass4', 1, '2023-01-01', 'user4', 'user2', 1, 1, 1, "aaa")
,(9, 'con9', '2023-01-01', 1, 0, 'localhost', 'pass2', 1, '2023-01-01', 'user2', 'user3', 1, 1, 1, "aaa")
,(10, 'con10', '2023-01-01', 1, 0, 'localhost', 'pass3', 1, '2023-01-01', 'user3', 'user4', 1, 1, 1, "aaa");

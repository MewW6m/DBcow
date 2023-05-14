INSERT INTO dbcow.`user`
(id, createUserDate, deleteFlag, updateUserDate, password, roles, username)
VALUES(1, '2023-01-01', 0, '2023-01-01', '{noop}password', 'ROLE_USER', 'user1');
INSERT INTO dbcow.`user`
(id, createUserDate, deleteFlag, updateUserDate, password, roles, username)
VALUES(2, '2023-01-01', 0, '2023-01-01', '{noop}password', 'ROLE_ADMIN', 'user2');
INSERT INTO dbcow.`user`
(id, createUserDate, deleteFlag, updateUserDate, password, roles, username)
VALUES(3, '2023-01-01', 1, '2023-01-01', '{noop}password', 'ROLE_USER', 'user3');
INSERT INTO dbcow.`user`
(id, createUserDate, deleteFlag, updateUserDate, password, roles, username)
VALUES(4, '2023-01-01', 1, '2023-01-01', '{noop}password', 'ROLE_ADMIN', 'user4');
DELETE FROM users;

INSERT INTO users (username, password)
VALUES ('user1', '$2a$10$Zir06PcCpnAmg73CwOM.m.D7PVHrp9EoZBXWCx6GoAdYIYcPVa3zK'),
       ('user2', '$2a$10$Zir06PcCpnAmg73CwOM.m.D7PVHrp9EoZBXWCx6GoAdYIYcPVa3zK'),
       ('admin', '$2a$10$Zir06PcCpnAmg73CwOM.m.D7PVHrp9EoZBXWCx6GoAdYIYcPVa3zK');


-- Пароль для всех пользователей: "password" (закодирован BCrypt)
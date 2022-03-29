INSERT into
    role(role_name,description)
VALUES
       ('ADMIN','Głowny administrator systemu'),
       ('USER','Użytkownik');

INSERT INTO
    user(first_name,last_name,user_name,role )
VALUES
    ('Admin','Admin','admin',1);

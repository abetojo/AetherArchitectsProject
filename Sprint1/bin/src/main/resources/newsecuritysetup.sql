-- create table SEC_USER(
--                          userId BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
--                          userName VARCHAR(35) NOT NULL UNIQUE,
--                          encryptedPassword VARCHAR(128) NOT NULL,
--
--                          enabled BIT NOT NULL
-- );

create table SEC_USER(
                        userId BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         email VARCHAR(50) NOT NULL UNIQUE,
                         firstName VARCHAR(35) NOT NULL,
                         lastName VARCHAR(35) NOT NULL,
                         phone BIGINT NOT NULL,
                        secondaryEmail VARCHAR(50),
                        province VARCHAR(25) NOT NULL,
                        city VARCHAR (25) NOT NULL,
                        postalCode VARCHAR(25),
                         encryptedPassword VARCHAR(128) NOT NULL,
                         accountEnabled BIT NOT NULL
);

create table SEC_ROLE(
                         roleId BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         roleName VARCHAR(30) NOT NULL UNIQUE
);

create table USER_ROLE(
                          ID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                          userId BIGINT NOT NULL,
                          roleId BIGINT NOT NULL
);

alter table USER_ROLE
    add constraint USER_ROLE_UK unique (userId, roleId);

alter table USER_ROLE
    add constraint USER_ROLE_FK1 foreign key (userId)
        references SEC_USER(userId);

alter table USER_ROLE
    add constraint USER_ROLE_FK2 foreign key (roleId)
        references SEC_ROLE(roleId);

insert into SEC_USER (email,firstName, lastName, phone, province,city,encryptedPassword, accountEnabled)
values ('admin@email.com','Default','Admin' ,'4161231234','Ontario','Toronto',  'password',1);
-- insert into SEC_USER (email,firstName, lastName, phone, province,city,encryptedPassword, enabled)
-- values ('Bob', '$2a$10$jY8MbRS9Fiig1aFFGfM63OqEsmHOIrYhNjp61ri.FbeBSy5b58e9y',1);

-- insert into SEC_USER (userName, encryptedPassword, enabled)
-- values ('Bill', '$2a$10$jY8MbRS9Fiig1aFFGfM63OqEsmHOIrYhNjp61ri.FbeBSy5b58e9y',1);
--
-- insert into SEC_USER (userName, encryptedPassword, enabled)
-- values ('Alice', '$2a$10$jY8MbRS9Fiig1aFFGfM63OqEsmHOIrYhNjp61ri.FbeBSy5b58e9y',1);

insert into sec_role(roleName) values ('ROLE_ADMIN');
insert into sec_role(roleName) values ('ROLE_USER');
insert into user_role(userId, roleId) values (1,1);
-- insert into user_role(userId, roleId) values (2,2);
-- insert into user_role(userId, roleId) values (3,2);


--
-- CREATE TABLE IF NOT EXISTS books (
--                                      id      LONG NOT NULL PRIMARY KEY AUTO_INCREMENT,
--                                      title   VARCHAR(128) NOT NULL,
--                                      author  VARCHAR(128) NOT NULL
-- );

-- CREATE TABLE IF NOT EXISTS reviews (
--                                        id      LONG NOT NULL PRIMARY KEY AUTO_INCREMENT,
--                                        bookId  Long NOT NULL,
--                                        text    VARCHAR(1024) NOT NULL UNIQUE
-- );

-- alter table reviews
--     add constraint IF NOT EXISTS book_review_fk foreign key (bookId)
--         references books (id);

-- insert into books (title, author)
-- values ('The 7 Habits of Highly Effective People', 'Stephen R. Covey'),
--        ('The Martian', 'Andy Weir'),
--        ('To Kill A Mockingbird', 'Harper Lee' );
--
-- insert into reviews (text, bookId)
-- values ('An older book, but still a very good read for principle-centered leadership', 1),
--        ('Old but gold :)', 1),
--        ('Kind of cliche but might be worthwhile', 1),
--        ('6/10', 1);
--
-- insert into reviews (text, bookId)
-- values ('A great science fiction book about an astronaut stranded on Mars', 2),
--        ('Great book but suuuper long... dont expect too much in the first half', 2);
--
-- insert into reviews (text, bookId)
-- values ('Always love reading this one to the class! 10/10 would recommend!', 3),
--        ('Ruined my whole summer. I hate you Miss B', 3);

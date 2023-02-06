create database if not exists mind_db;

create user mind_user@localhost identified by 'password';

grant all on mind_db.* to mind_user@localhost;
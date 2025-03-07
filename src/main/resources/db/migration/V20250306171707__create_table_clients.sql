create table clients (
    id bigserial not null primary key,
    name varchar(150) not null,
    email varchar(150) not null,
    phone bpchar(11) not null,
    constraint uk_email unique (email),
    constraint uk_phone unique (phone)
)
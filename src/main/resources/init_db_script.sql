create table users
(
    id       serial primary key,
    username varchar(16) unique not null,
    role     varchar(16)        not null
);

create table schedules
(
    id      serial primary key,
    name    varchar(16) not null,
    user_id bigint      not null references users (id)
);

create table feeders
(
    id          serial primary key,
    name        varchar(16) not null,
    active      bool        not null default false,
    capacity    bigint      not null,
    load        bigint      not null default 0,
    type        varchar(16) not null,
    status      varchar(16) not null,
    user_id     bigint      not null references users (id),
    schedule_id bigint references schedules (id)
);
create table users
(
    id       serial primary key,
    username varchar(16) unique not null,
    role     varchar(16)        not null
);

create table schedules
(
    id         serial primary key,
    name       varchar(16) not null,
    user_id    bigint      not null references users (id),
    start_time time        not null default '6:00',
    end_time   time        not null default '22:00',
    interval   int         not null default 120
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

create table logs
(
    id          serial primary key,
    created     timestamp    not null,
    message     varchar(200) not null,
    stack_trace varchar(1000),
    status      varchar(10)  not null,
    user_id     bigint references users (id)
);

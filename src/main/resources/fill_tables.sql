insert into users (username, role) VALUES ('Kirill','CLIENT');

insert into schedules (name, user_id, start_time, end_time, interval) VALUES ('S1',1,'14:30','15:45',5);
insert into schedules (name, user_id, start_time, end_time, interval) VALUES ('S2',1,'11:30','12:45',522);
insert into schedules (name, user_id, start_time, end_time, interval) VALUES ('S3',1,'15:30','18:45',1235);


insert into feeders (name, active, capacity, type, status, user_id) VALUES ('F1',false,2000,'SCALES','ACCEPTED',1);
insert into feeders (name, active, capacity,load, type, status, user_id,schedule_id) VALUES ('F2',false,2000,100,'TIMER','MODERATING',1,1);
insert into feeders (name, active, capacity,load, type, status, user_id) VALUES ('F3',false,2000,267,'SCALES','ACCEPTED',1);
insert into feeders (name, active, capacity, type, status, user_id,schedule_id) VALUES ('F4',false,2000,'TIMER','ACCEPTED',1,2);
insert into feeders (name, active, capacity, type, status, user_id) VALUES ('F5',false,2000,'SCALES','MODERATING',1);
insert into feeders (name, active, capacity, type, status, user_id,schedule_id) VALUES ('F6',false,2000,'TIMER','ACCEPTED',1,3);
insert into feeders (name, active, capacity,load, type, status, user_id) VALUES ('F7',false,2000,1444,'SCALES','REJECTED',1);
insert into feeders (name, active, capacity,load, type, status, user_id) VALUES ('F8',false,2000,243,'TIMER','MODERATING',1);
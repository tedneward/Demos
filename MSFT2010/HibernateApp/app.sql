drop table author
drop table hibernate_unique_key
create table author (id bigint not null, firstname varchar(255), lastname varchar(255), primary key (id))
create table hibernate_unique_key ( next_hi integer )
insert into hibernate_unique_key values ( 0 )

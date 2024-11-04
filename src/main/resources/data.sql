
insert into user_table(id,name,last_name,age, password) values
                                                            (1,'Соня', 'Белая',23, '$2a$10$X2qVO5wl3ScG8Ol8TbkG9.hV0EqXXFM99LedQOaEtjskOYsLTLPkC'),
                                                            (2,'Кира','Найтли',45, '$2a$10$WUChV8Jfnrhs8zUN3pZQ8ueWrJnQ0K.wb1aFlRyXC0iQ4yjPegomG'),
                                                            (3,'Лера','Волкова',33, '$2a$10$RwU80qRtpOl/5XS33r5rZOWo2CUwKCjg14thD3i0yPZHGDGPzJkle'),
                                                            (4,'Вера','Блейн',26, '$2a$10$mqYBBkmp39z92.gcqi3bieDObTrvRLrfGB/GQmNfi3YjBptWTr0u.');

insert into role(id,role_name) values
                                   (1,'ROLE_USER'),
                                   (2,'ROLE_ADMIN');
create table if not exists user_role (
    user_id int not null ,
    role_id int not null,

    foreign key (user_id) references user_table(id),
    foreign key (role_id) references role(id),

    unique (user_id,role_id)
);

insert into user_role(user_id, role_id) values(1,1),
                                              (1,2),
                                              (2,1),
                                              (3,2),
                                              (4,2);


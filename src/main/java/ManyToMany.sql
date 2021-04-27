-- auto-generated definition
create table manytomany
(
    idPeople  int not null
        primary key,
    idAddress int not null,
    constraint ManyToMany_address_id_fk
        foreign key (idAddress) references address (id),
    constraint ManyToMany_people_id_fk
        foreign key (idPeople) references people (id)
);


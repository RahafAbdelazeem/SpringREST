create table product_details
(
    id              int auto_increment
        primary key,
    name            varchar(255)         not null,
    expiration_date date                 not null,
    manufacturer    varchar(255)         not null,
    price           double               not null,
    available       tinyint(1) default 0 not null
);

create table product
(
    id                 int auto_increment
        primary key,
    name               varchar(255) not null,
    product_details_id int          not null,
    constraint product_ibfk_1
        foreign key (product_details_id) references product_details (id)
);

create index product_details_id
    on product (product_details_id);



drop table if exists orders cascade;

drop table if exists enterprise cascade;

drop table if exists contract cascade;

drop table if exists inventory cascade;

drop table if exists center_record cascade;

drop table if exists model cascade;

drop table if exists staff cascade;

drop table if exists center cascade;

drop table if exists "user" cascade;


create table model
(
    id           serial primary key,
    number       varchar(7)   not null,
    model_name   varchar(128) not null,
    product_name varchar(128) not null,
    unit_price   int          not null,
    sales        int          not null,
    constraint model_uk unique (number, model_name)
--     constraint unit_price_valid check ( unit_price > 0 )
);

create table center
(
    id          serial primary key,
    name        varchar(256) not null,
    expenditure int          not null,
    revenue     int          not null,
    constraint center_uk unique (name)
);

create table staff
(
    id               serial primary key,
    name             varchar(64) not null,
    age              int         not null,
    gender           varchar(10) not null,
    number           varchar(8)  not null,
    mobile_number    varchar(11) not null,
    type             varchar(32) not null,
    supply_center_id int         not null,
    constraint center_staff_fk foreign key (supply_center_id) references center (id) on delete cascade,
    constraint staff_uk unique (number),
    constraint age_valid check ( age >= 0 )
);

create table enterprise
(
    id               serial primary key,
    name             varchar(128) not null,
    country          varchar(128) not null,
    city             varchar(128) not null,
    supply_center_id int          not null,
    industry         varchar(128) not null,
    constraint enterprise_uk unique (name),
    constraint enterprise_supply_center_fk foreign key (supply_center_id) references center (id) on delete cascade
);

create table contract
(
    contract_number     varchar(16) primary key,
    contract_date       date         not null,
    contract_manager_id int          not null,
    contract_type       varchar(256) not null,
    constraint contract_uk unique (contract_number),
    constraint contract_staff_fk foreign key (contract_manager_id) references staff (id) on delete cascade
);

create table orders
(
    contract_number         varchar(16) not null,
    enterprise_id           int         not null,
    product_model_id        int         not null,
    quantity                int         not null,
    estimated_delivery_date date        not null,
    lodgement_date          date        not null,
    salesman_id             int         not null,
--     constraint orders_uk unique (contract_number, product_model_id, salesman_id),
    constraint orders_product_model_fk foreign key (product_model_id) references model (id) on delete cascade,
    constraint orders_salesman_fk foreign key (salesman_id) references staff (id) on delete cascade,
    constraint orders_enterprise_fk foreign key (enterprise_id) references enterprise (id) on delete cascade,
    constraint orders_contract_fk foreign key (contract_number) references contract (contract_number) on delete cascade
);

create table inventory
(
    supply_center_id int not null,
    product_model_id int not null,
    count            int not null,
    constraint inventory_uk unique (supply_center_id, product_model_id),
    constraint stock_center_fk foreign key (supply_center_id) references center (id) on delete cascade,
    constraint stock_model_fk foreign key (product_model_id) references model (id) on delete cascade
);

create table center_record
(
    supply_center_id int  not null,
    product_model_id int  not null,
    staff_id         int  not null,
    date             DATE not null,
    purchase_price   int  not null,
    quantity         int  not null,
    constraint center_record_supply_center_fk foreign key (supply_center_id) references center (id) on delete cascade,
    constraint center_record_product_model_fk foreign key (product_model_id) references model (id) on delete cascade,
    constraint center_record_staff_fk foreign key (staff_id) references staff (id) on delete cascade
);

create table users
(
    username varchar(30) primary key,
    password varchar(18) not null
);

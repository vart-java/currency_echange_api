drop schema if exists my_schema cascade;
create schema if not exists my_schema;

create table if not exists my_schema.currency_exchange
(
    id                           bigserial primary key,
    base_currency                varchar(3) not null,
    exchange_currency            varchar(3) not null,
    sell_rate                    double precision not null,
    buy_rate                     double precision not null,
    cross_rate                   double precision not null,
    loading_date                 timestamp not null,
    bank_name                    varchar(10) not null
);



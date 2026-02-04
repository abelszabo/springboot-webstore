-- =========================
-- BASE TABLES
-- =========================

create table address (
                         id              bigserial primary key,
                         uuid            uuid not null unique,
                         version         bigint not null,
                         created_at      timestamptz not null,
                         updated_at      timestamptz,
                         created_by      uuid not null,
                         updated_by      uuid,

                         country         varchar(100),
                         postal_code     varchar(20),
                         city            varchar(100),
                         street          varchar(255),
                         house_number    varchar(50),
                         floor           varchar(50),
                         door            varchar(50),

                         company_name    varchar(255),
                         tax_number      varchar(50)
);

create table app_user (
                          id                  bigserial primary key,
                          uuid                uuid not null unique,
                          version             bigint not null,
                          created_at          timestamptz not null,
                          updated_at          timestamptz,
                          created_by          uuid not null,
                          updated_by          uuid,

                          email               varchar(255) not null unique,
                          full_name           varchar(255),

                          delivery_address_id bigint references address(id),
                          billing_address_id  bigint references address(id)
);

-- =========================
-- PRODUCT
-- =========================

create table product_category (
                                  id                  bigserial primary key,
                                  uuid                uuid not null unique,
                                  version             bigint not null,
                                  created_at          timestamptz not null,
                                  updated_at          timestamptz,
                                  created_by          uuid not null,
                                  updated_by          uuid,

                                  name                varchar(255) not null,
                                  parent_category_id  bigint references product_category(id)
);

create table product (
                         id              bigserial primary key,
                         uuid            uuid not null unique,
                         version         bigint not null,
                         created_at      timestamptz not null,
                         updated_at      timestamptz,
                         created_by      uuid not null,
                         updated_by      uuid,

                         name            varchar(255) not null,
                         description     text,
                         price           numeric(19,2) not null,
                         category_id     bigint references product_category(id)
);

create table inventory (
                           id                  bigserial primary key,
                           uuid                uuid not null unique,
                           version             bigint not null,
                           created_at          timestamptz not null,
                           updated_at          timestamptz,
                           created_by          uuid not null,
                           updated_by          uuid,

                           product_id          bigint not null unique references product(id),
                           quantity            integer not null,
                           reserved_quantity   integer not null
);

-- =========================
-- PAYMENT / DELIVERY
-- =========================

create table payment_type (
                              id          bigserial primary key,
                              uuid        uuid not null unique,
                              version     bigint not null,
                              created_at  timestamptz not null,
                              updated_at  timestamptz,
                              created_by  uuid not null,
                              updated_by  uuid,

                              code        varchar(50) not null unique,
                              name        varchar(255) not null
);

create table delivery_type (
                               id          bigserial primary key,
                               uuid        uuid not null unique,
                               version     bigint not null,
                               created_at  timestamptz not null,
                               updated_at  timestamptz,
                               created_by  uuid not null,
                               updated_by  uuid,

                               code        varchar(50) not null unique,
                               name        varchar(255) not null
);

-- =========================
-- ORDER
-- =========================

create table order_head (
                            id                  bigserial primary key,
                            uuid                uuid not null unique,
                            version             bigint not null,
                            created_at          timestamptz not null,
                            updated_at          timestamptz,
                            created_by          uuid not null,
                            updated_by          uuid,

                            order_number        varchar(50) not null unique,
                            status              varchar(50) not null,
                            total_gross         numeric(19,2),

                            user_id             bigint references app_user(id),
                            payment_type_id     bigint references payment_type(id),
                            delivery_type_id    bigint references delivery_type(id),
                            delivery_address_id bigint references address(id),
                            billing_address_id  bigint references address(id)
);

create table order_item (
                            id              bigserial primary key,
                            uuid            uuid not null unique,
                            version         bigint not null,
                            created_at      timestamptz not null,
                            updated_at      timestamptz,
                            created_by      uuid not null,
                            updated_by      uuid,

                            order_head_id   bigint not null references order_head(id) on delete cascade,
                            product_id      bigint references product(id),

                            product_name    varchar(255) not null,
                            unit_price      numeric(19,2) not null,
                            vat_percent     numeric(5,2),
                            quantity        integer not null
);

-- =========================
-- INVOICE
-- =========================

create table invoice_head (
                              id              bigserial primary key,
                              uuid            uuid not null unique,
                              version         bigint not null,
                              created_at      timestamptz not null,
                              updated_at      timestamptz,
                              created_by      uuid not null,
                              updated_by      uuid,

                              invoice_number  varchar(50) not null unique,
                              order_id        bigint not null unique references order_head(id),
                              user_id         bigint references app_user(id),
                              billing_address_id bigint references address(id),

                              total_net       numeric(19,2),
                              total_vat       numeric(19,2),
                              total_gross     numeric(19,2)
);

create table invoice_item (
                              id              bigserial primary key,
                              uuid            uuid not null unique,
                              version         bigint not null,
                              created_at      timestamptz not null,
                              updated_at      timestamptz,
                              created_by      uuid not null,
                              updated_by      uuid,

                              invoice_head_id bigint not null references invoice_head(id) on delete cascade,
                              product_name    varchar(255) not null,
                              unit_price      numeric(19,2) not null,
                              vat_percent     numeric(5,2),
                              quantity        integer not null
);

-- =========================
-- OUTBOX
-- =========================

create table outbox_event (
                              id              bigserial primary key,
                              uuid            uuid not null unique,
                              version         bigint not null,
                              created_at      timestamptz not null,
                              updated_at      timestamptz,
                              created_by      uuid not null,
                              updated_by      uuid,

                              event_id        uuid not null unique,
                              aggregate_type  varchar(100) not null,
                              aggregate_uuid  uuid not null,
                              event_type      varchar(100) not null,
                              payload         jsonb not null,
                              status          varchar(50) not null,
                              retry_count     integer not null default 0,
                              sent_at         timestamptz
);

create index ix_outbox_status_created
    on outbox_event(status, created_at);

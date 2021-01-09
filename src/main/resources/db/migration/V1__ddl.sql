create table produto
(
    id bigserial not null,
    nome varchar not null,
    valor int not null
);

create unique index produto_id_uindex
	on produto (id);

alter table produto
    add constraint produto_pk
        primary key (id);

create table pedido
(
    id bigserial not null,
    numero serial not null,
    valor_total int not null
);

create unique index pedido_id_uindex
	on pedido (id);

alter table pedido
    add constraint pedido_pk
        primary key (id);

create table pedido_produto
(
    id bigserial not null,
    pedido_id bigint not null
        constraint pedido_produto_pedido_id_fk
            references pedido,
    produto_id bigint not null
        constraint pedido_produto_produto_id_fk
            references produto
);

create unique index pedido_produto_id_uindex
	on pedido_produto (id);

alter table pedido_produto
    add constraint pedido_produto_pk
        primary key (id);

create table ordem_transporte
(
    id bigserial not null,
    pedido_id bigint not null
        constraint ordem_transporte_pedido_id_fk
            references pedido,
    status varchar not null
);

create unique index ordem_transporte_id_uindex
    on ordem_transporte (id);

alter table ordem_transporte
    add constraint ordem_transporte_pk
        primary key (id);

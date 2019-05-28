drop table if exists article;

create table article
(
    id bigserial not null constraint article_primary_key primary key,
    visible boolean not null default false,
    content varchar(255) not null,
    modified_date timestamp not null
);
create table "todos" (
    "id" bigserial primary key not null,
    "title" varchar(255) not null,
    "description" varchar(255) not null,
    "is_completed" boolean not null default '0'

    "author_id" bigint null,
    "manager_id" bigint null,
    "executor_id" bigint null,

    "created_at" timestamp(0) without time zone null,
    "updated_at" timestamp(0) without time zone null
);
create index "todos_author_id_index" on "todos" ("author_id");
create index "todos_manager_id_index" on "todos" ("manager_id");
create index "todos_executor_id_index" on "todos" ("executor_id");

alter table "todos" add constraint "todos_author_id_foreign" foreign key ("author_id") references "users" ("id")"
alter table "todos" add constraint "todos_manager_id_foreign" foreign key ("manager_id") references "users" ("id")"
alter table "todos" add constraint "todos_executor_id_foreign" foreign key ("executor_id") references "users" ("id")"

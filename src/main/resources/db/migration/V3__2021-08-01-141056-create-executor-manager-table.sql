create table "executor_manager" (
"executor_id" bigint not null,
"manager_id" bigint not null
);
alter table "executor_manager" add constraint "executor_manager_executor_id_foreign" foreign key ("executor_id") references "users" ("id");
alter table "executor_manager" add constraint "executor_manager_manager_id_foreign" foreign key ("manager_id") references "users" ("id");


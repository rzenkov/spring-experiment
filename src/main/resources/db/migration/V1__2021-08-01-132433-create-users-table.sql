create table "users" (
    "id" bigserial primary key not null,

    "username" varchar(255) not null,
    "email" varchar(255) not null,
    "password" varchar(255) not null,
    "remember_token" varchar(100) null,

    "email_verified_at" timestamp(0) without time zone null,
    "created_at" timestamp(0) without time zone null,
    "updated_at" timestamp(0) without time zone null
)
alter table "users" add constraint "users_email_unique" unique ("email");

create table schedules (
    id bigserial not null primary key,
    start_at timestamp not null,
    end_at timestamp not null,
    client_id bigserial not null,
    constraint uk_schedule_interval unique (start_at, end_at),
    constraint fk_clients_schedules foreign key(client_id) references clients(id)
)
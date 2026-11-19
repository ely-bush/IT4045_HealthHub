alter table appointments
    add user_id binary(16) not null;

alter table appointments
    add constraint fk_appointment_user_id
        foreign key (user_id) references users (id);

alter table health_log
    add user_id binary(16) not null;

alter table health_log
    add constraint fk_healthlog_user_id
        foreign key (user_id) references users (id);

alter table medications
    add user_id binary(16) not null;

alter table medications
    add constraint fk_medications_user_id
        foreign key (user_id) references users (id);

alter table providers
    add user_id binary(16) not null;

alter table providers
    add constraint fk_providers_user_id
        foreign key (user_id) references users (id);
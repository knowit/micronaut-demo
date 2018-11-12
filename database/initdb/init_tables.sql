CREATE TABLE public.tasklist
(
    list_id bigserial PRIMARY KEY,
    owner varchar(60) NOT NULL,
    title varchar(100) NOT NULL
);
CREATE UNIQUE INDEX tasklist_list_id_uindex ON public.tasklist (list_id);


CREATE TABLE public.task
(
    task_id bigserial,
    parent_id bigint NOT NULL,
    description varchar(255) NOT NULL,
    status int NOT NULL,
    CONSTRAINT task_tasklist_list_id_fk FOREIGN KEY (parent_id) REFERENCES public.tasklist (list_id)
);
CREATE UNIQUE INDEX task_task_id_uindex ON public.task (task_id);
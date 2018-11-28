INSERT INTO public.tasklist (owner, title) VALUES ('jvm-chapter', 'Demo-list');
INSERT INTO public.tasklist (owner, title) VALUES ('jvm-chapter', 'Chapter Todo');
INSERT INTO public.tasklist (owner, title) VALUES ('security-chapter', 'Chapter Todo');

INSERT INTO public.task (parent_id, description, status) VALUES (1, 'test-task', 0);
INSERT INTO public.task (parent_id, description, status) VALUES (2, 'test-task', 1);
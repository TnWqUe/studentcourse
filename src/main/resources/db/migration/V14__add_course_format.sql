--уровни олимпиад
INSERT INTO public.olymp_level(name)
VALUES ('Внутривузовский'),
       ('Межвузовский'),
       ('Всероссийский'),
       ('Международный');

--формат олимпиад
INSERT INTO public.olymp_format(name)
VALUES ('Очный'),
       ('Смешанный'),
       ('Дистанционный');

--языки
INSERT INTO public.lang(name)
VALUES ('Русский'),
       ('Английский'),
       ('Немецкий'),
       ('Французский');

--добавление курсов
INSERT INTO public.elective_course(name,begin_date,begin_time,cabinet,id_day_week)
VALUES ('Олимпиадная физика','2024-02-10','17:30','Б-200',8),
       ('Олимпиадная математика','2024-02-05','18:30','М-710',12),
       ('Олимпиадная информатика','2024-02-05','18:30','М-710',11),
       ('Подготовка к олимпиадам по ТОЭ','2024-02-15','18:00','Д-207',9);
commit ;
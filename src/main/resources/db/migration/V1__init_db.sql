CREATE TABLE public.usr(
                           id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                           surname varchar(40) NOT NULL,
                           usrname varchar(40) NOT NULL,
                           patronymic varchar(40) NOT NULL,
                           created_at date NOT NULL DEFAULT CURRENT_DATE,
                           email varchar(150) NOT NULL,
                           password varchar(150) NOT NULL,
                           CONSTRAINT usr_id PRIMARY KEY (id),
                           CONSTRAINT email_unique UNIQUE (email)

);
-- ddl-end --

ALTER TABLE public.usr OWNER TO postgres;

-- object: public.usr_role | type: TABLE --
CREATE TABLE public.usr_role(
                                id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                                name varchar(50) NOT NULL,
                                CONSTRAINT usr_role_id PRIMARY KEY (id)

);
-- ddl-end --

ALTER TABLE public.usr_role OWNER TO postgres;

-- object: public.elective_course | type: TABLE --
CREATE TABLE public.elective_course(
                                       id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                                       name varchar(150) NOT NULL,
                                       begin_date date NOT NULL,
                                       begin_time time NOT NULL,
                                       cabinet varchar(10) NOT NULL,
                                       description text,
                                       id_day_week bigint,
                                       CONSTRAINT elective_course_id PRIMARY KEY (id)

);
-- ddl-end --

ALTER TABLE public.elective_course OWNER TO postgres;

-- object: public.olymp_tour | type: TABLE --
CREATE TABLE public.olymp_tour(
                                  id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                                  name varchar(250) NOT NULL,
                                  olymp_url varchar(200) NOT NULL,
                                  organizer varchar(250) NOT NULL,
                                  tours_amount integer NOT NULL,
                                  tour_number integer NOT NULL,
                                  start_reg date,
                                  end_reg date,
                                  is_open_for_requests boolean,
                                  begin_tour date NOT NULL,
                                  end_tour date NOT NULL,
                                  address varchar(512) NOT NULL,
                                  comment_text text,
                                  id_olymp_level bigint,
                                  id_olymp_format bigint,
                                  CONSTRAINT olymp_tour_id PRIMARY KEY (id)

);
-- ddl-end --

ALTER TABLE public.olymp_tour OWNER TO postgres;

-- object: public.stud | type: TABLE --
CREATE TABLE public.stud(
                            id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                            stud_group varchar(15) NOT NULL,
                            id_dept bigint,
                            id_usr bigint,
                            CONSTRAINT stud_id PRIMARY KEY (id)

);
-- ddl-end --

ALTER TABLE public.stud OWNER TO postgres;

-- object: public.teacher | type: TABLE --
CREATE TABLE public.teacher(
                               id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                               mpei_url varchar(200) NOT NULL,
                               id_usr bigint,
                               CONSTRAINT teacher_id PRIMARY KEY (id)

);
-- ddl-end --

ALTER TABLE public.teacher OWNER TO postgres;

-- object: public.discipline | type: TABLE --
CREATE TABLE public.discipline(
                                  id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                                  name varchar(100) NOT NULL,
                                  CONSTRAINT discipline_id PRIMARY KEY (id)

);
-- ddl-end --

ALTER TABLE public.discipline OWNER TO postgres;

-- object: public.usr_usr_role | type: TABLE --
CREATE TABLE public.usr_usr_role(
                                    id_usr bigint NOT NULL,
                                    id_usr_role bigint NOT NULL,
                                    CONSTRAINT usr_usr_role_pk PRIMARY KEY (id_usr,id_usr_role)

);
-- ddl-end --
-- object: usr_fk | type: CONSTRAINT --
ALTER TABLE public.usr_usr_role ADD CONSTRAINT usr_fk FOREIGN KEY (id_usr)
    REFERENCES public.usr (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: usr_role_fk | type: CONSTRAINT --
ALTER TABLE public.usr_usr_role ADD CONSTRAINT usr_role_fk FOREIGN KEY (id_usr_role)
    REFERENCES public.usr_role (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: public.dept | type: TABLE --
CREATE TABLE public.dept(
                            id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                            name varchar(100) NOT NULL,
                            id_orgn bigint,
                            CONSTRAINT dept_id PRIMARY KEY (id)

);
-- ddl-end --

ALTER TABLE public.dept OWNER TO postgres;

-- object: public.olymp_level | type: TABLE --
CREATE TABLE public.olymp_level(
                                   id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                                   name varchar(40) NOT NULL,
                                   CONSTRAINT olymp_level_id PRIMARY KEY (id)

);
-- ddl-end --

ALTER TABLE public.olymp_level OWNER TO postgres;

-- object: public.lang | type: TABLE --
CREATE TABLE public.lang(
                            id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                            name varchar(50) NOT NULL,
                            CONSTRAINT lang_id PRIMARY KEY (id)

);
-- ddl-end --

ALTER TABLE public.lang OWNER TO postgres;

-- object: public.orgn | type: TABLE --
CREATE TABLE public.orgn(
                            id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                            name varchar(50) NOT NULL,
                            CONSTRAINT orgn_id PRIMARY KEY (id)

);
-- ddl-end --

ALTER TABLE public.orgn OWNER TO postgres;

-- object: public.day_week | type: TABLE --
CREATE TABLE public.day_week(
                                id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                                name varchar(11) NOT NULL,
                                CONSTRAINT day_week_id PRIMARY KEY (id)

);
-- ddl-end --

ALTER TABLE public.day_week OWNER TO postgres;

-- object: public.olymp_format | type: TABLE --
CREATE TABLE public.olymp_format(
                                    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                                    name varchar(60) NOT NULL,
                                    CONSTRAINT olymp_format_id PRIMARY KEY (id)

);
-- ddl-end --

ALTER TABLE public.olymp_format OWNER TO postgres;

-- object: orgn_fk | type: CONSTRAINT --
ALTER TABLE public.dept ADD CONSTRAINT orgn_fk FOREIGN KEY (id_orgn)
    REFERENCES public.orgn (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: dept_fk | type: CONSTRAINT --
ALTER TABLE public.stud ADD CONSTRAINT dept_fk FOREIGN KEY (id_dept)
    REFERENCES public.dept (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: public.team | type: TABLE --
CREATE TABLE public.team(
                            id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                            name varchar(200) NOT NULL,
                            created_at date NOT NULL DEFAULT CURRENT_DATE,
                            updated_at date,
                            description varchar(512),
                            id_teacher bigint,
                            CONSTRAINT team_id PRIMARY KEY (id)

);
-- ddl-end --

ALTER TABLE public.team OWNER TO postgres;

-- object: public.stud_team | type: TABLE --
CREATE TABLE public.stud_team(
                                 id_stud bigint NOT NULL,
                                 id_team bigint NOT NULL,
                                 CONSTRAINT stud_team_pk PRIMARY KEY (id_stud,id_team)

);
-- ddl-end --
-- object: stud_fk | type: CONSTRAINT --
ALTER TABLE public.stud_team ADD CONSTRAINT stud_fk FOREIGN KEY (id_stud)
    REFERENCES public.stud (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: team_fk | type: CONSTRAINT --
ALTER TABLE public.stud_team ADD CONSTRAINT team_fk FOREIGN KEY (id_team)
    REFERENCES public.team (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: public.elective_course_discipline | type: TABLE --
CREATE TABLE public.elective_course_discipline(
                                                  id_elective_course bigint NOT NULL,
                                                  id_discipline bigint NOT NULL,
                                                  CONSTRAINT elective_course_discipline_pk PRIMARY KEY (id_elective_course,id_discipline)

);
-- ddl-end --
-- object: elective_course_fk | type: CONSTRAINT --
ALTER TABLE public.elective_course_discipline ADD CONSTRAINT elective_course_fk FOREIGN KEY (id_elective_course)
    REFERENCES public.elective_course (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: discipline_fk | type: CONSTRAINT --
ALTER TABLE public.elective_course_discipline ADD CONSTRAINT discipline_fk FOREIGN KEY (id_discipline)
    REFERENCES public.discipline (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: public.teacher_elective_course | type: TABLE --
CREATE TABLE public.teacher_elective_course(
                                               id_teacher bigint NOT NULL,
                                               id_elective_course bigint NOT NULL,
                                               CONSTRAINT teacher_elective_course_pk PRIMARY KEY (id_teacher,id_elective_course)

);
-- ddl-end --
-- object: teacher_fk | type: CONSTRAINT --
ALTER TABLE public.teacher_elective_course ADD CONSTRAINT teacher_fk FOREIGN KEY (id_teacher)
    REFERENCES public.teacher (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: elective_course_fk | type: CONSTRAINT --
ALTER TABLE public.teacher_elective_course ADD CONSTRAINT elective_course_fk FOREIGN KEY (id_elective_course)
    REFERENCES public.elective_course (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: public.elective_course_olymp_tour | type: TABLE --
CREATE TABLE public.elective_course_olymp_tour(
                                                  id_elective_course bigint NOT NULL,
                                                  id_olymp_tour bigint NOT NULL,
                                                  CONSTRAINT elective_course_olymp_tour_pk PRIMARY KEY (id_elective_course,id_olymp_tour)

);
-- ddl-end --
-- object: elective_course_fk | type: CONSTRAINT --
ALTER TABLE public.elective_course_olymp_tour ADD CONSTRAINT elective_course_fk FOREIGN KEY (id_elective_course)
    REFERENCES public.elective_course (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: olymp_tour_fk | type: CONSTRAINT --
ALTER TABLE public.elective_course_olymp_tour ADD CONSTRAINT olymp_tour_fk FOREIGN KEY (id_olymp_tour)
    REFERENCES public.olymp_tour (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: olymp_level_fk | type: CONSTRAINT --
ALTER TABLE public.olymp_tour ADD CONSTRAINT olymp_level_fk FOREIGN KEY (id_olymp_level)
    REFERENCES public.olymp_level (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: public.lang_olymp_tour | type: TABLE --
CREATE TABLE public.lang_olymp_tour(
                                       id_lang bigint NOT NULL,
                                       id_olymp_tour bigint NOT NULL,
                                       CONSTRAINT lang_olymp_tour_pk PRIMARY KEY (id_lang,id_olymp_tour)

);
-- ddl-end --
-- object: lang_fk | type: CONSTRAINT --
ALTER TABLE public.lang_olymp_tour ADD CONSTRAINT lang_fk FOREIGN KEY (id_lang)
    REFERENCES public.lang (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: olymp_tour_fk | type: CONSTRAINT --
ALTER TABLE public.lang_olymp_tour ADD CONSTRAINT olymp_tour_fk FOREIGN KEY (id_olymp_tour)
    REFERENCES public.olymp_tour (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: olymp_format_fk | type: CONSTRAINT --
ALTER TABLE public.olymp_tour ADD CONSTRAINT olymp_format_fk FOREIGN KEY (id_olymp_format)
    REFERENCES public.olymp_format (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: public.team_olymp_tour | type: TABLE --
CREATE TABLE public.team_olymp_tour(
                                       id_team bigint NOT NULL,
                                       id_olymp_tour bigint NOT NULL,
                                       CONSTRAINT team_olymp_tour_pk PRIMARY KEY (id_team,id_olymp_tour)

);
-- ddl-end --
-- object: team_fk | type: CONSTRAINT --
ALTER TABLE public.team_olymp_tour ADD CONSTRAINT team_fk FOREIGN KEY (id_team)
    REFERENCES public.team (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: olymp_tour_fk | type: CONSTRAINT --
ALTER TABLE public.team_olymp_tour ADD CONSTRAINT olymp_tour_fk FOREIGN KEY (id_olymp_tour)
    REFERENCES public.olymp_tour (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: public.stud_elective_course | type: TABLE --
CREATE TABLE public.stud_elective_course(
                                            id_stud bigint NOT NULL,
                                            id_elective_course bigint NOT NULL,
                                            CONSTRAINT stud_elective_course_pk PRIMARY KEY (id_stud,id_elective_course)

);
-- ddl-end --
-- object: stud_fk | type: CONSTRAINT --
ALTER TABLE public.stud_elective_course ADD CONSTRAINT stud_fk FOREIGN KEY (id_stud)
    REFERENCES public.stud (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: elective_course_fk | type: CONSTRAINT --
ALTER TABLE public.stud_elective_course ADD CONSTRAINT elective_course_fk FOREIGN KEY (id_elective_course)
    REFERENCES public.elective_course (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: day_week_fk | type: CONSTRAINT --
ALTER TABLE public.elective_course ADD CONSTRAINT day_week_fk FOREIGN KEY (id_day_week)
    REFERENCES public.day_week (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: usr_fk | type: CONSTRAINT --
ALTER TABLE public.teacher ADD CONSTRAINT usr_fk FOREIGN KEY (id_usr)
    REFERENCES public.usr (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: teacher_uq | type: CONSTRAINT --
ALTER TABLE public.teacher ADD CONSTRAINT teacher_uq UNIQUE (id_usr);
-- ddl-end --


-- object: teacher_fk | type: CONSTRAINT --
ALTER TABLE public.team ADD CONSTRAINT teacher_fk FOREIGN KEY (id_teacher)
    REFERENCES public.teacher (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: usr_fk | type: CONSTRAINT --
ALTER TABLE public.stud ADD CONSTRAINT usr_fk FOREIGN KEY (id_usr)
    REFERENCES public.usr (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: stud_uq | type: CONSTRAINT --
ALTER TABLE public.stud ADD CONSTRAINT stud_uq UNIQUE (id_usr);
-- ddl-end --
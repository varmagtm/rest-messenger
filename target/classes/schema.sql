create table MESSAGE (ID bigint generated by default as identity (start with 1),
	CONTENT LONGVARCHAR not null, CREATED DATE not null, AUTHOR varchar(100) not null, primary key (id));

/* create table STUDENT (ID bigint generated by default as identity (start with 1), ADDRESS varchar(255), NAME varchar(255), primary key (id));
create table STUDENT_ENROLLMENTS (STUDENT_ID bigint not null, ENROLLMENT_ID bigint not null);
alter table STUDENT_ENROLLMENTS add constraint ENROLLMENT_ID_FK foreign key (ENROLLMENT_ID) references Course;
alter table STUDENT_ENROLLMENTS add constraint STUDENT_ID_FK foreign key (STUDENT_ID) references Student; */
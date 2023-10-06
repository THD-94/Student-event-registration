DROP SCHEMA IF EXISTS `EventDB`;
DROP SCHEMA IF EXISTS `UniversityDB`;
create schema `UniversityDB`;
use UniversityDB;
create table Staff(
                      StaffName varchar(200),
                      primary key (StaffName)
);
create table StudyProgram(
                             ProgramName varchar(200),
                             Responsibility varchar(200),
                             primary key (ProgramName),
                             foreign key (Responsibility) references Staff(StaffName)
);
create table Student(
                        StudentName varchar(200),
                        Password varchar(200),
                        Program varchar(200),
                        primary key (StudentName),
                        foreign key (Program) references StudyProgram(ProgramName)
);

insert into Staff(StaffName)
VALUES ('Rolando');
insert into Staff(StaffName)
VALUES ('Bengt');
insert into Staff(StaffName)
VALUES ('Tomas');
insert into Staff(StaffName)
VALUES ('Amina');
insert into Staff(StaffName)
VALUES ('Alexander');
insert into Staff(StaffName)
VALUES ('Bogdan');

insert into StudyProgram(ProgramName, responsibility)
VALUES ('Frontend and Mobile Development','Rolando' );
insert into StudyProgram(ProgramName, responsibility)
VALUES ('Programming', 'Bogdan');
insert into StudyProgram(ProgramName, responsibility)
VALUES ('E-business', 'Amina');
insert into StudyProgram(ProgramName, responsibility)
VALUES ('Cyber', 'Bengt');

insert into Student(StudentName,Password, Program)
VALUES ('Marit', '1234','Frontend and Mobile Development');
insert into Student(StudentName,Password, Program)
VALUES ('Dipinti','0987', 'Frontend and Mobile Development');
insert into Student(StudentName,Password, Program)
VALUES ('Tore','5678', 'Programming');
insert into Student(StudentName,Password, Program)
VALUES ('Richard', 'Hello','Programming');
insert into Student(StudentName,Password, Program)
VALUES ('Ellen','Bye','E-business');
insert into Student(StudentName,Password, Program)
VALUES ('Marius','6543','E-business');
insert into Student(StudentName,Password, Program)
VALUES ('Helene','Exam','Cyber');
insert into Student(StudentName,Password, Program)
VALUES ('Elisabeth','OOP' ,'Cyber');


create schema `EventDB`;
use EventDB;
create table Participants(
                             ParticipantsID int not null unique auto_increment,
                             GuestName varchar(200),
                             StaffName varchar(200),
                             StudentName varchar(200),
                             primary key (ParticipantsID),
                             foreign key (StaffName) references UniversityDB.Staff(StaffName),
                             foreign key (StudentName) references UniversityDB.Student(StudentName)
);/* Stackoverflow (2020) Select Table while in one schema from another Schema [closed], Hentet 14.juni:
      https://s-in-one-schema-from-another-schematackoverflow.com/questions/57575492/select-table-while*/
insert into Participants(StaffName)
VALUES ('Rolando');
insert into Participants(StaffName)
VALUES ('Bengt');
insert into Participants(StaffName)
VALUES ('Tomas');
insert into Participants(StaffName)
VALUES ('Amina');
insert into Participants(StaffName)
VALUES ('Alexander');
insert into Participants(StaffName)
VALUES ('Bogdan');
select * from Participants;
SELECT *
from Participants;

/*select Participants.*, Student.*
from EventDB.Participants join UniversityDB.Student
    on Participants.StudentName = Student.StudentName
where Program = 'Frontend and Mobile Development';*/

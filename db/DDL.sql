--To insert / recreate the DDL.sql into the database run: sqlite3 database.db < DDL.sql

--Drops all of the tables
DROP TABLE IF EXISTS assigned_lecturers;
DROP TABLE IF EXISTS room_bookings;
DROP TABLE IF EXISTS slots;
DROP TABLE IF EXISTS lecturers;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS rooms;


--A lot of TEXT columns have to be case insensitive
--https://stackoverflow.com/questions/20914946/how-to-make-a-case-insensitive-unique-column-in-sqlite
--I understand that PRIMARY KEY implies UNIQUE column but for the column to be case sensitive, I had to add UNIQUE constraint on top of the PRIMARY KEY

--https://www.sqlite.org/lang_createtable.html#:~:text=Unless%20the%20column%20is%20an,so%20might%20break%20legacy%20applications.
--From the link above - "Unless the column is an INTEGER PRIMARY KEY or the table is a WITHOUT ROWID table or a STRICT table or the column is declared NOT NULL, SQLite allows NULL values in a PRIMARY KEY column. SQLite could be fixed to conform to the standard, but doing so might break legacy applications. Hence, it has been decided to merely document the fact that SQLite allows NULLs in most PRIMARY KEY columns."
-- This means that TEXT which is PRIMARY KEY can still be NULL, therefore, I will put NOT NULL in all PRIMARY KEYs which are set on TEXT columns.

CREATE TABLE rooms (
	name TEXT NOT NULL PRIMARY KEY,
	capacity INTEGER NOT NULL,
	UNIQUE (name COLLATE NOCASE)
);

CREATE TABLE courses (
	name TEXT NOT NULL PRIMARY KEY,
	capacity INTEGER NOT NULL,
	UNIQUE (name COLLATE NOCASE)
);

CREATE TABLE lecturers (
	name TEXT NOT NULL PRIMARY KEY,
	UNIQUE (name COLLATE NOCASE)
);

CREATE TABLE slots (
	name TEXT NOT NULL PRIMARY KEY,
	UNIQUE (name COLLATE NOCASE)
);

CREATE TABLE room_bookings (
	course_name TEXT NOT NULL REFERENCES courses(name),
	slot_name TEXT NOT NULL REFERENCES slots(name),
	room_name TEXT NOT NULL REFERENCES rooms(name),
	PRIMARY KEY(slot_name, room_name),
	UNIQUE(slot_name, course_name)
);
--UNIQUE constraint on slot_name and course_name makes sure that the course can only be booked once every time slot

CREATE TABLE assigned_lecturers (
	lecturer_name TEXT NOT NULL REFERENCES lecturers(name),
	slot_name TEXT NOT NULL,
	room_name TEXT NOT NULL,
	FOREIGN KEY(slot_name, room_name) REFERENCES room_bookings(slot_name, room_name),
	PRIMARY KEY(lecturer_name, slot_name)
);

--Apparently in sqlite foreign keys have to be enabled https://www.sqlitetutorial.net/sqlite-foreign-key/
PRAGMA foreign_keys = ON;

INSERT INTO rooms(name, capacity) VALUES("10.2.20", 40);
INSERT INTO rooms(name, capacity) VALUES("12.1.10", 60);
INSERT INTO rooms(name, capacity) VALUES("20.2.15", 25);
INSERT INTO rooms(name, capacity) VALUES("10.1.14", 20);
INSERT INTO rooms(name, capacity) VALUES("25.3.11", 20);

INSERT INTO courses(name, capacity) VALUES("Software Development", 20);
INSERT INTO courses(name, capacity) VALUES("Essential Computing", 30);
INSERT INTO courses(name, capacity) VALUES("Interactive Digital Systems", 25);
INSERT INTO courses(name, capacity) VALUES("Introduction to Database Systems", 60);

INSERT INTO lecturers(name) VALUES("Beckett Cubie");
INSERT INTO lecturers(name) VALUES("Isaac Lembo");
INSERT INTO lecturers(name) VALUES("Jade Catt");
INSERT INTO lecturers(name) VALUES("Jayden Letang");
INSERT INTO lecturers(name) VALUES("Michael Scheibe");
INSERT INTO lecturers(name) VALUES("Marley Henein");
INSERT INTO lecturers(name) VALUES("Vincent Trueluck");

INSERT INTO slots(name) VALUES("Monday AM"), ("Monday PM");
INSERT INTO slots(name) VALUES("Tuesday AM"), ("Tuesday PM");
INSERT INTO slots(name) VALUES("Wednesday AM"), ("Wednesday PM");
INSERT INTO slots(name) VALUES("Thursday AM"), ("Thursday PM");
INSERT INTO slots(name) VALUES("Friday AM"), ("Friday PM");

--Room bookings
INSERT INTO room_bookings(course_name, slot_name, room_name) VALUES("Software Development", "Monday AM", "12.1.10");
INSERT INTO room_bookings(course_name, slot_name, room_name) VALUES("Software Development", "Thursday AM", "12.1.10");
INSERT INTO room_bookings(course_name, slot_name, room_name) VALUES("Software Development", "Friday PM", "25.3.11");

INSERT INTO room_bookings(course_name, slot_name, room_name) VALUES("Essential Computing", "Tuesday PM", "12.1.10");
INSERT INTO room_bookings(course_name, slot_name, room_name) VALUES("Essential Computing", "Thursday AM", "10.1.14");


INSERT INTO room_bookings(course_name, slot_name, room_name) VALUES("Interactive Digital Systems", "Monday PM", "10.2.20");
INSERT INTO room_bookings(course_name, slot_name, room_name) VALUES("Interactive Digital Systems", "Wednesday AM", "20.2.15");
INSERT INTO room_bookings(course_name, slot_name, room_name) VALUES("Interactive Digital Systems", "Friday PM", "10.1.14");


INSERT INTO room_bookings(course_name, slot_name, room_name) VALUES("Introduction to Database Systems", "Tuesday AM", "10.1.14");
INSERT INTO room_bookings(course_name, slot_name, room_name) VALUES("Introduction to Database Systems", "Tuesday PM", "10.1.14");

--Assigned lecturers
INSERT INTO assigned_lecturers(lecturer_name, slot_name, room_name) VALUES("Beckett Cubie", "Monday AM", "12.1.10");
INSERT INTO assigned_lecturers(lecturer_name, slot_name, room_name) VALUES("Beckett Cubie", "Thursday AM", "12.1.10");
INSERT INTO assigned_lecturers(lecturer_name, slot_name, room_name) VALUES("Beckett Cubie", "Friday PM", "25.3.11");

INSERT INTO assigned_lecturers(lecturer_name, slot_name, room_name) VALUES("Beckett Cubie", "Tuesday PM", "12.1.10");
INSERT INTO assigned_lecturers(lecturer_name, slot_name, room_name) VALUES("Isaac Lembo", "Tuesday PM", "12.1.10");
INSERT INTO assigned_lecturers(lecturer_name, slot_name, room_name) VALUES("Isaac Lembo", "Thursday AM", "10.1.14");


INSERT INTO assigned_lecturers(lecturer_name, slot_name, room_name) VALUES("Jade Catt", "Monday PM", "10.2.20");
INSERT INTO assigned_lecturers(lecturer_name, slot_name, room_name) VALUES("Jayden Letang", "Wednesday AM", "20.2.15");
INSERT INTO assigned_lecturers(lecturer_name, slot_name, room_name) VALUES("Michael Scheibe", "Friday PM", "10.1.14");

INSERT INTO assigned_lecturers(lecturer_name, slot_name, room_name) VALUES("Marley Henein", "Tuesday AM", "10.1.14");
INSERT INTO assigned_lecturers(lecturer_name, slot_name, room_name) VALUES("Vincent Trueluck", "Tuesday AM", "10.1.14");
INSERT INTO assigned_lecturers(lecturer_name, slot_name, room_name) VALUES("Jade Catt", "Tuesday PM", "10.1.14");
INSERT INTO assigned_lecturers(lecturer_name, slot_name, room_name) VALUES("Michael Scheibe", "Tuesday PM", "10.1.14");

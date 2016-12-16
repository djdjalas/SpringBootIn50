package com.yourname.dao;

import com.yourname.entity.Student;

import java.util.Collection;

public interface StudentDao {
    Collection<Student> getAllStudents();

    Student getStudentById(int id);

    void removeStudentById(int id);

    void updateStudent(Student student);

    void insertStudentToDb(Student student);
//    yourname.database.studentDevDB.selectall=SELECT id, name, course FROM students
//    yourname.database.studentDevDB.insert=INSERT INTO students (name, course) VALUES (?, ?)
//    yourname.database.studentDevDB.update=UPDATE students SET name = ?, course = ? WHERE id = ?
//    yourname.database.studentDevDB.delete=DELETE FROM students WHERE id = ?
//    yourname.database.studentDevDB.selectbyid=SELECT id, name, course FROM students where id = ?
}

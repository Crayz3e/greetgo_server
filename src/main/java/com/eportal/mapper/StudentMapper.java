package com.eportal.mapper;

import com.eportal.model.Student;
import java.util.List;

public interface StudentMapper {
    Student getStudentById(int id);

    Student getStudentByParams(Student s);

    List<Student> getStudents();

    void insertStudent(Student student);

    void deleteStudent(Student student);
}

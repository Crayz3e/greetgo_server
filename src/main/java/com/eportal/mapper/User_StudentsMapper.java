package com.eportal.mapper;

import com.eportal.model.Student;
import com.eportal.model.User;

import java.util.HashMap;
import java.util.List;

import com.eportal.model.User_Students;
import org.apache.ibatis.annotations.*;

public interface User_StudentsMapper {
    List<Student> getStudentsByLogin(String login);

    void insertUser_Student(@Param("login") String login, @Param("id") Integer id);

    void deleteStudentFromList(@Param("login") String login, @Param("id") Integer id);
}

package com.eportal.controller;

import com.eportal.mapper.StudentMapper;
import com.eportal.mapper.UserMapper;
import com.eportal.model.Student;
import com.eportal.model.User_Students;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.mybatis.spring.SqlSessionTemplate;

import com.eportal.mapper.User_StudentsMapper;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path="/students")
public class StudentsController {
    String resource = "mybatis-config.xml";
    Reader reader = Resources.getResourceAsReader(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
    User_StudentsMapper user_studentsMapper = sqlSessionTemplate.getMapper(User_StudentsMapper.class);
    StudentMapper studentMapper = sqlSessionTemplate.getMapper(StudentMapper.class);

    public StudentsController() throws IOException {
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Student> getStudents(@RequestBody Map<String, Object> body) {
        List<Student> students = user_studentsMapper.getStudentsByLogin((String) body.get("login"));

        return students;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HttpStatus deleteStudentFromList(@RequestBody Map<String, Object> body) {
        user_studentsMapper.deleteStudentFromList((String) body.get("login"), (Integer) body.get("id"));
        return HttpStatus.OK;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HttpStatus insertStudent(@RequestBody Map<String, Object> body) {
        Student student = new Student();
        student.setName((String) body.get("name"));
        student.setAge((Integer) body.get("age"));
        student.setAddress((String) body.get("address"));
        student.setPhoneNumber((String) body.get("phoneNumber"));
        studentMapper.insertStudent(student);
        Student s = studentMapper.getStudentByParams(student);
        Integer id = s.getId();
        Student s1 = studentMapper.getStudentById(id);
        user_studentsMapper.insertUser_Student((String) body.get("login"), id);
        return HttpStatus.OK;
    }
}
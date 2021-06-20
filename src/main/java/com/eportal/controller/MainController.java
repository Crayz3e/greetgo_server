package com.eportal.controller;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.eportal.model.User;
import com.eportal.mapper.UserMapper;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path="/api")
public class MainController {
    String resource = "mybatis-config.xml";
    Reader reader = Resources.getResourceAsReader(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    UserMapper userMapper = sqlSessionFactory.openSession().getMapper(UserMapper.class);

    public MainController() throws IOException {
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HttpStatus login(@RequestBody Map<String, Object> body) {
        User u = userMapper.getUserByLogin((String) body.get("login"));
        if (u == null) {
            return HttpStatus.NOT_FOUND;
        }

        if (!u.getPassword().equals((String) body.get("password"))) {
            return HttpStatus.CONFLICT;
        }

        return HttpStatus.OK;
    }
}
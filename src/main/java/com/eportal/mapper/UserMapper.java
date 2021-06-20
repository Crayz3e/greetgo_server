package com.eportal.mapper;

import com.eportal.model.Student;
import com.eportal.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserMapper {
    User getUserByLogin(String login);

    List<User> getUsers();

    void insertUser(User user);

    void deleteUser(User user);
}

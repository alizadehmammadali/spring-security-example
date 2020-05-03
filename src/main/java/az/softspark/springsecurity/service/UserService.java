package az.softspark.springsecurity.service;

import az.softspark.springsecurity.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Integer id);

    User saveUser(User user);
}

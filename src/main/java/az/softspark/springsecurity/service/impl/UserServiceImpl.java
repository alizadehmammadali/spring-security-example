package az.softspark.springsecurity.service.impl;

import az.softspark.springsecurity.dao.UserDao;
import az.softspark.springsecurity.errors.EntityNotFoundException;
import az.softspark.springsecurity.model.User;
import az.softspark.springsecurity.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private  final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class, "Not found any information according this id ", id + ""));
    }

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }
}

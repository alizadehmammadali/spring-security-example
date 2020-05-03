package az.softspark.springsecurity.controller;

import az.softspark.springsecurity.model.User;
import az.softspark.springsecurity.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final UserService userService;

    public ApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello " + SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping(value = "/users", produces = "application/json")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/users/{id}", produces = "application/json")
    public User getUsersById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/users")
    public User updateUserById(User user) {
        return userService.saveUser(user);
    }


}

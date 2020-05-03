package az.softspark.springsecurity;

import az.softspark.springsecurity.dao.UserDao;
import az.softspark.springsecurity.dao.UserRoleDao;
import az.softspark.springsecurity.model.User;
import az.softspark.springsecurity.model.UserRole;
import az.softspark.springsecurity.model.enums.Role;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableWebSecurity
public class SpringSecurityApplication {

    final UserDao userDao;
    final UserRoleDao userRoleDao;
    final PasswordEncoder passwordEncoder;

    public SpringSecurityApplication(UserDao userDao, UserRoleDao userRoleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.userRoleDao = userRoleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initRUserRoles() {

        for (Role currentRole : Role.values()) {
            UserRole userRole = new UserRole();
            userRole.setId(currentRole.getId());
            userRole.setRole(currentRole);
            userRoleDao.save(userRole);
        }
    }

    @PostConstruct
    public void initUsers() {
        UserRole user = userRoleDao.getUserRoleById(Role.USER.getId());
        UserRole admin = userRoleDao.getUserRoleById(Role.ADMIN.getId());
        UserRole manager = userRoleDao.getUserRoleById(Role.MANAGER.getId());
        List<UserRole> onlyUser = new ArrayList<>();
        List<UserRole> onlyManager = new ArrayList<>();
        List<UserRole> onlyAdmin = new ArrayList<>();
        List<UserRole> bothUserAndManager = new ArrayList<>();
        List<UserRole> all = new ArrayList<>();
        onlyUser.add(user);
        onlyManager.add(manager);
        onlyAdmin.add(admin);
        all.add(user);
        all.add(manager);
        all.add(admin);
        bothUserAndManager.add(user);
        bothUserAndManager.add(manager);
        List<User> users = Stream.of(
                new User(1, "mammadali", passwordEncoder.encode("admin"), "test1@gmail.com", true, all),
                new User(2, "royal", passwordEncoder.encode("royal1"), "test2@gmail.com", true, onlyUser),
                new User(3, "araz", passwordEncoder.encode("araz1"), "test3@gmail.com", true, onlyManager),
                new User(4, "memmedaga", passwordEncoder.encode("alizada1"), "test4@gmail.com", true, bothUserAndManager),
                new User(5, "elqar", passwordEncoder.encode("aliyev1"), "test5@gmail.com", true, onlyAdmin),
                new User(6, "mammadali2", passwordEncoder.encode("admin2"), "test6@gmail.com", false, all)

                ).collect(Collectors.toList());
        userDao.saveAll(users);
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }


}

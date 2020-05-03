package az.softspark.springsecurity.dao;

import az.softspark.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    //To Solve N+1 problem
    @Query("select u from User u join  fetch u.userRoles where u.username=:userName")
    User findByUsername(String userName);

    //To Solve N+1 problem
    @Query("select u from User u join  fetch u.userRoles")
    List<User> findAll();

    //To Solve N+1 problem
    @Query("select u from User u join  fetch u.userRoles where u.id=:id")
    Optional<User> findById(Integer id);

}

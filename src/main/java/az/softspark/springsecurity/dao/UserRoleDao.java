package az.softspark.springsecurity.dao;

import az.softspark.springsecurity.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Integer> {

    UserRole getUserRoleById(Integer id);
}

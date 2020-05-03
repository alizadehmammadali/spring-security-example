package az.softspark.springsecurity.model;

import az.softspark.springsecurity.dao.converters.RoleConverter;
import az.softspark.springsecurity.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @Column(name = "id",updatable = false,unique = true,nullable = false)
    private int id;

    @Column(name = "role")
    @NotNull
    @Convert(converter = RoleConverter.class)
    private Role role;

    @JsonBackReference
    @ManyToMany(mappedBy = "userRoles",fetch = FetchType.LAZY)
    private List<User> users;


}

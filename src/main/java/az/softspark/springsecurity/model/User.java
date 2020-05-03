package az.softspark.springsecurity.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {
    @Id
    private int id;
    private String username;
    private String password;
    private String email;
    private boolean enabled;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // If we use FetchMode.SUBSELECT and  FetchType.EAGER data coming in just two queries
    // But best option is use JOIN FETCH in repository - because it retrieves data just one query :)
    // @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "user_with_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<UserRole> userRoles;
}

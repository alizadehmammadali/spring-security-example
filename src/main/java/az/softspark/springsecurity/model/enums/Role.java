package az.softspark.springsecurity.model.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public enum Role {
    USER(1), MANAGER(2), ADMIN(3);
    private int id;

    private static final Map<Integer, Role> BY_ID = new HashMap<>();

    static {
        for (Role r : values()) {
            BY_ID.put(r.getId(), r);
        }
    }

    public int getId() {
        return id;
    }

    public static int getId(String roleName) {
        return Role.valueOf(roleName).getId();
    }

    @Nullable
    public static Role fromId(Integer id) {
        return BY_ID.getOrDefault(id, null);
    }
}

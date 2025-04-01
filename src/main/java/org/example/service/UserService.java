package org.example.service;



import org.example.model.User;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private final List<User> users = Arrays.asList(
            new User(1, "John Doe", "john@example.com"),
            new User(2, "Jane Doe", "jane@example.com")
    );

    public User getUserById(int userId) {
        return users.stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .orElse(null);
    }
}


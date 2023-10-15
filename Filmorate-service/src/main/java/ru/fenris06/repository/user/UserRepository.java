package ru.fenris06.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fenris06.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
package ru.fenris06.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.fenris06.model.user.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users" +
            " JOIN (SELECT friend_id FROM friends WHERE user_id = ?) AS fr ON users.id = fr.friend_id" +
            " JOIN (SELECT friend_id FROM friends WHERE user_id = ?) AS nf ON users.id = nf.friend_id", nativeQuery = true)
    List<User> commonFriends(Long id, Long id1);
}
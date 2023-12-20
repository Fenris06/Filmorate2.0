package ru.fenris06.model.user;

import lombok.Getter;
import lombok.Setter;
import ru.fenris06.model.film.Film;

import javax.persistence.*;
import java.time.LocalDate;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "friends",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<User> friends;
}

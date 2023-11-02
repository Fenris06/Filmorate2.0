package ru.fenris06.model.film;

import lombok.Getter;
import lombok.Setter;
import ru.fenris06.model.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "films")
public class Film {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false, length = 200)
    private String description;
    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;
    @Column(name = "duration", nullable = false)
    private Integer duration;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "likes",
    joinColumns = @JoinColumn(name = "film_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likes;
}

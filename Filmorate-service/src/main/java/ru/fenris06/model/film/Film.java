package ru.fenris06.model.film;

import lombok.Getter;
import lombok.Setter;
import ru.fenris06.model.genre.Genre;
import ru.fenris06.model.mpa.Mpa;
import ru.fenris06.model.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
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
    @ManyToOne
    @JoinColumn(name = "mpa_id")
    private Mpa mpa;
    @ManyToMany
    @JoinTable(name = "film_genres",
    joinColumns = @JoinColumn(name = "film_id"),
    inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "likes",
    joinColumns = @JoinColumn(name = "film_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likes;
}

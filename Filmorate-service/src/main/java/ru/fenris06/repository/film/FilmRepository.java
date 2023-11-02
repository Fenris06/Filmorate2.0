package ru.fenris06.repository.film;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.fenris06.model.film.Film;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query(value = "select f.*, COUNT(l.user_id)" +
            " from films AS f LEFT JOIN  likes AS l on f.id = l.film_id" +
            " GROUP BY f.id order by COUNT (l.user_id) DESC" +
            " LIMIT ?", nativeQuery = true)
    List<Film> getPopularFilm(Integer count);
}
package ru.fenris06.repository.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fenris06.model.genre.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
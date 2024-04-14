package ru.fenris06.repository.mpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fenris06.model.mpa.Mpa;

public interface MpaRepository extends JpaRepository<Mpa, Long> {
}
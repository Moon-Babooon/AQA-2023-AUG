package org.prog.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonsJpa extends JpaRepository<Persons, Long> {

    List<Persons> findAllByPersonIdGreaterThan(Long id);

    @Query(value = "SELECT * FROM Persons p ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Persons getRandomEntry();
}
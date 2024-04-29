package com.jeep.jeepney.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jeep.jeepney.model.Code;


@Repository
public interface CodeRepo extends JpaRepository<Code, String> {
    @Query("SELECT c FROM Code c JOIN FETCH c.places WHERE c.code = ?1")
    Optional<Code> findByCodeWithPlaces(String code);

    @Query("SELECT c FROM Code c JOIN FETCH c.places WHERE c.code = ?1")
    Optional<Code> findByIdWithPlaces(String code);
}
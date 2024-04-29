package com.jeep.jeepney.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeep.jeepney.model.Place;


@Repository
public interface PlaceRepo extends JpaRepository<Place, String> {
}

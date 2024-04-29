package com.jeep.jeepney.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeep.jeepney.model.Code;
import com.jeep.jeepney.model.Place;
import com.jeep.jeepney.repo.CodeRepo;

@Service
public class JeepService {
    
    private final CodeRepo codeRepo;

    public JeepService(CodeRepo codeRepo) {
        this.codeRepo = codeRepo;
    }

    @Transactional(readOnly = true)
    public List<Place> getPlacesByJeepCode(String code) {
        return codeRepo.findByCodeWithPlaces(code)
         .map(Code::getPlaces)
        .orElse(Collections.emptyList());
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getPlacesForMultipleCodes(List<String> codes) {
        Map<String, List<String>> routes = new HashMap<>();
        Set<String> commonPlaces = new HashSet<>();
        boolean isFirstCode = true;

        for (String code : codes) {
            List<String> places = codeRepo.findByIdWithPlaces(code)
                .map(c -> c.getPlaces().stream().map(Place::getPlace).collect(Collectors.toList()))
                .orElse(Collections.emptyList());

            if (!places.isEmpty()) {
                routes.put(code, places);
                if (isFirstCode) {
                    commonPlaces.addAll(places);
                    isFirstCode = false;
                } else {
                    commonPlaces.retainAll(new HashSet<>(places));
                }
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("routes", routes);
        result.put("commonPlaces", new ArrayList<>(commonPlaces));
        return result;
    }
}

package com.jeep.jeepney.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeep.jeepney.model.Place;
import com.jeep.jeepney.service.JeepService;

@RestController
@RequestMapping("/jeepcodes")
@CrossOrigin
public class JeepneyController {
    private final JeepService jeepService;

    public JeepneyController(JeepService jeepService) {
        this.jeepService = jeepService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<List<Place>> getPlacesByJeepCode(@PathVariable String code) {
        List<Place> places = jeepService.getPlacesByJeepCode(code);
        return places.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(places, HttpStatus.OK);
    }

    @GetMapping("/multiple")
    public ResponseEntity<Map<String, Object>> getMultipleJeepRoutesByCodes(@RequestParam List<String> codes) {
        Map<String, Object> routesAndCommons = jeepService.getPlacesForMultipleCodes(codes);
        if (((Map)routesAndCommons.get("routes")).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(routesAndCommons, HttpStatus.OK);
        }
    }

}

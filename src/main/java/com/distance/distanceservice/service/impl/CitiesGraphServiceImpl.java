package com.distance.distanceservice.service.impl;

import com.distance.distanceservice.dto.DistanceDto;
import com.distance.distanceservice.service.ICitiesGraphService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public final class CitiesGraphServiceImpl implements ICitiesGraphService {

    private Map<String, Set<Neighbour>> cityNeighbours = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock(true);

    public void addCity(DistanceDto cityDistance) {
        var neighbourOne = new Neighbour(cityDistance.getCityTwo(), cityDistance.getDistance());
        var neighbourTwo = new Neighbour(cityDistance.getCityOne(), cityDistance.getDistance());
        try {
            lock.writeLock().lock();
            Optional.ofNullable(cityNeighbours.putIfAbsent(cityDistance.getCityOne(), Set.of(neighbourOne))).ifPresent(it -> it.add(neighbourOne));
            Optional.ofNullable(cityNeighbours.putIfAbsent(cityDistance.getCityTwo(), Set.of(neighbourTwo))).ifPresent(it -> it.add(neighbourTwo));
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Map<String, Set<Neighbour>> getCitiesGraphSnapshot() {
        Map<String, Set<Neighbour>> copy = new HashMap<>();
        try {
            lock.readLock().lock();
            for (Map.Entry<String, Set<Neighbour>> neighboursSetEntry : cityNeighbours.entrySet()) {
                copy.put(neighboursSetEntry.getKey(), Set.copyOf(neighboursSetEntry.getValue()));
            }
        } finally {
            lock.readLock().unlock();
        }
        return copy;
    }


    @Data
    @RequiredArgsConstructor
    public static final class Neighbour {
        private final String city;
        private final long distance;

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }

            if (other instanceof Neighbour) {
                return city.equals(((Neighbour) other).city);
            }
            return false;
        }
    }

}


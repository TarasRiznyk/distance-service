package com.distance.distanceservice.service.impl;

import com.distance.distanceservice.dto.DistanceDto;
import com.distance.distanceservice.service.ICitiesGraphService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
@RequiredArgsConstructor
public final class CitiesGraphServiceImpl implements ICitiesGraphService {

    private final ObjectProvider<DistanceCalculationService> distanceCalculationService;

    private Map<String, Set<Neighbour>> cityNeighbours = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock(true);

    public void addCity(DistanceDto cityDistance) {
        var neighbourOne = new Neighbour(cityDistance.getCityTwo(), cityDistance.getDistance());
        var neighbourTwo = new Neighbour(cityDistance.getCityOne(), cityDistance.getDistance());
        try {
            lock.writeLock().lock();
            Optional.ofNullable(cityNeighbours.putIfAbsent(cityDistance.getCityOne(), new HashSet<>(Set.of(neighbourOne)))).ifPresent(it -> it.add(neighbourOne));
            Optional.ofNullable(cityNeighbours.putIfAbsent(cityDistance.getCityTwo(), new HashSet<>(Set.of(neighbourTwo)))).ifPresent(it -> it.add(neighbourTwo));
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Map<String, Set<Neighbour>> getCitiesGraphSnapshot() {
        try {
            lock.readLock().lock();
            Map<String, Set<Neighbour>> copy = new HashMap<>();
            for (Map.Entry<String, Set<Neighbour>> neighboursSetEntry : cityNeighbours.entrySet()) {
                copy.put(neighboursSetEntry.getKey(), Set.copyOf(neighboursSetEntry.getValue()));
            }
            return copy;
        } finally {
            lock.readLock().unlock();
        }
    }

    public List<Stack<CitiesGraphServiceImpl.Neighbour>> calculateDistances(String cityOne, String cityTwo) {
        try {
            lock.readLock().lock();
            System.out.println(cityNeighbours);
            var stack = new Stack<Neighbour>();
            stack.add(new Neighbour(cityOne, 0));
            return distanceCalculationService.getObject(cityNeighbours).calculateDistances(cityOne, cityTwo, stack);
        } finally {
            lock.readLock().unlock();
        }
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

        @Override
        public int hashCode() {
            return city.hashCode();
        }
    }

}


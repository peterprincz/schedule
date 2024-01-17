package hu.pp.schedule.service;

import hu.pp.schedule.enums.Station;
import hu.pp.schedule.model.Route;

import java.time.LocalDateTime;
import java.util.List;

public interface CollectorService <T extends Station> {

    List<Route> getRoutes(LocalDateTime day, T from, T to);

}

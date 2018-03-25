package com.yourcompany.hibernateExample.DAO;

import com.yourcompany.hibernateExample.logic.Bus;
import com.yourcompany.hibernateExample.logic.Route;
import com.yourcompany.hibernateExample.logic.Driver;

import java.util.Collection;
import java.sql.SQLException;

public interface RouteDAO {
    public void addRoute(Route route) throws SQLException;
    public void updateRoute(Long route_id, Route route) throws SQLException;
    public Route getRouteById(Long route_id) throws SQLException;
    public Collection getAllRoutes() throws SQLException;
    public void deleteRoute(Route route) throws SQLException;
    public Collection getRouteByDriver(Driver driver) throws SQLException;
    public Collection getRoutesByBus(Bus bus) throws SQLException;
}
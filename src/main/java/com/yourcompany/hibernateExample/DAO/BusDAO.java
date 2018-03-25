package com.yourcompany.hibernateExample.DAO;

import com.yourcompany.hibernateExample.logic.Bus;
import com.yourcompany.hibernateExample.logic.Route;
import com.yourcompany.hibernateExample.logic.Driver;

import java.util.Collection;
import java.sql.SQLException;

public interface BusDAO {
    public void addBus(Bus bus) throws SQLException;
    public void updateBus(Long bus_id, Bus bus) throws SQLException;
    public Bus getBusById(Long bus_id) throws SQLException;
    public Collection getAllBusses() throws SQLException;
    public void deleteBus(Bus bus) throws SQLException;
    public Collection getBussesByDriver(Driver driver) throws SQLException;
    public Collection getBussesByRoute(Route route) throws SQLException;
}

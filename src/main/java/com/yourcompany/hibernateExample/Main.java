package com.yourcompany.hibernateExample;

import com.yourcompany.hibernateExample.DAO.factory.Factory;
import com.yourcompany.hibernateExample.logic.Bus;
import com.yourcompany.hibernateExample.logic.Driver;
import com.yourcompany.hibernateExample.logic.Route;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws SQLException {
        Collection routes = Factory.getInstance().getRouteDAO().getAllRoutes();
        Iterator iterator = routes.iterator();
        System.out.println("-=All Routes:=-");
        while (iterator.hasNext()){
            Route route = (Route) iterator.next();
            System.out.println("Route: " + route.getName() + " Number of route: " + route.getNumber());
            Collection busses = Factory.getInstance().getBusDAO().getBussesByRoute(route);
            Iterator iterator1 = busses.iterator();
            while (iterator.hasNext()){
                Bus bus = (Bus) iterator1.next();
                System.out.println("Buss #" + bus.getNumber());
            }
        }

        Collection busses = Factory.getInstance().getBusDAO().getAllBusses();
        iterator = busses.iterator();
        System.out.println("-=All Busses:=-");
        while (iterator.hasNext()){
            Bus bus = (Bus) iterator.next();
            Collection drivers = Factory.getInstance().getDriverDAO().getDriversByBus(bus);
            Iterator iterator1 = drivers.iterator();
            System.out.println("Bus #" + bus.getNumber());
            while (iterator1.hasNext()){
                Driver driver = (Driver) iterator1.next();
                System.out.println("Name: " + driver.getName() + " Surname: " + driver.getSurname());
            }
        }
    }
}

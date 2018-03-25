package com.yourcompany.hibernateExample.DAO.impl;

import com.yourcompany.hibernateExample.DAO.DriverDAO;
import com.yourcompany.hibernateExample.logic.Bus;
import com.yourcompany.hibernateExample.logic.Driver;
import com.yourcompany.hibernateExample.logic.Route;

import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

import com.yourcompany.hibernateExample.sessions.HibernateUtil;
import javax.swing.*;
import org.hibernate.Session;
import org.hibernate.Query;

public class DriverDAOImpl implements DriverDAO{
    @Override
    public void addDriver(Driver driver) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(driver);
            session.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error in insertion", JOptionPane.OK_OPTION);
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public void updateDriver(Long driver_id, Driver driver) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(driver);
            session.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error in updeit", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public Driver getDriverById(Long driver_id) throws SQLException {
        Session session = null;
        Driver driver = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            driver = (Driver) session.load(Driver.class, driver_id);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error 'findByType'", JOptionPane.OK_OPTION);
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return driver;
    }

    @Override
    public Collection getAllDrivers() throws SQLException {
        Session session = null;
        List drivers = new ArrayList<Driver>();
        try{
            session = session.getSessionFactory().openSession();
            drivers = session.createCriteria(Driver.class).list();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error in taked List<Bus>", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return drivers;
    }

    @Override
    public void deleteDriver(Driver driver) throws SQLException {
        Session session = null;
        try {
            session = session.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(driver);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error in delete part", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public Collection getDriversByBus(Bus bus) throws SQLException {
        Session session = null;
        List drivers = new ArrayList<Driver>();
        try {
            session = session.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Long bus_id = bus.getId();
            Query query = session.createQuery("select b" + " from Driver b INNER JOIN  b.busses bus" + " where bus.id = :busId").setLong("busId", bus_id);
            drivers = (List<Driver>)query.list();
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return drivers;
    }

    @Override
    public Collection getDriversByRoute(Route route) throws SQLException {
        Session session = null;
        List drivers = new ArrayList<Driver>();
        try {
            session = session.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Long route_id = route.getId();
            Query query = session.createQuery("from Driver where route_id = :routeId").setLong("routeId", route_id);
            drivers = (List<Driver>) query.list();
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return drivers;
    }
}

package com.yourcompany.hibernateExample.DAO.impl;

import com.yourcompany.hibernateExample.DAO.BusDAO;
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

public class BusDAOImpl implements BusDAO {
    @Override
    public void addBus(Bus bus) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(bus);
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
    public void updateBus(Long bus_id, Bus bus) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(bus);
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
    public Bus getBusById(Long bus_id) throws SQLException {
        Session session = null;
        Bus bus = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            bus = (Bus) session.load(Bus.class, bus_id);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error 'findByType'", JOptionPane.OK_OPTION);
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return bus;
    }

    @Override
    public Collection getAllBusses() throws SQLException {
        Session session = null;
        List busses = new ArrayList<Bus>();
        try{
            session = session.getSessionFactory().openSession();
            busses = session.createCriteria(Bus.class).list();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error in taked List<Bus>", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return busses;
    }

    @Override
    public void deleteBus(Bus bus) throws SQLException {
        Session session = null;
        try {
            session = session.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(bus);
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
    public Collection getBussesByDriver(Driver driver) throws SQLException {
        Session session = null;
        List busses = new ArrayList<Bus>();
        try {
            session = session.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Long driver_id = driver.getId();
            Query query = session.createQuery("select b" + " from Bus b INNER JOIN  b.drivers driver" + " where driver.id = :driverId").setLong("driverId", driver_id);
            busses = (List<Bus>)query.list();
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return busses;
    }

    @Override
    public Collection getBussesByRoute(Route route) throws SQLException {
        Session session = null;
        List busses = new ArrayList<Bus>();
        try {
            session = session.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Long route_id = route.getId();
            Query query = session.createQuery("from Bus where route_id = :routeId").setLong("routeId", route_id);
            busses = (List<Bus>) query.list();
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return busses;
    }

}

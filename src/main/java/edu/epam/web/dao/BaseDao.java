package edu.epam.web.dao;

import edu.epam.web.exception.DaoException;

import java.util.List;

public interface BaseDao<T> {

    boolean create(T t, String password) throws DaoException;
    boolean delete(T t) throws DaoException;
    boolean update(T t);
    List<T> findAll() throws DaoException;
}

package com.example.bookingsystem.service;

import com.example.bookingsystem.dao.UserDao;
import com.example.bookingsystem.model.User;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public void save(User user) throws SQLException {
        userDao.save(user);
    }

    public User findById(Long id) throws SQLException {
        return userDao.findById(id);
    }

    public List<User> findAll() throws SQLException {
        return userDao.findAll();
    }

    public User findByUsername(String username) throws SQLException {
        return userDao.findByUsername(username);
    }

    public void update(User user) throws SQLException {
        userDao.update(user);
    }

    public void delete(User user) throws SQLException {
        userDao.delete(user);
    }

    public User validateUser(String username, String password) throws SQLException, NoSuchAlgorithmException {
        User user = userDao.findByUsername(username);
        if (user != null && user.getPassword().equals(userDao.hashPassword(password))) {
            return user;
        }
        return null;
    }

}

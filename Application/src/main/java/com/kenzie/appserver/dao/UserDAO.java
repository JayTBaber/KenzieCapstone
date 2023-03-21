package com.kenzie.appserver.dao;

import com.kenzie.appserver.service.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final List<User> users;

    public UserDAO(){ this.users = new ArrayList<>();}

    public void addUser(User user) {users.add(user);}

    public List<User> getAllUsers() {return users;}

    public User getUserByUserName(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }
}

package com.kenzie.appserver.dao;

import com.kenzie.appserver.service.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDAO {

    private final List<User> users;

    public UserDAO(){ this.users = new ArrayList<>();}

    public void addUser(User user) {users.add(user);}

    public List<User> getAllUsers() {return users;}

    public User getUserByUserName(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }
    public User getUSerByUserID(UUID userID) {
        for (User user : users) {
            if (user.getUserId().equals(userID)){
                return user;
            }
        }
        return null;
    }
}

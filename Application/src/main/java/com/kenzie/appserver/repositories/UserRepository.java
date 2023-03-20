package com.kenzie.appserver.repositories;

import com.kenzie.appserver.service.model.User;

import java.util.List;

public interface UserRepository {

    User saveUser(User user);

    User findByUserName(String userName);

    int savePurse(int purse);

    int saveWins(int wins);

    int saveLosses(int losses);

    void deleteUser(String username);

    List<User> findAll();

    void deleteByUserName(String userName);
}

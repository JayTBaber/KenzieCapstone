package com.kenzie.appserver.repositories;

import com.kenzie.appserver.service.model.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository {

    User saveUser(User user);

    User findByUserName(String username);

    User findByUserID(UUID userID);

    int savePurse(int purse);

    int saveWins(int wins);

    int saveLosses(int losses);

    void deleteUser(String username);

    List<User> findAll();

    void deleteByUserName(String username);
}

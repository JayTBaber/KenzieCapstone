package com.kenzie.appserver.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.appserver.service.UserService;
import com.kenzie.appserver.service.model.User;

import java.util.List;
import java.util.Optional;

public class DynamoDBUserRepository implements UserRepository {


    private final DynamoDBMapper mapper;

    UserService userService = new UserService();

    public DynamoDBUserRepository(DynamoDBMapper mapper) {this.mapper = mapper;}


    @Override
    public User saveUser(User user) {
        mapper.save(user);
        return user;
    }

    @Override
    public User findByUserName(String userName) {
        return mapper.load(User.class, userName);
    }

    @Override
    public int savePurse(int purse) {
        mapper.save(purse);
        return purse;
    }

    @Override
    public int saveWins(int wins) {
        mapper.save(wins);
        return wins;
    }

    @Override
    public int saveLosses(int losses) {
        mapper.save(losses);
        return losses;
    }

    @Override
    public void deleteUser(String username) {
        Optional<User> user = userService.getUserByUserName(username);
        mapper.delete(user);
    }

    @Override
    public List<User> findAll() {return userService.getAllUsers();}

    @Override
    public void deleteByUserName(String userName) {

    }
}

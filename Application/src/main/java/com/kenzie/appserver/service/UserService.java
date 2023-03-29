package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.service.model.Score;
import com.kenzie.appserver.service.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService {

    private UserRepository userRepository;

    public List<User> getAllUsers(){return (List<User>) userRepository.findAll();}

    public Optional<User> getUserByUserName(String username) {
        return Optional.ofNullable(userRepository.findByUserName(String.valueOf(username)));
    }
    public Optional<User> getUserByUserID(UUID userID) {
        return Optional.ofNullable(userRepository.findByUserID(userID));
    }
    public User saveUser(User user){return userRepository.saveUser(user);}

    public void deleteUser(User user) {userRepository.deleteUser(user.getUserName());}

    public void addUser(User user) {userRepository.saveUser(user);}

    public void deleteUserByUserName(String username) {userRepository.deleteUser(username);}
}

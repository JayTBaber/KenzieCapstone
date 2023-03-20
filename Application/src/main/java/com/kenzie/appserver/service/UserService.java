package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.service.model.Score;
import com.kenzie.appserver.service.model.User;

import java.util.List;
import java.util.Optional;

public class UserService {

    private UserRepository userRepository;

    public List<User> getAllUsers(){return (List<User>) userRepository.findAll();}

    public Optional<User> getUserByUserName(String userName) {
        return Optional.ofNullable(userRepository.findByUserName(String.valueOf(userName)));
    }
    public User saveUser(User user){return userRepository.saveUser(user);}

    public void deleteUser(User user) {userRepository.deleteUser(user.getUserName());}

    public void addUser(User user) {userRepository.saveUser(user);}

    public void deleteUserByUserName(String userName) {userRepository.deleteUser(userName);}
}

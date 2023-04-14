package com.kenzie.appserver.service;

import com.amazonaws.services.ec2.model.UserData;
import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.service.model.Score;
import com.kenzie.appserver.service.model.User;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUserByUserName(String username) {
        UserRecord userRecord = userRepository.findById(username).orElse(null);

        if (userRecord == null) {
            return null;
        } else {

            return new User(
                    userRecord.getUsername(),
                    userRecord.getPassword(),
                    userRecord.getPurse(),
                    userRecord.getWins(),
                    userRecord.getLosses());
        }
    }

    public User updateExistingUser(User user) {

        if (userRepository.existsById(user.getUserName())) {

            UserRecord userRecord = new UserRecord();
            userRecord.setUsername(user.getUserName());
            userRecord.setPassword(user.getPassword());
            userRecord.setPurse(user.getPurse());
            userRecord.setWins(userRecord.getWins());
            userRecord.setLosses(userRecord.getLosses());

            userRepository.save(userRecord);

            return user;
        } else {
            return null;
        }
    }

    public User addNewUser(User user) {

        if (userRepository.existsById(user.getUserName())) {
            return null;
        } else {
            UserRecord userRecord = new UserRecord();
            userRecord.setUsername(user.getUserName());
            userRecord.setPassword(user.getPassword());
            userRecord.setPurse(user.getPurse());
            userRecord.setWins(userRecord.getWins());
            userRecord.setLosses(userRecord.getLosses());

            userRepository.save(userRecord);

            return user;
        }
    }
}

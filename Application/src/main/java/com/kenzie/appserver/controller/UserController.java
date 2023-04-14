package com.kenzie.appserver.controller;


import com.kenzie.appserver.controller.model.*;
import com.kenzie.appserver.repositories.model.GameRecord;
import com.kenzie.appserver.repositories.model.PlayerRecord;
import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.service.EntityNotFoundException;
import com.kenzie.appserver.service.GameService;
import com.kenzie.appserver.service.PlayerService;
import com.kenzie.appserver.service.UserService;
import com.kenzie.appserver.service.model.Dealer;
import com.kenzie.appserver.service.model.Player;
import com.kenzie.appserver.service.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private PlayerService playerService;
    private GameService gameService;
    private GameRecord gameRecord;
    private PlayerRecord playerRecord;
    private PlayerResponse playerResponse;
    private UserRecord userRecord;

    public UserController(UserService userService) {this.userService = userService;}


    @GetMapping("/users/{username}")
    public ResponseEntity<UserResponse> getUserByUserName(@PathVariable String username) throws EntityNotFoundException {
        User user = userService.getUserByUserName(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            UserResponse userResponse = new UserResponse();
            userResponse.setUsername(user.getUserName());
            userResponse.setPurse(user.getPurse());
            userResponse.setWins(user.getWins());
            userResponse.setLosses(user.getLosses());
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
    }


    @PostMapping
    public ResponseEntity<UserResponse> addNewUser(@RequestBody UserRequest userRequest) {

        User userToAdd = new User(userRequest.getUsername(), userRequest.getPassword(), 100, 0, 0);
        User user = userService.addNewUser(userToAdd);

        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getUserName());
        userResponse.setPurse(100);
        userResponse.setWins(0);
        userResponse.setLosses(0);

        return ResponseEntity.ok(userResponse);
    }


    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateUserRequest updateUserRequest){

        User user = userService.getUserByUserName(updateUserRequest.getUsername());
        User updatedUser = new User(user.getUserName(), user.getPassword(), user.getPurse() + updateUserRequest.getPurse(), user.getWins() + updateUserRequest.getWins(), user.getLosses() + updateUserRequest.getLosses());

        userService.updateExistingUser(updatedUser);

        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(updatedUser.getUserName());
        userResponse.setPurse(updatedUser.getPurse());
        userResponse.setWins(updatedUser.getWins());
        userResponse.setLosses(updatedUser.getLosses());

        return ResponseEntity.ok(userResponse);
    }
}

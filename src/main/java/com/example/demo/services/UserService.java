package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repostory.UserRepostory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  UserRepostory userRepostory;

  public UserService(UserRepostory userRepostory) {
    this.userRepostory = userRepostory;
  }

  public List<User> getAllUsers() {
    return userRepostory.findAll();
  }

  public User saveOneUser(User newUser) {
    return userRepostory.save(newUser);
  }

  public User getOneUserById(Long userId) {
    return userRepostory.findById(userId).orElse(null);
  }

  public User updateOneUser(Long userId, User newUser) {
    Optional<User> user = userRepostory.findById(userId);
    if (user.isPresent()) {
      User foundUser = user.get();

      foundUser.setUsername(newUser.getUsername());
      foundUser.setPassword(newUser.getPassword());

      userRepostory.save(foundUser);

      return foundUser;
    } else {
      return null;
    }
  }

  public void deleteById(Long userId) {
    userRepostory.deleteById(userId);
  }
}


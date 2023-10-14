package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.repostory.UserRepostory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

  private UserRepostory userRepostory;

  public UserController(UserRepostory userRepostory) {
    this.userRepostory = userRepostory;
  }

  @GetMapping

  public List<User> getAllUsers() {
    return userRepostory.findAll();
  }

  @PostMapping
  public User createUser(@RequestBody User newUser) {
    return userRepostory.save(newUser);
  }

  @GetMapping("/{userId}")
  public User getOneUser(@PathVariable Long userId) {
    // custom exception
    return userRepostory.findById(userId).orElse(null);
  }

  @PutMapping("/{userId}")
  public User udpateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
    Optional<User> user = userRepostory.findById(userId);
    if (user.isPresent()) {
      User foundUser = user.get();

      foundUser.setUsername(newUser.getUsername());
      foundUser.setPassword(newUser.getPassword());

      userRepostory.save(foundUser);

      return foundUser;
    }else {
      return null;
    }
  }

  @DeleteMapping("/{userId}")
  public void deleteOneUser(@PathVariable Long userId){
  userRepostory.deleteById(userId);
  }
}

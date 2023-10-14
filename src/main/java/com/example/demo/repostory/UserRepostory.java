package com.example.demo.repostory;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepostory extends   JpaRepository<User,Long>{
}

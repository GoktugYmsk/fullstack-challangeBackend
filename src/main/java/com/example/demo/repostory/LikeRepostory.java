package com.example.demo.repostory;

import com.example.demo.entities.Like;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LikeRepostory extends   JpaRepository<Like,Long>{
}

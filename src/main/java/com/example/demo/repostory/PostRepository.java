package com.example.demo.repostory;

import com.example.demo.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends   JpaRepository<Post,Long>{
}

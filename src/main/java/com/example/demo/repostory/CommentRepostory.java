package com.example.demo.repostory;

import com.example.demo.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepostory extends   JpaRepository<Comment,Long>{
}

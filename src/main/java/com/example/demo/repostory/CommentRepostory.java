package com.example.demo.repostory;

import com.example.demo.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.List;


public interface CommentRepostory extends JpaRepository<Comment, Long> {
  List<Comment> findByUserIdAndPostId(Long userId, Long postId);

  List<Comment> findByUserId(Long userId);

  List<Comment> findByPostId(Long postId);
}

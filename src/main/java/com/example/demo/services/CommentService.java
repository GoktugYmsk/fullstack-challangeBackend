package com.example.demo.services;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.repostory.CommentRepostory;
import com.example.demo.requests.CommentRequest;
import com.example.demo.requests.CommentUpdate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
  private CommentRepostory commentRepostory;
  private UserService userService;
  private PostService postService;

  public CommentService(CommentRepostory commentRepostory, PostService postService,
                        UserService userService) {
    this.commentRepostory = commentRepostory;
  }

  public List<Comment> getAllCommmentsWithParam(Optional<Long> userId, Optional<Long> postId) {
    if (userId.isPresent() && postId.isPresent()) {
      return commentRepostory.findByUserIdAndPostId(userId.get(), postId.get());
    } else if (userId.isPresent()) {
      return commentRepostory.findByUserId(userId.get());
    } else if (postId.isPresent()) {
      return commentRepostory.findByPostId(postId.get());
    } else {
      return commentRepostory.findAll();
    }

  }


  public Comment getOneCommentById(Long commentId) {
    return commentRepostory.findById(commentId).orElse(null);
  }

  public Comment createOneComment(CommentRequest request) {
    User user = userService.getOneUserById(request.getUserId());
    Post post = postService.getOnePostById(request.getPostId());
    if (user != null && post != null) {
      Comment commentToSave = new Comment();
      commentToSave.setId(request.getId());
      commentToSave.setPost(post);
      commentToSave.setUser(user);
      commentToSave.setText(request.getText());
      return commentRepostory.save(commentToSave);
    } else {
      return null;
    }
  }

  public Comment updateOneCommmentById(Long commentId, CommentUpdate request) {
    Optional<Comment> comment = commentRepostory.findById(commentId);
    if (comment.isPresent()) {
      Comment commeToUpdate = comment.get();
      commeToUpdate.setText(request.getText());
      return commentRepostory.save(commeToUpdate);
    } else {
      return null;
    }
  }

  public void deleteOneCommmentById(Long commentId) {
commentRepostory.deleteById(commentId);
  }
}

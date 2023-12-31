package com.example.demo.controllers;

import com.example.demo.entities.Comment;
import com.example.demo.requests.CommentRequest;
import com.example.demo.requests.CommentUpdate;
import com.example.demo.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

  private CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @GetMapping
  public List<Comment> getAllComments(@RequestParam Optional<Long> userId,
                                      @RequestParam Optional<Long> postId){
     return commentService.getAllCommmentsWithParam(userId,postId);
  }

  @PostMapping
  public Comment createOneComment(@RequestBody CommentRequest request){
    return commentService.createOneComment(request);
  }

  @GetMapping("/{commentId}")
  public Comment getOneComment(@PathVariable Long commentId){
    return commentService.getOneCommentById(commentId);
  }

  @PutMapping("/{commentId}")
  public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdate request){
    return commentService.updateOneCommmentById(commentId,request);
  }

  @DeleteMapping("/{commentId}")

  public void deleteOneComment(@PathVariable Long commentId){
    commentService.deleteOneCommmentById(commentId);
  }

}

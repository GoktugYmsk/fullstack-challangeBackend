package com.example.demo.services;

import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.repostory.PostRepository;
import com.example.demo.requests.PostCreateRequest;
import com.example.demo.requests.PostUpdate;
import com.example.demo.response.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

  private PostRepository postRepository;
  private UserService userService;

  public PostService(PostRepository postRepository, UserService userService) {
    this.postRepository = postRepository;
    this.userService = userService;
  }

  public List<PostResponse> getAllPosts(Optional<Long> userId) {
    List <Post> list;
    if (userId.isPresent()){
      list =  postRepository.findByUserId(userId.get());
    }
      else{
    list =  postRepository.findAll();
    }
      return list.stream().map(p -> new PostResponse(p)).collect(Collectors.toList()) ;
  }

  public Post getOnePostById(Long postId) {
    return postRepository.findById(postId).orElse(null);
  }

  public Post createOnePost(PostCreateRequest newPostRequest) {
    User user = userService.getOneUserById(newPostRequest.getUserId());
    if (user == null) {
      return  null;
    }

    Post toSave = new Post();
    toSave.setId(newPostRequest.getId());
    toSave.setText(newPostRequest.getText());
    toSave.setTitle(newPostRequest.getTitle());
    toSave.setUser(user);

    return postRepository.save(toSave);
  }

  public Post updateOnePostById(Long postId, PostUpdate updatePost) {
    Optional<Post> post = postRepository.findById(postId);

    if(post.isPresent()){
      Post toUpdate = post.get();
      toUpdate.setText(updatePost.getText());
      toUpdate.setTitle(updatePost.getTitle());
      postRepository.save(toUpdate);
      return toUpdate;
    }
    return null;

  }

  public void deleteOnePostById(Long postId) {
    postRepository.deleteById(postId);
  }
}

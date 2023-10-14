package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="comment")
@Data
public class Comment {

  @Id
  Long id;
  Long postId;
  Long userId;
  String password;
  @Lob
  @Column(columnDefinition = "text")
  String text;
}

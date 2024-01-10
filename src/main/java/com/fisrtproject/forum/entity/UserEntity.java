package com.fisrtproject.forum.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @OneToMany(mappedBy="userEntity")
    private List<PostEntity> posts;

    @OneToMany(mappedBy="userEntity")
    private List<CommentEntity> comments;

    private String nickName;
}

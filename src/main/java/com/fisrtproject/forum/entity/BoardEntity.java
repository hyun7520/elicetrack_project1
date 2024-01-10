package com.fisrtproject.forum.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long boardId;

    private String topic;
    private Long postId;

    @OneToMany(mappedBy = "board")
    private List<PostEntity> posts = new ArrayList<>();
}

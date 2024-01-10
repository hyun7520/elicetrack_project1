package com.fisrtproject.forum.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue
    @Column(name="post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="board_id")
    private BoardEntity board;

    private String title;
    private String content;
    private LocalDateTime createdAt;



}

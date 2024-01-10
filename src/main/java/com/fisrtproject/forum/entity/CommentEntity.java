package com.fisrtproject.forum.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;

    private Long commentId;
    private String content;
    private LocalDateTime createdAt;
}

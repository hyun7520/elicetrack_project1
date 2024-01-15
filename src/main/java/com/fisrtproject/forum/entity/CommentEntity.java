package com.fisrtproject.forum.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;

    private String content;
    private Timestamp createdAt;

    public CommentEntity(PostEntity postEntity, String content, Timestamp createdAt) {
        this.postEntity = postEntity;
        this.content = content;
        this.createdAt = createdAt;
    }

    public void updateComment(String content) {
        this.content = content;
    }
}

package com.fisrtproject.forum.dto;

import com.fisrtproject.forum.entity.CommentEntity;
import com.fisrtproject.forum.entity.PostEntity;
import lombok.*;

import java.sql.Timestamp;


@NoArgsConstructor
@Getter
public class CommentRequestDto {

    private PostEntity postEntity;
    private String content;
    private Timestamp createdAt;

    public CommentEntity toEntity() {
        return new CommentEntity(postEntity, content, createdAt);
    }
}

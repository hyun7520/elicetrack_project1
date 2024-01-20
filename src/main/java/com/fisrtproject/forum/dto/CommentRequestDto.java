package com.fisrtproject.forum.dto;

import com.fisrtproject.forum.entity.CommentEntity;
import com.fisrtproject.forum.entity.PostEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;


@NoArgsConstructor
@Getter
@Setter
public class CommentRequestDto {

    private PostEntity postEntity;
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createdAt;

    public CommentEntity toEntity() {
        return new CommentEntity(postEntity, content, createdAt);
    }
}

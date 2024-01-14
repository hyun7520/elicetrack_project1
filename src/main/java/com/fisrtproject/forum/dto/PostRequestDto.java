package com.fisrtproject.forum.dto;

import com.fisrtproject.forum.entity.BoardEntity;
import com.fisrtproject.forum.entity.PostEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class PostRequestDto {

    private BoardEntity boardEntity;
    private String title;
    private String content;
    private Timestamp createdAt;

    public PostEntity toEntity() {
        return new PostEntity(boardEntity, title, content, createdAt);
    }
}

package com.fisrtproject.forum.dto;

import com.fisrtproject.forum.entity.BoardEntity;
import com.fisrtproject.forum.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {

    private BoardEntity boardEntity;
    private String title;
    private String content;
    private Long boardId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createdAt;

    public PostEntity toEntity() {
        return new PostEntity(boardEntity, title, content, createdAt);
    }
}

package com.fisrtproject.forum.dto;

import com.fisrtproject.forum.entity.BoardEntity;
import lombok.*;

@ToString
@Data
public class BoardCreateDto {

    private String topic;
    private String boardAbout;

    @Builder
    public BoardCreateDto(String topic, String boardAbout) {
        this.topic = topic;
        this.boardAbout = boardAbout;
    }
}

package com.fisrtproject.forum.dto;

import com.fisrtproject.forum.entity.BoardEntity;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCreateDto {

    private String topic;
    private String boardDescription;
}

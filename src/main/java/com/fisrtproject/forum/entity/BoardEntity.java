package com.fisrtproject.forum.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @OneToMany(mappedBy = "boardEntity")
    private List<PostEntity> posts = new ArrayList<>();

    private String topic;

    @Column(name="board_about")
    private String boardAbout;

    @Builder
    public BoardEntity(String topic, String boardAbout) {
        this.topic = topic;
        this.boardAbout =boardAbout;
    }
}

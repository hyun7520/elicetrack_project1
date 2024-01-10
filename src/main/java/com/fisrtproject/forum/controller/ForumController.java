package com.fisrtproject.forum.controller;

import com.fisrtproject.forum.entity.PostEntity;
import com.fisrtproject.forum.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class ForumController {

    private final BoardService boardService;

    @GetMapping
    public List<PostEntity> getPosts() {
        System.out.println("get all posts");
        return boardService.getAllPosts();
    }
}

package com.fisrtproject.forum.controller;

import com.fisrtproject.forum.entity.PostEntity;
import com.fisrtproject.forum.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{postId}")
    public Optional<PostEntity> getPostById(@PathVariable("postId") Long id) {
        Optional<PostEntity> foundPost = boardService.findSubject(id);
        return foundPost;
    }

}

package com.fisrtproject.forum.controller;

import com.fisrtproject.forum.entity.BoardEntity;
import com.fisrtproject.forum.entity.PostEntity;
import com.fisrtproject.forum.service.BoardService;
import com.fisrtproject.forum.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class ForumController {

    private final BoardService boardService;
    private final PostService postService;

    @GetMapping
    public List<BoardEntity> getBoards() {
        System.out.println("get all posts");
        return boardService.getAllPosts();
    }

    @GetMapping("/{boardId}")
    public Optional<BoardEntity> getBoardById(@PathVariable("postId") Long id) {
        return boardService.findSubject(id);
    }

    // 게시글 생성 페이지 - 추후 작성
    @GetMapping("/create")
    public String getAddView() {
        return "addPostForm";
    }
}

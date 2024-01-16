package com.fisrtproject.forum.controller;

import com.fisrtproject.forum.dto.PostPatchDto;
import com.fisrtproject.forum.dto.PostRequestDto;
import com.fisrtproject.forum.entity.PostEntity;
import com.fisrtproject.forum.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/forum")
public class PostController {

    private final PostService postService;

    @GetMapping("/{boardId}/posts")
    public Page<PostEntity> getAllPosts(@PathVariable("boardId") Long id,
                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "5") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return postService.findPostsByBoard(id, pageRequest);
    }

    @GetMapping("/{boardId}/posts/{postId}")
    public PostEntity getPostById(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId) {
        return postService.findPost(boardId, postId);
    }

    @PostMapping("/{boardId}/posts/create")
    public PostEntity createPost(@RequestBody PostRequestDto postRequestDto) {

        return postService.savePost(postRequestDto.toEntity());

    }

    @PostMapping("/{boardId}/posts/{postId}/update")
    public void updateBoard(@PathVariable("boardId") Long boardId,
                            @PathVariable("postId") Long postId,
                            @RequestBody PostPatchDto postPatchDto) {
        postService.updatePost(boardId, postId, postPatchDto);
    }

    @DeleteMapping("/{boardId}/posts/{postId}/delete")
    public void deletePost(@PathVariable("boardId") Long boardId,
                           @PathVariable("postId") Long postId) {
        postService.deletePost(boardId, postId);
    }
}

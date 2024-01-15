package com.fisrtproject.forum.controller;

import com.fisrtproject.forum.dto.BoardCreateDto;
import com.fisrtproject.forum.dto.CommentRequestDto;
import com.fisrtproject.forum.dto.PostPatchDto;
import com.fisrtproject.forum.dto.PostRequestDto;
import com.fisrtproject.forum.entity.BoardEntity;
import com.fisrtproject.forum.entity.CommentEntity;
import com.fisrtproject.forum.entity.PostEntity;
import com.fisrtproject.forum.service.BoardService;
import com.fisrtproject.forum.service.CommentService;
import com.fisrtproject.forum.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class ForumController {

    private final BoardService boardService;
    private final PostService postService;
    private final CommentService commentService;

    // Board CRUD 메소드
    @GetMapping
    public List<BoardEntity> getBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{boardId}")
    public Optional<BoardEntity> getBoardById(@PathVariable("boardId") Long id) {
        return boardService.findBoard(id);
    }

    @PostMapping("/createBoard")
    public void createNewBoard(@RequestBody BoardCreateDto boardCreateDto) {
        boardService.createBoard(boardCreateDto);
    }

    @PostMapping("/{boardId}/update")
    public void updateBoard(@PathVariable("boardId") Long id, @RequestBody BoardCreateDto boardCreateDto) {
        boardService.updateBoard(id, boardCreateDto);
    }

    @DeleteMapping("/{boardId}/delete")
    public void deleteBoard(@PathVariable("boardId") Long id) {
        boardService.deleteBoard(id);
    }


    // Post CRUD 메소드
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

    // Comment Curd 메소드
    @GetMapping("/{boardId}/posts/{postId}/comments")
    public List<CommentEntity> getAllComments(@PathVariable("postId") Long postId) {
        return commentService.findAllComments(postId);
    }

    @GetMapping("/{boardId}/posts/{postId}/comments/{commentId}")
    public CommentEntity getComments(@PathVariable("postId") Long postId,
                                           @PathVariable("commentId") Long commentId) {
        return commentService.findCommentById(postId, commentId);
    }

    @PostMapping("/{boardId}/posts/{postId}/comments/create")
    public CommentEntity createComment(@RequestBody CommentRequestDto commentRequestDto) {
        return commentService.createComment(commentRequestDto.toEntity());
    }

}

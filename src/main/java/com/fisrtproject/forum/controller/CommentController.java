package com.fisrtproject.forum.controller;

import com.fisrtproject.forum.dto.CommentPatchDto;
import com.fisrtproject.forum.dto.CommentRequestDto;
import com.fisrtproject.forum.entity.CommentEntity;
import com.fisrtproject.forum.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/forum")
public class CommentController {

    private final CommentService commentService;

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

    @PostMapping("/{boardId}/posts/{postId}/comments/{commentId}/update")
    public void updateComment(@PathVariable("commentId") Long commentId,
                              @RequestBody CommentPatchDto commentPatchDto) {
        commentService.updateComment(commentId, commentPatchDto);
    }

    @DeleteMapping("/{boardId}/posts/{postId}/comments/{commentId}/delete")
    public void deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
    }
}

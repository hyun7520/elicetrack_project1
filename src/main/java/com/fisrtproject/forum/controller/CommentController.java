package com.fisrtproject.forum.controller;

import com.fisrtproject.forum.dto.CommentPatchDto;
import com.fisrtproject.forum.dto.CommentRequestDto;
import com.fisrtproject.forum.entity.CommentEntity;
import com.fisrtproject.forum.entity.PostEntity;
import com.fisrtproject.forum.service.CommentService;
import com.fisrtproject.forum.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/forum")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    @GetMapping("none")
    public List<CommentEntity> getAllComments(Model model,
                                              @PathVariable("postId") Long postId) {

        List<CommentEntity> comments = commentService.findAllComments(postId);
        return comments;
    }

    @GetMapping("/{boardId}/posts/{postId}/comments/{commentId}")
    public CommentEntity getComments(@PathVariable("postId") Long postId,
                                     @PathVariable("commentId") Long commentId) {
        return commentService.findCommentById(postId, commentId);
    }

    @GetMapping("/{postId}/comments/create")
    public String toCreateCommentPage(Model model,
                                      @PathVariable("postId") Long id) {
        CommentRequestDto commentRequestDto = new CommentRequestDto();
        model.addAttribute("dto", commentRequestDto);
        model.addAttribute("postId", id);
        return "createComment";
    }

    @PostMapping("/{postId}/comments/create")
    public String createComment(@PathVariable("postId") Long postId,
                                       CommentRequestDto commentRequestDto) {
        PostEntity foundPost = postService.findPostById(postId);
        commentRequestDto.setPostEntity(foundPost);
        commentService.createComment(commentRequestDto.toEntity());
        return "redirect:/forum";
    }

    @GetMapping("/{postId}/comments/{commentId}/update")
    public String toUpdateCommentPage(Model model,
                                      @PathVariable("postId") Long postId,
                                      @PathVariable("commentId") Long commentId) {
        CommentPatchDto commentPatchDto = new CommentPatchDto();
        CommentEntity commentEntity = commentService.findCommentById(postId, commentId);
        model.addAttribute("comment", commentEntity);
        model.addAttribute("dto", commentPatchDto);
        return "updateComment";
    }

    @PutMapping("/{postId}/comments/{commentId}/update")
    public String updateComment(@PathVariable("commentId") Long commentId,
                              CommentPatchDto commentPatchDto) {
        commentService.updateComment(commentId, commentPatchDto);
        return "redirect:/forum";
    }

    @DeleteMapping("/comments/{commentId}/delete")
    public String deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/forum";
    }
}

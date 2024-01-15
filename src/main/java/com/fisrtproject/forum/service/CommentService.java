package com.fisrtproject.forum.service;

import com.fisrtproject.forum.dto.CommentPatchDto;
import com.fisrtproject.forum.entity.CommentEntity;
import com.fisrtproject.forum.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public List<CommentEntity> findAllComments(Long postId) {
        return commentRepository.findAllCommentByPostEntity_Id(postId);
    }

    public CommentEntity findCommentById(Long postId, Long commentId) {
        return commentRepository.findCommentByPostEntity_IdAndId(postId, commentId);
    }

    public CommentEntity createComment(CommentEntity commentEntity) {
        return commentRepository.save(commentEntity);
    }

    public void updateComment(Long commentId, CommentPatchDto commentPatchDto) {
        CommentEntity comment = commentRepository.findCommentById(commentId);
        comment.updateComment(commentPatchDto.getContent());
        commentRepository.save(comment);
    }
}

package com.fisrtproject.forum.repository;

import com.fisrtproject.forum.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findAllCommentByPostEntity_Id(Long postId);

    CommentEntity findCommentByPostEntity_IdAndId(Long postId, Long commentId);

    CommentEntity findCommentById(Long commentId);
}

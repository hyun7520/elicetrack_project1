package com.fisrtproject.forum.repository;

import com.fisrtproject.forum.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    public List<CommentEntity> findAllCommentByPostEntity_Id(Long postId);

    public CommentEntity findCommentByPostEntity_IdAndId(Long postId, Long commentId);
}

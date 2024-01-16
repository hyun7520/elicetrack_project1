package com.fisrtproject.forum.repository;

import com.fisrtproject.forum.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    Page<PostEntity> findByBoardEntity_id(Long id, Pageable pageable);

    PostEntity findPostByBoardEntity_IdAndId(Long boardId, Long postId);

}
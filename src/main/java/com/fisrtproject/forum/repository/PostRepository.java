package com.fisrtproject.forum.repository;

import com.fisrtproject.forum.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    Page<PostEntity> findByBoardEntity_id(Long id, Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT * FROM post WHERE board_id = ?1 AND post_id = ?2")
    PostEntity findPostByBoardIdAndPostId(Long boardId, Long postId);
}
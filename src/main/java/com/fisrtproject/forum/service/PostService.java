package com.fisrtproject.forum.service;

import com.fisrtproject.forum.entity.BoardEntity;
import com.fisrtproject.forum.entity.PostEntity;
import com.fisrtproject.forum.repository.JdbcTemplateBoardRepository;
import com.fisrtproject.forum.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    // 게시판 별로 게시글의 조회하는 메서드
    // PostRepository의 메서드를 postService를 통해 조회하려고 하면 에러가 발생했다.
    // 반면 같은 PostRepository의 메서드를 controller에서 바로 호출하는 경우는 에러가 없었다.
    // 리마인더: 이유를 찾아볼 것.
//    public Page<PostEntity> findAllPosts(Long id, Pageable pageable) {
//        return PostRepository.findById(id, pageable);
//    }

    public PostEntity findPost(Long boardId, Long postId) {
        return postRepository.findPostByBoardIdAndPostId(boardId, postId);
    }
}

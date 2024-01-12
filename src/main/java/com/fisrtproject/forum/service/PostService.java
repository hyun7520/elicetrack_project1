package com.fisrtproject.forum.service;

import com.fisrtproject.forum.entity.BoardEntity;
import com.fisrtproject.forum.entity.PostEntity;
import com.fisrtproject.forum.repository.JdbcTemplateBoardRepository;
import com.fisrtproject.forum.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public PostEntity findPost(Long boardId, Long postId) {
        return postRepository.findPostByBoardIdAndPostId(boardId, postId);
    }
}

package com.fisrtproject.forum.service;

import com.fisrtproject.forum.dto.PostPatchDto;
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


    public Page<PostEntity> findPostsByBoard(Long id, Pageable pageable) {
        return postRepository.findByBoardEntity_id(id, pageable);
    }

    public PostEntity findPost(Long boardId, Long postId) {
        return postRepository.findPostByBoardEntity_IdAndId(boardId, postId);
    }

    public void updatePost(Long boardId, Long postId, PostPatchDto postPatchDto) {
        PostEntity post = postRepository.findPostByBoardEntity_IdAndId(boardId, postId);
        post.updatePost(postPatchDto.getTitle(), postPatchDto.getContent());
        postRepository.save(post);
    }

    public void deletePost(Long boardId, Long postId) {
        PostEntity savedPost = postRepository.findPostByBoardEntity_IdAndId(boardId, postId);
        postRepository.delete(savedPost);
    }
}

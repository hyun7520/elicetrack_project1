package com.fisrtproject.forum.service;

import com.fisrtproject.forum.dto.PostPatchDto;
import com.fisrtproject.forum.entity.CommentEntity;
import com.fisrtproject.forum.entity.PostEntity;
import com.fisrtproject.forum.repository.CommentRepository;
import com.fisrtproject.forum.repository.PostRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public Page<PostEntity> findPostsByBoard(Long id, Pageable pageable) {
        return postRepository.findByBoardEntity_id(id, pageable);
    }

    public PostEntity findPostById(Long id) {
        return postRepository.findPostById(id);
    }

    public PostEntity findPost(Long boardId, Long postId) {
        return postRepository.findPostByBoardEntity_IdAndId(boardId, postId);
    }

    public PostEntity savePost(PostEntity postEntity) {
        return postRepository.save(postEntity);
    }

    public void updatePost(Long boardId, Long postId, PostPatchDto postPatchDto) {
        PostEntity post = postRepository.findPostByBoardEntity_IdAndId(boardId, postId);
        post.updatePost(postPatchDto.getTitle(), postPatchDto.getContent());
        postRepository.save(post);
    }

    public void deletePost(Long boardId, Long postId) {
        PostEntity savedPost = postRepository.findPostByBoardEntity_IdAndId(boardId, postId);
        List<CommentEntity> savedComment = commentRepository.findAllCommentByPostEntity_Id(postId);
        commentRepository.deleteAll(savedComment);
        postRepository.delete(savedPost);
    }
}

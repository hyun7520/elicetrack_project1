package com.fisrtproject.forum.service;

import com.fisrtproject.forum.entity.PostEntity;
import com.fisrtproject.forum.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public PostEntity createPost(PostEntity post) {
        return postRepository.save(post);
    }
}

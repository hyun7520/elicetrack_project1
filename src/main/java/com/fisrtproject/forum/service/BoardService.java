package com.fisrtproject.forum.service;

import com.fisrtproject.forum.entity.PostEntity;
import com.fisrtproject.forum.repository.JdbcTemplateBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final JdbcTemplateBoardRepository boardRepository;

    public List<PostEntity> getAllPosts() {
        return boardRepository.findAll();
    }
}

package com.fisrtproject.forum.service;

import com.fisrtproject.forum.entity.BoardEntity;
import com.fisrtproject.forum.repository.JdbcTemplateBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final JdbcTemplateBoardRepository boardRepository;

    public List<BoardEntity> getAllPosts() {
        return boardRepository.findAll();
    }

    public Optional<BoardEntity> findSubject(Long id) {
        return boardRepository.findById(id);
    }
}

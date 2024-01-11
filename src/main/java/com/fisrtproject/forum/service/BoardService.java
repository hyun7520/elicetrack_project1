package com.fisrtproject.forum.service;

import com.fisrtproject.forum.dto.BoardCreateDto;
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

    public void createBoard(BoardCreateDto boardCreateDto) {
        BoardEntity board = BoardEntity.builder()
                .topic(boardCreateDto.getTopic())
                .boardAbout(boardCreateDto.getBoardAbout())
                .build();
        boardRepository.save(board);
    }

    public List<BoardEntity> getAllBoards() {
        return boardRepository.findAll();
    }

    public Optional<BoardEntity> findBoard(Long id) {
        return boardRepository.findById(id);
    }

    public void updateBoard(Long id, BoardCreateDto boardCreateDto) {
        boardRepository.updateBoard(id, boardCreateDto);
    }

}

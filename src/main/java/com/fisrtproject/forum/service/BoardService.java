package com.fisrtproject.forum.service;

import com.fisrtproject.forum.dto.BoardCreateDto;
import com.fisrtproject.forum.entity.BoardEntity;
import com.fisrtproject.forum.repository.JdbcTemplateBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
                .boardDescription(boardCreateDto.getBoardDescription())
                .build();
        boardRepository.save(board);
    }

    public List<BoardEntity> getAllBoards() {
        return boardRepository.findAll();
    }

    public BoardEntity findBoard(Long id) {

        BoardEntity board = null;

        Optional<BoardEntity> foundBoard = boardRepository.findById(id);

        if (foundBoard.isPresent()) {
            board = foundBoard.get();
        } else {
            return null;
        }
        return board;
    }

    public void updateBoard(Long id, BoardCreateDto boardCreateDto) {
        boardRepository.updateBoard(id, boardCreateDto);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteBoard(id);
    }

}

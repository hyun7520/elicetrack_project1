package com.fisrtproject.forum.controller;

import com.fisrtproject.forum.dto.BoardCreateDto;
import com.fisrtproject.forum.entity.BoardEntity;
import com.fisrtproject.forum.entity.PostEntity;
import com.fisrtproject.forum.service.BoardService;
import com.fisrtproject.forum.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class ForumController {

    private final BoardService boardService;
    private final PostService postService;

    @GetMapping
    public List<BoardEntity> getBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{boardId}")
    public Optional<BoardEntity> getBoardById(@PathVariable("boardId") Long id) {
        return boardService.findBoard(id);
    }

    @PostMapping("/createBoard")
    public void createNewBoard(@RequestBody BoardCreateDto boardCreateDto) {
        boardService.createBoard(boardCreateDto);
    }

    @PostMapping("/{boardId}/update")
    public void updateBoard(@PathVariable("boardId") Long id, @RequestBody BoardCreateDto boardCreateDto) {
        boardService.updateBoard(id, boardCreateDto);
    }

    @DeleteMapping("/{boardId}/delete")
    public void deleteBoard(@PathVariable("boardId") Long id) {
        boardService.deleteBoard(id);
    }
}

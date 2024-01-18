package com.fisrtproject.forum.controller;

import com.fisrtproject.forum.dto.*;
import com.fisrtproject.forum.entity.BoardEntity;

import com.fisrtproject.forum.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/forum")
public class ForumController {

    private final BoardService boardService;

    // 조회 메서드
    @GetMapping
    public String getBoards(Model model) {
        List<BoardEntity> boards = boardService.getAllBoards();
        model.addAttribute("boardPage", boards);
        return "forum";
    }

    // 사용하지 않는 특정 게시판 찾기 메서드 삭제하기는 아까운데
    @GetMapping("/{boardId}")
    public BoardEntity getBoardById(@PathVariable("boardId") Long id) throws Exception {

        BoardEntity board = boardService.findBoard(id);

        if(board == null) {
            throw new NoSuchObjectException("board was not found!");
        }
        return boardService.findBoard(id);
    }

    // create 메서드
    @GetMapping("/createBoard")
    public String toCreateBoardPage() {
        return "createBoard";
    }

    @PostMapping("/createBoard")
    //    Resolved [org.springframework.web.HttpMediaTypeNotSupportedException
    //    검색 결과 @RequestBody를 삭제 하는 것으로 해결
    //    json의 MultipartFile files로 인해 발생하는 오류아는데 아직 이해가 안된다...
    public String createNewBoard(BoardCreateDto boardCreateDto) {
        boardService.createBoard(boardCreateDto);
        return "redirect:/forum";
    }

    // update 메서드
    @GetMapping("/{boardId}/update")
    public String updateBoard(Model model,
                              @PathVariable("boardId") Long id) throws Exception {

        BoardEntity board = boardService.findBoard(id);

        if(board == null) {
            throw new NoSuchObjectException("board was not found!");
            // throw 와 동시에 redirect? 방법 찾아보자
            // return "redirect:/forum";
        } else {
            model.addAttribute("updateObject", board);
            return "updateBoard";
        }
    }

    // 멱등성을 위해 postmapping 에서 putmapping으로 수정
    @PutMapping("/{boardId}/update")
    public String updateBoard(@PathVariable("boardId") Long id,
                              BoardCreateDto boardCreateDto) {
        boardService.updateBoard(id, boardCreateDto);
        return "redirect:/forum";
    }

    // delete 메서드
    @DeleteMapping("/{boardId}/delete")
    public String deleteBoard(@PathVariable("boardId") Long id) {
        boardService.deleteBoard(id);
        return "redirect:/forum";
    }
}

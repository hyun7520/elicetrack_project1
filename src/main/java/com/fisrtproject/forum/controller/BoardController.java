package com.fisrtproject.forum.controller;

import com.fisrtproject.forum.service.BoardService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    private BoardService boardService;

    // 의존성 주입
    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("content", boardService.hello());
        return "home";
    }
}

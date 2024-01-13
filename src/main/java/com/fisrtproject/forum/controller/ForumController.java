package com.fisrtproject.forum.controller;

import com.fisrtproject.forum.dto.BoardCreateDto;
import com.fisrtproject.forum.entity.BoardEntity;
import com.fisrtproject.forum.entity.PostEntity;
import com.fisrtproject.forum.repository.PostRepository;
import com.fisrtproject.forum.service.BoardService;
import com.fisrtproject.forum.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class ForumController {

    private final BoardService boardService;
    private final PostService postService;
    private final PostRepository postRepository;

    // Board CRUD 메소드
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


    // Post CRUD 메소드
    @GetMapping("/{boardId}/posts")
    public Page<PostEntity> getAllPosts(@PathVariable("boardId") Long id,
                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "5") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return postRepository.findByBoardEntity_id(id, pageRequest);
    }

    @GetMapping("/{boardId}/posts/{postId}")
    public PostEntity getPostById(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId) {
        return postService.findPost(boardId, postId);
    }
}

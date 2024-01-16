package com.fisrtproject.forum.controller;

import com.fisrtproject.forum.dto.*;
import com.fisrtproject.forum.entity.BoardEntity;
import com.fisrtproject.forum.entity.CommentEntity;
import com.fisrtproject.forum.entity.PostEntity;
import com.fisrtproject.forum.service.BoardService;
import com.fisrtproject.forum.service.CommentService;
import com.fisrtproject.forum.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/forum")
public class ForumController {

    private final BoardService boardService;
    private final PostService postService;
    private final CommentService commentService;

    // Board CRUD 메소드
    @GetMapping
    public String getBoards(Model model) {
        List<BoardEntity> boards = boardService.getAllBoards();
        model.addAttribute("boardPage", boards);
        return "forum";
    }

    @GetMapping("/{boardId}")
    public BoardEntity getBoardById(@PathVariable("boardId") Long id) throws Exception {

        BoardEntity board = boardService.findBoard(id);

        if(board == null) {
            throw new NoSuchObjectException("board was not found!");
        }

        return boardService.findBoard(id);
    }

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

    @GetMapping("/{boardId}/update")
    public String updateBoard(Model model,
                              @PathVariable("boardId") Long id) throws Exception {

        BoardEntity board = boardService.findBoard(id);

        if(board == null) {
            throw new NoSuchObjectException("board was not found!");
            //  return "redirect:/forum";
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

    @DeleteMapping("/{boardId}/delete")
    public String deleteBoard(@PathVariable("boardId") Long id) {
        boardService.deleteBoard(id);
        return "redirect:/forum";
    }


    // Post CRUD 메소드
    @GetMapping("/{boardId}/posts")
    public Page<PostEntity> getAllPosts(@PathVariable("boardId") Long id,
                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "5") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return postService.findPostsByBoard(id, pageRequest);
    }

    @GetMapping("/{boardId}/posts/{postId}")
    public PostEntity getPostById(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId) {
        return postService.findPost(boardId, postId);
    }

    @PostMapping("/{boardId}/posts/create")
    public PostEntity createPost(@RequestBody PostRequestDto postRequestDto) {

        return postService.savePost(postRequestDto.toEntity());

    }

    @PostMapping("/{boardId}/posts/{postId}/update")
    public void updateBoard(@PathVariable("boardId") Long boardId,
                            @PathVariable("postId") Long postId,
                            @RequestBody PostPatchDto postPatchDto) {
        postService.updatePost(boardId, postId, postPatchDto);
    }

    @DeleteMapping("/{boardId}/posts/{postId}/delete")
    public void deletePost(@PathVariable("boardId") Long boardId,
                           @PathVariable("postId") Long postId) {
        postService.deletePost(boardId, postId);
    }

    // Comment CRUD 메소드
    @GetMapping("/{boardId}/posts/{postId}/comments")
    public List<CommentEntity> getAllComments(@PathVariable("postId") Long postId) {
        return commentService.findAllComments(postId);
    }

    @GetMapping("/{boardId}/posts/{postId}/comments/{commentId}")
    public CommentEntity getComments(@PathVariable("postId") Long postId,
                                           @PathVariable("commentId") Long commentId) {
        return commentService.findCommentById(postId, commentId);
    }

    @PostMapping("/{boardId}/posts/{postId}/comments/create")
    public CommentEntity createComment(@RequestBody CommentRequestDto commentRequestDto) {
        return commentService.createComment(commentRequestDto.toEntity());
    }

    @PostMapping("/{boardId}/posts/{postId}/comments/{commentId}/update")
    public void updateComment(@PathVariable("commentId") Long commentId,
                              @RequestBody CommentPatchDto commentPatchDto) {
        commentService.updateComment(commentId, commentPatchDto);
    }

    @DeleteMapping("/{boardId}/posts/{postId}/comments/{commentId}/delete")
    public void deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
    }

}

package com.fisrtproject.forum.controller;

import com.fisrtproject.forum.dto.PostPatchDto;
import com.fisrtproject.forum.dto.PostRequestDto;
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

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/forum")
public class PostController {

    private final PostService postService;
    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/{boardId}/posts")
    public String getAllPosts(Model model,
                                        @PathVariable("boardId") Long id,
                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "5") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<PostEntity> foundPosts = postService.findPostsByBoard(id, pageRequest);
        BoardEntity selectedBoard = boardService.findBoard(id);
        model.addAttribute("posts", foundPosts);
        model.addAttribute("board", selectedBoard);

        return "showPosts";
    }

    @GetMapping("/{boardId}/posts/{postId}")
    public String getPostById( Model model,
                               @PathVariable("boardId") Long boardId,
                               @PathVariable("postId") Long postId) {
        PostEntity foundPost = postService.findPost(boardId, postId);
        List<CommentEntity> comments = commentService.findAllComments(postId);

        model.addAttribute("postInfo", foundPost);
        model.addAttribute("comments", comments);

        return "showPost";
    }

    @GetMapping("/{boardId}/posts/create")
    public String toCreatePostPage(Model model) {
        List<BoardEntity> boardList = boardService.getAllBoards();
        PostRequestDto postRequestDto = new PostRequestDto();
        model.addAttribute("dto", postRequestDto);
        model.addAttribute("boardList", boardList);
        return "createPost";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute("dto") PostRequestDto postRequestDto) {
        Long boardId = postRequestDto.getBoardId();
        BoardEntity board = boardService.findBoard(boardId);
        postRequestDto.setBoardEntity(board);
        postService.savePost(postRequestDto.toEntity());
        return "redirect:/forum";
    }

    @GetMapping("{boardId}/posts/{postId}/update")
    public String toUpdatePostPage(Model model,
                                 @PathVariable("boardId") Long boardId,
                                 @PathVariable("postId") Long postId) {

        PostEntity post = postService.findPost(boardId, postId);
        PostPatchDto postPatchDto = new PostPatchDto();

        if(post == null) {
            return null;
            // throw 와 동시에 redirect? 방법 찾아보자
            // return "redirect:/forum";
        } else {
            model.addAttribute("dto", postPatchDto);
            model.addAttribute("updateObject", post);
            return "updatePost";
        }
    }

    @PutMapping("/{boardId}/posts/{postId}/update")
    public String updatePost(@PathVariable("boardId") Long boardId,
                            @PathVariable("postId") Long postId,
                            @ModelAttribute("dto") PostPatchDto postPatchDto) {
        postService.updatePost(boardId, postId, postPatchDto);
        return "redirect:/forum";
    }

    @DeleteMapping("/{boardId}/posts/{postId}/delete")
    public String deletePost(Model model,
                             @PathVariable("boardId") Long boardId,
                             @PathVariable("postId") Long postId) {

        postService.deletePost(boardId, postId);

        return "redirect:/forum";
    }
}

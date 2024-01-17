package com.fisrtproject.forum.controller;

import com.fisrtproject.forum.dto.PostPatchDto;
import com.fisrtproject.forum.dto.PostRequestDto;
import com.fisrtproject.forum.entity.BoardEntity;
import com.fisrtproject.forum.entity.PostEntity;
import com.fisrtproject.forum.service.BoardService;
import com.fisrtproject.forum.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/forum")
public class PostController {

    private final PostService postService;
    private final BoardService boardService;

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
    public PostEntity getPostById(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId) {
        return postService.findPost(boardId, postId);
    }

    @GetMapping("/{boardId}/posts/create")
    public String toCreatePostPage(Model model) {
        PostRequestDto postRequestDto = new PostRequestDto();
        model.addAttribute("dto", postRequestDto);
        return "createPost";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute("dto") PostRequestDto postRequestDto) {
        BoardEntity board = boardService.findBoard(postRequestDto.getBoardId());
        postRequestDto.setBoardEntity(board);
        postService.savePost(postRequestDto.toEntity());
        return "redirect:/forum";
    }

    @PostMapping("/{boardId}/posts/{postId}/update")
    public void updateBoard(@PathVariable("boardId") Long boardId,
                            @PathVariable("postId") Long postId,
                            @RequestBody PostPatchDto postPatchDto) {
        postService.updatePost(boardId, postId, postPatchDto);
    }

    @DeleteMapping("/{boardId}/posts/{postId}/delete")
    public String deletePost(Model model,
                             @PathVariable("boardId") Long boardId,
                             @PathVariable("postId") Long postId,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "5") int size) {

        postService.deletePost(boardId, postId);

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<PostEntity> foundPosts = postService.findPostsByBoard(boardId, pageRequest);
        BoardEntity selectedBoard = boardService.findBoard(postId);

        model.addAttribute("posts", foundPosts);
        model.addAttribute("board", selectedBoard);

        return "showPosts";
    }
}

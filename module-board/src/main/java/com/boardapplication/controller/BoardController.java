package com.boardapplication.controller;

import com.boardapplication.dto.BoardDto;
import com.boardapplication.dto.CommentCreateRequestDto;
import com.boardapplication.service.BoardService;
import com.boardapplication.service.CommentService;
import com.boardapplication.vo.CreateBoardForm;
import com.core.entity.Board;
import com.core.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;


    @GetMapping("/boardList")
    public String list(Model model, @PageableDefault(page = 0, size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<BoardDto> boardDtoPage = boardService.getBoardList(pageable);
        //Pageable은 0부터 시작이라 1을 더해줘야함
        int nowPage = boardDtoPage.getPageable().getPageNumber() + 1;
        // 현재 페이지에서 가장 앞에 페이지 번호를 보여즘
        // -1값이 들어가는 것을 막기 위해 max값으로 두개 값을 넣고 더 큰값을 넣어줌
        int startPage = Math.max(nowPage - 4, 1);
        //현재 페이지에서 가장 뒤에 페이지 번호를 보여줌 페이지 번호가 넘어가버리면 안되기에  min사용
        int endPage = Math.min(startPage + 9, boardDtoPage.getTotalPages());
        model.addAttribute("boardPage", boardDtoPage);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "boardList.html";
    }

    @GetMapping("/searchBoardList")
    public String searchBoardList(String searchType, String keyword, Model model, @PageableDefault(page = 0, size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<BoardDto> boardDtoPage = boardService.searchBoardList(searchType, keyword, pageable);
        if (boardDtoPage.isEmpty()) {
            model.addAttribute("noResults", true);
        }
        //Pageable은 0부터 시작이라 1을 더해줘야함
        int nowPage = boardDtoPage.getPageable().getPageNumber() + 1;
        // 현재 페이지에서 가장 앞에 페이지 번호를 보여즘
        // -1값이 들어가는 것을 막기 위해 max값으로 두개 값을 넣고 더 큰값을 넣어줌
        int startPage = Math.max(nowPage - 4, 1);
        //현재 페이지에서 가장 뒤에 페이지 번호를 보여줌 페이지 번호가 넘어가버리면 안되기에  min사용
        int endPage = Math.min(startPage + 9, boardDtoPage.getTotalPages());
        model.addAttribute("boardPage", boardDtoPage);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "searchBoardList.html";

    }

    @GetMapping("/board/write")
    public String createBoardView() {
        return "board/write";
    }

    @PostMapping("/board/write")
    public String createBoard(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam MultipartFile file,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request
    ) throws IOException {
        Long userId = 1L;
        CreateBoardForm createBoardForm = CreateBoardForm.builder()
                .title(title)
                .content(content)
                .file(file)
                .build();

        Long savedBoardId = boardService.save(userId, createBoardForm, file);
        redirectAttributes.addAttribute("boardId", savedBoardId);
        return "redirect:/board/{boardId}";
    }

    @GetMapping("/board/{boardId}")
    public String getBoardDetailView(Model model, @PathVariable Long boardId, HttpServletResponse response) throws IOException {
        Board board = boardService.getBoardById(boardId);
        model.addAttribute("board", board);
        List<Comment> list = commentService.getAllByBoardId(boardId);
        model.addAttribute("comments", list);
        System.out.println(list);
        return "board/detail";
    }

    @PostMapping("/board/{boardId}/comment")
    public String createComment(@ModelAttribute CommentCreateRequestDto commentCreateRequestDto, @PathVariable Long boardId, RedirectAttributes redirectAttributes) throws IOException {
        System.out.println(commentCreateRequestDto);
        Comment comment = commentService.createParent(1L, commentCreateRequestDto.getContent(), boardId);
//        redirectAttributes.addAttribute("comment", comment);
        return "redirect:/board/{boardId}";
    }

    @PostMapping("/board/{boardId}/comment/{commentId}")
    public String createChildrenComment(@ModelAttribute CommentCreateRequestDto commentCreateRequestDto, @PathVariable Long commentId, @PathVariable Long boardId, RedirectAttributes redirectAttributes) throws IOException {
        commentService.createChildren(1L, boardId, commentId, commentCreateRequestDto);
//        redirectAttributes.addAttribute("comment", comment);
        return "redirect:/board/{boardId}";
    }
}
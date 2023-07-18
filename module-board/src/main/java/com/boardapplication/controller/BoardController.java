package com.boardapplication.controller;

import com.boardapplication.dto.BoardDto;
import com.boardapplication.service.BoardService;
import com.boardapplication.vo.CreateBoardForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

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
            HttpServletRequest request
    ) throws IOException {
        Long userId = 1L;
        CreateBoardForm createBoardForm = CreateBoardForm.builder()
                .title(title)
                .content(content)
                .file(file)
                .build();

        Long savedBoardId = boardService.save(userId, createBoardForm, file);
        return "redirect:/board" + savedBoardId;
    }

    @GetMapping("/board/{boardId}")
    public String getBoardDetailView(@PathVariable Long boardId, HttpServletResponse response) throws IOException {
        response.sendError(404);

//        boardService.getBoardById(boardId);
        return "board/main";
    }
}
package com.boardapplication.controller;

import com.boardapplication.dto.BoardDto;
import com.boardapplication.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/boardList")
    public String list(Model model){
        List<BoardDto> boardDtoList = boardService.getBoardList()
                .stream()
                .sorted(Comparator.comparing(BoardDto::getId).reversed())
                .collect(Collectors.toList());
        model.addAttribute("boardList", boardDtoList);
        return "boardList.html";
    }
}

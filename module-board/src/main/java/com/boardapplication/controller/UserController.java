package com.boardapplication.controller;

import com.boardapplication.configuration.JoinValidator;
import com.boardapplication.dto.JoinDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final JoinValidator joinValidator;

    @GetMapping
    public String index() {
        return "test";
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("joinForm", new JoinDTO());
        return "join";
    }

    @PostMapping("/join")
    public String joinForm(@Valid JoinDTO joinDTO, Errors errors) {
        if (errors.hasErrors()) {
            return "join";
        }

        joinValidator.validate(joinDTO, errors);
        if (errors.hasErrors()) {
            return "join";
        }

        return "redirect:/test"; //구현 후 login으로 바꿈
    }
}

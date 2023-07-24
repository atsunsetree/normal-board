package com.boardapplication.controller;

import com.boardapplication.configuration.JoinValidator;
import com.boardapplication.dto.CheckDuplicateNicknameResponseDto;
import com.boardapplication.dto.JoinDto;
import com.boardapplication.repository.UserRepository;
import com.boardapplication.service.UserService;
import com.core.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final JoinValidator joinValidator;
    private final UserService userService;
    private final UserRepository userRepository;

    @InitBinder("joinDto")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(joinValidator);
    }

    @GetMapping("/")
    public String index(Authentication authentication) {

        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/boardList";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("joinDto", new JoinDto());
        return "/join";
    }

    @PostMapping("/join")
    public String joinDto(
            @Valid JoinDto joinDto,
            Errors errors
    ) {
        if (errors.hasErrors()) {
            return "/join";
        }
        User user = userService.processNewUser(joinDto);

        return "redirect:/login";
    }

    @GetMapping("/checkusername")
    public ResponseEntity<CheckDuplicateNicknameResponseDto> checkUsername(@RequestParam String username) {
        System.out.println("username = " + username);
        if (userRepository.existsByUsername(username)) {
            CheckDuplicateNicknameResponseDto checkDuplicateNicknameResponseDto = CheckDuplicateNicknameResponseDto.builder()
                    .message("이미 사용중인 아이디 입니다.")
                    .build();

            return ResponseEntity.badRequest().body(checkDuplicateNicknameResponseDto);
        } else {
            CheckDuplicateNicknameResponseDto checkDuplicateNicknameResponseDto = CheckDuplicateNicknameResponseDto.builder()
                    .message("사용 가능한 아이디입니다.")
                    .build();
            return ResponseEntity.ok(checkDuplicateNicknameResponseDto);
        }
    }

    @GetMapping("/user/{id}")
    public String userInfo(@PathVariable Long id, Model model) {
        Optional<User> userOp = userRepository.findById(id);
        User user = userOp.get();
        model.addAttribute("user", user);
        return "/profile";
    }

    @PostMapping("/user/password")
    public String updatePassword(@PathVariable Long id, @RequestParam String newPassword, Model model) {
        Optional<User> userOp = userRepository.findById(id);
        User user = userOp.orElseThrow(() -> new RuntimeException("User not found"));
        userService.updatePassword(user, newPassword);

        return "redirect:/user/{id}";
    }

    @PostMapping("/user/update")
    public String updateUserInfo(@PathVariable Long id, JoinDto joinDto, Model model) {
        Optional<User> userOp = userRepository.findById(id);
        User user = userOp.orElseThrow(() -> new RuntimeException("User not found"));
        userService.updateUserInfo(user, joinDto);

        return "redirect:/user/{id}";
    }


}

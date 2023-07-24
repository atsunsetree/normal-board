package com.boardapplication.controller;

import com.boardapplication.configuration.JoinValidator;
import com.boardapplication.dto.JoinDto;
import com.boardapplication.repository.UserRepository;
import com.boardapplication.service.UserService;
import com.core.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
            return "/login";
        }

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("joinDto", new JoinDto());
        return "/join";
    }


    @PostMapping("/user/join")
    public String joinUser(@Valid JoinDto joinDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/join";
        }
        User user = userService.processNewUser(joinDto);

        return "redirect:/login";
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


    /*
    @GetMapping("/checkusername")
    public ResponseEntity<String> checkUsername(@RequestParam String username) {
        if (userRepository.existsByUsername(username)) {
            return ResponseEntity.ok("이미 사용중인 아이디입니다.");
        } else {
            return ResponseEntity.ok("사용 가능한 아이디입니다.");
        }
    }

     */

/*
    @GetMapping("/user/{username}")
    public Optional<User> userInfo(@PathVariable String username, Model model) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            // 현재 요청한 username과 user 객체의 username이 같으면 다른 페이지로 리다이렉트
            if (username.equals(user.getUsername())) {
                // 리다이렉트할 URL 설정
                String redirectUrl = "/user/{username}/update";
                return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectUrl).build();
            } else {
                // username이 다를 경우, 사용자 정보를 반환
                return ResponseEntity.ok(user);
            }
        } else {
            // 사용자가 존재하지 않을 경우 404 Not Found 반환
            return ResponseEntity.notFound().build();
        }
    }

 */
}







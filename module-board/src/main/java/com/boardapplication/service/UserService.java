package com.boardapplication.service;

import com.boardapplication.dto.BoardDto;
import com.boardapplication.dto.JoinDto;
import com.boardapplication.repository.BoardRepository;
import com.boardapplication.repository.UserRepository;
import com.core.entity.Role;
import com.core.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;


@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final ModelMapper modelMapper;

    public User processNewUser(@Valid JoinDto joinDto) {
        User newUser = saveNewUser(joinDto);
        return userRepository.save(newUser);
    }

    private User saveNewUser(@Valid JoinDto joinDto) {
        joinDto.setPassword(passwordEncoder.encode(joinDto.getPassword()));
        User user = modelMapper.map(joinDto, User.class);
        return userRepository.save(user);
    }

    public void updatePassword(User user, String newPassword) {
        User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
            return new IllegalArgumentException("회원 찾기 실패");
        });
        newPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public void updateUserInfo(User user, JoinDto joinDto) {
        User userOp = userRepository.findByUsername(joinDto.getUsername());
        String newNickname = joinDto.getNickname();
        String newEmail = joinDto.getEmail();

        user.setNickname(newNickname);
        user.setEmail(newEmail);
        userRepository.save(user);
    }

    //스케줄러 자동등업
    @Scheduled(fixedRate = 600000) //10분
    public void autoUpdateRole() {
        Pageable pageable = Pageable.unpaged(); //전체조회
        Page<BoardDto> boardList = boardService.getBoardList(pageable);

        for (BoardDto boardDto : boardList) {
            Long userId = boardDto.getUser().getId();
            int postCount = boardRepository.countByUserId(userId);
            if (postCount >= 10) {
                User user = userRepository.findByUsername(String.valueOf(userId));
                if (user != null && !"BLACK".equals(user.getRole())) {
                    user.setRole(Role.valueOf("VIP"));
                    userRepository.save(user);
                }
            }
        }
    }

}
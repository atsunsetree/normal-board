package com.boardapplication.service;

import com.boardapplication.dto.BoardDto;
import com.boardapplication.dto.JoinDTO;
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

    public User processNewUser(JoinDTO joinDTO) {
        User newUser = saveNewUser(joinDTO);
        return userRepository.save(newUser);
    }

    private User saveNewUser(@Valid JoinDTO joinDTO) {
        joinDTO.setPassword(passwordEncoder.encode(joinDTO.getPassword()));
        User user = modelMapper.map(joinDTO, User.class);
        return userRepository.save(user);
    }

    public void updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    //스케줄러 자동등업
    @Scheduled(fixedRate = 600000)
    public void autoUpdateRole() {
        Pageable pageable = Pageable.unpaged(); //전체조회
        Page<BoardDto> boardList = boardService.getBoardList(pageable);

        for (BoardDto boardDto : boardList) {
            Long userId = boardDto.getUserId();
            int postCount = boardRepository.countByUserId(userId);
            if (postCount >= 10) {
                User user = userRepository.findByUsername(String.valueOf(userId));
                if (user != null && !"black".equals(user.getRole())) {
                    user.setRole(Role.valueOf("VIP"));
                    userRepository.save(user);
                }
            }
        }
    }

}
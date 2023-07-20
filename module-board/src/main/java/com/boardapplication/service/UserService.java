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
import java.util.List;
import java.util.Optional;

import static java.rmi.server.LogStream.log;


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
    @Scheduled(fixedRate = 60000)
    public void autoUpdateRole() {
        List<User> users = userRepository.findAll();
        for(User user : users){
            int postCount = boardRepository.countByUserId(user.getId());
            if(user.getRole() == Role.NORMAL && postCount >= 10){
                user.setRole(Role.VIP);
                userRepository.save(user);
            }
        }
    }

}
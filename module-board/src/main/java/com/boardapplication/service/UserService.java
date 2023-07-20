package com.boardapplication.service;

import com.boardapplication.dto.JoinDTO;
import com.boardapplication.repository.UserRepository;
import com.core.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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

}
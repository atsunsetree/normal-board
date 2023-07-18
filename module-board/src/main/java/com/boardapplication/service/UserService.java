package com.boardapplication.service;

import com.boardapplication.dto.JoinDTO;
import com.boardapplication.repository.UserRepository;
import com.core.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public abstract class UserService implements UserDetailsService {

    private final JoinDTO joinDTO;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    /*
    public User JoinNewUser(@Valid JoinDTO joinDTO) {
        User user = JoinDTO.newUser(joinDTO, passwordEncoder);
        //User user = modelMapper.map(joinDTO, User.class);
        return userRepository.save(user);

    }

     */


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return null;
    }


}
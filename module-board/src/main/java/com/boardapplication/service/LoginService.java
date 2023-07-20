package com.boardapplication.service;

import com.boardapplication.dto.UserSessionDto;
import com.boardapplication.repository.UserRepository;
import com.core.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class LoginService implements UserDetailsService {
    private final UserRepository userRepository;
    private final HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        session.setAttribute("username", username);
        return new UserSessionDto(user);
    }
}

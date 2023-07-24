package com.boardapplication.dto;

import com.core.entity.User;
import com.core.entity.Role;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class JoinDto {
    @NotEmpty
    @Length(min = 3, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]{3,20}$")
    private String username;

    @NotEmpty
    @Length(min = 8, max = 60)
    private String password;

    @NotEmpty
    @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")
    private String email;

    @NotEmpty
    @Length(min = 3, max = 20)
    @Pattern(regexp = "^[a-zA-Z가-힣]{3,20}$")
    private String nickname;

    public User toEntity(PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setNickname(nickname);
        user.setRole(Role.NORMAL);

        if (password != null) {
            String enPassword = passwordEncoder.encode(password);
            user.setPassword(enPassword);
        }

        return user;
    }

    @Data
    public class updateInfoDto {
        //username, email,role,createdAt
        private Long id;
        private String nickname;
        private String email;

    }

}

package com.boardapplication.dto;

import com.core.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class JoinDTO {

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

    @NotNull
    @Length(min = 3, max = 20)
    @Pattern(regexp = "^[a-zA-Z가-힣]{3,20}$")
    private String nickname;

}

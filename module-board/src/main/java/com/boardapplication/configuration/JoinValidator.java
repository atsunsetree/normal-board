package com.boardapplication.configuration;
import com.boardapplication.dto.JoinDTO;
import com.boardapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(JoinDTO.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        JoinDTO joinDTO = (JoinDTO) obj;
        if (userRepository.existsByUsername(joinDTO.getUsername())) {
            errors.rejectValue("username", "invalid.username",
                    new Object[]{joinDTO.getUsername()}, "이미 사용중인 아이디입니다.");
        }
    }
}